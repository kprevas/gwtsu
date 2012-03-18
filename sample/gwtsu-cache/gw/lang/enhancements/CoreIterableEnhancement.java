// Compiled from CoreIterableEnhancement.gsx
public gw.lang.enhancements.CoreIterableEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static int getCount(Iterable $that$, IType typeparam$T) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("java.util.Collection", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      return ((Collection) ((Iterable) $that$)).size();
    } else {
      iter = $that$.iterator();
      i = 0;
      while (iter.hasNext()) {
        iter.next();
        i = i + 1;
      }
      return i;
    }
  }

  public static Object single(Iterable $that$, IType typeparam$T) {
    iter = $that$.iterator();
    if (!(iter.hasNext())) {
      throw new IllegalStateException("This iterable has no elements in it");
    }
    val = iter.next();
    if (iter.hasNext()) {
      throw new IllegalStateException("This iterable has more than one element in it");
    }
    return val;
  }

  public static Collection toCollection(Iterable $that$, IType typeparam$T) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("java.util.Collection", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      return ((Collection) TypeAsTransformer.coerceValue(((Collection) ((Iterable) $that$)), TypeSystem.getByFullName("java.util.Collection", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance()));
    } else {
      lst = new ArrayList();
      /*foreach*/
            *temp3 = ForEachStatementTransformer.makeIterator($that$);

            e = null;

      if (*temp3 != null) {
        while (*temp3.hasNext()) {
          e = ((Object) *temp3.next());

          lst.add(e);
        }
      }
      return lst;
    }
  }

  public static List toList(Iterable $that$, IType typeparam$T) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      return ((List) TypeAsTransformer.coerceValue(((List) ((Iterable) $that$)), TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance()));
    } else {
      lst = new ArrayList();
      /*foreach*/
            *temp3 = ForEachStatementTransformer.makeIterator($that$);

            e = null;

      if (*temp3 != null) {
        while (*temp3.hasNext()) {
          e = ((Object) *temp3.next());

          lst.add(e);
        }
      }
      return lst;
    }
  }

  public static Set toSet(Iterable $that$, IType typeparam$T) {
    if ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("java.util.Set", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      return ((Set) TypeAsTransformer.coerceValue(((Set) ((Iterable) $that$)), TypeSystem.getByFullName("java.util.Set", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance()));
    } else {
      st = new HashSet();
      /*foreach*/
            *temp3 = ForEachStatementTransformer.makeIterator($that$);

            e = null;

      if (*temp3 != null) {
        while (*temp3.hasNext()) {
          e = ((Object) *temp3.next());

          st.add(e);
        }
      }
      return st;
    }
  }

  public static Object; toTypedArray(Iterable $that$, IType typeparam$T) {
    arr = ((Object;) TypeAsTransformer.coerceValue(((IType) typeparam$T).makeArrayInstance([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp0, *temp1)
    ]]), typeparam$T.getArrayType(), RuntimeCoercer.instance()));
    /*foreach*/
        *temp2 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

        i = -1;

    if (*temp2 != null) {
      while (*temp2.hasNext()) {
        elt = ((Object) *temp2.next());

        i = i + 1;

        ArrayAssignmentStatementTransformer.setArrayElement(arr, i, elt);
      }
    }
    return arr;
  }

  public static boolean allMatch(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        e = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        e = ((Object) *temp0.next());

        if (!(((Boolean) cond.invoke(e)).booleanValue())) {
          return false;
        }
      }
    }
    return true;
  }

  public static BigDecimal average(Iterable $that$, IType typeparam$T, IFunction1 select$$unboxedParam) {
    select = [[
      *temp0 = new IFunction1[1];
      *temp0[0] = select$$unboxedParam;
      *temp0
    ]];
    return ((BigDecimal) MultiplicativeExpression.evaluate(TypeSystem.get(java.math.BigDecimal.class), [[
      *temp2 = $that$;
      *temp3 = typeparam$T;
      *temp4 = [[
        *temp1 = new block_0_($that$, select, typeparam$T);
        *temp1._returnType = TypeSystem.get(java.math.BigDecimal.class);
        *temp1
      ]];
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableBigDecimalSumEnhancement.sum(*temp2, *temp3, *temp4)
    ]], ((BigDecimal) TypeAsTransformer.coerceValue(Integer.valueOf([[
      *temp5 = $that$;
      *temp6 = typeparam$T;
      if (*temp5 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp5, *temp6)
    ]]), TypeSystem.get(java.math.BigDecimal.class), BigDecimalCoercer.instance())), TypeSystem.get(java.math.BigDecimal.class), TypeSystem.get(java.math.BigDecimal.class), '/', false));
  }

  public static Collection concat(Iterable $that$, IType typeparam$T, Collection that) {
    returnList = new ArrayList([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp0, *temp1)
    ]] + [[
      *temp2 = that;
      *temp3 = typeparam$T;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp2, *temp3)
    ]]);
    returnList.addAll([[
      *temp4 = $that$;
      *temp5 = typeparam$T;
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toList(*temp4, *temp5)
    ]]);
    returnList.addAll(that);
    return returnList;
  }

  public static int countWhere(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    i = 0;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        e = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        e = ((Object) *temp0.next());

        if (((Boolean) cond.invoke(e)).booleanValue()) {
          i = i + 1;
        }
      }
    }
    return i;
  }

  public static Set disjunction(Iterable $that$, IType typeparam$T, Collection that) {
    intersection = [[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      *temp2 = that;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.intersect(*temp0, *temp1, *temp2)
    ]];
    return [[
      *temp6 = [[
        *temp3 = $that$;
        *temp4 = typeparam$T;
        *temp5 = that;
        if (*temp3 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.union(*temp3, *temp4, *temp5)
      ]];
      *temp7 = typeparam$T;
      *temp8 = intersection;
      if (*temp6 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.subtract(*temp6, *temp7, *temp8)
    ]];
  }

  public static void each(Iterable $that$, IType typeparam$T, IFunction1 operation) {
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        operation.invoke(elt);
      }
    }
    return;
  }

  public static void eachWithIndex(Iterable $that$, IType typeparam$T, IFunction2 operation) {
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

        i = -1;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        i = i + 1;

        operation.invoke(elt, Integer.valueOf(i));
      }
    }
    return;
  }

  public static List flatMap(Iterable $that$, IType typeparam$T, IType typeparam$R, IFunction1 mapper) {
    returnList = new ArrayList();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        iter = ((Collection) mapper.invoke(elt));
        /*foreach*/
                *temp1 = ForEachStatementTransformer.makeIterator(iter);

                result = null;

        if (*temp1 != null) {
          while (*temp1.hasNext()) {
            result = ((Object) *temp1.next());

            returnList.add(result);
          }
        }
      }
    }
    return returnList;
  }

  public static Object fold(Iterable $that$, IType typeparam$T, IFunction2 aggregator) {
    retVal = null;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

        i = -1;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        i = i + 1;

        if (i == 0) {
          retVal = elt;
        } else {
          retVal = ((Object) aggregator.invoke(retVal, elt));
        }
      }
    }
    return retVal;
  }

  public static Object first(Iterable $that$, IType typeparam$T) {
    if ([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp0, *temp1)
    ]] == 0) {
      return null;
    } else {
      if ([[
        *temp2 = $that$;
        (*temp2 == null ? false : TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
          *temp3 = new IType[1];
          *temp3[0] = TypeSystem.get(java.lang.Object.class);
          *temp3
        ]]).isAssignableFrom(TypeSystem.getFromObject(*temp2)))
      ]]) {
        return ((Object) TypeAsTransformer.coerceValue(((Object) ArrayAccess.getArrayElement(((List) ((Iterable) $that$)), 0, false)), typeparam$T, TypeVariableCoercer.instance()));
      } else {
        return $that$.iterator().next();
      }
    }
  }

  public static Object firstWhere(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        e = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        e = ((Object) *temp0.next());

        if (((Boolean) cond.invoke(e)).booleanValue()) {
          return e;
        }
      }
    }
    return null;
  }

  public static Boolean isHasElements(Iterable $that$, IType typeparam$T) {
    return ((Boolean) TypeAsTransformer.coerceValue(Boolean.valueOf([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp0, *temp1)
    ]] != 0), TypeSystem.get(java.lang.Boolean.class), BooleanHighPriorityCoercer.instance()));
  }

  public static boolean hasMatch(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        e = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        e = ((Object) *temp0.next());

        if (((Boolean) cond.invoke(e)).booleanValue()) {
          return true;
        }
      }
    }
    return false;
  }

  public static Set intersect(Iterable $that$, IType typeparam$T, Collection that) {
    set = ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("java.util.Set", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = TypeSystem.get(java.lang.Object.class);
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]] ? new HashSet([[
      *temp2 = $that$;
      *temp3 = typeparam$T;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toList(*temp2, *temp3)
    ]]) : new LinkedHashSet([[
      *temp4 = $that$;
      *temp5 = typeparam$T;
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toList(*temp4, *temp5)
    ]]));
    set.retainAll(that);
    return set;
  }

  public static String join(Iterable $that$, IType typeparam$T, String delimiter) {
    retVal = new StringBuilder();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

        i = -1;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        i = i + 1;

        if (i > 0) {
          retVal.append(delimiter);
        }
        retVal.append(((String) TypeAsTransformer.coerceValue(elt, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
      }
    }
    return retVal.toString();
  }

  public static Object last(Iterable $that$, IType typeparam$T) {
    i = [[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp0, *temp1)
    ]];
    if (i == 0) {
      return null;
    } else {
      if ([[
        *temp2 = $that$;
        (*temp2 == null ? false : TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
          *temp3 = new IType[1];
          *temp3[0] = TypeSystem.get(java.lang.Object.class);
          *temp3
        ]]).isAssignableFrom(TypeSystem.getFromObject(*temp2)))
      ]]) {
        return ((Object) TypeAsTransformer.coerceValue(((Object) ArrayAccess.getArrayElement(((List) ((Iterable) $that$)), i - 1, false)), typeparam$T, TypeVariableCoercer.instance()));
      } else {
        ret = null;
        /*foreach*/
                *temp4 = ForEachStatementTransformer.makeIterator($that$);

                elt = null;

        if (*temp4 != null) {
          while (*temp4.hasNext()) {
            elt = ((Object) *temp4.next());

            ret = elt;
          }
        }
        return ret;
      }
    }
  }

  public static Object lastWhere(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    returnVal = null;
    found = false;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        if (((Boolean) cond.invoke(elt)).booleanValue()) {
          returnVal = elt;
          found = true;
        }
      }
    }
    return returnVal;
  }

  public static List map(Iterable $that$, IType typeparam$T, IType typeparam$Q, IFunction1 mapper) {
    returnList = new ArrayList();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        returnList.add(((Object) mapper.invoke(elt)));
      }
    }
    return returnList;
  }

  public static Comparable max(Iterable $that$, IType typeparam$T, IType typeparam$R, IFunction1 transform) {
    if ([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp0, *temp1)
    ]] == 0) {
      throw new IllegalStateException([[
        *temp2 = new StringBuilder();
        *temp2.append(((String) TypeAsTransformer.coerceValue($that$, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
        *temp2.append(" is empty");
        *temp2.toString()
      ]]);
    }
    returnVal = ((Comparable) transform.invoke([[
      *temp3 = $that$;
      *temp4 = typeparam$T;
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.first(*temp3, *temp4)
    ]]));
    /*foreach*/
        *temp5 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp5 != null) {
      while (*temp5.hasNext()) {
        elt = ((Object) *temp5.next());

        eltVal = ((Comparable) transform.invoke(elt));
        if (RelationalExpressionTransformer.evaluate(eltVal, typeparam$R, ">", returnVal, typeparam$R)) {
          returnVal = eltVal;
        }
      }
    }
    return returnVal;
  }

  public static Object maxBy(Iterable $that$, IType typeparam$T, IFunction1 comparison) {
    max = null;
    maxVal = null;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        altVal = (elt == null ? null : ((Comparable) comparison.invoke(elt)));
        if ((elt != null && (maxVal == null || RelationalExpressionTransformer.evaluate(maxVal, TypeSystem.getByFullName("java.lang.Comparable", "_default_").getParameterizedType([[
          *temp1 = new IType[1];
          *temp1[0] = TypeSystem.get(java.lang.Object.class);
          *temp1
        ]]), "<", altVal, TypeSystem.getByFullName("java.lang.Comparable", "_default_").getParameterizedType([[
          *temp2 = new IType[1];
          *temp2[0] = TypeSystem.get(java.lang.Object.class);
          *temp2
        ]]))))) {
          max = elt;
          maxVal = altVal;
        }
      }
    }
    return max;
  }

  public static Comparable min(Iterable $that$, IType typeparam$T, IType typeparam$R, IFunction1 transform) {
    if ([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.getCount(*temp0, *temp1)
    ]] == 0) {
      throw new IllegalStateException([[
        *temp2 = new StringBuilder();
        *temp2.append(((String) TypeAsTransformer.coerceValue($that$, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
        *temp2.append(" is empty");
        *temp2.toString()
      ]]);
    }
    returnVal = ((Comparable) transform.invoke([[
      *temp3 = $that$;
      *temp4 = typeparam$T;
      if (*temp3 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.first(*temp3, *temp4)
    ]]));
    /*foreach*/
        *temp5 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp5 != null) {
      while (*temp5.hasNext()) {
        elt = ((Object) *temp5.next());

        eltVal = ((Comparable) transform.invoke(elt));
        if (RelationalExpressionTransformer.evaluate(eltVal, typeparam$R, "<", returnVal, typeparam$R)) {
          returnVal = eltVal;
        }
      }
    }
    return returnVal;
  }

  public static Object minBy(Iterable $that$, IType typeparam$T, IFunction1 comparison) {
    min = null;
    minVal = null;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        altVal = (elt == null ? null : ((Comparable) comparison.invoke(elt)));
        if ((elt != null && (minVal == null || RelationalExpressionTransformer.evaluate(minVal, TypeSystem.getByFullName("java.lang.Comparable", "_default_").getParameterizedType([[
          *temp1 = new IType[1];
          *temp1[0] = TypeSystem.get(java.lang.Object.class);
          *temp1
        ]]), ">", altVal, TypeSystem.getByFullName("java.lang.Comparable", "_default_").getParameterizedType([[
          *temp2 = new IType[1];
          *temp2[0] = TypeSystem.get(java.lang.Object.class);
          *temp2
        ]]))))) {
          min = elt;
          minVal = altVal;
        }
      }
    }
    return min;
  }

  public static Map partitionUniquely(Iterable $that$, IType typeparam$T, IType typeparam$Q, IFunction1 mapper) {
    returnMap = new HashMap();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        key = ((Object) mapper.invoke(elt));
        currentVal = ((Object) returnMap.get(key));
        if (currentVal != null) {
          throw new IllegalStateException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), [[
            *temp1 = new StringBuilder();
            *temp1.append(((String) TypeAsTransformer.coerceValue(mapper, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp1.append(" does not define a unique value across all elements of this Collection : ");
            *temp1.toString()
          ]], [[
            *temp2 = new StringBuilder();
            *temp2.append(" Element ");
            *temp2.append(((String) TypeAsTransformer.coerceValue(elt, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp2.append(" and element ");
            *temp2.append(((String) TypeAsTransformer.coerceValue(currentVal, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp2.append(" both have the value ");
            *temp2.append(((String) TypeAsTransformer.coerceValue(key, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp2.toString()
          ]], TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
        }
        returnMap.put(key, elt);
      }
    }
    return returnMap;
  }

  public static Object reduce(Iterable $that$, IType typeparam$T, IType typeparam$V, Object init, IFunction2 aggregator) {
    retVal = init;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        retVal = ((Object) aggregator.invoke(retVal, elt));
      }
    }
    return retVal;
  }

  public static void retainWhere(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    iter = $that$.iterator();
    while (iter.hasNext()) {
      if (!(((Boolean) cond.invoke(iter.next())).booleanValue())) {
        iter.remove();
      }
    }
    return;
  }

  public static void removeWhere(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    iter = $that$.iterator();
    while (iter.hasNext()) {
      if (((Boolean) cond.invoke(iter.next())).booleanValue()) {
        iter.remove();
      }
    }
    return;
  }

  public static List reverse(Iterable $that$, IType typeparam$T) {
    returnList = ([[
      *temp0 = $that$;
      (*temp0 == null ? false : TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = typeparam$T;
        *temp1
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]] ? [[
      *temp4 = [[
        *temp2 = $that$;
        ((*temp2 instanceof List) ? ((List) *temp2) : ((List) TypeAsTransformer.coerceValue(*temp2, TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
          *temp3 = new IType[1];
          *temp3[0] = typeparam$T;
          *temp3
        ]]), RuntimeCoercer.instance())))
      ]];
      *temp5 = typeparam$T;
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreListEnhancement.copy(*temp4, *temp5)
    ]] : [[
      *temp6 = $that$;
      *temp7 = typeparam$T;
      if (*temp6 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toList(*temp6, *temp7)
    ]]);
    Collections.reverse(returnList);
    return returnList;
  }

  public static Object singleWhere(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    found = false;
    returnVal = null;
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        if (((Boolean) cond.invoke(elt)).booleanValue()) {
          if (found) {
            throw new IllegalStateException("More than one element matches the given condition");
          } else {
            returnVal = elt;
            found = true;
          }
        }
      }
    }
    if (found) {
      return returnVal;
    } else {
      throw new IllegalStateException("No elements match the given condition");
    }
  }

  public static Set subtract(Iterable $that$, IType typeparam$T, Collection that) {
    returnSet = new LinkedHashSet([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toList(*temp0, *temp1)
    ]]);
    returnSet.removeAll(that);
    return returnSet;
  }

  public static Set union(Iterable $that$, IType typeparam$T, Collection that) {
    returnSet = new LinkedHashSet([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toList(*temp0, *temp1)
    ]]);
    returnSet.addAll(that);
    return returnSet;
  }

  public static List where(Iterable $that$, IType typeparam$T, IFunction1 cond) {
    result = new ArrayList();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        if (((Boolean) cond.invoke(elt)).booleanValue()) {
          result.add(elt);
        }
      }
    }
    return result;
  }

  public static List whereTypeIs(Iterable $that$, IType typeparam$T, IType typeparam$R, IType type) {
    retList = new ArrayList();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        if (type.isAssignableFrom([[
          *temp1 = elt;
          (*temp1 == null ? TypeSystem.get(void.class) : GosuRuntimeMethods.typeof(*temp1))
        ]])) {
          retList.add(((Object) TypeAsTransformer.coerceValue(elt, typeparam$R, TypeVariableCoercer.instance())));
        }
      }
    }
    return retList;
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("@Count()");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.ShortCircuitingProperty.class));
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("Count");
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.ShortCircuitingProperty.class));
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}