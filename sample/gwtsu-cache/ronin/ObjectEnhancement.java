// Compiled from ObjectEnhancement.gsx
public ronin.ObjectEnhancement extends Object{

  internal static Object toJSONObject(Object val, int depth$$unboxedParam, List include$$unboxedParam, List exclude$$unboxedParam) {
    depth = [[
      *temp0 = new int[1];
      *temp0[0] = depth$$unboxedParam;
      *temp0
    ]];
    include = [[
      *temp1 = new List[1];
      *temp1[0] = include$$unboxedParam;
      *temp1
    ]];
    exclude = [[
      *temp2 = new List[1];
      *temp2[0] = exclude$$unboxedParam;
      *temp2
    ]];
    if ([[
      *temp3 = val;
      (*temp3 == null ? false : TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
        *temp4 = new IType[1];
        *temp4[0] = TypeSystem.get(java.lang.Object.class);
        *temp4
      ]]).isAssignableFrom(TypeSystem.getFromObject(*temp3)))
    ]]) {
      return [[
        *temp8 = [[
          *temp5 = val;
          ((*temp5 instanceof List) ? ((List) *temp5) : ((List) TypeAsTransformer.coerceValue(*temp5, TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
            *temp6 = new IType[1];
            *temp6[0] = TypeSystem.get(java.lang.Object.class);
            *temp6
          ]]), RuntimeCoercer.instance())))
        ]];
        *temp9 = TypeSystem.get(java.lang.Object.class);
        *temp10 = TypeSystem.get(java.lang.Object.class);
        *temp11 = [[
          *temp7 = new block_0_(exclude, depth, include);
          *temp7._returnType = TypeSystem.get(java.lang.Object.class);
          *temp7
        ]];
        if (*temp8 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.map(*temp8, *temp9, *temp10, *temp11)
      ]];
    }
    if ([[
      *temp12 = val;
      (*temp12 == null ? false : TypeSystem.get([Ljava.lang.Object;.class).isAssignableFrom(TypeSystem.getFromObject(*temp12)))
    ]]) {
      return ObjectEnhancement.toJSONObject([[
        *temp14 = [[
          *temp13 = val;
          ((*temp13 instanceof Object;) ? ((Object;) *temp13) : ((Object;) TypeAsTransformer.coerceValue(*temp13, TypeSystem.get([Ljava.lang.Object;.class), RuntimeCoercer.instance())))
        ]];
        *temp15 = TypeSystem.get(java.lang.Object.class);
        if (*temp14 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.toList(*temp14, *temp15)
      ]], depth[0], include[0], exclude[0]);
    } else {
      if ([[
        *temp16 = val;
        (*temp16 == null ? false : TypeSystem.getByFullName("java.util.Map", "_default_").getParameterizedType([[
          *temp17 = new IType[2];
          *temp17[0] = TypeSystem.get(java.lang.Object.class);
          *temp17[1] = TypeSystem.get(java.lang.Object.class);
          *temp17
        ]]).isAssignableFrom(TypeSystem.getFromObject(*temp16)))
      ]]) {
        returnMap = [[
          *temp18 = new LinkedHashMap[1];
          *temp18[0] = new LinkedHashMap();
          *temp18
        ]];
        [[
          *temp22 = [[
            *temp19 = val;
            ((*temp19 instanceof Map) ? ((Map) *temp19) : ((Map) TypeAsTransformer.coerceValue(*temp19, TypeSystem.getByFullName("java.util.Map", "_default_").getParameterizedType([[
              *temp20 = new IType[2];
              *temp20[0] = TypeSystem.get(java.lang.Object.class);
              *temp20[1] = TypeSystem.get(java.lang.Object.class);
              *temp20
            ]]), RuntimeCoercer.instance())))
          ]];
          *temp23 = TypeSystem.get(java.lang.Object.class);
          *temp24 = TypeSystem.get(java.lang.Object.class);
          *temp25 = [[
            *temp21 = new block_1_(exclude, returnMap, depth, include);
            *temp21._returnType = TypeSystem.get(void.class);
            *temp21
          ]];
          if (*temp22 == null) {
            throw new NullPointerException();
          }
          CoreMapEnhancement.eachKeyAndValue(*temp22, *temp23, *temp24, *temp25)
        ]];
        return returnMap[0];
      } else {
        if (val == null) {
          return null;
        } else {
          if (val.getClass().getName().startsWith("java")) {
            return val;
          } else {
            if ([[
              *temp26 = val;
              (*temp26 == null ? false : TypeSystem.get(gw.lang.reflect.IType.class).isAssignableFrom(TypeSystem.getFromObject(*temp26)))
            ]]) {
              return [[
                *temp27 = val;
                ((*temp27 instanceof IType) ? ((IType) *temp27) : ((IType) TypeAsTransformer.coerceValue(*temp27, TypeSystem.get(gw.lang.reflect.IType.class), RuntimeCoercer.instance())))
              ]].getName();
            } else {
              if ([[
                *temp28 = val;
                (*temp28 == null ? false : TypeSystem.getByFullName("java.lang.Class", "_default_").getParameterizedType([[
                  *temp29 = new IType[1];
                  *temp29[0] = TypeSystem.get(java.lang.Object.class);
                  *temp29
                ]]).isAssignableFrom(TypeSystem.getFromObject(*temp28)))
              ]]) {
                return [[
                  *temp30 = val;
                  ((*temp30 instanceof Class) ? ((Class) *temp30) : ((Class) TypeAsTransformer.coerceValue(*temp30, TypeSystem.getByFullName("java.lang.Class", "_default_").getParameterizedType([[
                    *temp31 = new IType[1];
                    *temp31[0] = TypeSystem.get(java.lang.Object.class);
                    *temp31
                  ]]), RuntimeCoercer.instance())))
                ]].getName();
              } else {
                if (depth[0] == 0) {
                  return null;
                } else {
                  map = new LinkedHashMap();
                  /*foreach*/
                                    *temp42 = ForEachStatementTransformer.makeIterator([[
                    *temp38 = [[
                      *temp34 = [[
                        *temp32 = val;
                        (*temp32 == null ? TypeSystem.get(void.class) : GosuRuntimeMethods.typeof(*temp32))
                      ]].getTypeInfo().getProperties();
                      *temp35 = TypeSystem.get(gw.lang.reflect.IPropertyInfo.class);
                      *temp36 = [[
                        *temp33 = new block_2_();
                        *temp33._returnType = TypeSystem.get(boolean.class);
                        *temp33
                      ]];
                      if (*temp34 == null) {
                        throw new NullPointerException();
                      }
                      CoreIterableEnhancement.where(*temp34, *temp35, *temp36)
                    ]];
                    *temp39 = TypeSystem.get(gw.lang.reflect.IPropertyInfo.class);
                    *temp40 = TypeSystem.get(java.lang.String.class);
                    *temp41 = [[
                      *temp37 = new block_3_();
                      *temp37._returnType = TypeSystem.get(java.lang.String.class);
                      *temp37
                    ]];
                    if (*temp38 == null) {
                      throw new NullPointerException();
                    }
                    CoreCollectionEnhancement.orderBy(*temp38, *temp39, *temp40, *temp41)
                  ]]);

                                    prop = [[
                    *temp43 = new IPropertyInfo[1];
                    *temp43[0] = null;
                    *temp43
                  ]];

                  if (*temp42 != null) {
                    while (*temp42.hasNext()) {
                      prop = [[
                        *temp44 = new IPropertyInfo[1];
                        *temp44[0] = ((IPropertyInfo) *temp42.next());
                        *temp44
                      ]];

                      if ([[
                        *temp45 = exclude[0];
                        (*temp45 == null ? false : [[
                          *temp48 = *temp45;
                          *temp49 = TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
                            *temp47 = new IType[2];
                            *temp47[0] = TypeSystem.get(java.lang.Object.class);
                            *temp47[1] = TypeSystem.get(java.lang.Object.class);
                            *temp47
                          ]]);
                          *temp50 = [[
                            *temp46 = new block_4_(prop);
                            *temp46._returnType = TypeSystem.get(boolean.class);
                            *temp46
                          ]];
                          if (*temp48 == null) {
                            throw new NullPointerException();
                          }
                          CoreIterableEnhancement.hasMatch(*temp48, *temp49, *temp50)
                        ]])
                      ]]) {
                        continue;                      }
                      if ((include[0] != null && !([[
                        *temp53 = include[0];
                        *temp54 = TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
                          *temp52 = new IType[2];
                          *temp52[0] = TypeSystem.get(java.lang.Object.class);
                          *temp52[1] = TypeSystem.get(java.lang.Object.class);
                          *temp52
                        ]]);
                        *temp55 = [[
                          *temp51 = new block_5_(prop);
                          *temp51._returnType = TypeSystem.get(boolean.class);
                          *temp51
                        ]];
                        if (*temp53 == null) {
                          throw new NullPointerException();
                        }
                        CoreIterableEnhancement.hasMatch(*temp53, *temp54, *temp55)
                      ]]))) {
                        continue;                      }
                      propVal = prop[0].getAccessor().getValue(val);
                      transformed = ObjectEnhancement.toJSONObject(propVal, depth[0] - 1, include[0], exclude[0]);
                      if (transformed != null) {
                        map.put(prop[0].getName(), transformed);
                      }
                    }
                  }
                  return map;
                }
              }
            }
          }
        }
      }
    }
  }

  public static String toJSON(Object $that$, int depth, List include, List exclude) {
    return JSchemaUtils.serializeJson(ObjectEnhancement.toJSONObject($that$, depth, include, exclude));
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}