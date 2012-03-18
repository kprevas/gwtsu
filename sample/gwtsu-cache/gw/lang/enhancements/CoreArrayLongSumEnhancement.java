// Compiled from CoreArrayLongSumEnhancement.gsx
public gw.lang.enhancements.CoreArrayLongSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static Long sum(Object; $that$, IType typeparam$T, IFunction1 mapper) {
    sum = (long) 0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = [[
          *temp1 = ((Long) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Long.class), Long.valueOf(sum), ((Long) mapper.invoke(elt)), TypeSystem.get(long.class), TypeSystem.get(java.lang.Long.class), true, false, true));
          (*temp1 == null ? 0 : *temp1.longValue())
        ]];
      }
    }
    return ((Long) TypeAsTransformer.coerceValue(Long.valueOf(sum), TypeSystem.get(java.lang.Long.class), LongHighPriorityCoercer.instance()));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}