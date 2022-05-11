import eval.*;

public class runmini {
	// Entry point class for runing interpreter and type checker

    public static void main(String args[]) {
		if (args.length != 1) {
			// If there if no filename passed by user
			System.err.println("Usage: runmini <SourceFile>");
			System.exit(1);	
		}

		mini.Yylex l = null;
		try {
			l = new mini.Yylex(new java.io.FileReader(args[0])); // Lexer
			mini.parser p = new mini.parser(l); // Parser
			mini.Absyn.Program parse_tree = p.pProgram(); // Parse tree
			new TypeChecker().typecheck(parse_tree); // Type check
			new Interpreter().interpret(parse_tree); // Interpret
		} catch (TypeException e) {
			System.out.println("TYPE ERROR");
			System.err.println(e.toString());
			System.exit(1);
		} catch (RuntimeException e) {
			System.out.println("RUNTIME ERROR");
			System.err.println(e.toString());
			System.exit(1);
		} catch (java.io.IOException e) {
			System.err.println(e.toString());
			System.exit(1);
		} catch (Throwable e) {
			System.out.println("SYNTAX ERROR");
			System.out.println("At line " + String.valueOf(l.line_num()) 
					+ ", near \"" + l.buff() + "\" :");
			System.out.println("     " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
    }
    
}
