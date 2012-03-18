// Compiled from CoreCollectionEnhancement.gsx
public gw.lang.enhancements.CoreCollectionEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static IOrderedList orderBy(Collection $that$, IType typeparam$T, IType typeparam$R, IFunction1 value) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("gw.util.IOrderedList", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      throw new IllegalStateException("You must only call thenBy() after an orderBy()");
    }
    ordered = new OrderedList($that$);
    ordered.addOrderBy(((OrderedList$IToComparable) TypeAsTransformer.coerceValue(value, TypeSystem.getByFullName("gw.lang.enhancements.OrderedList.IToComparable", "_default_").getParameterizedType([[
      *temp2 = new IType[1];
      *temp2[0] = typeparam$T;
      *temp2
    ]]), FunctionToInterfaceCoercer.instance())));
    return ordered;
  }

  public static IOrderedList orderByDescending(Collection $that$, IType typeparam$T, IType typeparam$R, IFunction1 value) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("gw.util.IOrderedList", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      throw new IllegalStateException("You must only call thenBy() after an orderBy()");
    }
    ordered = new OrderedList($that$);
    ordered.addOrderByDescending(((OrderedList$IToComparable) TypeAsTransformer.coerceValue(value, TypeSystem.getByFullName("gw.lang.enhancements.OrderedList.IToComparable", "_default_").getParameterizedType([[
      *temp2 = new IType[1];
      *temp2[0] = typeparam$T;
      *temp2
    ]]), FunctionToInterfaceCoercer.instance())));
    return ordered;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}