// Compiled from CoreArrayBigIntegerSumEnhancement.gsx
public gw.lang.enhancements.CoreArrayBigIntegerSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static BigInteger sum(Object; $that$, IType typeparam$T, IFunction1 mapper) {
    sum = new BigInteger("0");
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = ((BigInteger) AdditiveExpression.evaluate(TypeSystem.get(java.math.BigInteger.class), sum, ((BigInteger) mapper.invoke(elt)), TypeSystem.get(java.math.BigInteger.class), TypeSystem.get(java.math.BigInteger.class), true, false, true));
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}