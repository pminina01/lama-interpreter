// File generated by the BNF Converter (bnfc 2.9.4).

package lama.Absyn;

public class SImp  extends Stm {
  public final String string_;
  public SImp(String p1) { string_ = p1; }

  public <R,A> R accept(lama.Absyn.Stm.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(java.lang.Object o) {
    if (this == o) return true;
    if (o instanceof lama.Absyn.SImp) {
      lama.Absyn.SImp x = (lama.Absyn.SImp)o;
      return this.string_.equals(x.string_);
    }
    return false;
  }

  public int hashCode() {
    return this.string_.hashCode();
  }


}
