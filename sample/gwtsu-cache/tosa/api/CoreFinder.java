// Compiled from CoreFinder.gs
public tosa.api.CoreFinder extends Object implements IGosuClassObject {
  public IDBType _dbType;
  internal final synthetic IType typeparam$T;

  public void <init>(IType typeparam$T, IDBType dbType) {
    this.typeparam$T = typeparam$T;
    this.<init>();

    this._dbType = dbType;
    return;
  }

  internal static SqlAndParams subIfNecessary(String sql, String prefix, Map params) {
    queryString = null;
    paramArray = null;
    if ((sql == null || sql.isEmpty())) {
      queryString = prefix;
      paramArray = new Object[0];
    } else {
      if (params != null) {
        query = CoreFinder.sub(sql, params);
        queryString = (prefix == null ? query.getSql() : ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), prefix, " WHERE ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), query.getSql(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
        paramArray = query.getParams();
      } else {
        queryString = (prefix == null ? sql : ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), prefix, " WHERE ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), sql, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
        paramArray = new Object[0];
      }
    }
    return new SqlAndParams(queryString, paramArray);
  }

  internal static SqlAndParams sub(String input, Map tokenValues) {
    return SqlStringSubstituter.substitute(input, tokenValues);
  }

  internal static SqlAndParams buildWhereClause(IDBObject template) {
    clauses = new ArrayList();
    params = new ArrayList();
    if (template != null) {
      /*foreach*/
            *temp0 = ForEachStatementTransformer.makeIterator(template.getDBTable().getColumns());

            column = null;

      if (*temp0 != null) {
        while (*temp0.hasNext()) {
          column = ((IDBColumn) *temp0.next());

          value = template.getColumnValue(column.getName());
          if (value != null) {
            result = CoreFinder.sub(":column = :value", [[
              *temp1 = new HashMap();
              *temp1.put("column", column);
              *temp1.put("value", value);
              *temp1
            ]]);
            clauses.add(result.getSql());
            params.add(result.getParamObjects()[0]);
          }
        }
      }
    }
    if (!(clauses.isEmpty())) {
      return new SqlAndParams(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), " WHERE ", [[
        *temp2 = clauses;
        *temp3 = TypeSystem.get(java.lang.String.class);
        *temp4 = " AND ";
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.join(*temp2, *temp3, *temp4)
      ]], TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), [[
        *temp5 = params;
        *temp6 = TypeSystem.get(java.lang.Object.class);
        if (*temp5 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.toTypedArray(*temp5, *temp6)
      ]]);
    } else {
      return new SqlAndParams(" WHERE 1 = 1", [[
        *temp7 = params;
        *temp8 = TypeSystem.get(java.lang.Object.class);
        if (*temp7 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.toTypedArray(*temp7, *temp8)
      ]]);
    }
  }

  public static CachedDBObject buildObject(IDBType type, ResultSet resultSet) {
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

  public IDBObject fromId(long id) {
    table = this._dbType.getTable();
    idColumn = table.getColumn("id");
    query = CoreFinder.sub("SELECT * FROM :table WHERE :id_column = :id", [[
      *temp0 = new HashMap();
      *temp0.put("table", table);
      *temp0.put("id_column", idColumn);
      *temp0.put("id", ((Long) TypeAsTransformer.coerceValue(Long.valueOf(id), TypeSystem.get(java.lang.Long.class), ((ICoercer) [[
        *temp1 = BasePrimitiveCoercer.LongPCoercer;
        *temp1.get()
      ]]))));
      *temp0
    ]]);
    results = this.selectEntity(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".fromId()", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), query.getSql(), query.getParams());
    if (results.getCount() == 0) {
      return null;
    } else {
      if (results.getCount() == 1) {
        return ((IDBObject) TypeAsTransformer.coerceValue(((IDBObject) results.get(0)), this.typeparam$T, TypeVariableCoercer.instance()));
      } else {
        *temp3 = [[
          *temp2 = new StringBuilder();
          *temp2.append("More than one row in table ");
          *temp2.append(table.getName());
          *temp2.append(" had id ");
          *temp2.append(((String) TypeAsTransformer.coerceValue(Long.valueOf(id), TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
          *temp2.toString()
        ]];
        if ((*temp3 instanceof Throwable)) {
          throw ((Throwable) *temp3);
        } else {
          throw new EvaluationException(((String) *temp3));
        }
      }
    }
  }

  public long count(String sql, Map params) {
    if (!(sql.startsWith("SELECT count(*) as count"))) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "The count(String, Map) method must always be called with 'SELECT count(*) as count FROM' as the start of the statement.  The sql passed in was ", sql, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    }
    countArgs = CoreFinder.subIfNecessary(sql, ((String) null), params);
    return this.countImpl(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".count(String, Map)", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), countArgs.getSql(), countArgs.getParams());
  }

  public long countWhere(String sql, Map params) {
    if ((sql != null && sql.toUpperCase().startsWith("SELECT"))) {
      throw new IllegalArgumentException("The countWhere(String, Map) method should only be called with the WHERE clause of a query.  To specify the full SQL for the query, use the count(String, Map) method instead.");
    }
    queryPrefix = CoreFinder.sub("SELECT count(*) as count FROM :table", [[
      *temp0 = new HashMap();
      *temp0.put("table", this._dbType.getTable());
      *temp0
    ]]).getSql();
    countArgs = CoreFinder.subIfNecessary(sql, queryPrefix, params);
    return this.countImpl(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".countWhere(String, Map)", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), countArgs.getSql(), countArgs.getParams());
  }

  public long countAll() {
    sql = CoreFinder.sub("SELECT count(*) as count FROM :table", [[
      *temp0 = new HashMap();
      *temp0.put("table", this._dbType.getTable());
      *temp0
    ]]).getSql();
    return this.countImpl(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".countAll()", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), sql, new IPreparedStatementParameter[0]);
  }

  public long countLike(IDBObject template) {
    queryPrefix = CoreFinder.sub("SELECT count(*) as count FROM :table", [[
      *temp0 = new HashMap();
      *temp0.put("table", this._dbType.getTable());
      *temp0
    ]]).getSql();
    whereClause = CoreFinder.buildWhereClause(template);
    return this.countImpl(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".countLike(", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._dbType.getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), ")", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), queryPrefix, whereClause.getSql(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), whereClause.getParams());
  }

  public QueryResult select(String sql, Map params) {
    if (!(sql.toUpperCase().startsWith("SELECT * FROM"))) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "The select(String, Map) method must always be called with 'SELECT * FROM' as the start of the statement.  The sql passed in was ", sql, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    }
    selectArgs = CoreFinder.subIfNecessary(sql, ((String) null), params);
    return [[
      *temp0 = this.selectEntity(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".select(String, Map)", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), selectArgs.getSql(), selectArgs.getParams());
      ((*temp0 instanceof QueryResult) ? ((QueryResult) *temp0) : ((QueryResult) TypeAsTransformer.coerceValue(*temp0, TypeSystem.getByFullName("tosa.api.QueryResult", "_default_").getParameterizedType([[
        *temp1 = new IType[1];
        *temp1[0] = this.typeparam$T;
        *temp1
      ]]), RuntimeCoercer.instance())))
    ]];
  }

  public QueryResult selectWhere(String sql, Map params) {
    if ((sql != null && sql.toUpperCase().startsWith("SELECT"))) {
      throw new IllegalArgumentException("The selectWhere(String, Map) method should only be called with the WHERE clause of a query.  To specify the full SQL for the query, use the select(String, Map) method instead.");
    }
    queryPrefix = CoreFinder.sub("SELECT * FROM :table", [[
      *temp0 = new HashMap();
      *temp0.put("table", this._dbType.getTable());
      *temp0
    ]]).getSql();
    selectArgs = CoreFinder.subIfNecessary(sql, queryPrefix, params);
    return [[
      *temp1 = this.selectEntity(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".selectWhere(String, Map)", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), selectArgs.getSql(), selectArgs.getParams());
      ((*temp1 instanceof QueryResult) ? ((QueryResult) *temp1) : ((QueryResult) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("tosa.api.QueryResult", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = this.typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance())))
    ]];
  }

  public QueryResult selectLike(IDBObject template) {
    queryPrefix = CoreFinder.sub("SELECT * FROM :table", [[
      *temp0 = new HashMap();
      *temp0.put("table", this._dbType.getTable());
      *temp0
    ]]).getSql();
    whereClause = CoreFinder.buildWhereClause(template);
    return [[
      *temp1 = this.selectEntity(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".selectWhere(String, Map)", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), queryPrefix, whereClause.getSql(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), whereClause.getParams());
      ((*temp1 instanceof QueryResult) ? ((QueryResult) *temp1) : ((QueryResult) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("tosa.api.QueryResult", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = this.typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance())))
    ]];
  }

  public QueryResult selectAll() {
    sql = CoreFinder.sub("SELECT * FROM :table", [[
      *temp0 = new HashMap();
      *temp0.put("table", this._dbType.getTable());
      *temp0
    ]]).getSql();
    return [[
      *temp1 = this.selectEntity(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._dbType.getName(), ".selectAll()", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), sql, new IPreparedStatementParameter[0]);
      ((*temp1 instanceof QueryResult) ? ((QueryResult) *temp1) : ((QueryResult) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("tosa.api.QueryResult", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = this.typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance())))
    ]];
  }

  internal long countImpl(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
    profiler = Util.newProfiler(profilerTag);
    profiler.start(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), sqlStatement, " (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Arrays.asList(parameters), TypeSystem.get(java.lang.String.class), TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
      *temp0 = new IType[1];
      *temp0[0] = TypeSystem.get(tosa.api.IPreparedStatementParameter.class);
      *temp0
    ]]), true, false, false)), ")", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    try {
      results = this._dbType.getTable().getDatabase().getDBExecutionKernel().executeSelect(sqlStatement, ((IQueryResultProcessor) TypeAsTransformer.coerceValue([[
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

  internal QueryResult selectEntity(String profilerTag, String sqlStatement, IPreparedStatementParameter; parameters) {
    return new QueryResultImpl(TypeSystem.get(tosa.api.IDBObject.class), profilerTag, sqlStatement, parameters, this._dbType.getTable().getDatabase(), ((IQueryResultProcessor) TypeAsTransformer.coerceValue([[
      *temp0 = new block_1_(this);
      *temp0._returnType = TypeSystem.get(tosa.impl.CachedDBObject.class);
      *temp0
    ]], TypeSystem.getByFullName("tosa.api.IQueryResultProcessor", "_default_").getParameterizedType([[
      *temp1 = new IType[1];
      *temp1[0] = TypeSystem.get(tosa.api.IDBObject.class);
      *temp1
    ]]), FunctionToInterfaceCoercer.instance())));
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this).getParameterizedType([[
      *temp0 = new IType[1];
      *temp0[0] = this.typeparam$T;
      *temp0
    ]]);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("fromId(long)");
    builder.addGosuAnnotation(new Param("dbType", "the implicit dbType initial argument"));
    builder.addGosuAnnotation(new Param("id", "the id to load"));
    return builder.getAnnotations();
  }

}