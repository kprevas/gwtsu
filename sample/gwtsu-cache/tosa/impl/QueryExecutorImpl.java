// Compiled from QueryExecutorImpl.gs
public tosa.impl.QueryExecutorImpl extends Object implements QueryExecutor, IGosuClassObject {
  internal IDatabase _db;

  public void <init>(IDatabase db) {
    this.<init>();

    this._db = db;
    return;
  }

  public static IDBObject buildObject(IDBType type, ResultSet resultSet) {
    obj = new CachedDBObject(type, false);
    table = type.getTable();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator(table.getColumns());

        column = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        column = ((IDBColumn) *temp0.next());

        resultObject = column.getColumnType().readFromResultSet(resultSet, ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), table.getName(), ".", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), column.getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
        obj.setColumnValue(column.getName(), resultObject);
      }
    }
    return obj;
  }

  public int count(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
    profiler = Util.newProfiler(profilerTag);
    profiler.start(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), sqlStatement, " (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Arrays.asList(parameters), TypeSystem.get(java.lang.String.class), TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
      *temp0 = new IType[1];
      *temp0[0] = TypeSystem.get(tosa.api.IPreparedStatementParameter.class);
      *temp0
    ]]), true, false, false)), ")", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    try {
      results = this._db.getDBExecutionKernel().executeSelect(sqlStatement, ((IQueryResultProcessor) TypeAsTransformer.coerceValue([[
        *temp1 = new block_0_(this);
        *temp1._returnType = TypeSystem.get(java.lang.Integer.class);
        *temp1
      ]], TypeSystem.getByFullName("tosa.api.IQueryResultProcessor", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = TypeSystem.get(java.lang.Integer.class);
        *temp2
      ]]), FunctionToInterfaceCoercer.instance())), parameters);
      if (results.size() == 0) {
        return *temp3;
      } else {
        if (results.size() == 1) {
          return *temp5;
        } else {
          throw new IllegalStateException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "Expected count query ", sqlStatement, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " to return 0 or 1 result, but got ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Integer.valueOf(results.size()), TypeSystem.get(java.lang.String.class), TypeSystem.get(int.class), true, false, false)));
        }
      }
    }
    finally {
      profiler.stop();
    }
  }

  public List selectEntity(String profilerTag, IDBType type$$unboxedParam, String sqlStatement, IPreparedStatementParameter; parameters) {
    type = [[
      *temp0 = new IDBType[1];
      *temp0[0] = type$$unboxedParam;
      *temp0
    ]];
    profiler = Util.newProfiler(profilerTag);
    profiler.start(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), sqlStatement, " (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Arrays.asList(parameters), TypeSystem.get(java.lang.String.class), TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
      *temp1 = new IType[1];
      *temp1[0] = TypeSystem.get(tosa.api.IPreparedStatementParameter.class);
      *temp1
    ]]), true, false, false)), ")", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    try {
      return *temp4;
    }
    finally {
      profiler.stop();
    }
  }

  public void update(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
    profiler = Util.newProfiler(profilerTag);
    profiler.start(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), sqlStatement, " (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Arrays.asList(parameters), TypeSystem.get(java.lang.String.class), TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
      *temp0 = new IType[1];
      *temp0[0] = TypeSystem.get(tosa.api.IPreparedStatementParameter.class);
      *temp0
    ]]), true, false, false)), ")", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    try {
      this._db.getDBExecutionKernel().executeUpdate(sqlStatement, parameters);
    }
    finally {
      profiler.stop();
    }
    return;
  }

  public Object insert(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
    profiler = Util.newProfiler(profilerTag);
    profiler.start(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), sqlStatement, " (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Arrays.asList(parameters), TypeSystem.get(java.lang.String.class), TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
      *temp0 = new IType[1];
      *temp0[0] = TypeSystem.get(tosa.api.IPreparedStatementParameter.class);
      *temp0
    ]]), true, false, false)), ")", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    try {
      return *temp1;
    }
    finally {
      profiler.stop();
    }
  }

  public void delete(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
    profiler = Util.newProfiler(profilerTag);
    profiler.start(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), sqlStatement, " (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Arrays.asList(parameters), TypeSystem.get(java.lang.String.class), TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
      *temp0 = new IType[1];
      *temp0[0] = TypeSystem.get(tosa.api.IPreparedStatementParameter.class);
      *temp0
    ]]), true, false, false)), ")", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    try {
      this._db.getDBExecutionKernel().executeDelete(sqlStatement, parameters);
    }
    finally {
      profiler.stop();
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