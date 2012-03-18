// Compiled from ReverseFkEntityCollectionImpl.gs
public tosa.impl.ReverseFkEntityCollectionImpl extends EntityCollectionImplBase implements IGosuClassObject {
  internal IDBColumn _fkColumn;
  internal final synthetic IType typeparam$T;

  public void <init>(IType typeparam$T, IDBObject owner, IDBType fkType, IDBColumn fkColumn, QueryExecutor queryExecutor) {
    this.typeparam$T = typeparam$T;
    this.<init>(typeparam$T, owner, fkType, queryExecutor);

    this._fkColumn = fkColumn;
    return;
  }

  protected int issueCountQuery() {
    text = SimpleSqlBuilder.substitute("SELECT count(*) as count FROM ${fkTable} WHERE ${fkColumn} = ?", "fkTable", this._fkType.getTable(), "fkColumn", this._fkColumn);
    param = this._fkColumn.wrapParameterValue(this._owner.getColumnValue(DBTypeInfo.ID_COLUMN));
    return this._queryExecutor.count("ReverseFkEntityCollectionImpl.size()", text, [[
      *temp0 = new IPreparedStatementParameter[1];
      *temp0[0] = param;
      *temp0
    ]]);
  }

  protected List loadResults() {
    idColumn = this._fkColumn.getTable().getColumn(DBTypeInfo.ID_COLUMN);
    sql = SimpleSqlBuilder.substitute("SELECT * FROM ${fkTable} WHERE ${fkColumn} = ? ORDER BY ${idColumn}", "fkTable", this._fkColumn.getTable(), "fkColumn", this._fkColumn, "idColumn", idColumn);
    param = this._fkColumn.wrapParameterValue(this._owner.getColumnValue(DBTypeInfo.ID_COLUMN));
    return [[
      *temp1 = this._queryExecutor.selectEntity("ReverseFkEntityCollectionImpl.loadResultsIfNecessary()", this._fkType, sql, [[
        *temp0 = new IPreparedStatementParameter[1];
        *temp0[0] = param;
        *temp0
      ]]);
      ((*temp1 instanceof List) ? ((List) *temp1) : ((List) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = this.typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance())))
    ]];
  }

  protected void addImpl(IDBObject element) {
    existingId = element.getColumnValue(this._fkColumn.getName());
    if (existingId == null) {
      element.setFkValue(this._fkColumn.getName(), this._owner);
      if (element.isNew()) {
        element.update();
      } else {
        idColumn = this._fkColumn.getTable().getColumn(DBTypeInfo.ID_COLUMN);
        updateSql = SimpleSqlBuilder.substitute("UPDATE ${fkTable} SET ${fkColumn} = ? WHERE ${idColumn} = ?", "fkTable", this._fkColumn.getTable(), "fkColumn", this._fkColumn, "idColumn", idColumn);
        fkParam = idColumn.wrapParameterValue(this._owner.getColumnValue(DBTypeInfo.ID_COLUMN));
        idParam = idColumn.wrapParameterValue(element.getColumnValue(DBTypeInfo.ID_COLUMN));
        this._queryExecutor.update("ReverseFkEntityCollectionImpl.add()", updateSql, [[
          *temp0 = new IPreparedStatementParameter[2];
          *temp0[0] = fkParam;
          *temp0[1] = idParam;
          *temp0
        ]]);
      }
      if (this._cachedResults != null) {
        this._cachedResults.add(element);
      }
    } else {
      if (existingId.equals(this._owner.getColumnValue(DBTypeInfo.ID_COLUMN))) {
        element.setFkValue(this._fkColumn.getName(), this._owner);
      } else {
        throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "The element with id ", element.getColumnValue(DBTypeInfo.ID_COLUMN), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.Object.class), true, false, false)), " is already attached to another owner, with id ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), existingId, TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.Object.class), true, false, false)));
      }
    }
    return;
  }

  protected void removeImpl(IDBObject element) {
    fkId = element.getColumnValue(this._fkColumn.getName());
    if (!(this._owner.getId().equals(fkId))) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "The element with id ", element.getId(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.Long.class), true, false, false)), " is not a member of the array on element ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._owner.getId(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.Long.class), true, false, false)));
    }
    element.setFkValue(this._fkColumn.getName(), ((IDBObject) null));
    idColumn = this._fkColumn.getTable().getColumn(DBTypeInfo.ID_COLUMN);
    updateSql = SimpleSqlBuilder.substitute("UPDATE ${fkTable} SET ${fkColumn} = NULL WHERE ${idColumn} = ?", "fkTable", this._fkColumn.getTable(), "fkColumn", this._fkColumn, "idColumn", idColumn);
    idParam = idColumn.wrapParameterValue(element.getColumnValue(DBTypeInfo.ID_COLUMN));
    this._queryExecutor.update("ReverseFkEntityCollectionImpl.remove()", updateSql, [[
      *temp0 = new IPreparedStatementParameter[1];
      *temp0[0] = idParam;
      *temp0
    ]]);
    if (this._cachedResults != null) {
      /*foreach*/
            *temp1 = ((AbstractIntIterator) ForEachStatementTransformer.makeIterator(IntervalExpressionTransformer._makeIntegerInterval(Integer.valueOf(0), Integer.valueOf(this._cachedResults.size()), Integer.valueOf(1), true, false)));

            i = 0;

      if (*temp1 != null) {
        while (*temp1.hasNext()) {
          i = *temp1.nextInt();

          if (element.getId().equals(((IDBObject) this._cachedResults.get(i)).getId())) {
            this._cachedResults.remove(i);
            break;          }
        }
      }
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