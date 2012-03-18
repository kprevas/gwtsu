// Compiled from ShiroFilter.gs
internal ronin.auth.ShiroFilter extends AbstractShiroFilter implements IGosuClassObject {
  public AuthorizingRealm _realm;

  public void <init>(AuthorizingRealm realm) {
    this.<init>();

    this._realm = realm;
    return;
  }

  public void init() {
    this.setSecurityManager(new DefaultWebSecurityManager(this._realm));
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