// Compiled from CoreIterableOfBigDecimalsEnhancement.gsx
public gw.lang.enhancements.CoreIterableOfBigDecimalsEnhancement extends Object{

  public static BigDecimal sum(Iterable $that$) {
    sum = BigDecimal.ZERO;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((BigDecimal) *temp0.next());

        sum = ((BigDecimal) AdditiveExpression.evaluate(TypeSystem.get(java.math.BigDecimal.class), sum, elt, TypeSystem.get(java.math.BigDecimal.class), TypeSystem.get(java.math.BigDecimal.class), true, false, true));
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}