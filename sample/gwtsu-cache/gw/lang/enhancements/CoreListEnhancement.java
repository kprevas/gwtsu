// Compiled from CoreListEnhancement.gsx
public gw.lang.enhancements.CoreListEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static List cast(List $that$, IType typeparam$T, IType typeparam$N, IType type) {
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        if (!(type.isAssignableFrom([[
          *temp1 = elt;
          (*temp1 == null ? TypeSystem.get(void.class) : GosuRuntimeMethods.typeof(*temp1))
        ]]))) {
          throw new IllegalArgumentException([[
            *temp2 = new StringBuilder();
            *temp2.append("The element ");
            *temp2.append(((String) TypeAsTransformer.coerceValue(elt, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp2.append(" is not of type ");
            *temp2.append(type.getName());
            *temp2.toString()
          ]]);
        }
      }
    }
    return [[
      *temp3 = $that$;
      ((*temp3 instanceof List) ? ((List) *temp3) : ((List) TypeAsTransformer.coerceValue(*temp3, TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
        *temp4 = new IType[1];
        *temp4[0] = typeparam$N;
        *temp4
      ]]), RuntimeCoercer.instance())))
    ]];
  }

  public static Map partition(List $that$, IType typeparam$T, IType typeparam$Q, IFunction1 partitioner) {
    returnMap = new HashMap();
    autoMap = [[
      *temp3 = returnMap;
      *temp4 = typeparam$Q;
      *temp5 = TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]]);
      *temp6 = [[
        *temp0 = new block_0_($that$, typeparam$T, typeparam$Q);
        *temp0._returnType = TypeSystem.getByFullName("java.util.ArrayList", "_default_").getParameterizedType([[
          *temp1 = new IType[1];
          *temp1[0] = typeparam$T;
          *temp1
        ]]);
        *temp0
      ]];
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreMapEnhancement.toAutoMap(*temp3, *temp4, *temp5, *temp6)
    ]];
    /*foreach*/
        *temp7 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp7 != null) {
      while (*temp7.hasNext()) {
        elt = ((Object) *temp7.next());

        ((List) autoMap.get(((Object) partitioner.invoke(elt)))).add(elt);
      }
    }
    return returnMap;
  }

  public static List sort(List $that$, IType typeparam$T, IFunction2 isBefore) {
    comparator = new BlockSortComparator(isBefore);
    Collections.sort($that$, comparator);
    return $that$;
  }

  public static List sortBy(List $that$, IType typeparam$T, IFunction1 value) {
    comparator = new BlockSortByComparator(value, true);
    Collections.sort($that$, comparator);
    return $that$;
  }

  public static List sortByDescending(List $that$, IType typeparam$T, IFunction1 value) {
    comparator = new BlockSortByComparator(value, false);
    Collections.sort($that$, comparator);
    return $that$;
  }

  public static List shuffle(List $that$, IType typeparam$T) {
    Collections.shuffle($that$);
    return $that$;
  }

  public static List freeze(List $that$, IType typeparam$T) {
    return Collections.unmodifiableList($that$);
  }

  public static List copy(List $that$, IType typeparam$T) {
    return new ArrayList($that$);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}