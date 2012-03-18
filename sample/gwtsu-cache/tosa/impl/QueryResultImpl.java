// Compiled from QueryResultImpl.gs
public tosa.impl.QueryResultImpl extends Object implements QueryResult, IGosuClassObject {
  public String _profilerTag;
  public String _originalQuery;
  public IPreparedStatementParameter; _parameters;
  public IDatabase _db;
  public IQueryResultProcessor _resultProcessor;
  public List _results;
  public List _orderBys;
  public PagingInfo _pagingInfo;
  internal final synthetic IType typeparam$T;

  public void <init>(IType typeparam$T, String profilerTag, String originalQuery, IPreparedStatementParameter; parameters, IDatabase db, IQueryResultProcessor resultProcessor) {
    this.typeparam$T = typeparam$T;
    this.<init>();








    this._profilerTag = profilerTag;
    this._originalQuery = originalQuery;
    this._parameters = parameters;
    this._db = db;
    this._resultProcessor = resultProcessor;
    this._orderBys = new ArrayList();
    return;
  }

  public int getCount() {
    if (this._pagingInfo != null) {
      throw new UnsupportedOperationException("Tosa can't currently do a count on a paged query");
    } else {
      this.maybeLoadResults();
      return this._results.size();
    }
  }

  public int size() {
    return this.getCount();
  }

  public QueryResult orderBySql(String sql) {
    if (sql.toLowerCase().trim().startsWith("order by")) {
      throw new IllegalArgumentException("orderBySql should never be called with sql starting with ORDER BY:  the ORDER BY keywords will automatically be added in when the query is generated");
    }
    this._results = null;
    this._orderBys.add(new OrderByInfo(this, sql));
    return this;
  }

  public QueryResult orderBy(IPropertyReference sortColumn, SortDirection sortDirection) {
    this._results = null;
    this._orderBys.add(new OrderByInfo(this, sortColumn, sortDirection));
    return this;
  }

  public QueryResult clearOrderBys() {
    this._results = null;
    this._orderBys.clear();
    return this;
  }

  public QueryResult page(int startPage, int pageSize, int startOffset) {
    if ((startOffset > 0 && startPage > 0)) {
      *temp0 = "the call to page can specify either startPage or startOffset, but not both";
      if ((*temp0 instanceof Throwable)) {
        throw ((Throwable) *temp0);
      } else {
        throw new EvaluationException(((String) *temp0));
      }
    }
    if ((startOffset < 0 || startPage < 0)) {
      *temp1 = "startOffset and startPage are not allowed to have negative values";
      if ((*temp1 instanceof Throwable)) {
        throw ((Throwable) *temp1);
      } else {
        throw new EvaluationException(((String) *temp1));
      }
    }
    if (pageSize <= 0) {
      *temp2 = "pageSize must be a value greater than 0";
      if ((*temp2 instanceof Throwable)) {
        throw ((Throwable) *temp2);
      } else {
        throw new EvaluationException(((String) *temp2));
      }
    }
    realStartOffset = (startOffset > 0 ? startOffset : pageSize * startPage);
    this._pagingInfo = new PagingInfo(this, realStartOffset, pageSize);
    this._results = null;
    return this;
  }

  public List loadPage(int pageNumber) {
    if (this._pagingInfo == null) {
      this.maybeLoadResults();
      return new ArrayList(this._results);
    } else {
      if (this._pagingInfo._currentOffset != pageNumber * this._pagingInfo._pageSize) {
        this._results = null;
        this._pagingInfo._currentOffset = pageNumber * this._pagingInfo._pageSize;
      }
      this.maybeLoadResults();
      return new ArrayList(this._results);
    }
  }

  public QueryResult clearPaging() {
    this._results = null;
    this._pagingInfo = null;
    return this;
  }

  public QueryResult clear() {
    this._results = null;
    this._pagingInfo = null;
    this._orderBys.clear();
    return this;
  }

  public QueryResult clone() {
    clone = new QueryResultImpl(this.typeparam$T, this._profilerTag, this._originalQuery, this._parameters, this._db, this._resultProcessor);
    clone._orderBys.addAll(this._orderBys);
    if (this._pagingInfo != null) {
      clone._pagingInfo = this._pagingInfo.clone();
    }
    return clone;
  }

  public Iterator iterator() {
    if (this._pagingInfo != null) {
      this.resetToInitialOffset();
      this.maybeLoadResults();
      return new PagingIterator(this, this._results.iterator());
    } else {
      this.maybeLoadResults();
      return this._results.iterator();
    }
  }

  public Object get(int idx) {
    if (this._pagingInfo != null) {
      if (this._pagingInfo.containsIndex(idx)) {
        this.maybeLoadResults();
        return this._results.get(idx - this._pagingInfo._currentOffset - this._pagingInfo._startOffset);
      } else {
        this._pagingInfo.setToPageForIndex(idx);
        this._results = null;
        return this.get(idx);
      }
    } else {
      this.maybeLoadResults();
      return this._results.get(idx);
    }
  }

  internal void maybeLoadResults() {
    if (this._results == null) {
      this._results = this.executeQuery();
    }
    return;
  }

  internal SqlAndParams computeSql() {
    query = this._originalQuery;
    parameters = this._parameters;
    if (!(this._orderBys.isEmpty())) {
      query = ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), query, this.buildOrderByClause(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false));
    }
    if (this._pagingInfo != null) {
      query = ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), query, " LIMIT ? OFFSET ?", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false));
      newParameters = new ArrayList(Arrays.asList(parameters));
      newParameters.add(new PreparedStatementParameterImpl(((Integer) TypeAsTransformer.coerceValue(Integer.valueOf(this._pagingInfo.getPageSize()), TypeSystem.get(java.lang.Integer.class), ((ICoercer) [[
        *temp0 = BasePrimitiveCoercer.IntPCoercer;
        *temp0.get()
      ]]))), Types.BIGINT));
      newParameters.add(new PreparedStatementParameterImpl(((Integer) TypeAsTransformer.coerceValue(Integer.valueOf(this._pagingInfo.getCurrentOffset()), TypeSystem.get(java.lang.Integer.class), ((ICoercer) [[
        *temp1 = BasePrimitiveCoercer.IntPCoercer;
        *temp1.get()
      ]]))), Types.BIGINT));
      parameters = ((IPreparedStatementParameter;) [[
        *temp2 = newParameters;
        *temp3 = TypeSystem.get(tosa.api.IPreparedStatementParameter.class);
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.toTypedArray(*temp2, *temp3)
      ]]);
    }
    return new SqlAndParams(query, parameters);
  }

  internal String buildOrderByClause() {
    result = new StringBuilder();
    result.append(" ORDER BY ");
    result.append([[
      *temp5 = [[
        *temp1 = this._orderBys;
        *temp2 = TypeSystem.getByFullName("tosa.impl.QueryResultImpl.OrderByInfo", "_default_");
        *temp3 = TypeSystem.get(java.lang.String.class);
        *temp4 = [[
          *temp0 = new block_0_(this);
          *temp0._returnType = TypeSystem.get(java.lang.String.class);
          *temp0
        ]];
        if (*temp1 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.map(*temp1, *temp2, *temp3, *temp4)
      ]];
      *temp6 = TypeSystem.get(java.lang.String.class);
      *temp7 = ", ";
      if (*temp5 == null) {
        throw new NullPointerException();
      }
      CoreIterableEnhancement.join(*temp5, *temp6, *temp7)
    ]]);
    return result.toString();
  }

  internal List executeQuery() {
    profiler = Util.newProfiler(this._profilerTag);
    sqlAndParameters = this.computeSql();
    profiler.start(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), sqlAndParameters.getSql(), " (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Arrays.asList(sqlAndParameters.getParams()), TypeSystem.get(java.lang.String.class), TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
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

  internal void incrementPage() {
    this._pagingInfo.incrementPage();
    this._results = null;
    return;
  }

  internal void resetToInitialOffset() {
    if (this._pagingInfo._currentOffset != this._pagingInfo._startOffset) {
      this._results = null;
      this._pagingInfo.resetToInitialOffset();
    }
    return;
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
    return builder.getAnnotations();
  }

}