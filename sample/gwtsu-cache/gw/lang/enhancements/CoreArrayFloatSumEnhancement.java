// Compiled from CoreArrayFloatSumEnhancement.gsx
public gw.lang.enhancements.CoreArrayFloatSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static Float sum(Object; $that$, IType typeparam$T, IFunction1 mapper) {
    sum = (float) 0.0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = [[
          *temp1 = ((Float) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Float.class), Float.valueOf(sum), ((Float) mapper.invoke(elt)), TypeSystem.get(float.class), TypeSystem.get(java.lang.Float.class), true, false, true));
          (*temp1 == null ? 0.0 : *temp1.floatValue())
        ]];
      }
    }
    return ((Float) TypeAsTransformer.coerceValue(Float.valueOf(sum), TypeSystem.get(java.lang.Float.class), FloatHighPriorityCoercer.instance()));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}