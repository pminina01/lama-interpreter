package eval;
import mini.Absyn.*;
import mini.PrettyPrinter;

import java.util.HashMap;
import java.util.LinkedList;

public class TypeChecker {
	// Class foe checking the type correcness of program

    private static enum TypeCode { 
		// Store type codes
        INT    { public String toString() { return "int";    } }, 
        DOUBLE { public String toString() { return "double"; } }
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
			// Add variable to the environment scope if it is not exists yet
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
			// Remove context of scope == exiting the scope
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
		// and compating it to the expected type t
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
			// Variable: i
			// Find type of variable in the environment
			return env.lookupVar(p.ident_);
		}

		public TypeCode visit(EInt p, Env env) {
			// Integer: int i
			return TypeCode.INT;
		}
		public TypeCode visit(EDouble p, Env env) {
			// Double: double i
			return TypeCode.DOUBLE;
		}
		public TypeCode visit(EAdd p, Env env) {
			// Addition: i + 3 
			// Check if type of both variables if the same
			// If true then return type of first variable
			// Otherwise throw an exception
			TypeCode t1 = p.exp_1.accept(this, env);
			TypeCode t2 = p.exp_2.accept(this, env);
			if (t1 != t2) {
				throw new TypeException(PrettyPrinter.print(p.exp_1) + 
							" has type " + t1
							+ " but " + PrettyPrinter.print(p.exp_1)
							+ " has type " + t2);
			}
			return t1;
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
    }

}
