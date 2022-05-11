# Lambda programming language

_The full documentation is in Notion: https://www.notion.so/Lambda-Programming-Language-c557298411894b74ac4fa3dd8466b922_

Lambda is imperative interpreted programming C++ like language.
Extension of the Lambda programming language id `.mini`.

A Lambda program is a sequence of definitions. It may also contain comments and preprocessor directives. It must have a function `main` of type `int` that takes no arguments. It may or may not have a `return` statement.

```cpp
    int main () {
      ...
    }
```

Lambda language has two **built-in functions** dealing with input and output:

```cpp
    void print(T x);   // prints an argument of type T and a newline in standard output
    T    read();       // reads an argument of type T from standard input
```

## Features
    
- Statements
	- While loops
	- Conditionals (`if {...} else {...}  // No else-less if`)
	- Blocks, etc.
- Expressions
	- Literals (integer, floating point and true, false literals)
	- Identifiers
	- Function call
	- Post- and preincrement and post- and predecrement, eg. i++, i--, ++i, --i
	- Arithmetic operations (addition, subtraction, multiplication, and division)
	- Comparisons (`<, >, >=, <=, == b, != b)
	- Binaries, e.g. (`&&,||`)
   	- Assignment, e.g. x = a
- Base types
	- int: integer numbers, e.g. `-47` is int value
	- double: numbers with remainder, e.g. `3.14159`
	- bool: boolean values, e.g. `true` and `false`
	- string: string/text values, e.g. `‘Hello, world!’` (use single and double quotes)
	- void: can be perceived as an empty or undefined value, which need never be shown
- User-defined terms and types (user-defined types are structured algebraic data types)
- Nested definitions
- Sequencing
- Type Ascription
- Class fields/methods accessibility level
- General Recursion
- Let-bindings
- Tuples
- Records
- Built-in Lists
- Built-in Arrays
- Built-in Dict
- Subtyping
- Simple Constraint-Based Type Inference (e.g. support auto types)
- Standard Library
- Exceptions


## Implementation

### Environment

| Name | Version | Link |
| --- | --- | --- |
| BNFC | 2.9.4 | http://bnfc.digitalgrammars.com/ |
| CUP | 0.11b | http://www2.cs.tum.edu/projects/cup/ |
| Jlex | 1.2.6 | https://www.cs.princeton.edu/~appel/modern/java/JLex/ |
| Java | 12.0.2 | https://www.oracle.com/java/technologies/javase/jdk12-archive-downloads.html |

### How To Build

Make sure you installed java, Jlex, CUP and BNFC (you can follow tutorial [https://bnfc.digitalgrammars.com/tutorial/bnfc-tutorial.html](https://bnfc.digitalgrammars.com/tutorial/bnfc-tutorial.html)).

You probably want to clean up previous build files first:

```bash
make clean
```

Run make file for generating parser and lexer. This should generate `Mini/Test` files that you can now use to test parsing of the source code in the target language.

```bash
make
```

Compile `eval` folder

```bash
javac eval/Interpreter.java eval/TypeException.java eval/TypeChecker.java
```

Compile `runmini.java`

```bash
javac -Xlint runmini.java
```

---

### How To Use

The interpreter reads standard input and files, parses a series of expressions separated by a semicolon (;), typechecks and evaluates each expression and prints out the results.

Input can be read from user typing on the terminal and standard input redirected from a file or by `echo`

```
>>> java runmini example.mini                 # Typing on the terminal
		3
>>> java runmini example.mini <test-input     # Standard input redirected from a file
>>> echo 3 | java runmini example.mini        # Standard input redirected from a echo
```

Consider next Lambda language program at file `example.lama`:

```cpp
int x;
int y;
x = 6;
y = x + 7;
print y;
```

Run program and see the result:

```bash
>>> java runmini ./examples/example.mini
13
```

### Valid program

```cpp
int main ()
  {
    int i = read();
		int factorial(int num) /* Let's create the factorial function */
    {
			if (5!=7) && (num > 0) 
			{ 
				int result = 1
				while (num > 0)
				{
					result = result * num;
					--num;
				};
				return result;
			};
			else return 1;
    }
		factorial(7) // function call
  }
```

### Invalid Program - Type error

```cpp
int y;
y = x + 3;
print(y);
```

```cpp
TYPE ERROR
eval.TypeException: Unknown variable x.
```

### Invalid Program - Interpreter error

```
int i;
printInt(i);
```

```cpp
INTERPRETER ERROR
Uninitialized variable i.
```

---

## Additional Information

### Notion link

https://www.notion.so/Lambda-Programming-Language-c557298411894b74ac4fa3dd8466b922

### Team members

- **Polina Minina** - implements type checker, implements compiler
- **Amina Khusnutdinova** - implements syntax, implements compiler
- **Kseniya Evdokimova** - implements syntax, implements tests
