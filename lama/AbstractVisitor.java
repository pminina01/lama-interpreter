// File generated by the BNF Converter (bnfc 2.9.4).

package lama;

/** Abstract Visitor */

public class AbstractVisitor<R,A> implements AllVisitor<R,A> {
    /* Program */
    public R visit(lama.Absyn.Prog p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(lama.Absyn.Program p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Stm */
    public R visit(lama.Absyn.SExp p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SDecl p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SAss p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SInit p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SPrint p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SWhile p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SBlock p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SReturn p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SIfElse p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SFun p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.SImp p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(lama.Absyn.Stm p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Exp */
    public R visit(lama.Absyn.EVar p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EInt p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EDouble p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EBool p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EPostIncr p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EPostDecr p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EPreIncr p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EPreDecr p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EMul p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EDiv p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EAdd p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.ESub p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.ELess p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EGreater p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.ELEq p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EGEq p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EEq p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.ENEq p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EAnd p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.EOr p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.Array p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.Append p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.Head p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.IsEmpty p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.Last p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(lama.Absyn.Exp p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Bool */
    public R visit(lama.Absyn.Bool_true p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.Bool_false p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(lama.Absyn.Bool p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }
    /* Type */
    public R visit(lama.Absyn.TInt p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.TDouble p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.TBool p, A arg) { return visitDefault(p, arg); }
    public R visit(lama.Absyn.TArray p, A arg) { return visitDefault(p, arg); }
    public R visitDefault(lama.Absyn.Type p, A arg) {
      throw new IllegalArgumentException(this.getClass().getName() + ": " + p);
    }

}
