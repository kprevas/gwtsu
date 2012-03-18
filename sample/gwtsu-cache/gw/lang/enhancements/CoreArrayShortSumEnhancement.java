// Compiled from CoreArrayShortSumEnhancement.gsx
public gw.lang.enhancements.CoreArrayShortSumEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static int sum(Object; $that$, IType typeparam$T, IFunction1 mapper) {
    sum = 0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        sum = [[
          *temp1 = ((Integer) AdditiveExpression.evaluate(TypeSystem.get(java.lang.Integer.class), Integer.valueOf(sum), ((Short) mapper.invoke(elt)), TypeSystem.get(int.class), TypeSystem.get(java.lang.Short.class), true, false, true));
          (*temp1 == null ? 0 : *temp1.intValue())
        ]];
      }
    }
    return sum;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}