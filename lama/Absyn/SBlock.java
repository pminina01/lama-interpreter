// File generated by the BNF Converter (bnfc 2.9.4).

package lama.Absyn;

public class SBlock  extends Stm {
  public final ListStm liststm_;
  public SBlock(ListStm p1) { liststm_ = p1; }

  public <R,A> R accept(lama.Absyn.Stm.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(java.lang.Object o) {
    if (this == o) return true;
    if (o instanceof lama.Absyn.SBlock) {
      lama.Absyn.SBlock x = (lama.Absyn.SBlock)o;
      return this.liststm_.equals(x.liststm_);
    }
    return false;
  }

  public int hashCode() {
    return this.liststm_.hashCode();
  }


}
