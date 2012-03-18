// Compiled from CoreBigDecimalEnhancement.gsx
public gw.lang.enhancements.CoreBigDecimalEnhancement extends Object{

  public static boolean isIsZero(BigDecimal $that$) {
    return EqualityExpressionTransformer.evaluate($that$.negate(), TypeSystem.get(java.math.BigDecimal.class), true, $that$, TypeSystem.get(java.math.BigDecimal.class));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}