// Compiled from CoreArrayDoubleSumEnhancement.gsx
public gw.lang.enhancements.CoreArrayDoubleSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static Double sum(Object; $that$, IType typeparam$T, IFunction1 mapper) {
    sum = 0.0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = [[
          *temp1 = ((Double) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Double.class), Double.valueOf(sum), ((Double) mapper.invoke(elt)), TypeSystem.get(double.class), TypeSystem.get(java.lang.Double.class), true, false, true));
          (*temp1 == null ? 0.0 : *temp1.doubleValue())
        ]];
      }
    }
    return ((Double) TypeAsTransformer.coerceValue(Double.valueOf(sum), TypeSystem.get(java.lang.Double.class), DoubleHighPriorityCoercer.instance()));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}