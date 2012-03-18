// Compiled from CachedDBObjectDelegateImpl.gs
public tosa.impl.CachedDBObjectDelegateImpl extends Object implements CachedDBObject$Delegate, IGosuClassObject {
  internal Map _columns;
  internal Map _cachedFks;
  internal Map _cachedArrays;
  internal QueryExecutor _queryExecutor;
  internal IDBType _type;
  internal boolean _new;
  internal CachedDBObject _owner;

  public void <init>(IDBType type, boolean isNew, CachedDBObject owner) {
    this.<init>();







    this._type = type;
    this._new = isNew;
    this._owner = owner;
    this._columns = new HashMap();
    this._cachedFks = new HashMap();
    this._cachedArrays = new HashMap();
    this._queryExecutor = new QueryExecutorImpl(this._type.getTable().getDatabase());
    return;
  }

  internal String getTableName() {
    return this.getDBTable().getName();
  }

  public IDBTable getDBTable() {
    return this._type.getTable();
  }

  public Long getId() {
    return [[
      *temp0 = this.getColumnValue(DBTypeInfo.ID_COLUMN);
      ((*temp0 instanceof Long) ? ((Long) *temp0) : ((Long) TypeAsTransformer.coerceValue(*temp0, TypeSystem.get(java.lang.Long.class), RuntimeCoercer.instance())))
    ]];
  }

  public boolean isNew() {
    return this._new;
  }

  public Object getColumnValue(String columnName) {
    return this._columns.get(columnName);
  }

  public void setColumnValue(String columnName, Object value) {
    this._columns.put(columnName, value);
    return;
  }

  public IDBObject getFkValue(String columnName) {
    column = this.getAndValidateFkColumn(columnName);
    fkObject = ((IDBObject) this._cachedFks.get(columnName));
    if (fkObject != null) {
      return fkObject;
    }
    fkID = [[
      *temp0 = this._columns.get(columnName);
      ((*temp0 instanceof Long) ? ((Long) *temp0) : ((Long) TypeAsTransformer.coerceValue(*temp0, TypeSystem.get(java.lang.Long.class), RuntimeCoercer.instance())))
    ]];
    if (fkID == null) {
      return null;
    }
    fkObject = this.loadEntity(column.getFKTarget(), fkID);
    if (fkObject == null) {
      throw new IllegalStateException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "Column ", columnName, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " on table ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._type.getTable().getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " has a value of ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), fkID, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.Long.class), true, false, false)), ", but no corresponding row was found in the database", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    }
    return fkObject;
  }

  public void setFkValue(String columnName, IDBObject value) {
    column = this.getAndValidateFkColumn(columnName);
    if (value == null) {
      this._columns.put(columnName, null);
      this._cachedFks.put(columnName, null);
    } else {
      this._columns.put(columnName, value.getId());
      this._cachedFks.put(columnName, value);
    }
    return;
  }

  internal IDBColumn getAndValidateFkColumn(String columnName) {
    column = this._type.getTable().getColumn(columnName);
    if (column == null) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "Column name ", columnName, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " is not a valid column on the  ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._type.getTable().getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " table", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    }
    if (!(column.isFK())) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "Column ", columnName, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " on table ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._type.getTable().getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " is not a foreign key", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    }
    return column;
  }

  public EntityCollection getArray(String arrayName) {
    return this.getArray(this._type.getTable().getArray(arrayName));
  }

  public EntityCollection getArray(IDBArray dbArray) {
    result = ((EntityCollection) this._cachedArrays.get(dbArray.getPropertyName()));
    if (result == null) {
      if ([[
        *temp0 = dbArray;
        (*temp0 == null ? false : TypeSystem.get(tosa.api.IDBFkArray.class).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
      ]]) {
        fkColumn = [[
          *temp1 = dbArray;
          ((*temp1 instanceof IDBFkArray) ? ((IDBFkArray) *temp1) : ((IDBFkArray) TypeAsTransformer.coerceValue(*temp1, TypeSystem.get(tosa.api.IDBFkArray.class), RuntimeCoercer.instance())))
        ]].getFkColumn();
        fkType = [[
          *temp2 = TypeSystem.getByFullName(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), fkColumn.getTable().getDatabase().getNamespace(), ".", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), fkColumn.getTable().getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
          ((*temp2 instanceof IDBType) ? ((IDBType) *temp2) : ((IDBType) TypeAsTransformer.coerceValue(*temp2, TypeSystem.get(tosa.loader.IDBType.class), RuntimeCoercer.instance())))
        ]];
        result = new ReverseFkEntityCollectionImpl(TypeSystem.get(tosa.api.IDBObject.class), this._owner, fkType, fkColumn, new QueryExecutorImpl(fkColumn.getTable().getDatabase()));
      } else {
        if ([[
          *temp3 = dbArray;
          (*temp3 == null ? false : TypeSystem.get(tosa.api.IDBJoinArray.class).isAssignableFrom(TypeSystem.getFromObject(*temp3)))
        ]]) {
          targetType = [[
            *temp5 = TypeSystem.getByFullName(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this.getDBTable().getDatabase().getNamespace(), ".", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), [[
              *temp4 = dbArray;
              ((*temp4 instanceof IDBJoinArray) ? ((IDBJoinArray) *temp4) : ((IDBJoinArray) TypeAsTransformer.coerceValue(*temp4, TypeSystem.get(tosa.api.IDBJoinArray.class), RuntimeCoercer.instance())))
            ]].getTargetTable().getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
            ((*temp5 instanceof IDBType) ? ((IDBType) *temp5) : ((IDBType) TypeAsTransformer.coerceValue(*temp5, TypeSystem.get(tosa.loader.IDBType.class), RuntimeCoercer.instance())))
          ]];
          result = new JoinArrayEntityCollectionImpl(TypeSystem.get(tosa.api.IDBObject.class), this._owner, targetType, [[
            *temp6 = dbArray;
            ((*temp6 instanceof IDBJoinArray) ? ((IDBJoinArray) *temp6) : ((IDBJoinArray) TypeAsTransformer.coerceValue(*temp6, TypeSystem.get(tosa.api.IDBJoinArray.class), RuntimeCoercer.instance())))
          ]].getSrcColumn(), [[
            *temp7 = dbArray;
            ((*temp7 instanceof IDBJoinArray) ? ((IDBJoinArray) *temp7) : ((IDBJoinArray) TypeAsTransformer.coerceValue(*temp7, TypeSystem.get(tosa.api.IDBJoinArray.class), RuntimeCoercer.instance())))
          ]].getTargetColumn(), new QueryExecutorImpl(this.getDBTable().getDatabase()));
        }
      }
      this._cachedArrays.put(dbArray.getPropertyName(), result);
    }
    return result;
  }

  public Long toID() {
    return this.getId();
  }

  public Map getColumns() {
    return this._columns;
  }

  public void update() {
    columnValues = this.gatherChangedValues();
    if (this._new) {
      columns = new ArrayList();
      values = new ArrayList();
      parameters = new IPreparedStatementParameter[columnValues.size()];
      /*foreach*/
            *temp0 = ForEachStatementTransformer.makeIterator(columnValues);

            pair = null;

            i = -1;

      if (*temp0 != null) {
        while (*temp0.hasNext()) {
          pair = ((ColumnValuePair) *temp0.next());

          i = i + 1;

          columns.add(pair._column);
          values.add("?");
          parameters[i] = pair._parameter;
        }
      }
      query = SimpleSqlBuilder.substitute("INSERT INTO ${table} (${columns}) VALUES (${values})", "table", this.getDBTable(), "columns", columns, "values", values);
      id = this._queryExecutor.insert(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._type.getName(), ".update()", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), query, parameters);
      if (id != null) {
        this._columns.put(DBTypeInfo.ID_COLUMN, id);
        this._new = false;
      }
    } else {
      values = new StringBuilder();
      params = new ArrayList();
      /*foreach*/
            *temp1 = ((AbstractIntIterator) ForEachStatementTransformer.makeIterator(IntervalExpressionTransformer._makeIntegerInterval(Integer.valueOf(0), Integer.valueOf(columnValues.size()), Integer.valueOf(1), true, false)));

            i = 0;

      if (*temp1 != null) {
        while (*temp1.hasNext()) {
          i = *temp1.nextInt();

          if (i > 0) {
            values.append(", ");
          }
          values.append(SimpleSqlBuilder.substitute("${column} = ?", "column", ((ColumnValuePair) columnValues.get(i))._column));
          params.add(((ColumnValuePair) columnValues.get(i))._parameter);
        }
      }
      idColumn = this.getDBTable().getColumn(DBTypeInfo.ID_COLUMN);
      params.add(idColumn.wrapParameterValue(this.getId()));
      query = SimpleSqlBuilder.substitute("UPDATE ${table} SET ${values} WHERE ${idColumn} = ?", "table", this.getDBTable(), "values", values.toString(), "idColumn", idColumn);
      this._queryExecutor.update(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._type.getName(), ".update()", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), query, ((IPreparedStatementParameter;) params.toArray(new IPreparedStatementParameter[params.size()])));
    }
    return;
  }

  internal List gatherChangedValues() {
    columnValues = new ArrayList();
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator(this.getDBTable().getColumns());

        column = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        column = ((IDBColumn) *temp0.next());

        if (this._columns.containsKey(column.getName())) {
          columnValues.add(new ColumnValuePair(column, column.wrapParameterValue(this._columns.get(column.getName()))));
        }
      }
    }
    return columnValues;
  }

  public void delete() {
    idColumn = this.getDBTable().getColumn(DBTypeInfo.ID_COLUMN);
    query = SimpleSqlBuilder.substitute("DELETE FROM ${table} WHERE ${idColumn} = ?", "table", this.getDBTable(), "idColumn", idColumn);
    this._queryExecutor.delete(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), this._type.getName(), ".delete()", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), query, [[
      *temp0 = new IPreparedStatementParameter[1];
      *temp0[0] = idColumn.wrapParameterValue(this.getId());
      *temp0
    ]]);
    return;
  }

  public String toString() {
    return this._columns.toString();
  }

  public int hashCode() {
    hashCode = this._type.hashCode();
    keys = new ArrayList(this._columns.keySet());
    Collections.sort(keys);
    /*foreach*/
        *temp0 = ForEachStatementTransformer.makeIterator(keys);

        columnName = null;

    if (*temp0 != null) {
      while (*temp0.hasNext()) {
        columnName = ((String) *temp0.next());

        if (this._columns.get(columnName) != null) {
          hashCode = hashCode * 17 + this._columns.get(columnName).hashCode();
        } else {
          hashCode = hashCode * 17;
        }
      }
    }
    return hashCode;
  }

  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if ([[
      *temp0 = obj;
      (*temp0 == null ? false : TypeSystem.getByFullName("tosa.impl.CachedDBObjectDelegateImpl", "_default_").isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      if (this._type.equals([[
        *temp1 = obj;
        ((*temp1 instanceof CachedDBObjectDelegateImpl) ? ((CachedDBObjectDelegateImpl) *temp1) : ((CachedDBObjectDelegateImpl) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("tosa.impl.CachedDBObjectDelegateImpl", "_default_"), RuntimeCoercer.instance())))
      ]]._type)) {
        /*foreach*/
                *temp2 = ForEachStatementTransformer.makeIterator(this._columns.keySet());

                columnName = null;

        if (*temp2 != null) {
          while (*temp2.hasNext()) {
            columnName = ((String) *temp2.next());

            if (this._columns.get(columnName) != null) {
              if (!(this._columns.get(columnName).equals([[
                *temp3 = obj;
                ((*temp3 instanceof CachedDBObjectDelegateImpl) ? ((CachedDBObjectDelegateImpl) *temp3) : ((CachedDBObjectDelegateImpl) TypeAsTransformer.coerceValue(*temp3, TypeSystem.getByFullName("tosa.impl.CachedDBObjectDelegateImpl", "_default_"), RuntimeCoercer.instance())))
              ]]._columns.get(columnName)))) {
                return false;
              }
            }
          }
        }
        /*foreach*/
                *temp5 = ForEachStatementTransformer.makeIterator([[
          *temp4 = obj;
          ((*temp4 instanceof CachedDBObjectDelegateImpl) ? ((CachedDBObjectDelegateImpl) *temp4) : ((CachedDBObjectDelegateImpl) TypeAsTransformer.coerceValue(*temp4, TypeSystem.getByFullName("tosa.impl.CachedDBObjectDelegateImpl", "_default_"), RuntimeCoercer.instance())))
        ]]._columns.keySet());

                columnName = null;

        if (*temp5 != null) {
          while (*temp5.hasNext()) {
            columnName = ((String) *temp5.next());

            if ([[
              *temp6 = obj;
              ((*temp6 instanceof CachedDBObjectDelegateImpl) ? ((CachedDBObjectDelegateImpl) *temp6) : ((CachedDBObjectDelegateImpl) TypeAsTransformer.coerceValue(*temp6, TypeSystem.getByFullName("tosa.impl.CachedDBObjectDelegateImpl", "_default_"), RuntimeCoercer.instance())))
            ]]._columns.get(columnName) != null) {
              if (!([[
                *temp7 = obj;
                ((*temp7 instanceof CachedDBObjectDelegateImpl) ? ((CachedDBObjectDelegateImpl) *temp7) : ((CachedDBObjectDelegateImpl) TypeAsTransformer.coerceValue(*temp7, TypeSystem.getByFullName("tosa.impl.CachedDBObjectDelegateImpl", "_default_"), RuntimeCoercer.instance())))
              ]]._columns.get(columnName).equals(this._columns.get(columnName)))) {
                return false;
              }
            }
          }
        }
        return true;
      }
    }
    return false;
  }

  internal IDBObject loadEntity(IDBTable table, Long id) {
    idColumn = table.getColumn(DBTypeInfo.ID_COLUMN);
    resultType = [[
      *temp0 = TypeSystem.getByFullName(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), table.getDatabase().getNamespace(), ".", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), table.getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
      ((*temp0 instanceof IDBType) ? ((IDBType) *temp0) : ((IDBType) TypeAsTransformer.coerceValue(*temp0, TypeSystem.get(tosa.loader.IDBType.class), RuntimeCoercer.instance())))
    ]];
    sql = SimpleSqlBuilder.substitute("SELECT * FROM ${table} WHERE ${idColumn} = ?", "table", table, "idColumn", idColumn);
    param = idColumn.wrapParameterValue(id);
    results = new QueryExecutorImpl(table.getDatabase()).selectEntity("CachedDBObject.loadEntity()", resultType, sql, [[
      *temp1 = new IPreparedStatementParameter[1];
      *temp1[0] = param;
      *temp1
    ]]);
    if (results.isEmpty()) {
      return null;
    } else {
      if (results.size() == 1) {
        return ((IDBObject) results.get(0));
      } else {
        throw new IllegalStateException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "Expected to get one result back from query ", sql, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), " (", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), param, TypeSystem.get(java.lang.String.class), TypeSystem.get(tosa.api.IPreparedStatementParameter.class), true, false, false)), ") but got ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Integer.valueOf(results.size()), TypeSystem.get(java.lang.String.class), TypeSystem.get(int.class), true, false, false)));
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