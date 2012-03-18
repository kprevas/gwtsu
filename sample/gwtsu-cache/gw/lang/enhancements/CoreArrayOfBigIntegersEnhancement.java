// Compiled from CoreArrayOfBigIntegersEnhancement.gsx
public gw.lang.enhancements.CoreArrayOfBigIntegersEnhancement extends Object{

  public static BigInteger sum(BigInteger; $that$) {
    sum = BigInteger.ZERO;
    /*foreach*/
        *temp0 = $that$;

        *temp1 = -1 + (*temp0 == null ? -1 : *temp0.length);

        *temp2 = -1;

        elt = null;

    if (*temp0 != null) {
      while (*temp2 != *temp1) {
        *temp2 = *temp2 + 1;

        elt = *temp0[*temp2];

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