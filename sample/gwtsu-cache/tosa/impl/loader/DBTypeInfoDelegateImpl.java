// Compiled from DBTypeInfoDelegateImpl.gs
public tosa.impl.loader.DBTypeInfoDelegateImpl extends Object implements DBTypeInfoDelegate, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public IDBObject fromId(IDBType dbType, long id) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).fromId(id);
  }

  public long count(IDBType dbType, String sql, Map params) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).count(sql, params);
  }

  public long countAll(IDBType dbType) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).countAll();
  }

  public long countWhere(IDBType dbType, String sql, Map params) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).countWhere(sql, params);
  }

  public long countLike(IDBType dbType, IDBObject template) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).countLike(template);
  }

  public Object select(IDBType dbType, String sql, Map params) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).select(sql, params);
  }

  public Object selectAll(IDBType dbType) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).selectAll();
  }

  public Object selectWhere(IDBType dbType, String sql, Map params) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).selectWhere(sql, params);
  }

  public Object selectLike(IDBType dbType, IDBObject template) {
    return new CoreFinder(TypeSystem.get(tosa.api.IDBObject.class), dbType).selectLike(template);
  }

  public IDBObject newInstance(IDBType dbType, boolean isNew) {
    return new CachedDBObject(dbType, isNew);
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}