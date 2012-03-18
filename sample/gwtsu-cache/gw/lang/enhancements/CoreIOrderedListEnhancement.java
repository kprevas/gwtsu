// Compiled from CoreIOrderedListEnhancement.gsx
public gw.lang.enhancements.CoreIOrderedListEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static IOrderedList thenBy(IOrderedList $that$, IType typeparam$T, IType typeparam$R, IFunction1 by) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("gw.lang.enhancements.OrderedList", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      typedThis = ((OrderedList) TypeAsTransformer.coerceValue(((OrderedList) ((IOrderedList) $that$)), TypeSystem.getByFullName("gw.lang.enhancements.OrderedList", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance()));
      return typedThis.addThenBy(((OrderedList$IToComparable) TypeAsTransformer.coerceValue(by, TypeSystem.getByFullName("gw.lang.enhancements.OrderedList.IToComparable", "_default_").getParameterizedType([[
        *temp3 = new IType[1];
        *temp3[0] = typeparam$T;
        *temp3
      ]]), FunctionToInterfaceCoercer.instance())));
    } else {
      throw new IllegalStateException("Cannot call thenBy() on anything except an OrderedList");
    }
  }

  public static IOrderedList thenByDescending(IOrderedList $that$, IType typeparam$T, IType typeparam$R, IFunction1 by) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("gw.lang.enhancements.OrderedList", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      typedThis = ((OrderedList) TypeAsTransformer.coerceValue(((OrderedList) ((IOrderedList) $that$)), TypeSystem.getByFullName("gw.lang.enhancements.OrderedList", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance()));
      return typedThis.addThenByDescending(((OrderedList$IToComparable) TypeAsTransformer.coerceValue(by, TypeSystem.getByFullName("gw.lang.enhancements.OrderedList.IToComparable", "_default_").getParameterizedType([[
        *temp3 = new IType[1];
        *temp3[0] = typeparam$T;
        *temp3
      ]]), FunctionToInterfaceCoercer.instance())));
    } else {
      throw new IllegalStateException("Cannot call thenBy() on anything except an OrderedList");
    }
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}