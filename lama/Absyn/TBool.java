// File generated by the BNF Converter (bnfc 2.9.4).

package lama.Absyn;

public class TBool  extends Type {
  public TBool() { }

  public <R,A> R accept(lama.Absyn.Type.Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(java.lang.Object o) {
    if (this == o) return true;
    if (o instanceof lama.Absyn.TBool) {
      return true;
    }
    return false;
  }

  public int hashCode() {
    return 37;
  }


}
