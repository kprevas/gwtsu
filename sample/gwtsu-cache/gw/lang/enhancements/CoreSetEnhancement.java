// Compiled from CoreSetEnhancement.gsx
public gw.lang.enhancements.CoreSetEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static Set cast(Set $that$, IType typeparam$T, IType typeparam$N, IType type) {
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
      ((*temp3 instanceof Set) ? ((Set) *temp3) : ((Set) TypeAsTransformer.coerceValue(*temp3, TypeSystem.getByFullName("java.util.Set", "_default_").getParameterizedType([[
        *temp4 = new IType[1];
        *temp4[0] = typeparam$N;
        *temp4
      ]]), RuntimeCoercer.instance())))
    ]];
  }

  public static Map partition(Set $that$, IType typeparam$T, IType typeparam$Q, IFunction1 partitioner) {
    returnMap = new HashMap();
    autoMap = [[
      *temp3 = returnMap;
      *temp4 = typeparam$Q;
      *temp5 = TypeSystem.getByFullName("java.util.Set", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]]);
      *temp6 = [[
        *temp0 = new block_0_($that$, typeparam$T, typeparam$Q);
        *temp0._returnType = TypeSystem.getByFullName("java.util.HashSet", "_default_").getParameterizedType([[
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

        ((Set) autoMap.get(((Object) partitioner.invoke(elt)))).add(elt);
      }
    }
    return returnMap;
  }

  public static Set powerSet(Set $that$, IType typeparam$T) {
    if ($that$.size() > 10) {
      *temp0 = "You cannot call powerSet() on a set that is larger than size 10.  It will kill the CPU.";
      if ((*temp0 instanceof Throwable)) {
        throw ((Throwable) *temp0);
      } else {
        throw new EvaluationException(((String) *temp0));
      }
    } else {
      returnSet = [[
        *temp1 = new HashSet();
        *temp1.add(new HashSet());
        *temp1
      ]];
      /*foreach*/
            *temp2 = ForEachStatementTransformer.makeIterator($that$);

            t = null;

      if (*temp2 != null) {
        while (*temp2.hasNext()) {
          t = ((Object) *temp2.next());

          temp = new HashSet();
          /*foreach*/
                    *temp3 = ForEachStatementTransformer.makeIterator(returnSet);

                    h = null;

          if (*temp3 != null) {
            while (*temp3.hasNext()) {
              h = ((HashSet) *temp3.next());

              copy = [[
                *temp4 = h.clone();
                ((*temp4 instanceof HashSet) ? ((HashSet) *temp4) : ((HashSet) TypeAsTransformer.coerceValue(*temp4, TypeSystem.getByFullName("java.util.HashSet", "_default_").getParameterizedType([[
                  *temp5 = new IType[1];
                  *temp5[0] = typeparam$T;
                  *temp5
                ]]), RuntimeCoercer.instance())))
              ]];
              copy.add(t);
              temp.add(copy);
            }
          }
          returnSet.addAll(temp);
        }
      }
      return returnSet;
    }
  }

  public static Set freeze(Set $that$, IType typeparam$T) {
    return Collections.unmodifiableSet($that$);
  }

  public static Set copy(Set $that$, IType typeparam$T) {
    return new HashSet($that$);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}