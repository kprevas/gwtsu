// Compiled from URLUtil.gs
public ronin.URLUtil extends Object implements IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public static String urlFor(MethodReference target) {
    prefix = Ronin.getCurrentRequest().getPrefix();
    httpsMethodAnnotation = [[
      *temp0 = target.getMethodInfo().getAnnotation(TypeSystem.getByFullName("ronin.HttpsOnly", "_default_"));
      (*temp0 == null ? ((Object) null) : *temp0.getInstance())
    ]];
    if (httpsMethodAnnotation != null) {
      prefix = ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "https", prefix.substring(prefix.indexOf(":")), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false));
    }
    httpsTypeAnnotation = [[
      *temp1 = target.getRootType().getTypeInfo().getAnnotation(TypeSystem.getByFullName("ronin.HttpsOnly", "_default_"));
      (*temp1 == null ? ((Object) null) : *temp1.getInstance())
    ]];
    if (httpsTypeAnnotation != null) {
      prefix = ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "https", prefix.substring(prefix.indexOf(":")), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false));
    }
    url = null;
    if (prefix != null) {
      url = new StringBuilder(prefix);
    } else {
      url = new StringBuilder();
    }
    url.append(target.getMethodInfo().getOwnersType().getRelativeName()).append("/").append(target.getMethodInfo().getDisplayName());
    if ([[
      *temp4 = [[
        *temp2 = target.getMethodInfo().getParameters();
        *temp3 = TypeSystem.get(gw.lang.reflect.IParameterInfo.class);
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreArrayEnhancement.isHasElements(*temp2, *temp3)
      ]];
      (*temp4 == null ? false : *temp4.booleanValue())
    ]]) {
      url.append("?");
      /*foreach*/
            *temp5 = target.getMethodInfo().getParameters();

            *temp6 = -1 + (*temp5 == null ? -1 : *temp5.length);

            *temp7 = -1;

            param = null;

            i = -1;

      if (*temp5 != null) {
        while (*temp7 != *temp6) {
          *temp7 = *temp7 + 1;

          param = *temp5[*temp7];

          i = i + 1;

          argValue = (target.getBoundValues() == null ? null : target.getBoundValues()[i]);
          if (param.getFeatureType().isArray()) {
            arrayType = param.getFeatureType();
            if (argValue != null) {
              arrayLength = arrayType.getArrayLength(argValue);
              /*foreach*/
                            *temp8 = ((AbstractIntIterator) ForEachStatementTransformer.makeIterator(IntervalExpressionTransformer._makeIntegerInterval(Integer.valueOf(0), Integer.valueOf(arrayLength), Integer.valueOf(1), true, false)));

                            j = 0;

              if (*temp8 != null) {
                while (*temp8.hasNext()) {
                  j = *temp8.nextInt();

                  componentValue = arrayType.getArrayComponent(argValue, j);
                  if (componentValue != null) {
                    if ((i > 0 || j > 0)) {
                      url.append("&");
                    }
                    stringValue = URLUtil.getStringValue(componentValue);
                    url.append(URLEncoder.encode(param.getName(), "UTF-8")).append("[").append(j).append("]").append("=").append(URLEncoder.encode(stringValue.toString(), "UTF-8"));
                  }
                }
              }
            }
          } else {
            if (argValue != null) {
              if (i > 0) {
                url.append("&");
              }
              stringValue = URLUtil.getStringValue(argValue);
              url.append(URLEncoder.encode(param.getName(), "UTF-8")).append("=").append(URLEncoder.encode(stringValue.toString(), "UTF-8"));
            }
          }
        }
      }
    }
    return url.toString();
  }

  internal static String getStringValue(Object argValue) {
    stringValue = null;
    idMethod = [[
      *temp0 = argValue;
      (*temp0 == null ? TypeSystem.get(void.class) : GosuRuntimeMethods.typeof(*temp0))
    ]].getTypeInfo().getMethod("toId", new IType[0]);
    if (idMethod != null) {
      return ((String) TypeAsTransformer.coerceValue((idMethod.getCallHandler().handleCall(argValue, new Object[0]) != null ? idMethod.getCallHandler().handleCall(argValue, new Object[0]) : "null"), TypeSystem.get(java.lang.String.class), StringCoercer.instance()));
    } else {
      return ((String) TypeAsTransformer.coerceValue((argValue != null ? argValue : "null"), TypeSystem.get(java.lang.String.class), StringCoercer.instance()));
    }
  }

  public static String baseUrlFor(MethodReference target) {
    return [[
      *temp0 = new StringBuilder();
      *temp0.append((Ronin.getCurrentRequest().getPrefix() != null ? Ronin.getCurrentRequest().getPrefix() : ""));
      *temp0.append(target.getMethodInfo().getOwnersType().getRelativeName());
      *temp0.append("/");
      *temp0.append(target.getMethodInfo().getDisplayName());
      *temp0.toString()
    ]];
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}