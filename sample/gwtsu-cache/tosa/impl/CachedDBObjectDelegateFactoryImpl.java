// Compiled from CachedDBObjectDelegateFactoryImpl.gs
public tosa.impl.CachedDBObjectDelegateFactoryImpl extends Object implements CachedDBObject$DelegateFactory, IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public CachedDBObject$Delegate createDelegate(IDBType type, boolean isNew, CachedDBObject owner) {
    return new CachedDBObjectDelegateImpl(type, isNew, owner);
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}