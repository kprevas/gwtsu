// Compiled from CoreBigIntegerEnhancement.gsx
public gw.lang.enhancements.CoreBigIntegerEnhancement extends Object{

  public static boolean isIsZero(BigInteger $that$) {
    return EqualityExpressionTransformer.evaluate($that$.negate(), TypeSystem.get(java.math.BigInteger.class), true, $that$, TypeSystem.get(java.math.BigInteger.class));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}