// Compiled from CoreIterableOfBigIntegersEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfBigIntegersEnhancement extends Object{

  public static BigInteger sum(Iterable $that$) {
    sum = BigInteger.ZERO;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((BigInteger) *temp0.next());

        sum = ((BigInteger) AdditiveExpression.evaluate(TypeSystem.get(java.math.BigInteger.class), sum, elt, TypeSystem.get(java.math.BigInteger.class), TypeSystem.get(java.math.BigInteger.class), true, false, true));
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}