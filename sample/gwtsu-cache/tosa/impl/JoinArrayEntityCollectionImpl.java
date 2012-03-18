// Compiled from JoinArrayEntityCollectionImpl.gs
public tosa.impl.JoinArrayEntityCollectionImpl extends EntityCollectionImplBase implements IGosuClassObject {
  internal IDBColumn _srcColumn;
  internal IDBColumn _targetColumn;
  internal final synthetic IType typeparam$T;

  public void <init>(IType typeparam$T, IDBObject owner, IDBType fkType, IDBColumn srcColumn, IDBColumn targetColumn, QueryExecutor queryExecutor) {
    this.typeparam$T = typeparam$T;
    this.<init>(typeparam$T, owner, fkType, queryExecutor);


    this._srcColumn = srcColumn;
    this._targetColumn = targetColumn;
    return;
  }

  protected void removeImpl(IDBObject element) {
    if (!(this.isAlreadyInArray(element))) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "The element ", element.getDBTable().getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), "(", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), element.getId(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.Long.class), true, false, false)), ") cannot be removed from the join array on ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._owner.getDBTable().getName(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), "(", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._owner.getId(), TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.Long.class), true, false, false)), ") as it's not currently in the array", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)));
    }
    sql = SqlStringSubstituter.substitute("DELETE FROM :joinTable WHERE :srcColumn = :srcFk AND :targetColumn = :targetFk", [[
      *temp0 = new HashMap();
      *temp0.put("joinTable", this._srcColumn.getTable());
      *temp0.put("srcColumn", this._srcColumn);
      *temp0.put("targetColumn", this._targetColumn);
      *temp0.put("srcFk", this._owner.getId());
      *temp0.put("targetFk", element.getId());
      *temp0
    ]]);
    this._queryExecutor.delete("JoinArrayEntityCollectionImpl.removeImpl()", sql.getSql(), sql.getParams());
    if (this._cachedResults != null) {
      /*foreach*/
            *temp1 = ((AbstractIntIterator) ForEachStatementTransformer.makeIterator(IntervalExpressionTransformer._makeIntegerInterval(Integer.valueOf(0), Integer.valueOf(this._cachedResults.size()), Integer.valueOf(1), true, false)));

            i = 0;

      if (*temp1 != null) {
        while (*temp1.hasNext()) {
          i = *temp1.nextInt();

          if (((IDBObject) this._cachedResults.get(i)).getId().equals(element.getId())) {
            this._cachedResults.remove(i);
            break;          }
        }
      }
    }
    return;
  }

  protected void addImpl(IDBObject element) {
    if (!(this.isAlreadyInArray(element))) {
      if (element.isNew()) {
        element.update();
      }
      sql = SqlStringSubstituter.substitute("INSERT INTO :joinTable (:srcColumn, :targetColumn) VALUES (:srcId, :targetId)", [[
        *temp0 = new HashMap();
        *temp0.put("joinTable", this._srcColumn.getTable());
        *temp0.put("srcColumn", this._srcColumn);
        *temp0.put("targetColumn", this._targetColumn);
        *temp0.put("srcId", this._owner.getId());
        *temp0.put("targetId", element.getId());
        *temp0
      ]]);
      this._queryExecutor.insert("JoinArrayEntityCollectionImpl.addImpl()", sql.getSql(), sql.getParams());
      if (this._cachedResults != null) {
        this._cachedResults.add(element);
      }
    } else {
      if (this._cachedResults != null) {
        /*foreach*/
                *temp1 = ((AbstractIntIterator) ForEachStatementTransformer.makeIterator(IntervalExpressionTransformer._makeIntegerInterval(Integer.valueOf(0), Integer.valueOf(this._cachedResults.size()), Integer.valueOf(1), true, false)));

                i = 0;

        if (*temp1 != null) {
          while (*temp1.hasNext()) {
            i = *temp1.nextInt();

            if (((IDBObject) this._cachedResults.get(i)).getId().equals(element.getId())) {
              this._cachedResults.set(i, element);
              break;            }
          }
        }
      }
    }
    return;
  }

  internal boolean isAlreadyInArray(IDBObject element) {
    if (this._cachedResults != null) {
      /*foreach*/
            *temp0 = ForEachStatementTransformer.makeIterator(this._cachedResults);

            result = null;

      if (*temp0 != null) {
        while (*temp0.hasNext()) {
          result = ((IDBObject) *temp0.next());

          if (result.getId().equals(element.getId())) {
            return true;
          }
        }
      }
      return false;
    } else {
      if (element.isNew()) {
        return false;
      } else {
        sql = SqlStringSubstituter.substitute("SELECT count(*) as count FROM :joinTable WHERE :srcColumn = :srcFk AND :targetColumn = :targetFk", [[
          *temp1 = new HashMap();
          *temp1.put("joinTable", this._srcColumn.getTable());
          *temp1.put("srcColumn", this._srcColumn);
          *temp1.put("targetColumn", this._targetColumn);
          *temp1.put("srcFk", this._owner.getId());
          *temp1.put("targetFk", element.getId());
          *temp1
        ]]);
        numResults = this._queryExecutor.count("JoinArrayEntityCollectionImpl.isAlreadyInArray()", sql.getSql(), sql.getParams());
        return numResults > 0;
      }
    }
  }

  protected List loadResults() {
    sql = SqlStringSubstituter.substitute("SELECT * FROM :targetTable INNER JOIN :joinTable as j ON j.:targetColumn = :targetTable.:idColumn WHERE j.:srcColumn = :srcFk", [[
      *temp0 = new HashMap();
      *temp0.put("targetTable", this._fkType.getTable());
      *temp0.put("joinTable", this._srcColumn.getTable());
      *temp0.put("idColumn", this._fkType.getTable().getColumn("id"));
      *temp0.put("targetColumn", this._targetColumn);
      *temp0.put("srcColumn", this._srcColumn);
      *temp0.put("srcFk", this._owner.getId());
      *temp0
    ]]);
    return [[
      *temp1 = this._queryExecutor.selectEntity("JoinArrayEntityCollectionImpl.loadResultsIfNecessary()", this._fkType, sql.getSql(), sql.getParams());
      ((*temp1 instanceof List) ? ((List) *temp1) : ((List) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("java.util.List", "_default_").getParameterizedType([[
        *temp2 = new IType[1];
        *temp2[0] = this.typeparam$T;
        *temp2
      ]]), RuntimeCoercer.instance())))
    ]];
  }

  protected int issueCountQuery() {
    sql = SqlStringSubstituter.substitute("SELECT count(*) as count FROM :joinTable WHERE :srcColumn = :srcFk", [[
      *temp0 = new HashMap();
      *temp0.put("joinTable", this._srcColumn.getTable());
      *temp0.put("srcColumn", this._srcColumn);
      *temp0.put("srcFk", this._owner.getId());
      *temp0
    ]]);
    return this._queryExecutor.count("JoinArrayEntityCollectionImpl.size()", sql.getSql(), sql.getParams());
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