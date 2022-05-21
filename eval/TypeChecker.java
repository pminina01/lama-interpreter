package eval;
import lama.Absyn.*;
import lama.PrettyPrinter;

import java.util.HashMap;
import java.util.LinkedList;

public class TypeChecker {
	// Class for checking the type correctness of a program

    private static enum TypeCode { 
		// Store type codes
        INT    { public String toString() { return "int";    } }, 
        DOUBLE { public String toString() { return "double"; } },
		BOOL   { public String toString() { return "bool"; } }
    }

    private static class Env { 
		// Class for storing environment variables

		private LinkedList<HashMap<String,TypeCode>> scopes;

		public Env() {
			// Environment for storing context of scopes in linked list.
			// Structure: [{'ident':'type','s2':'c2',...},{'s3':'c3',...},...]
			scopes = new LinkedList<HashMap<String,TypeCode>>();
			enterScope();
		}

		public TypeCode lookupVar(String x) {
			// Looking up inside whole environment for an identifier
			// If identifier is not found then throw an exception
			for (HashMap<String,TypeCode> scope : scopes) {
				TypeCode t = scope.get(x);
				if (t != null)
					return t;
			}
			throw new TypeException("Unknown variable " + x + ".");
		}

		public void addVar(String x, TypeCode t) {
			// Add variable to the environment scope if it is not exist yet
			// Otherwise throw a type exception
			if (scopes.getFirst().containsKey(x))
				throw new TypeException("Variable " + x 
							+ " is already declared in this scope.");
			scopes.getFirst().put(x,t);
		}

		public void enterScope() {
			// Adds empty hash map of scope at the begining of list
			scopes.addFirst(new HashMap<String,TypeCode>());
		}

		public void leaveScope() {
			// Remove context of scope from evironment == exiting the scope
			scopes.removeFirst();
		}
    }

    public void typecheck(Program p) {
		// Entry point
		// Create environment(context) and check each statement
		Prog prog = (Prog)p;
		Env env = new Env();
		for (Stm s : prog.liststm_) {
			checkStm(s, env);
		}
    }

    private void checkStm(Stm st, Env env) {
		// Check statement using StmChecker class
		// 'accept' is method for using a visitor and returning a value
		st.accept(new StmChecker(), env);
    }

    private class StmChecker implements Stm.Visitor<Object,Env> {
		// Class for visiting the corresponding statement and check its type

		public Object visit(SDecl p, Env env) {
			// Declaration: int i;
			// Add variable to the scope
			env.addVar(p.ident_, typeCode(p.type_));
			return null;
		}

		public Object visit(SAss p, Env env) {
			// Assignment: i = 9 + j;
			// Lookup for type of variable at left hand side
			// Then checks right hand side expression
			TypeCode t = env.lookupVar(p.ident_);
			checkExp(p.exp_, t, env);
			return null;
		}

		public Object visit(SInit p, Env env) {
			// Initialisation: int i = 9 + j;
			// Add variable to the scope
			// Then check right hand side expression
			TypeCode t = typeCode(p.type_);
			env.addVar(p.ident_, t);
			checkExp(p.exp_, t, env);
			return null;
		}

		public Object visit(SBlock p, Env env) {
			// Block: {...}
			// Enter the scope (add scope to environment), check all statements inside
			// then leave the scope (delete scope from the environment)
			env.enterScope();
			for (Stm st : p.liststm_) {
				checkStm(st, env);
			}
			env.leaveScope();
			return null;
		}

		public Object visit(SPrint p, Env env) {
			// Print: print 9;
			// We don't care what the type is, just that there is one
			inferExp(p.exp_, env);
			return null;
		}
    }

    private void checkExp(Exp e, TypeCode t, Env env) {
		// Check correctness of an expression type 
		// and comparing it to the expected type t
		TypeCode et = inferExp(e,env);
		if (et != t) {
			throw new TypeException(PrettyPrinter.print(e) 
						+ " has type " + et 
						+ " expected " + t);
		}
    }

    private TypeCode inferExp(Exp e, Env env) {
		// Infer type of an expression using class TypeInferrer
		return e.accept(new TypeInferrer(), env);
    }

    private class TypeInferrer implements Exp.Visitor<TypeCode,Env> {
		// Class for infering the type of and expression and check its correctness

		public TypeCode visit(EVar p, Env env) {
			// Variable (identifier): i
			// Find type of variable in the environment
			return env.lookupVar(p.ident_);
		}

		public TypeCode visit(EInt p, Env env) {
			// Integer: int i
			return TypeCode.INT;
		}
		public TypeCode visit(EDouble p, Env env) {
			// Double: double d
			return TypeCode.DOUBLE;
		}
		public TypeCode visit(EBool p, Env env) {
			// Bool: bool b
			return TypeCode.BOOL;
		}
		public TypeCode visit(EAdd p, Env env) {
			// Addition: i + 3 
			// Check if type of both variables is the same
			// If true then return type of first variable
			// Otherwise throw an exception
			TypeCode t1 = p.exp_1.accept(this, env);
			TypeCode t2 = p.exp_2.accept(this, env);
			if (t1 != t2) {
				throw new TypeException(PrettyPrinter.print(p.exp_1) + 
							" has type " + t1
							+ " but " + PrettyPrinter.print(p.exp_2)
							+ " has type " + t2);
			}
			if (t1 == TypeCode.BOOL) {
				throw new TypeException(PrettyPrinter.print(p.exp_1) + 
							"addition operation written in " 
							+ p + 
							" does not support bool parameters");
			}
			return t1;
		}
		public TypeCode visit(ESub p, Env env) {
			// Subtraction: i - 3 
			// Check if type of both variables is the same
			// If true then return type of first variable
			// Otherwise throw an exception
			TypeCode t1 = p.exp_1.accept(this, env);
			TypeCode t2 = p.exp_2.accept(this, env);
			if (t1 != t2) {
				throw new TypeException(PrettyPrinter.print(p.exp_1) + 
							" has type " + t1
							+ " but " + PrettyPrinter.print(p.exp_2)
							+ " has type " + t2);
			}
			if (t1 == TypeCode.BOOL) {
				throw new TypeException(PrettyPrinter.print(p.exp_1) + 
							"subtraction operation written in " 
							+ p + 
							" does not support bool parameters");
			}
			return t1;
		}
		public TypeCode visit(EMul p, Env env) {
			// Multiplication: i * 3 
			// Check if type of both variables is numeric
			// If true then return [INT if int*int] 
			// or [DOUBLE if at least one of the multipliers 
			// is of type duoble]
			// Otherwise throw an exception
			TypeCode t1 = p.exp_1.accept(this, env);
			TypeCode t2 = p.exp_2.accept(this, env);

			if (t1 == TypeCode.BOOL) {
				throw new TypeException(PrettyPrinter.print(p.exp_1) + 
							"multiplication operation written in " 
							+ p + 
							" does not support bool parameters");
			}
			if (t2 == TypeCode.BOOL) {
				throw new TypeException(PrettyPrinter.print(p.exp_2) + 
							"multiplication operation written in " 
							+ p + 
							" does not support bool parameters");
			}
			if (t1 == TypeCode.DOUBLE || t2 == TypeCode.DOUBLE){
				return TypeCode.DOUBLE;
			}
			return t1;
		}
		public TypeCode visit(EDiv p, Env env) {
			// Division: i / 3 
			// Check if type of both variables is numeric
			// If true then return type DOUBLE
			TypeCode t1 = p.exp_1.accept(this, env);
			TypeCode t2 = p.exp_2.accept(this, env);
			if (t1 == TypeCode.BOOL) {
				throw new TypeException(PrettyPrinter.print(p.exp_1) + 
							"division operation written in " 
							+ p + 
							" does not support bool parameters");
			}
			if (t2 == TypeCode.BOOL) {
				throw new TypeException(PrettyPrinter.print(p.exp_2) + 
							"division operation written in " 
							+ p + 
							" does not support bool parameters");
			}
			return TypeCode.DOUBLE;
		}
		public TypeCode visit(EPostIncr p, Env env) {
			// Post Increment: i ++  
			// Check if type of the variable is int
			// If true then return type INT
			// Otherwise throw an exception
			TypeCode t = env.lookupVar(p.ident_);
			if (t != TypeCode.INT) {
				throw new TypeException("post increment written in " 
							+ p + 
							" supports only int parameters, but "
							+ t + " was given");
			}
			return TypeCode.INT;
		}
		public TypeCode visit(EPostDecr p, Env env) {
			// Post Decrement: i -- 
			// Check if type of the variable is int
			// If true then return type INT
			// Otherwise throw an exception
			TypeCode t = env.lookupVar(p.ident_);
			if (t != TypeCode.INT) {
				throw new TypeException("post decrement written in " 
							+ p + 
							" supports only int parameters, but "
							+ t + " was given");
			}
			return TypeCode.INT;
		}
		public TypeCode visit(EPreIncr p, Env env) {
			// Pre Increment: ++ i   
			// Check if type of the variable is int
			// If true then return type INT
			// Otherwise throw an exception
			TypeCode t = env.lookupVar(p.ident_);
			if (t != TypeCode.INT) {
				throw new TypeException("pre increment written in " 
							+ p + 
							" supports only int parameters, but "
							+ t + " was given");
			}
			return TypeCode.INT;
		}
		public TypeCode visit(EPreDecr p, Env env) {
			// Pre Decrement: -- i 
			// Check if type of the variable is int
			// If true then return type INT
			// Otherwise throw an exception
			TypeCode t = env.lookupVar(p.ident_);
			if (t != TypeCode.INT) {
				throw new TypeException("pre decrement written in " 
							+ p + 
							" supports only int parameters, but "
							+ t + " was given");
			}
			return TypeCode.INT;
		}

    }

    private TypeCode typeCode(Type t) {
		// Return type code in string format
		return t.accept(new TypeCoder(), null);
    }

    private class TypeCoder implements Type.Visitor<TypeCode,Object> {
		// Class for returning the type code in string format

		public TypeCode visit(TInt t, Object arg) {
			// Integer
			return TypeCode.INT;
		}

		public TypeCode visit(TDouble t, Object arg) {
			// Double
			return TypeCode.DOUBLE;
		}

		public TypeCode visit(TBool t, Object arg) {
			// Bool
			return TypeCode.BOOL;
		}
    }

}
