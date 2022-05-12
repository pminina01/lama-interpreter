import eval.*;

public class runlama {
	// Entry point class for runing interpreter and type checker

    public static void main(String args[]) {
		if (args.length != 1) {
			// If there is no filename passed by user
			System.err.println("Usage: runlama <SourceFile>");
			System.exit(1);	
		}

		lama.Yylex l = null;
		try {
			l = new lama.Yylex(new java.io.FileReader(args[0])); // Lexer
			lama.parser p = new lama.parser(l); // Parser
			lama.Absyn.Program parse_tree = p.pProgram(); // Parse tree
			new TypeChecker().typecheck(parse_tree); // Type check
			new Interpreter().interpret(parse_tree); // Interpret
		} catch (TypeException e) {
			// Type error
			System.out.println("TYPE ERROR");
			System.err.println(e.toString());
			System.exit(1);
		} catch (RuntimeException e) {
			// Runtime error
			System.out.println("RUNTIME ERROR");
			System.err.println(e.toString());
			System.exit(1);
		} catch (java.io.IOException e) {
			// IO error
			System.err.println(e.toString());
			System.exit(1);
		} catch (Throwable e) {
			// Syntax error
			System.out.println("SYNTAX ERROR");
			System.out.println("At line " + String.valueOf(l.line_num()) 
					+ ", near \"" + l.buff() + "\" :");
			System.out.println("     " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
    }
    
}
