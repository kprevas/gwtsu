// Compiled from DefaultParamConverter.gs
public ronin.config.DefaultParamConverter extends Object implements IParamConverter, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public Object convertValue(IType paramType, String paramValue) {
    if (EqualityExpressionTransformer.evaluate(paramType, MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), true, TypeSystem.get(boolean.class), MetaType.get(TypeSystem.get(boolean.class)))) {
      return ((Boolean) TypeAsTransformer.coerceValue(Boolean.valueOf(("on".equals(paramValue) || "true".equals(paramValue))), TypeSystem.get(java.lang.Boolean.class), ((ICoercer) [[
        *temp0 = BasePrimitiveCoercer.BooleanPCoercer;
        *temp0.get()
      ]])));
    }
    if (!([[
      *temp1 = paramValue;
      (*temp1 == null ? false : [[
        *temp2 = *temp1;
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreStringEnhancement.isHasContent(*temp2)
      ]])
    ]])) {
      if (!(paramType.isPrimitive())) {
        return null;
      } else {
        throw new IncompatibleTypeException();
      }
    }
    factoryMethod = this.getFactoryMethod(paramType);
    if (factoryMethod != null) {
      return factoryMethod.getCallHandler().handleCall(null, [[
        *temp3 = new Object[1];
        *temp3[0] = this.convertValue(factoryMethod.getParameters()[0].getFeatureType(), paramValue);
        *temp3
      ]]);
    } else {
      switch(      *temp4 = paramType;
) {
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(int.class)), TypeSystem.get(int.class)):
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(java.lang.Integer.class)), TypeSystem.get(java.lang.Integer.class)):
          return ((Integer) TypeAsTransformer.coerceValue(Integer.valueOf(Integer.parseInt(paramValue)), TypeSystem.get(java.lang.Integer.class), ((ICoercer) [[
            *temp5 = BasePrimitiveCoercer.IntPCoercer;
            *temp5.get()
          ]])));
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(long.class)), TypeSystem.get(long.class)):
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(java.lang.Long.class)), TypeSystem.get(java.lang.Long.class)):
          return ((Long) TypeAsTransformer.coerceValue(Long.valueOf(Long.parseLong(paramValue)), TypeSystem.get(java.lang.Long.class), ((ICoercer) [[
            *temp6 = BasePrimitiveCoercer.LongPCoercer;
            *temp6.get()
          ]])));
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(float.class)), TypeSystem.get(float.class)):
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(java.lang.Float.class)), TypeSystem.get(java.lang.Float.class)):
          return ((Float) TypeAsTransformer.coerceValue(Float.valueOf(Float.parseFloat(paramValue)), TypeSystem.get(java.lang.Float.class), ((ICoercer) [[
            *temp7 = BasePrimitiveCoercer.FloatPCoercer;
            *temp7.get()
          ]])));
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(double.class)), TypeSystem.get(double.class)):
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(java.lang.Double.class)), TypeSystem.get(java.lang.Double.class)):
          return ((Double) TypeAsTransformer.coerceValue(Double.valueOf(Double.parseDouble(paramValue)), TypeSystem.get(java.lang.Double.class), ((ICoercer) [[
            *temp8 = BasePrimitiveCoercer.DoublePCoercer;
            *temp8.get()
          ]])));
        case BeanAccess.areValuesEqual(MetaType.get(TypeSystem.getByFullName("gw.internal.gosu.parser.MetaType.DefaultType", "_default_")), *temp4, MetaType.get(TypeSystem.get(java.util.Date.class)), TypeSystem.get(java.util.Date.class)):
          return new Date(paramValue);
        default:
          try {
            return CommonServices.getCoercionManager().convertValue(paramValue, paramType);
          }
          catch( IncompatibleTypeException ex) {
            if (([[
              *temp9 = paramType;
              (*temp9 == null ? false : *temp9.isPrimitive())
            ]] || [[
              *temp11 = [[
                *temp10 = paramType;
                (*temp10 == null ? ((String) null) : *temp10.getNamespace())
              ]];
              (*temp11 == null ? false : *temp11.startsWith("java"))
            ]])) {
              throw ex;
            } else {
              throw new IncompatibleTypeException([[
                *temp12 = new StringBuilder();
                *temp12.append("Did you expect a static fromId() method on ");
                *temp12.append(paramType.getName());
                *temp12.append("?");
                *temp12.toString()
              ]], ex);
            }
          }
      }
    }
  }

  protected IMethodInfo getFactoryMethod(IType type) {
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator(type.getTypeInfo().getMethods());

        method = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        method = ((IMethodInfo) *temp0.next());

        if ((((method.isStatic() && EqualityExpressionTransformer.evaluate(method.getDisplayName(), TypeSystem.get(java.lang.String.class), true, "fromId", TypeSystem.get(java.lang.String.class))) && EqualityExpressionTransformer.evaluate(method.getReturnType().getName(), TypeSystem.get(java.lang.String.class), true, type.getName(), TypeSystem.get(java.lang.String.class))) && [[
          *temp1 = method.getParameters();
          *temp2 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
          if (*temp1 == null) {
            throw new NullPointerException();
          }
          CoreArrayEnhancement.getCount(*temp1, *temp2)
        ]] == 1)) {
          return method;
        }
      }
    }
    return null;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("getFactoryMethod(Type<gw.internal.gosu.parser.MetaType.DefaultType>)");
    builder.addGosuAnnotation(new Param("type", "The type which the factory method must return."));
    return builder.getAnnotations();
  }

}