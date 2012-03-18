// Compiled from URLMethodValidator.gs
public ronin.URLMethodValidator extends Object implements IUsageSiteValidator, IAnnotation, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public void validate(IParsedElement pe) {
    args = null;
    argTypes = null;
    if ([[
      *temp0 = pe;
      (*temp0 == null ? false : TypeSystem.get(gw.lang.parser.expressions.IBeanMethodCallExpression.class).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      args = [[
        *temp1 = pe;
        ((*temp1 instanceof IBeanMethodCallExpression) ? ((IBeanMethodCallExpression) *temp1) : ((IBeanMethodCallExpression) TypeAsTransformer.coerceValue(*temp1, TypeSystem.get(gw.lang.parser.expressions.IBeanMethodCallExpression.class), RuntimeCoercer.instance())))
      ]].getArgs();
      argTypes = [[
        *temp2 = pe;
        ((*temp2 instanceof IBeanMethodCallExpression) ? ((IBeanMethodCallExpression) *temp2) : ((IBeanMethodCallExpression) TypeAsTransformer.coerceValue(*temp2, TypeSystem.get(gw.lang.parser.expressions.IBeanMethodCallExpression.class), RuntimeCoercer.instance())))
      ]].getArgTypes();
    } else {
      if ([[
        *temp3 = pe;
        (*temp3 == null ? false : TypeSystem.get(gw.lang.parser.expressions.IMethodCallExpression.class).isAssignableFrom(TypeSystem.getFromObject(*temp3)))
      ]]) {
        args = [[
          *temp4 = pe;
          ((*temp4 instanceof IMethodCallExpression) ? ((IMethodCallExpression) *temp4) : ((IMethodCallExpression) TypeAsTransformer.coerceValue(*temp4, TypeSystem.get(gw.lang.parser.expressions.IMethodCallExpression.class), RuntimeCoercer.instance())))
        ]].getArgs();
      }
    }
    if ((args != null && args.length == 1)) {
      arg = args[0];
      if ([[
        *temp5 = arg;
        (*temp5 == null ? false : TypeSystem.get(gw.lang.parser.expressions.IFeatureLiteralExpression.class).isAssignableFrom(TypeSystem.getFromObject(*temp5)))
      ]]) {
        if (!(((GosuClass_Proxy) TypeSystem.getByFullName("ronin.RoninController", "_default_")).isAssignableFrom([[
          *temp6 = arg;
          ((*temp6 instanceof IFeatureLiteralExpression) ? ((IFeatureLiteralExpression) *temp6) : ((IFeatureLiteralExpression) TypeAsTransformer.coerceValue(*temp6, TypeSystem.get(gw.lang.parser.expressions.IFeatureLiteralExpression.class), RuntimeCoercer.instance())))
        ]].getRootType()))) {
          pe.addParseException(Res.MSG_ANY, [[
            *temp7 = new String[1];
            *temp7[0] = "Method must be on a class extending ronin.RoninController.";
            *temp7
          ]]);
        }
        if ((([[
          *temp9 = [[
            *temp8 = arg;
            ((*temp8 instanceof IFeatureLiteralExpression) ? ((IFeatureLiteralExpression) *temp8) : ((IFeatureLiteralExpression) TypeAsTransformer.coerceValue(*temp8, TypeSystem.get(gw.lang.parser.expressions.IFeatureLiteralExpression.class), RuntimeCoercer.instance())))
          ]].getFeature();
          (*temp9 == null ? false : TypeSystem.get(gw.lang.reflect.IMethodInfo.class).isAssignableFrom(TypeSystem.getFromObject(*temp9)))
        ]] && [[
          *temp12 = [[
            *temp11 = [[
              *temp10 = arg;
              ((*temp10 instanceof IFeatureLiteralExpression) ? ((IFeatureLiteralExpression) *temp10) : ((IFeatureLiteralExpression) TypeAsTransformer.coerceValue(*temp10, TypeSystem.get(gw.lang.parser.expressions.IFeatureLiteralExpression.class), RuntimeCoercer.instance())))
            ]].getFeature();
            ((*temp11 instanceof IMethodInfo) ? ((IMethodInfo) *temp11) : ((IMethodInfo) TypeAsTransformer.coerceValue(*temp11, TypeSystem.get(gw.lang.reflect.IMethodInfo.class), RuntimeCoercer.instance())))
          ]].getParameters();
          *temp13 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
          if (*temp12 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.getCount(*temp12, *temp13)
        ]] > 0) && !([[
          *temp18 = [[
            *temp15 = [[
              *temp14 = arg;
              ((*temp14 instanceof IFeatureLiteralExpression) ? ((IFeatureLiteralExpression) *temp14) : ((IFeatureLiteralExpression) TypeAsTransformer.coerceValue(*temp14, TypeSystem.get(gw.lang.parser.expressions.IFeatureLiteralExpression.class), RuntimeCoercer.instance())))
            ]].getBoundArgs();
            (*temp15 == null ? ((Boolean) null) : [[
              *temp16 = *temp15;
              *temp17 = TypeSystem.get(gw.lang.parser.IExpression.class);
              if (*temp16 == null) {
                throw new NullPointerException();
              }
              CoreIterableEnhancement.isHasElements(*temp16, *temp17)
            ]])
          ]];
          (*temp18 == null ? false : *temp18.booleanValue())
        ]]))) {
          pe.addParseException(Res.MSG_ANY, [[
            *temp19 = new String[1];
            *temp19[0] = "Method arguments must be bound to actual values.";
            *temp19
          ]]);
        }
      } else {
        if (EqualityExpressionTransformer.evaluate(pe.getGosuClass().getName(), TypeSystem.get(java.lang.String.class), false, "ronin.RoninServlet", TypeSystem.get(java.lang.String.class))) {
          pe.addParseException(Res.MSG_ANY, [[
            *temp20 = new String[1];
            *temp20[0] = "Must pass a single feature literal.";
            *temp20
          ]]);
        }
      }
    } else {
      pe.addParseException(Res.MSG_ANY, [[
        *temp21 = new String[1];
        *temp21[0] = "Must pass a single feature literal.";
        *temp21
      ]]);
    }
    return;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}