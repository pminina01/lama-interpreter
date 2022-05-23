package eval;
import lama.Absyn.*;

import java.util.HashMap;
import java.util.LinkedList;

public class Interpreter {
	// Class for interpreting the program

    public void interpret(Program p) {
		// Create empty environment of a program and execute statements
		Prog prog = (Prog)p;
		Env env = new Env();
		for (Stm s : prog.liststm_) {
			execStm(s, env);
		}
    }

    private static abstract class Value {
		// Class for storing information about the value

		public boolean isInt() { 
			// Return boolean if value is integer
			// Default is false, because it is "value"
			return false; 
		}

		public boolean isDouble() { 
			// Return boolean if value is double
			// Default is false, because it is "value"
			return false; 
		}

		public Integer getInt() { 
			// Throw an exception as "value" is not "integer"
			throw new RuntimeException(this + " is not an integer."); 
		}

		public Double getDouble() { 
			// Throw an exception as "value" is not "double"
			throw new RuntimeException(this + " is not a double."); 
		}

		public Boolean getBool() { 
			// Throw an exception as "value" is not "bool"
			throw new RuntimeException(this + " is not a bool."); 
		}

		public static class Undefined extends Value {
			// Class for storing undefined value
			// Undefined value is a value with only name of identifier

			public Undefined() {
				// Constructor 
			}

			public String toString() { 
				// For printing
				return "undefined"; 
			}
		}

		public static class IntValue extends Value {
			// Class for storing integer value

			private Integer i;

			public IntValue(Integer i) { 
				// Constructor
				this.i = i; 
			}

			public boolean isInt() { 
				// Return boolean if value is integer
				// As this is integer class, then isInt() is true
				return true; 
			}

			public Integer getInt() {
				// Return int value itself 
				return i; 
			}

			public Double getDouble() {
				// Return double value (cast int value to double)
				double d = i; 
				return d; 
			}

			public String toString() { 
				// For printing
				return i.toString(); 
			}
		}

		public static class DoubleValue extends Value {
			// Class for storing double value

			private Double d;

			public DoubleValue(Double d) { 
				// Constructor
				this.d = d; 
			}

			public boolean isDouble() { 
				// Return boolean if value is double
				// As this is double class, then isDouble() is true
				return true; 
			}

			public Double getDouble() { 
				// Return double value itself
				return d; 
			}

			public String toString() { 
				// For printing
				return d.toString(); 
			}
		}

		public static class BoolValue extends Value {
			// Class for storing bool value

			private Boolean b;

			public BoolValue(Bool b) { 
				// Constructor
				if (b instanceof lama.Absyn.Bool_false) {
					this.b = false;
				} else {
					this.b = true;
				}			 
			}
			public BoolValue(Boolean b) { 
				// Constructor
				this.b = b;			 
			}

			public Boolean getBool() { 
				// Return bool value itself
				return b; 
			}

			public String toString() { 
				// For printing
				return b.toString(); 
			}
		}
    }

    private static class Env { 
		// Class for storing environment variable of program

		private LinkedList<HashMap<String,Value>> scopes;

		public Env() {
			// Environment for storing context of scopes in linked list.
			// Structure: [{'ident':'value','s2':'c2',...},{'s3':'c3',...},...]
			scopes = new LinkedList<HashMap<String,Value>>();
			enterScope();
		}

		public Value lookupVar(String x) {
			// Looking up inside whole environment for an identifier
			// If identifier is found then return Value object
			// Otherwise throw an exception
			for (HashMap<String,Value> scope : scopes) {
				Value v = scope.get(x);
				if (v != null)
					return v;
			}
			throw new RuntimeException("Unknown variable " + x + " in " + scopes);
		}

		public void addVar(String x) {
			// Add variable to the environment scope
			// Form is: (variable_name, undefined_value_object)
			scopes.getFirst().put(x,new Value.Undefined());
		}

		public void setVar(String x, Value v) {
			// Find variable in the environment and set to new value
			for (HashMap<String,Value> scope : scopes) {
				if (scope.containsKey(x)) {
					scope.put(x,v);
					return;
				}
			}
		}

		public void enterScope() {
			// Adds empty hash map of scope at the begining of list
			scopes.addFirst(new HashMap<String,Value>());
		}

		public void leaveScope() {
			// Remove context of scope == exiting the scope
			scopes.removeFirst();
		}
    }

    private void execStm(Stm st, Env env) {
		// Execute statement using StmExecuter class
		// 'accept' is method for using a visitor and returning a value
		st.accept(new StmExecuter(), env);
    }

    private class StmExecuter implements Stm.Visitor<Object,Env> {
		// Class for visiting the corresponding statement and execute it

		public Object visit(lama.Absyn.SExp p, Env env) {
			// Declaration: int i;
			// Add variable to the scope as undentified
			evalExp(p.exp_, env);
			return null;
		}

		public Object visit(lama.Absyn.SDecl p, Env env) {
			// Declaration: int i;
			// Add variable to the scope as undentified
			env.addVar(p.ident_);
			return null;
		}

		public Object visit(lama.Absyn.SAss p, Env env) {
			// Assignment: i = 9 + j;
			// Evaluate right hand side expression
			// Then set left hand side variable to this result
			env.setVar(p.ident_, evalExp(p.exp_, env));
			return null;
		}

		public Object visit(lama.Absyn.SInit p, Env env) {
			// Initialisation: int i = 9 + j;
			// Add variable to the scope
			// Evaluate right hand side expression
			// Then set left hand side variable to this result
			env.addVar(p.ident_);
			env.setVar(p.ident_, evalExp(p.exp_, env));
			return null;
		}

		public Object visit(lama.Absyn.SBlock p, Env env) {
			// Block: {...}
			// Enter the scope (add scope to environment), execute all statements inside
			// then leave the scope (delete scope from the environment)
			env.enterScope();
			for (Stm st : p.liststm_) {
				execStm(st, env);
			}
			env.leaveScope();
			return null;
		}

		public Object visit(lama.Absyn.SPrint p, Env env) {
			// Print: print 9;
			// Evaluate expression after print and print it to console
			Value v = evalExp(p.exp_, env);
			System.err.println(v.toString());
			return null;
		}

		public Object visit(lama.Absyn.SWhile p, Env env) {
			// While: while (i > 1) ... ;
			// The condition expression is evaluated first. 
			// If the value is true, the body is interpreted in the resulting environment, 
			// and the whilestatement is executed again. Otherwise, exit the loop.
			Value condition = evalExp(p.exp_, env);
			while (condition.getBool() == true) {
				env.enterScope();
				execStm(p.stm_, env);
				env.leaveScope();
				condition = evalExp(p.exp_, env);
			}
			return null;
		}
    }

    private Value evalExp(Exp e, Env env) {
		// Evaluate expression using ExpEvaluator class
		// 'accept' is method for using a visitor and returning a value
		return e.accept(new ExpEvaluator(), env);
    }

    private class ExpEvaluator implements Exp.Visitor<Value,Env> {
		// Class for evaluating expression

		public Value visit(lama.Absyn.EVar p, Env env) {
			// Variable (identifier): i
			// Search for ident in environment and return Value object with info
			return env.lookupVar(p.ident_);
		}

		public Value visit(lama.Absyn.EInt p, Env env) {
			// Integer: int i
			// Return IntValue object
			return new Value.IntValue(p.integer_);
		}

		public Value visit(lama.Absyn.EDouble p, Env env) {
			// Double: double d
			// Return DoubleValue object
			return new Value.DoubleValue(p.double_);
		}

		public Value visit(lama.Absyn.EBool p, Env env) {
			// Bool: bool b
			// Return BoolValue object
			return new Value.BoolValue(p.bool_);
		}

		public Value visit(lama.Absyn.EAdd p, Env env) {
			// Addition: i + 3 
			// Check if type of first variables is int 
			// => return new IntValue object as result of addition
			// Otherwise return new DoubleValue object as result of addition
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			if (v1.isInt()) {
				return new Value.IntValue(v1.getInt() + v2.getInt());
			} else {
				return new Value.DoubleValue(v1.getDouble() + v2.getDouble());
			}
		}

		public Value visit(lama.Absyn.ESub p, Env env) {
			// Subtraction: i - 3 
			// Check if type of first variables is int 
			// => return new IntValue object as result of subtraction
			// Otherwise return new DoubleValue object as result of subtraction
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			if (v1.isInt()) {
				return new Value.IntValue(v1.getInt() - v2.getInt());
			} else {
				return new Value.DoubleValue(v1.getDouble() - v2.getDouble());
			}
		}

		public Value visit(lama.Absyn.EMul p, Env env) {
			// Multiplication: i * 3 
			// return new IntValue object if both multipliers are of type int
			// Otherwise return new DoubleValue object as result of multiplication
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			if (v1.isInt() && v2.isInt()) {
				return new Value.IntValue(v1.getInt() * v2.getInt());
			} else {
				return new Value.DoubleValue(v1.getDouble() * v2.getDouble());
			}
		}

		public Value visit(lama.Absyn.EDiv p, Env env) {
			// Division: i * 3 
			// return new DoubleValue object as result of division
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			return new Value.DoubleValue(v1.getDouble() / v2.getDouble());
		}

		public Value visit(lama.Absyn.EPostIncr p, Env env) {
			// Post Increment: i ++  
			// Return the unchanged value of the variable
			// Then set the incremented value to the variable
			Value tmp = env.lookupVar(p.ident_);
			Value v = new Value.IntValue(tmp.getInt() + 1);
			env.setVar(p.ident_, v);
			return tmp;
		}

		public Value visit(lama.Absyn.EPostDecr p, Env env) {
			// Post Decrement: i -- 
			// Return the unchanged value of the variable
			// Then set the decremented value to the variable
			Value tmp = env.lookupVar(p.ident_);
			Value v = new Value.IntValue(tmp.getInt() - 1);
			env.setVar(p.ident_, v);
			return tmp;
		}

		public Value visit(lama.Absyn.EPreIncr p, Env env) {
			// Pre Increment: ++ i   
			// Set the incremented value to the variable
			// Then return this value
			Value tmp = env.lookupVar(p.ident_);
			Value v = new Value.IntValue(tmp.getInt() + 1);
			env.setVar(p.ident_, v);
			return v;
		}

		public Value visit(lama.Absyn.EPreDecr p, Env env) {
			// Pre Decrement: -- i 
			// Set the decremented value to the variable
			// Then return this value
			Value tmp = env.lookupVar(p.ident_);
			Value v = new Value.IntValue(tmp.getInt() - 1);
			env.setVar(p.ident_, v);
			return v;
		}

		public Value visit(lama.Absyn.ELess p, Env env) {
			// Less Than: i < 3 
			// Compare two values and return the answer
			// Return new BoolValue object as result of comparison
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			if (v1.isInt()) {
				return new Value.BoolValue(v1.getInt() < v2.getInt());
			} else {
				return new Value.BoolValue(v1.getDouble() < v2.getDouble());
			}
		}

		public Value visit(lama.Absyn.EGreater p, Env env) {
			// Greater Than: i < 3 
			// Compare two values and return the answer
			// Return new BoolValue object as result of comparison
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			if (v1.isInt()) {
				return new Value.BoolValue(v1.getInt() > v2.getInt());
			} else {
				return new Value.BoolValue(v1.getDouble() > v2.getDouble());
			}
		}

		public Value visit(lama.Absyn.ELEq p, Env env) {
			// Less Than or Equal: i <= 3 
			// Compare two values and return the answer
			// Return new BoolValue object as result of comparison
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			if (v1.isInt()) {
				return new Value.BoolValue(v1.getInt() <= v2.getInt());
			} else {
				return new Value.BoolValue(v1.getDouble() <= v2.getDouble());
			}
		}

		public Value visit(lama.Absyn.EGEq p, Env env) {
			// Greater Than or Equal: i >= 3 
			// Compare two values and return the answer
			// Return new BoolValue object as result of comparison
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			if (v1.isInt()) {
				return new Value.BoolValue(v1.getInt() >= v2.getInt());
			} else {
				return new Value.BoolValue(v1.getDouble() >= v2.getDouble());
			}
		}

		public Value visit(lama.Absyn.EEq p, Env env) {
			// Equal: i == 3 
			// Compare two values and return the answer
			// Return new BoolValue object as result of comparison
			Value v1 = p.exp_1.accept(this, env);
			Value v2 = p.exp_2.accept(this, env);
			if (v1.isInt()) {
				return new Value.BoolValue(v1.getInt() == v2.getInt());
			} else if (v1.isDouble()) {
				return new Value.BoolValue(v1.getDouble().equals(v2.getDouble()));
			} else {
				return new Value.BoolValue(v1.getBool() == v2.getBool());
			}
		}
    }
}
