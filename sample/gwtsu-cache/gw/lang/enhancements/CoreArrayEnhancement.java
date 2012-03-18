// Compiled from CoreArrayEnhancement.gsx
public gw.lang.enhancements.CoreArrayEnhancement extends Object{
  internal final synthetic IType typeparam$T;

  public static List toList(Object; $that$, IType typeparam$T) {
    retList = new ArrayList([[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreArrayEnhancement.getCount(*temp0, *temp1)
    ]]);
    /*foreach*/
        *temp2 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

    if (*temp2 != null) {
      while (*temp2.hasNext()) {
        elt = ((Object) *temp2.next());

        retList.add(elt);
      }
    }
    return retList;
  }

  public static Object; cast(Object; $that$, IType typeparam$T, IType typeparam$N, IType type) {
    newArray = ((Object;) TypeAsTransformer.coerceValue(type.makeArrayInstance($that$.length), typeparam$N.getArrayType(), RuntimeCoercer.instance()));
    if ((GosuObjectUtil.isJavaReferenceArray($that$) && GosuObjectUtil.isJavaReferenceArray(newArray))) {
      System.arraycopy($that$, 0, newArray, 0, $that$.length);
    } else {
      /*foreach*/
            *temp0 = ((AbstractIntIterator) ForEachStatementTransformer.makeIterator(IntervalExpressionTransformer._makeIntegerInterval(Integer.valueOf(0), Integer.valueOf($that$.length), Integer.valueOf(1), true, false)));

            i = 0;

      if (*temp0 != null) {
        while (*temp0.hasNext()) {
          i = *temp0.nextInt();

          if (!([[
            *temp1 = ((Object) ArrayAccess.getArrayElement($that$, i, false));
            (*temp1 == null ? false : typeparam$N.isAssignableFrom(TypeSystem.getFromObject(*temp1)))
          ]])) {
            throw new ArrayStoreException();
          }
          ArrayAssignmentStatementTransformer.setArrayElement(newArray, i, ((Object) TypeAsTransformer.coerceValue(((Object) ArrayAccess.getArrayElement($that$, i, false)), typeparam$N, TypeVariableCoercer.instance())));
        }
      }
    }
    return newArray;
  }

  internal static List fastList(Object; $that$, IType typeparam$T) {
    if (GosuObjectUtil.isJavaReferenceArray($that$)) {
      return Arrays.asList($that$);
    } else {
      return [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.toList(*temp0, *temp1)
      ]];
    }
  }

  public static Object fold(Object; $that$, IType typeparam$T, IFunction2 aggregator) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = aggregator;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.fold(*temp2, *temp3, *temp4)
    ]];
  }

  public static Object reduce(Object; $that$, IType typeparam$T, IType typeparam$V, Object init, IFunction2 aggregator) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = typeparam$V;
      *temp5 = init;
      *temp6 = aggregator;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.reduce(*temp2, *temp3, *temp4, *temp5, *temp6)
    ]];
  }

  public static Boolean allMatch(Object; $that$, IType typeparam$T, IFunction1 cond) {
    return ((Boolean) TypeAsTransformer.coerceValue(Boolean.valueOf([[
      *temp5 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp6 = typeparam$T;
      *temp7 = ((IFunction1) TypeAsTransformer.coerceValue(cond, new BlockType(TypeSystem.get(boolean.class), [[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]], [[
        *temp3 = new String[1];
        *temp3[0] = "elt1";
        *temp3
      ]], [[
        *temp4 = new IExpression[1];
        *temp4[0] = null;
        *temp4
      ]]), BlockCoercer.instance()));
      if (*temp5 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.allMatch(*temp5, *temp6, *temp7)
    ]]), TypeSystem.get(java.lang.Boolean.class), BooleanHighPriorityCoercer.instance()));
  }

  public static Object; concat(Object; $that$, IType typeparam$T, Object; that) {
    return [[
      *temp7 = [[
        *temp4 = [[
          *temp0 = $that$;
          *temp1 = typeparam$T;
          if (*temp0 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.fastList(*temp0, *temp1)
        ]];
        *temp5 = typeparam$T;
        *temp6 = [[
          *temp2 = that;
          *temp3 = typeparam$T;
          if (*temp2 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.fastList(*temp2, *temp3)
        ]];
        if (*temp4 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.concat(*temp4, *temp5, *temp6)
      ]];
      *temp8 = typeparam$T;
      if (*temp7 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toTypedArray(*temp7, *temp8)
    ]];
  }

  public static Boolean isHasElements(Object; $that$, IType typeparam$T) {
    return ((Boolean) TypeAsTransformer.coerceValue(Boolean.valueOf($that$.length > 0), TypeSystem.get(java.lang.Boolean.class), BooleanHighPriorityCoercer.instance()));
  }

  public static Boolean hasMatch(Object; $that$, IType typeparam$T, IFunction1 cond) {
    return ((Boolean) TypeAsTransformer.coerceValue(Boolean.valueOf([[
      *temp5 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp6 = typeparam$T;
      *temp7 = ((IFunction1) TypeAsTransformer.coerceValue(cond, new BlockType(TypeSystem.get(boolean.class), [[
        *temp2 = new IType[1];
        *temp2[0] = typeparam$T;
        *temp2
      ]], [[
        *temp3 = new String[1];
        *temp3[0] = "elt1";
        *temp3
      ]], [[
        *temp4 = new IExpression[1];
        *temp4[0] = null;
        *temp4
      ]]), BlockCoercer.instance()));
      if (*temp5 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.hasMatch(*temp5, *temp6, *temp7)
    ]]), TypeSystem.get(java.lang.Boolean.class), BooleanHighPriorityCoercer.instance()));
  }

  public static BigDecimal average(Object; $that$, IType typeparam$T, IType typeparam$N, IFunction1 select) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = select;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.average(*temp2, *temp3, *temp4)
    ]];
  }

  public static boolean contains(Object; $that$, IType typeparam$T, Object elt) {
    return [[
      *temp0 = $that$;
      *temp1 = typeparam$T;
      if (*temp0 == null) {
        throw new NullPointerException();
      }
      CoreArrayEnhancement.fastList(*temp0, *temp1)
    ]].contains(elt);
  }

  public static int getCount(Object; $that$, IType typeparam$T) {
    return $that$.length;
  }

  public static int countWhere(Object; $that$, IType typeparam$T, IFunction1 match) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = match;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.countWhere(*temp2, *temp3, *temp4)
    ]];
  }

  public static boolean isIsEmpty(Object; $that$, IType typeparam$T) {
    return $that$.length == 0;
  }

  public static Object first(Object; $that$, IType typeparam$T) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.first(*temp2, *temp3)
    ]];
  }

  public static Object firstWhere(Object; $that$, IType typeparam$T, IFunction1 cond) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = cond;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.firstWhere(*temp2, *temp3, *temp4)
    ]];
  }

  public static Set intersect(Object; $that$, IType typeparam$T, Object; that) {
    return [[
      *temp4 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp5 = typeparam$T;
      *temp6 = [[
        *temp2 = that;
        *temp3 = typeparam$T;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp2, *temp3)
      ]];
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.intersect(*temp4, *temp5, *temp6)
    ]];
  }

  public static Object last(Object; $that$, IType typeparam$T) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.last(*temp2, *temp3)
    ]];
  }

  public static Object lastWhere(Object; $that$, IType typeparam$T, IFunction1 cond) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = cond;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.lastWhere(*temp2, *temp3, *temp4)
    ]];
  }

  public static Comparable max(Object; $that$, IType typeparam$T, IType typeparam$R, IFunction1 transform) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = typeparam$R;
      *temp5 = transform;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.max(*temp2, *temp3, *temp4, *temp5)
    ]];
  }

  public static Comparable min(Object; $that$, IType typeparam$T, IType typeparam$R, IFunction1 transform) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = typeparam$R;
      *temp5 = transform;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.min(*temp2, *temp3, *temp4, *temp5)
    ]];
  }

  public static Object; whereTypeIs(Object; $that$, IType typeparam$T, IType typeparam$R, IType type) {
    return [[
      *temp6 = [[
        *temp2 = [[
          *temp0 = $that$;
          *temp1 = typeparam$T;
          if (*temp0 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.fastList(*temp0, *temp1)
        ]];
        *temp3 = typeparam$T;
        *temp4 = typeparam$R;
        *temp5 = type;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.whereTypeIs(*temp2, *temp3, *temp4, *temp5)
      ]];
      *temp7 = typeparam$R;
      if (*temp6 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toTypedArray(*temp6, *temp7)
    ]];
  }

  public static IOrderedList orderBy(Object; $that$, IType typeparam$T, IType typeparam$R, IFunction1 value) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.toList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = typeparam$R;
      *temp5 = value;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreCollectionEnhancement.orderBy(*temp2, *temp3, *temp4, *temp5)
    ]];
  }

  public static IOrderedList orderByDescending(Object; $that$, IType typeparam$T, IType typeparam$R, IFunction1 value) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.toList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = typeparam$R;
      *temp5 = value;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreCollectionEnhancement.orderByDescending(*temp2, *temp3, *temp4, *temp5)
    ]];
  }

  public static Object; map(Object; $that$, IType typeparam$T, IType typeparam$Q, IFunction1 mapper) {
    return [[
      *temp6 = [[
        *temp2 = [[
          *temp0 = $that$;
          *temp1 = typeparam$T;
          if (*temp0 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.fastList(*temp0, *temp1)
        ]];
        *temp3 = typeparam$T;
        *temp4 = typeparam$Q;
        *temp5 = mapper;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.map(*temp2, *temp3, *temp4, *temp5)
      ]];
      *temp7 = typeparam$Q;
      if (*temp6 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toTypedArray(*temp6, *temp7)
    ]];
  }

  public static Object; flatMap(Object; $that$, IType typeparam$T, IType typeparam$R, IFunction1 mapper) {
    return [[
      *temp6 = [[
        *temp2 = [[
          *temp0 = $that$;
          *temp1 = typeparam$T;
          if (*temp0 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.fastList(*temp0, *temp1)
        ]];
        *temp3 = typeparam$T;
        *temp4 = typeparam$R;
        *temp5 = mapper;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.flatMap(*temp2, *temp3, *temp4, *temp5)
      ]];
      *temp7 = typeparam$R;
      if (*temp6 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toTypedArray(*temp6, *temp7)
    ]];
  }

  public static Object singleWhere(Object; $that$, IType typeparam$T, IFunction1 cond) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = cond;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.singleWhere(*temp2, *temp3, *temp4)
    ]];
  }

  public static Object single(Object; $that$, IType typeparam$T) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.single(*temp2, *temp3)
    ]];
  }

  public static Map partitionUniquely(Object; $that$, IType typeparam$T, IType typeparam$Q, IFunction1 partitioner) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = typeparam$Q;
      *temp5 = partitioner;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.partitionUniquely(*temp2, *temp3, *temp4, *temp5)
    ]];
  }

  public static Map partition(Object; $that$, IType typeparam$T, IType typeparam$Q, IFunction1 partitioner) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = typeparam$Q;
      *temp5 = partitioner;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreListEnhancement.partition(*temp2, *temp3, *temp4, *temp5)
    ]];
  }

  public static Set union(Object; $that$, IType typeparam$T, Object; that) {
    return [[
      *temp4 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp5 = typeparam$T;
      *temp6 = [[
        *temp2 = that;
        *temp3 = typeparam$T;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp2, *temp3)
      ]];
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.union(*temp4, *temp5, *temp6)
    ]];
  }

  public static Object; where(Object; $that$, IType typeparam$T, IFunction1 cond) {
    return [[
      *temp5 = [[
        *temp2 = [[
          *temp0 = $that$;
          *temp1 = typeparam$T;
          if (*temp0 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.fastList(*temp0, *temp1)
        ]];
        *temp3 = typeparam$T;
        *temp4 = cond;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.where(*temp2, *temp3, *temp4)
      ]];
      *temp6 = typeparam$T;
      if (*temp5 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toTypedArray(*temp5, *temp6)
    ]];
  }

  public static Set toSet(Object; $that$, IType typeparam$T) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toSet(*temp2, *temp3)
    ]];
  }

  public static Object; sort(Object; $that$, IType typeparam$T, IFunction2 isBefore) {
    comparator = new BlockSortComparator(isBefore);
    Arrays.sort(((Object;) TypeAsTransformer.coerceValue($that$, TypeSystem.get([Ljava.lang.Object;.class), ((ICoercer) null))), comparator);
    return $that$;
  }

  public static Object; sortBy(Object; $that$, IType typeparam$T, IFunction1 value) {
    comparator = new BlockSortByComparator(value, true);
    Arrays.sort(((Object;) TypeAsTransformer.coerceValue($that$, TypeSystem.get([Ljava.lang.Object;.class), ((ICoercer) null))), comparator);
    return $that$;
  }

  public static Object; sortByDescending(Object; $that$, IType typeparam$T, IFunction1 value) {
    comparator = new BlockSortByComparator(value, false);
    Arrays.sort(((Object;) TypeAsTransformer.coerceValue($that$, TypeSystem.get([Ljava.lang.Object;.class), ((ICoercer) null))), comparator);
    return $that$;
  }

  public static String join(Object; $that$, IType typeparam$T, String str) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = str;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.join(*temp2, *temp3, *temp4)
    ]];
  }

  public static Object; copy(Object; $that$, IType typeparam$T) {
    arr = ((Object;) TypeAsTransformer.coerceValue(((IType) typeparam$T).makeArrayInstance($that$.length), typeparam$T.getArrayType(), RuntimeCoercer.instance()));
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator($that$);

        elt = null;

        i = -1;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        elt = ((Object) *temp0.next());

        i = i + 1;

        ArrayAssignmentStatementTransformer.setArrayElement(arr, i, elt);
      }
    }
    return arr;
  }

  public static void each(Object; $that$, IType typeparam$T, IFunction1 operation) {
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

  public static void eachWithIndex(Object; $that$, IType typeparam$T, IFunction2 operation) {
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

  public static Object minBy(Object; $that$, IType typeparam$T, IFunction1 comparison) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = comparison;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.minBy(*temp2, *temp3, *temp4)
    ]];
  }

  public static Object maxBy(Object; $that$, IType typeparam$T, IFunction1 comparison) {
    return [[
      *temp2 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp3 = typeparam$T;
      *temp4 = comparison;
      if (*temp2 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.maxBy(*temp2, *temp3, *temp4)
    ]];
  }

  public static Set subtract(Object; $that$, IType typeparam$T, Object; otherArray) {
    return [[
      *temp4 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp5 = typeparam$T;
      *temp6 = [[
        *temp2 = otherArray;
        *temp3 = typeparam$T;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp2, *temp3)
      ]];
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.subtract(*temp4, *temp5, *temp6)
    ]];
  }

  public static Set disjunction(Object; $that$, IType typeparam$T, Object; otherArray) {
    return [[
      *temp4 = [[
        *temp0 = $that$;
        *temp1 = typeparam$T;
        if (*temp0 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp0, *temp1)
      ]];
      *temp5 = typeparam$T;
      *temp6 = [[
        *temp2 = otherArray;
        *temp3 = typeparam$T;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.fastList(*temp2, *temp3)
      ]];
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.disjunction(*temp4, *temp5, *temp6)
    ]];
  }

  public static Object; reverse(Object; $that$, IType typeparam$T) {
    return [[
      *temp4 = [[
        *temp2 = [[
          *temp0 = $that$;
          *temp1 = typeparam$T;
          if (*temp0 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.toList(*temp0, *temp1)
        ]];
        *temp3 = typeparam$T;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.reverse(*temp2, *temp3)
      ]];
      *temp5 = typeparam$T;
      if (*temp4 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.toTypedArray(*temp4, *temp5)
    ]];
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