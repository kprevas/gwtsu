// Compiled from SqlStringSubstituter.gs
public tosa.impl.query.SqlStringSubstituter extends Object implements IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public static SqlAndParams substitute(String query, Map values$$unboxedParam) {
    values = [[
      *temp0 = new Map[1];
      *temp0[0] = values$$unboxedParam;
      *temp0
    ]];
    paramValues = [[
      *temp1 = new ArrayList[1];
      *temp1[0] = new ArrayList();
      *temp1
    ]];
    result = StringSubstituter.substitute(query, ((StringSubstituter$TokenHandler) TypeAsTransformer.coerceValue([[
      *temp2 = new block_0_(paramValues, values);
      *temp2._returnType = TypeSystem.get(java.lang.String.class);
      *temp2
    ]], TypeSystem.get(tosa.impl.util.StringSubstituter$TokenHandler.class), FunctionToInterfaceCoercer.instance())));
    return new SqlAndParams(result, paramValues[0].toArray());
  }

  internal static String substituteToken(String token, Map tokenValues, List paramValues) {
    if (!(tokenValues.containsKey(token))) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "No value for the token ", token, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " was found in the map", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    }
    value = tokenValues.get(token);
    if ([[
      *temp0 = value;
      (*temp0 == null ? false : TypeSystem.get(tosa.api.IDBTable.class).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      return [[
        *temp1 = value;
        ((*temp1 instanceof IDBTable) ? ((IDBTable) *temp1) : ((IDBTable) TypeAsTransformer.coerceValue(*temp1, TypeSystem.get(tosa.api.IDBTable.class), RuntimeCoercer.instance())))
      ]].getPossiblyQuotedName();
    } else {
      if ([[
        *temp2 = value;
        (*temp2 == null ? false : TypeSystem.get(tosa.api.IDBColumn.class).isAssignableFrom(TypeSystem.getFromObject(*temp2)))
      ]]) {
        return [[
          *temp3 = value;
          ((*temp3 instanceof IDBColumn) ? ((IDBColumn) *temp3) : ((IDBColumn) TypeAsTransformer.coerceValue(*temp3, TypeSystem.get(tosa.api.IDBColumn.class), RuntimeCoercer.instance())))
        ]].getPossiblyQuotedName();
      } else {
        paramValues.add(value);
        return "?";
      }
    }
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}