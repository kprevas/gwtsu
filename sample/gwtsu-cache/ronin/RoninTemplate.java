// Compiled from RoninTemplate.gs
public ronin.RoninTemplate extends Object implements IRoninUtils, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public static void afterRender(IType template, Writer w) {
    return;
  }

  public static String h(String x) {
    return (x == null ? "" : x.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace(""", "&quot;"));
  }

  public static String g(String x) {
    return (x == null ? "" : x.replace(""", "\"").replace("<%", "\<%").replace("${", "\${"));
  }

  public static FormTarget target(MethodReference target) {
    return new FormTarget(target);
  }

  public static String getTargetURL() {
    if (IRoninUtilsEnhancement.getRoninRequest().getFormTarget() == null) {
      *temp0 = "TargetURL property used outside of using(target()) block.";
      if ((*temp0 instanceof Throwable)) {
        throw ((Throwable) *temp0);
      } else {
        throw new EvaluationException(((String) *temp0));
      }
    }
    return IRoninUtilsEnhancement.postUrlFor(IRoninUtilsEnhancement.getRoninRequest().getFormTarget());
  }

  public static String n(Object obj$$unboxedParam) {
    obj = [[
      *temp0 = new Object[1];
      *temp0[0] = obj$$unboxedParam;
      *temp0
    ]];
    target = IRoninUtilsEnhancement.getRoninRequest().getFormTarget();
    if (target == null) {
      *temp1 = "n() used outside of using(target()) block.";
      if ((*temp1 instanceof Throwable)) {
        throw ((Throwable) *temp1);
      } else {
        throw new EvaluationException(((String) *temp1));
      }
    }
    if ([[
      *temp2 = obj[0];
      (*temp2 == null ? false : MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")).isAssignableFrom(TypeSystem.getFromObject(*temp2)))
    ]]) {
      param = ((IParameterInfo) [[
        *temp4 = target.getMethodInfo().getParameters();
        *temp5 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
        *temp6 = [[
          *temp3 = new block_0_(obj);
          *temp3._returnType = TypeSystem.get(boolean.class);
          *temp3
        ]];
        if (*temp4 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.firstWhere(*temp4, *temp5, *temp6)
      ]]);
      if (param != null) {
        return param.getName();
      } else {
        *temp8 = [[
          *temp7 = new StringBuilder();
          *temp7.append("Controller method ");
          *temp7.append(target.getMethodInfo().getName());
          *temp7.append(" has no parameter of type ");
          *temp7.append(((String) TypeAsTransformer.coerceValue(((IType) TypeAsTransformer.coerceValue(obj[0], MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), RuntimeCoercer.instance())), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
          *temp7.append(".");
          *temp7.toString()
        ]];
        if ((*temp8 instanceof Throwable)) {
          throw ((Throwable) *temp8);
        } else {
          throw new EvaluationException(((String) *temp8));
        }
      }
    } else {
      if ([[
        *temp9 = obj[0];
        (*temp9 == null ? false : TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
          *temp10 = new IType[2];
          *temp10[0] = TypeSystem.get(java.lang.Object.class);
          *temp10[1] = TypeSystem.get(java.lang.Object.class);
          *temp10
        ]]).isAssignableFrom(TypeSystem.getFromObject(*temp9)))
      ]]) {
        rootType = [[
          *temp13 = new IType[1];
          *temp13[0] = [[
            *temp11 = obj[0];
            ((*temp11 instanceof IPropertyReference) ? ((IPropertyReference) *temp11) : ((IPropertyReference) TypeAsTransformer.coerceValue(*temp11, TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
              *temp12 = new IType[2];
              *temp12[0] = TypeSystem.get(java.lang.Object.class);
              *temp12[1] = TypeSystem.get(java.lang.Object.class);
              *temp12
            ]]), RuntimeCoercer.instance())))
          ]].getRootType();
          *temp13
        ]];
        param = ((IParameterInfo) [[
          *temp15 = target.getMethodInfo().getParameters();
          *temp16 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
          *temp17 = [[
            *temp14 = new block_1_(rootType);
            *temp14._returnType = TypeSystem.get(boolean.class);
            *temp14
          ]];
          if (*temp15 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.firstWhere(*temp15, *temp16, *temp17)
        ]]);
        if (param != null) {
          propertyName = [[
            *temp18 = obj[0];
            ((*temp18 instanceof IPropertyReference) ? ((IPropertyReference) *temp18) : ((IPropertyReference) TypeAsTransformer.coerceValue(*temp18, TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
              *temp19 = new IType[2];
              *temp19[0] = TypeSystem.get(java.lang.Object.class);
              *temp19[1] = TypeSystem.get(java.lang.Object.class);
              *temp19
            ]]), RuntimeCoercer.instance())))
          ]].getPropertyInfo().getName();
          return [[
            *temp20 = new StringBuilder();
            *temp20.append(param.getName());
            *temp20.append(".");
            *temp20.append(propertyName);
            *temp20.toString()
          ]];
        } else {
          *temp22 = [[
            *temp21 = new StringBuilder();
            *temp21.append("Controller method ");
            *temp21.append(target.getMethodInfo().getName());
            *temp21.append(" has no parameter of type ");
            *temp21.append(((String) TypeAsTransformer.coerceValue(rootType[0], TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp21.append(".");
            *temp21.toString()
          ]];
          if ((*temp22 instanceof Throwable)) {
            throw ((Throwable) *temp22);
          } else {
            throw new EvaluationException(((String) *temp22));
          }
        }
      } else {
        param = ((IParameterInfo) [[
          *temp24 = target.getMethodInfo().getParameters();
          *temp25 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
          *temp26 = [[
            *temp23 = new block_2_(obj);
            *temp23._returnType = TypeSystem.get(boolean.class);
            *temp23
          ]];
          if (*temp24 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.firstWhere(*temp24, *temp25, *temp26)
        ]]);
        if (param != null) {
          return param.getName();
        } else {
          *temp29 = [[
            *temp27 = new StringBuilder();
            *temp27.append("Controller method ");
            *temp27.append(target.getMethodInfo().getName());
            *temp27.append(" has no parameter of type ");
            *temp27.append(((String) TypeAsTransformer.coerceValue([[
              *temp28 = obj[0];
              (*temp28 == null ? TypeSystem.get(void.class) : GosuRuntimeMethods.typeof(*temp28))
            ]], TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp27.append(".");
            *temp27.toString()
          ]];
          if ((*temp29 instanceof Throwable)) {
            throw ((Throwable) *temp29);
          } else {
            throw new EvaluationException(((String) *temp29));
          }
        }
      }
    }
  }

  public static String n(Object obj, int index) {
    target = IRoninUtilsEnhancement.getRoninRequest().getFormTarget();
    if (target == null) {
      *temp0 = "n() used outside of using(target()) block.";
      if ((*temp0 instanceof Throwable)) {
        throw ((Throwable) *temp0);
      } else {
        throw new EvaluationException(((String) *temp0));
      }
    }
    if ([[
      *temp1 = obj;
      (*temp1 == null ? false : MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")).isAssignableFrom(TypeSystem.getFromObject(*temp1)))
    ]]) {
      arrayType = ((IType) TypeAsTransformer.coerceValue(obj, MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), RuntimeCoercer.instance())).getArrayType();
      return [[
        *temp2 = new StringBuilder();
        *temp2.append(RoninTemplate.n(arrayType));
        *temp2.append("[");
        *temp2.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(index), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
        *temp2.append("]");
        *temp2.toString()
      ]];
    } else {
      if ([[
        *temp3 = obj;
        (*temp3 == null ? false : TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
          *temp4 = new IType[2];
          *temp4[0] = TypeSystem.get(java.lang.Object.class);
          *temp4[1] = TypeSystem.get(java.lang.Object.class);
          *temp4
        ]]).isAssignableFrom(TypeSystem.getFromObject(*temp3)))
      ]]) {
        rootType = [[
          *temp7 = new IType[1];
          *temp7[0] = [[
            *temp5 = obj;
            ((*temp5 instanceof IPropertyReference) ? ((IPropertyReference) *temp5) : ((IPropertyReference) TypeAsTransformer.coerceValue(*temp5, TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
              *temp6 = new IType[2];
              *temp6[0] = TypeSystem.get(java.lang.Object.class);
              *temp6[1] = TypeSystem.get(java.lang.Object.class);
              *temp6
            ]]), RuntimeCoercer.instance())))
          ]].getRootType();
          *temp7
        ]];
        if (rootType[0].isArray()) {
          param = ((IParameterInfo) [[
            *temp9 = target.getMethodInfo().getParameters();
            *temp10 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
            *temp11 = [[
              *temp8 = new block_3_(rootType);
              *temp8._returnType = TypeSystem.get(boolean.class);
              *temp8
            ]];
            if (*temp9 == null) {
              throw new NullPointerException();
            }
            CoreArrayEnhancement.firstWhere(*temp9, *temp10, *temp11)
          ]]);
          if (param != null) {
            propertyName = [[
              *temp12 = obj;
              ((*temp12 instanceof IPropertyReference) ? ((IPropertyReference) *temp12) : ((IPropertyReference) TypeAsTransformer.coerceValue(*temp12, TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
                *temp13 = new IType[2];
                *temp13[0] = TypeSystem.get(java.lang.Object.class);
                *temp13[1] = TypeSystem.get(java.lang.Object.class);
                *temp13
              ]]), RuntimeCoercer.instance())))
            ]].getPropertyInfo().getName();
            return [[
              *temp14 = new StringBuilder();
              *temp14.append(param.getName());
              *temp14.append("[");
              *temp14.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(index), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
              *temp14.append("].");
              *temp14.append(propertyName);
              *temp14.toString()
            ]];
          } else {
            *temp16 = [[
              *temp15 = new StringBuilder();
              *temp15.append("Controller method ");
              *temp15.append(target.getMethodInfo().getName());
              *temp15.append(" has no parameter of type ");
              *temp15.append(((String) TypeAsTransformer.coerceValue(rootType[0], TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
              *temp15.append(".");
              *temp15.toString()
            ]];
            if ((*temp16 instanceof Throwable)) {
              throw ((Throwable) *temp16);
            } else {
              throw new EvaluationException(((String) *temp16));
            }
          }
        } else {
          return [[
            *temp17 = new StringBuilder();
            *temp17.append(RoninTemplate.n([[
              *temp18 = obj;
              ((*temp18 instanceof IPropertyReference) ? ((IPropertyReference) *temp18) : ((IPropertyReference) TypeAsTransformer.coerceValue(*temp18, TypeSystem.getByFullName("gw.lang.reflect.features.IPropertyReference", "_default_").getParameterizedType([[
                *temp19 = new IType[2];
                *temp19[0] = TypeSystem.get(java.lang.Object.class);
                *temp19[1] = TypeSystem.get(java.lang.Object.class);
                *temp19
              ]]), RuntimeCoercer.instance())))
            ]]));
            *temp17.append("[");
            *temp17.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(index), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
            *temp17.append("]");
            *temp17.toString()
          ]];
        }
      } else {
        return [[
          *temp20 = new StringBuilder();
          *temp20.append(RoninTemplate.n([[
            *temp21 = obj;
            (*temp21 == null ? TypeSystem.get(void.class) : GosuRuntimeMethods.typeof(*temp21))
          ]].getArrayType()));
          *temp20.append("[");
          *temp20.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(index), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
          *temp20.append("]");
          *temp20.toString()
        ]];
      }
    }
  }

  public static String n(int index) {
    target = IRoninUtilsEnhancement.getRoninRequest().getFormTarget();
    if (target == null) {
      *temp0 = "n() used outside of using(target()) block.";
      if ((*temp0 instanceof Throwable)) {
        throw ((Throwable) *temp0);
      } else {
        throw new EvaluationException(((String) *temp0));
      }
    }
    params = target.getMethodInfo().getParameters();
    if ([[
      *temp1 = params;
      *temp2 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreArrayEnhancement.getCount(*temp1, *temp2)
    ]] <= index) {
      *temp6 = [[
        *temp3 = new StringBuilder();
        *temp3.append("Controller method ");
        *temp3.append(target.getMethodInfo().getName());
        *temp3.append(" has only ");
        *temp3.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf([[
          *temp4 = params;
          *temp5 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
          if (*temp4 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.getCount(*temp4, *temp5)
        ]]), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
        *temp3.append(" parameters.");
        *temp3.toString()
      ]];
      if ((*temp6 instanceof Throwable)) {
        throw ((Throwable) *temp6);
      } else {
        throw new EvaluationException(((String) *temp6));
      }
    }
    param = target.getMethodInfo().getParameters()[index];
    return param.getName();
  }

  public static String n(int paramIndex, int arrayIndex) {
    return [[
      *temp0 = new StringBuilder();
      *temp0.append(RoninTemplate.n(paramIndex));
      *temp0.append("[");
      *temp0.append(((String) TypeAsTransformer.coerceValue(Integer.valueOf(arrayIndex), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
      *temp0.append("]");
      *temp0.toString()
    ]];
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("h(java.lang.String)");
    builder.addGosuAnnotation(new Param("x", "The string to be escaped."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the escaped string.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("g(java.lang.String)");
    builder.addGosuAnnotation(new Param("x", "The string to be escaped."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "the escaped string.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("target(gw.lang.reflect.features.MethodReference<java.lang.Object, java.lang.Object>)");
    builder.addGosuAnnotation(new Param("target", "The method which will be called by the request.  Arguments should be unbound."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "An object to pass to a using() statement.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("n(java.lang.Object)");
    builder.addGosuAnnotation(new Param("obj", "A value, type literal, or property reference."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The parameter name.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("n(java.lang.Object, int)");
    builder.addGosuAnnotation(new Param("obj", "A value, type literal, or property reference."));
    builder.addGosuAnnotation(new Param("index", "The desired index of the associated value in the array passed in to the parameter."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The parameter name.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("n(int)");
    builder.addGosuAnnotation(new Param("index", "The index of the parameter in the target method's parameter list."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The parameter name.");
    builder.finishJavaAnnotation();
    builder.startAnnotationInfoForFeature("n(int, int)");
    builder.addGosuAnnotation(new Param("index", "The index of the parameter in the target method's parameter list."));
    builder.addGosuAnnotation(new Param("index", "The desired index of the associated value in the array passed in to the parameter."));
    builder.startJavaAnnotation(TypeSystem.get(gw.lang.Returns.class));
    builder.withArg("value", "The parameter name.");
    builder.finishJavaAnnotation();
    return builder.getAnnotations();
  }

}