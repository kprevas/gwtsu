// Compiled from CoreArrayOfBigDecimalsEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfBigDecimalsEnhancement extends Object{

  public static BigDecimal sum(BigDecimal; $that$) {
    sum = BigDecimal.ZERO;
    /*foreach*/
        *temp0 = $that$;

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        elt = null;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        elt = *temp0[*temp2];

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