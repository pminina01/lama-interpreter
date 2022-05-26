// File generated by the BNF Converter (bnfc 2.9.4).

package lama;

public class PrettyPrinter
{
  //For certain applications increasing the initial size of the buffer may improve performance.
  private static final int INITIAL_BUFFER_SIZE = 128;
  private static final int INDENT_WIDTH = 2;
  //You may wish to change the parentheses used in precedence.
  private static final String _L_PAREN = new String("(");
  private static final String _R_PAREN = new String(")");
  //You may wish to change render
  private static void render(String s)
  {
    if (s.equals("{"))
    {
       onEmptyLine();
       buf_.append(s);
       _n_ = _n_ + INDENT_WIDTH;
       buf_.append("\n");
       indent();
    }
    else if (s.equals("(") || s.equals("["))
       buf_.append(s);
    else if (s.equals(")") || s.equals("]"))
    {
       removeTrailingWhitespace();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals("}"))
    {
       _n_ = _n_ - INDENT_WIDTH;
       onEmptyLine();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals(","))
    {
       removeTrailingWhitespace();
       buf_.append(s);
       buf_.append(" ");
    }
    else if (s.equals(";"))
    {
       removeTrailingWhitespace();
       buf_.append(s);
       buf_.append("\n");
       indent();
    }
    else if (s.equals("")) return;
    else if (s.trim().equals(""))
    {
       backup();
       buf_.append(s);
    }
    else
    {
       buf_.append(s);
       buf_.append(" ");
    }
  }


  //  print and show methods are defined for each category.
  public static String print(lama.Absyn.Program foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(lama.Absyn.Program foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(lama.Absyn.ListStm foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(lama.Absyn.ListStm foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(lama.Absyn.Stm foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(lama.Absyn.Stm foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(lama.Absyn.Exp foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(lama.Absyn.Exp foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(lama.Absyn.ListExp foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(lama.Absyn.ListExp foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(lama.Absyn.Bool foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(lama.Absyn.Bool foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String print(lama.Absyn.Type foo)
  {
    pp(foo, 0);
    trim();
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  public static String show(lama.Absyn.Type foo)
  {
    sh(foo);
    String temp = buf_.toString();
    buf_.delete(0,buf_.length());
    return temp;
  }
  /***   You shouldn't need to change anything beyond this point.   ***/

  private static void pp(lama.Absyn.Program foo, int _i_)
  {
    if (foo instanceof lama.Absyn.Prog)
    {
       lama.Absyn.Prog _prog = (lama.Absyn.Prog) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_prog.liststm_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }

  }

  private static void pp(lama.Absyn.ListStm foo, int _i_)
  {
    ppListStm(foo.iterator(), _i_);
  }

  private static void ppListStm(java.util.Iterator<lama.Absyn.Stm> it, int _i_)
  {
    if (it.hasNext())
    { /* cons */
      lama.Absyn.Stm el = it.next();
      pp(el, _i_); ppListStm(it, _i_);
    }
  }


  private static void pp(lama.Absyn.Stm foo, int _i_)
  {
    if (foo instanceof lama.Absyn.SExp)
    {
       lama.Absyn.SExp _sexp = (lama.Absyn.SExp) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sexp.exp_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.SDecl)
    {
       lama.Absyn.SDecl _sdecl = (lama.Absyn.SDecl) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sdecl.type_, 0);
       pp(_sdecl.ident_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.SAss)
    {
       lama.Absyn.SAss _sass = (lama.Absyn.SAss) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sass.ident_, 0);
       render("=");
       pp(_sass.exp_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.SInit)
    {
       lama.Absyn.SInit _sinit = (lama.Absyn.SInit) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sinit.type_, 0);
       pp(_sinit.ident_, 0);
       render("=");
       pp(_sinit.exp_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.SPrint)
    {
       lama.Absyn.SPrint _sprint = (lama.Absyn.SPrint) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("print");
       pp(_sprint.exp_, 0);
       render(";");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.SWhile)
    {
       lama.Absyn.SWhile _swhile = (lama.Absyn.SWhile) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("while");
       render("(");
       pp(_swhile.exp_, 0);
       render(")");
       pp(_swhile.stm_, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.SBlock)
    {
       lama.Absyn.SBlock _sblock = (lama.Absyn.SBlock) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("{");
       pp(_sblock.liststm_, 0);
       render("}");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.SIfElse)
    {
       lama.Absyn.SIfElse _sifelse = (lama.Absyn.SIfElse) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("if");
       render("(");
       pp(_sifelse.exp_, 0);
       render(")");
       pp(_sifelse.stm_1, 0);
       render("else");
       pp(_sifelse.stm_2, 0);
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.SFun)
    {
       lama.Absyn.SFun _sfun = (lama.Absyn.SFun) foo;
       if (_i_ > 0) render(_L_PAREN);
       pp(_sfun.type_1, 0);
       pp(_sfun.ident_1, 0);
       render("(");
       pp(_sfun.type_2, 0);
       pp(_sfun.ident_2, 0);
       render(")");
       render("{");
       pp(_sfun.liststm_, 0);
       render("return");
       pp(_sfun.exp_, 0);
       render("}");
       if (_i_ > 0) render(_R_PAREN);
    }

  }

  private static void pp(lama.Absyn.Exp foo, int _i_)
  {
    if (foo instanceof lama.Absyn.EVar)
    {
       lama.Absyn.EVar _evar = (lama.Absyn.EVar) foo;
       if (_i_ > 14) render(_L_PAREN);
       pp(_evar.ident_, 0);
       if (_i_ > 14) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EInt)
    {
       lama.Absyn.EInt _eint = (lama.Absyn.EInt) foo;
       if (_i_ > 14) render(_L_PAREN);
       pp(_eint.integer_, 0);
       if (_i_ > 14) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EDouble)
    {
       lama.Absyn.EDouble _edouble = (lama.Absyn.EDouble) foo;
       if (_i_ > 14) render(_L_PAREN);
       pp(_edouble.double_, 0);
       if (_i_ > 14) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EBool)
    {
       lama.Absyn.EBool _ebool = (lama.Absyn.EBool) foo;
       if (_i_ > 14) render(_L_PAREN);
       pp(_ebool.bool_, 0);
       if (_i_ > 14) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EPostIncr)
    {
       lama.Absyn.EPostIncr _epostincr = (lama.Absyn.EPostIncr) foo;
       if (_i_ > 12) render(_L_PAREN);
       pp(_epostincr.ident_, 0);
       render("++");
       if (_i_ > 12) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EPostDecr)
    {
       lama.Absyn.EPostDecr _epostdecr = (lama.Absyn.EPostDecr) foo;
       if (_i_ > 12) render(_L_PAREN);
       pp(_epostdecr.ident_, 0);
       render("--");
       if (_i_ > 12) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EPreIncr)
    {
       lama.Absyn.EPreIncr _epreincr = (lama.Absyn.EPreIncr) foo;
       if (_i_ > 11) render(_L_PAREN);
       render("++");
       pp(_epreincr.ident_, 0);
       if (_i_ > 11) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EPreDecr)
    {
       lama.Absyn.EPreDecr _epredecr = (lama.Absyn.EPreDecr) foo;
       if (_i_ > 11) render(_L_PAREN);
       render("--");
       pp(_epredecr.ident_, 0);
       if (_i_ > 11) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EMul)
    {
       lama.Absyn.EMul _emul = (lama.Absyn.EMul) foo;
       if (_i_ > 10) render(_L_PAREN);
       pp(_emul.exp_1, 10);
       render("*");
       pp(_emul.exp_2, 11);
       if (_i_ > 10) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EDiv)
    {
       lama.Absyn.EDiv _ediv = (lama.Absyn.EDiv) foo;
       if (_i_ > 10) render(_L_PAREN);
       pp(_ediv.exp_1, 10);
       render("/");
       pp(_ediv.exp_2, 11);
       if (_i_ > 10) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EAdd)
    {
       lama.Absyn.EAdd _eadd = (lama.Absyn.EAdd) foo;
       if (_i_ > 9) render(_L_PAREN);
       pp(_eadd.exp_1, 9);
       render("+");
       pp(_eadd.exp_2, 10);
       if (_i_ > 9) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.ESub)
    {
       lama.Absyn.ESub _esub = (lama.Absyn.ESub) foo;
       if (_i_ > 9) render(_L_PAREN);
       pp(_esub.exp_1, 9);
       render("-");
       pp(_esub.exp_2, 10);
       if (_i_ > 9) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.ELess)
    {
       lama.Absyn.ELess _eless = (lama.Absyn.ELess) foo;
       if (_i_ > 7) render(_L_PAREN);
       pp(_eless.exp_1, 8);
       render("<");
       pp(_eless.exp_2, 8);
       if (_i_ > 7) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EGreater)
    {
       lama.Absyn.EGreater _egreater = (lama.Absyn.EGreater) foo;
       if (_i_ > 7) render(_L_PAREN);
       pp(_egreater.exp_1, 8);
       render(">");
       pp(_egreater.exp_2, 8);
       if (_i_ > 7) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.ELEq)
    {
       lama.Absyn.ELEq _eleq = (lama.Absyn.ELEq) foo;
       if (_i_ > 7) render(_L_PAREN);
       pp(_eleq.exp_1, 8);
       render("<=");
       pp(_eleq.exp_2, 8);
       if (_i_ > 7) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EGEq)
    {
       lama.Absyn.EGEq _egeq = (lama.Absyn.EGEq) foo;
       if (_i_ > 7) render(_L_PAREN);
       pp(_egeq.exp_1, 8);
       render(">=");
       pp(_egeq.exp_2, 8);
       if (_i_ > 7) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EEq)
    {
       lama.Absyn.EEq _eeq = (lama.Absyn.EEq) foo;
       if (_i_ > 6) render(_L_PAREN);
       pp(_eeq.exp_1, 7);
       render("==");
       pp(_eeq.exp_2, 7);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.ENEq)
    {
       lama.Absyn.ENEq _eneq = (lama.Absyn.ENEq) foo;
       if (_i_ > 6) render(_L_PAREN);
       pp(_eneq.exp_1, 7);
       render("!=");
       pp(_eneq.exp_2, 7);
       if (_i_ > 6) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EAnd)
    {
       lama.Absyn.EAnd _eand = (lama.Absyn.EAnd) foo;
       if (_i_ > 4) render(_L_PAREN);
       pp(_eand.exp_1, 4);
       render("&&");
       pp(_eand.exp_2, 5);
       if (_i_ > 4) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.EOr)
    {
       lama.Absyn.EOr _eor = (lama.Absyn.EOr) foo;
       if (_i_ > 3) render(_L_PAREN);
       pp(_eor.exp_1, 3);
       render("||");
       pp(_eor.exp_2, 4);
       if (_i_ > 3) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.Array)
    {
       lama.Absyn.Array _array = (lama.Absyn.Array) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("[");
       pp(_array.type_, 0);
       render("]");
       render("[");
       pp(_array.listexp_, 0);
       render("]");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.Append)
    {
       lama.Absyn.Append _append = (lama.Absyn.Append) foo;
       if (_i_ > 1) render(_L_PAREN);
       pp(_append.exp_1, 2);
       render(".append(");
       pp(_append.exp_2, 2);
       render(")");
       if (_i_ > 1) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.Head)
    {
       lama.Absyn.Head _head = (lama.Absyn.Head) foo;
       if (_i_ > 1) render(_L_PAREN);
       pp(_head.exp_, 2);
       render(".first()");
       if (_i_ > 1) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.IsEmpty)
    {
       lama.Absyn.IsEmpty _isempty = (lama.Absyn.IsEmpty) foo;
       if (_i_ > 1) render(_L_PAREN);
       pp(_isempty.exp_, 2);
       render(".isEmpty()");
       if (_i_ > 1) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.Last)
    {
       lama.Absyn.Last _last = (lama.Absyn.Last) foo;
       if (_i_ > 1) render(_L_PAREN);
       pp(_last.exp_, 2);
       render(".last()");
       if (_i_ > 1) render(_R_PAREN);
    }

  }

  private static void pp(lama.Absyn.ListExp foo, int _i_)
  {
    ppListExp(foo.iterator(), _i_);
  }

  private static void ppListExp(java.util.Iterator<lama.Absyn.Exp> it, int _i_)
  {
    if (it.hasNext())
    {
      lama.Absyn.Exp el = it.next();
      if (!it.hasNext())
      { /* last */
        pp(el, _i_);
      }
      else
      { /* cons */
        pp(el, _i_); render(","); ppListExp(it, _i_);
      }
    }
  }


  private static void pp(lama.Absyn.Bool foo, int _i_)
  {
    if (foo instanceof lama.Absyn.Bool_true)
    {
       lama.Absyn.Bool_true _bool_true = (lama.Absyn.Bool_true) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("true");
       if (_i_ > 0) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.Bool_false)
    {
       lama.Absyn.Bool_false _bool_false = (lama.Absyn.Bool_false) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("false");
       if (_i_ > 0) render(_R_PAREN);
    }

  }

  private static void pp(lama.Absyn.Type foo, int _i_)
  {
    if (foo instanceof lama.Absyn.TInt)
    {
       lama.Absyn.TInt _tint = (lama.Absyn.TInt) foo;
       if (_i_ > 1) render(_L_PAREN);
       render("int");
       if (_i_ > 1) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.TDouble)
    {
       lama.Absyn.TDouble _tdouble = (lama.Absyn.TDouble) foo;
       if (_i_ > 1) render(_L_PAREN);
       render("double");
       if (_i_ > 1) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.TBool)
    {
       lama.Absyn.TBool _tbool = (lama.Absyn.TBool) foo;
       if (_i_ > 1) render(_L_PAREN);
       render("bool");
       if (_i_ > 1) render(_R_PAREN);
    }
    else     if (foo instanceof lama.Absyn.TArray)
    {
       lama.Absyn.TArray _tarray = (lama.Absyn.TArray) foo;
       if (_i_ > 0) render(_L_PAREN);
       render("[");
       pp(_tarray.type_, 0);
       render("]");
       if (_i_ > 0) render(_R_PAREN);
    }

  }


  private static void sh(lama.Absyn.Program foo)
  {
    if (foo instanceof lama.Absyn.Prog)
    {
       lama.Absyn.Prog _prog = (lama.Absyn.Prog) foo;
       render("(");
       render("Prog");
       render("[");
       sh(_prog.liststm_);
       render("]");
       render(")");
    }
  }

  private static void sh(lama.Absyn.ListStm foo)
  {
     for (java.util.Iterator<lama.Absyn.Stm> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(lama.Absyn.Stm foo)
  {
    if (foo instanceof lama.Absyn.SExp)
    {
       lama.Absyn.SExp _sexp = (lama.Absyn.SExp) foo;
       render("(");
       render("SExp");
       sh(_sexp.exp_);
       render(")");
    }
    if (foo instanceof lama.Absyn.SDecl)
    {
       lama.Absyn.SDecl _sdecl = (lama.Absyn.SDecl) foo;
       render("(");
       render("SDecl");
       sh(_sdecl.type_);
       sh(_sdecl.ident_);
       render(")");
    }
    if (foo instanceof lama.Absyn.SAss)
    {
       lama.Absyn.SAss _sass = (lama.Absyn.SAss) foo;
       render("(");
       render("SAss");
       sh(_sass.ident_);
       sh(_sass.exp_);
       render(")");
    }
    if (foo instanceof lama.Absyn.SInit)
    {
       lama.Absyn.SInit _sinit = (lama.Absyn.SInit) foo;
       render("(");
       render("SInit");
       sh(_sinit.type_);
       sh(_sinit.ident_);
       sh(_sinit.exp_);
       render(")");
    }
    if (foo instanceof lama.Absyn.SPrint)
    {
       lama.Absyn.SPrint _sprint = (lama.Absyn.SPrint) foo;
       render("(");
       render("SPrint");
       sh(_sprint.exp_);
       render(")");
    }
    if (foo instanceof lama.Absyn.SWhile)
    {
       lama.Absyn.SWhile _swhile = (lama.Absyn.SWhile) foo;
       render("(");
       render("SWhile");
       sh(_swhile.exp_);
       sh(_swhile.stm_);
       render(")");
    }
    if (foo instanceof lama.Absyn.SBlock)
    {
       lama.Absyn.SBlock _sblock = (lama.Absyn.SBlock) foo;
       render("(");
       render("SBlock");
       render("[");
       sh(_sblock.liststm_);
       render("]");
       render(")");
    }
    if (foo instanceof lama.Absyn.SIfElse)
    {
       lama.Absyn.SIfElse _sifelse = (lama.Absyn.SIfElse) foo;
       render("(");
       render("SIfElse");
       sh(_sifelse.exp_);
       sh(_sifelse.stm_1);
       sh(_sifelse.stm_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.SFun)
    {
       lama.Absyn.SFun _sfun = (lama.Absyn.SFun) foo;
       render("(");
       render("SFun");
       sh(_sfun.type_1);
       sh(_sfun.ident_1);
       sh(_sfun.type_2);
       sh(_sfun.ident_2);
       render("[");
       sh(_sfun.liststm_);
       render("]");
       sh(_sfun.exp_);
       render(")");
    }
  }

  private static void sh(lama.Absyn.Exp foo)
  {
    if (foo instanceof lama.Absyn.EVar)
    {
       lama.Absyn.EVar _evar = (lama.Absyn.EVar) foo;
       render("(");
       render("EVar");
       sh(_evar.ident_);
       render(")");
    }
    if (foo instanceof lama.Absyn.EInt)
    {
       lama.Absyn.EInt _eint = (lama.Absyn.EInt) foo;
       render("(");
       render("EInt");
       sh(_eint.integer_);
       render(")");
    }
    if (foo instanceof lama.Absyn.EDouble)
    {
       lama.Absyn.EDouble _edouble = (lama.Absyn.EDouble) foo;
       render("(");
       render("EDouble");
       sh(_edouble.double_);
       render(")");
    }
    if (foo instanceof lama.Absyn.EBool)
    {
       lama.Absyn.EBool _ebool = (lama.Absyn.EBool) foo;
       render("(");
       render("EBool");
       sh(_ebool.bool_);
       render(")");
    }
    if (foo instanceof lama.Absyn.EPostIncr)
    {
       lama.Absyn.EPostIncr _epostincr = (lama.Absyn.EPostIncr) foo;
       render("(");
       render("EPostIncr");
       sh(_epostincr.ident_);
       render(")");
    }
    if (foo instanceof lama.Absyn.EPostDecr)
    {
       lama.Absyn.EPostDecr _epostdecr = (lama.Absyn.EPostDecr) foo;
       render("(");
       render("EPostDecr");
       sh(_epostdecr.ident_);
       render(")");
    }
    if (foo instanceof lama.Absyn.EPreIncr)
    {
       lama.Absyn.EPreIncr _epreincr = (lama.Absyn.EPreIncr) foo;
       render("(");
       render("EPreIncr");
       sh(_epreincr.ident_);
       render(")");
    }
    if (foo instanceof lama.Absyn.EPreDecr)
    {
       lama.Absyn.EPreDecr _epredecr = (lama.Absyn.EPreDecr) foo;
       render("(");
       render("EPreDecr");
       sh(_epredecr.ident_);
       render(")");
    }
    if (foo instanceof lama.Absyn.EMul)
    {
       lama.Absyn.EMul _emul = (lama.Absyn.EMul) foo;
       render("(");
       render("EMul");
       sh(_emul.exp_1);
       sh(_emul.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.EDiv)
    {
       lama.Absyn.EDiv _ediv = (lama.Absyn.EDiv) foo;
       render("(");
       render("EDiv");
       sh(_ediv.exp_1);
       sh(_ediv.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.EAdd)
    {
       lama.Absyn.EAdd _eadd = (lama.Absyn.EAdd) foo;
       render("(");
       render("EAdd");
       sh(_eadd.exp_1);
       sh(_eadd.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.ESub)
    {
       lama.Absyn.ESub _esub = (lama.Absyn.ESub) foo;
       render("(");
       render("ESub");
       sh(_esub.exp_1);
       sh(_esub.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.ELess)
    {
       lama.Absyn.ELess _eless = (lama.Absyn.ELess) foo;
       render("(");
       render("ELess");
       sh(_eless.exp_1);
       sh(_eless.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.EGreater)
    {
       lama.Absyn.EGreater _egreater = (lama.Absyn.EGreater) foo;
       render("(");
       render("EGreater");
       sh(_egreater.exp_1);
       sh(_egreater.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.ELEq)
    {
       lama.Absyn.ELEq _eleq = (lama.Absyn.ELEq) foo;
       render("(");
       render("ELEq");
       sh(_eleq.exp_1);
       sh(_eleq.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.EGEq)
    {
       lama.Absyn.EGEq _egeq = (lama.Absyn.EGEq) foo;
       render("(");
       render("EGEq");
       sh(_egeq.exp_1);
       sh(_egeq.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.EEq)
    {
       lama.Absyn.EEq _eeq = (lama.Absyn.EEq) foo;
       render("(");
       render("EEq");
       sh(_eeq.exp_1);
       sh(_eeq.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.ENEq)
    {
       lama.Absyn.ENEq _eneq = (lama.Absyn.ENEq) foo;
       render("(");
       render("ENEq");
       sh(_eneq.exp_1);
       sh(_eneq.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.EAnd)
    {
       lama.Absyn.EAnd _eand = (lama.Absyn.EAnd) foo;
       render("(");
       render("EAnd");
       sh(_eand.exp_1);
       sh(_eand.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.EOr)
    {
       lama.Absyn.EOr _eor = (lama.Absyn.EOr) foo;
       render("(");
       render("EOr");
       sh(_eor.exp_1);
       sh(_eor.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.Array)
    {
       lama.Absyn.Array _array = (lama.Absyn.Array) foo;
       render("(");
       render("Array");
       sh(_array.type_);
       render("[");
       sh(_array.listexp_);
       render("]");
       render(")");
    }
    if (foo instanceof lama.Absyn.Append)
    {
       lama.Absyn.Append _append = (lama.Absyn.Append) foo;
       render("(");
       render("Append");
       sh(_append.exp_1);
       sh(_append.exp_2);
       render(")");
    }
    if (foo instanceof lama.Absyn.Head)
    {
       lama.Absyn.Head _head = (lama.Absyn.Head) foo;
       render("(");
       render("Head");
       sh(_head.exp_);
       render(")");
    }
    if (foo instanceof lama.Absyn.IsEmpty)
    {
       lama.Absyn.IsEmpty _isempty = (lama.Absyn.IsEmpty) foo;
       render("(");
       render("IsEmpty");
       sh(_isempty.exp_);
       render(")");
    }
    if (foo instanceof lama.Absyn.Last)
    {
       lama.Absyn.Last _last = (lama.Absyn.Last) foo;
       render("(");
       render("Last");
       sh(_last.exp_);
       render(")");
    }
  }

  private static void sh(lama.Absyn.ListExp foo)
  {
     for (java.util.Iterator<lama.Absyn.Exp> it = foo.iterator(); it.hasNext();)
     {
       sh(it.next());
       if (it.hasNext())
         render(",");
     }
  }

  private static void sh(lama.Absyn.Bool foo)
  {
    if (foo instanceof lama.Absyn.Bool_true)
    {
       lama.Absyn.Bool_true _bool_true = (lama.Absyn.Bool_true) foo;
       render("Bool_true");
    }
    if (foo instanceof lama.Absyn.Bool_false)
    {
       lama.Absyn.Bool_false _bool_false = (lama.Absyn.Bool_false) foo;
       render("Bool_false");
    }
  }

  private static void sh(lama.Absyn.Type foo)
  {
    if (foo instanceof lama.Absyn.TInt)
    {
       lama.Absyn.TInt _tint = (lama.Absyn.TInt) foo;
       render("TInt");
    }
    if (foo instanceof lama.Absyn.TDouble)
    {
       lama.Absyn.TDouble _tdouble = (lama.Absyn.TDouble) foo;
       render("TDouble");
    }
    if (foo instanceof lama.Absyn.TBool)
    {
       lama.Absyn.TBool _tbool = (lama.Absyn.TBool) foo;
       render("TBool");
    }
    if (foo instanceof lama.Absyn.TArray)
    {
       lama.Absyn.TArray _tarray = (lama.Absyn.TArray) foo;
       render("(");
       render("TArray");
       sh(_tarray.type_);
       render(")");
    }
  }


  private static void pp(Integer n, int _i_) { buf_.append(n); buf_.append(" "); }
  private static void pp(Double d, int _i_) { buf_.append(String.format(java.util.Locale.ROOT, "%.15g ", d)); }
  private static void pp(String s, int _i_) { buf_.append(s); buf_.append(" "); }
  private static void pp(Character c, int _i_) { buf_.append("'" + c.toString() + "'"); buf_.append(" "); }
  private static void sh(Integer n) { render(n.toString()); }
  private static void sh(Double d) { render(String.format(java.util.Locale.ROOT, "%.15g", d)); }
  private static void sh(Character c) { render("'" + c.toString() + "'"); }
  private static void sh(String s) { printQuoted(s); }

  private static void printQuoted(String s) { render("\"" + s + "\""); }

  private static void indent()
  {
    int n = _n_;
    while (n > 0)
    {
      buf_.append(' ');
      n--;
    }
  }

  private static void backup()
  {
    int prev = buf_.length() - 1;
    if (prev >= 0 && buf_.charAt(prev) == ' ')
      buf_.setLength(prev);
  }

  private static void trim()
  {
    // Trim initial spaces
    int end = 0;
    int len = buf_.length();
    while (end < len && buf_.charAt(end) == ' ')
      end++;
    buf_.delete(0, end);
    // Trim trailing spaces
    removeTrailingSpaces();
  }

  private static void removeTrailingSpaces()
  {
    int end = buf_.length();
    while (end > 0 && buf_.charAt(end-1) == ' ')
      end--;
    buf_.setLength(end);
  }

  private static void removeTrailingWhitespace()
  {
    int end = buf_.length();
    while (end > 0 && (buf_.charAt(end-1) == ' ' || buf_.charAt(end-1) == '\n'))
      end--;
    buf_.setLength(end);
  }

  private static void onEmptyLine()
  {
    removeTrailingSpaces();
    int len = buf_.length();
    if (len > 0 && buf_.charAt(len-1) != '\n') buf_.append("\n");
    indent();
  }

  private static int _n_ = 0;
  private static StringBuilder buf_ = new StringBuilder(INITIAL_BUFFER_SIZE);
}

