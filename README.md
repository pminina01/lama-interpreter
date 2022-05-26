# Lambda programming language

_The full documentation is in Notion: https://www.notion.so/Lambda-Programming-Language-c557298411894b74ac4fa3dd8466b922_

Lambda is imperative interpreted programming C++ like language.
Extension of the Lambda programming language id `.lama`.

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

Make sure to SET CLASSPATH for JavaCup and JLex before the next command.

Run make file for generating parser and lexer. This should generate `Lama/Test` files that you can now use to test parsing of the source code in the target language.

```bash
make
```
If the sym and parser files are not automatically moved, move them manually to the lama folder

Compile `eval` folder

```bash
javac eval/Interpreter.java eval/TypeException.java eval/TypeChecker.java
```

Compile `runlama.java`

```bash
javac -Xlint runlama.java
```

---

### How To Use

The interpreter reads standard input and files, parses a series of expressions separated by a semicolon (;), typechecks and evaluates each expression and prints out the results.

Input can be read from user typing on the terminal and standard input redirected from a file or by `echo`

```
>>> java runlama example.lama                 # Typing on the terminal
		3
>>> java runlama example.lama <test-input     # Standard input redirected from a file
>>> echo 3 | java runlama example.lama        # Standard input redirected from a echo
```

Consider next Lambda language program at file `summation.lama`:

```cpp
int x ;
x = 6 ;
int y ;
y = x + 7 ;
print y ;
{
  int y ;
  y = 4 ;
  print y ;
  x = y ;
  print x ;
}
print x ;
print y;
```

Run program and see the result:

```bash
>>> java runlama ./examples/summation.lama
13
4
4
4
13
```

### Valid program

```cpp
import "empty_program.lama";

int i = 0;
i --;
i = 10 + 11 * 11 - 10;
i ++ ;
print i;

double d;
d = 10/2;
if (d > 0.0) print true; 
else print false;

bool t = true;
print true == t ;
print t || false && false;

[int] a ;
a = [int] [];
print a;
print a.isEmpty();

[[int]] arr = [[int]] [[int][6]];
print arr;
arr.append(a);
print arr.first();

int funct ( int arg ) { 
    arg = 7;
    while (arg < 10) {
        print arg;
        arg ++ ;
    }
    return arg 
}
print funct;
```

### Invalid Program - Type error

```cpp
int a;
a = 0 ;
int res = a -- ;
res = -- a;
print res ;

bool b = true;
int exc = b ++ ;
print exc ;
```

```cpp
TYPE ERROR
eval.TypeException: post increment written in lama.Absyn.EPostIncr@62 supports only int parameters, but bool was given
```

---

## Additional Information

### Notion link

https://www.notion.so/Lambda-Programming-Language-c557298411894b74ac4fa3dd8466b922

### Team members

- **Polina Minina** - implements type checker, implements compiler
- **Amina Khusnutdinova** - implements syntax, implements compiler
- **Kseniya Evdokimova** - implements syntax, implements tests
