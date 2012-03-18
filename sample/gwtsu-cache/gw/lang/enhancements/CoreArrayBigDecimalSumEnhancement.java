// Compiled from CoreArrayBigDecimalSumEnhancement.gsx
public gw.lang.enhancements.CoreArrayBigDecimalSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static BigDecimal sum(Object; $that$, IType typeparam$T, IFunction1 mapper) {
    sum = new BigDecimal("0");
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = ((BigDecimal) AdditiveExpression.evaluate(TypeSystem.get(java.math.BigDecimal.class), sum, ((BigDecimal) mapper.invoke(elt)), TypeSystem.get(java.math.BigDecimal.class), TypeSystem.get(java.math.BigDecimal.class), true, false, true));
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}