// Compiled from EntityCollectionImplBase.gs
public abstract tosa.impl.EntityCollectionImplBase extends Object implements EntityCollection, IGosuClassObject {
  protected IDBObject _owner;
  protected IDBType _fkType;
  protected QueryExecutor _queryExecutor;
  protected List _cachedResults;
  internal final synthetic IType typeparam$T;

  protected void <init>(IType typeparam$T, IDBObject owner, IDBType fkType, QueryExecutor queryExecutor) {
    this.typeparam$T = typeparam$T;
    this.<init>();




    this._owner = owner;
    this._fkType = fkType;
    this._queryExecutor = queryExecutor;
    return;
  }

  public int size() {
    if (this._cachedResults == null) {
      return this.issueCountQuery();
    } else {
      return this._cachedResults.size();
    }
  }

  public Iterator iterator() {
    this.loadResultsIfNecessary();
    return new EntityCollectionImplIterator(this, this._cachedResults.iterator());
  }

  public IDBObject get(int index) {
    this.loadResultsIfNecessary();
    if ((index < 0 || index > this._cachedResults.size() - 1)) {
      throw new IndexOutOfBoundsException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "Index ", Integer.valueOf(index), TypeSystem.get(java.lang.String.class), TypeSystem.get(int.class), true, false, false)), " is invalid for an ReverseFkEntityCollectionImpl of size ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), Integer.valueOf(this._cachedResults.size()), TypeSystem.get(java.lang.String.class), TypeSystem.get(int.class), true, false, false)));
    }
    return ((IDBObject) this._cachedResults.get(index));
  }

  public void add(IDBObject element) {
    if (!(this._fkType.isAssignableFrom(element.getIntrinsicType()))) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "An element of type ", element.getIntrinsicType(), TypeSystem.get(java.lang.String.class), TypeSystem.get(gw.lang.reflect.IType.class), true, false, false)), " cannot be added to a collection of type ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._fkType, TypeSystem.get(java.lang.String.class), TypeSystem.get(tosa.loader.IDBType.class), true, false, false)));
    }
    if (this._owner.isNew()) {
      throw new IllegalStateException("The element cannot be added to the list, as the owner is not yet committed.  You must commit the owner prior to added anything to the list.");
    }
    this.addImpl(element);
    return;
  }

  public void remove(IDBObject element) {
    if (!(this._fkType.isAssignableFrom(element.getIntrinsicType()))) {
      throw new IllegalArgumentException(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), ((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), "An element of type ", element.getIntrinsicType(), TypeSystem.get(java.lang.String.class), TypeSystem.get(gw.lang.reflect.IType.class), true, false, false)), " cannot be added to a collection of type ", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), this._fkType, TypeSystem.get(java.lang.String.class), TypeSystem.get(tosa.loader.IDBType.class), true, false, false)));
    }
    if (this._owner.isNew()) {
      throw new IllegalStateException("Elements cannot be removed from an entity that has not yet been persisted");
    }
    this.removeImpl(element);
    return;
  }

  public void load() {
    this.loadResultsIfNecessary();
    return;
  }

  public void unload() {
    this._cachedResults = null;
    return;
  }

  public void setQueryExecutor(QueryExecutor queryExecutor) {
    this._queryExecutor = queryExecutor;
    return;
  }

  protected abstract int issueCountQuery() {
  }

  protected abstract List loadResults() {
  }

  protected abstract void addImpl(IDBObject element) {
  }

  protected abstract void removeImpl(IDBObject element) {
  }

  internal void loadResultsIfNecessary() {
    if (this._cachedResults == null) {
      this._cachedResults = this.loadResults();
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
    builder.startAnnotationInfoForFeature("setQueryExecutor(tosa.impl.QueryExecutor)");
    builder.addGosuAnnotation(new Param("queryExecutor", ""));
    return builder.getAnnotations();
  }

}