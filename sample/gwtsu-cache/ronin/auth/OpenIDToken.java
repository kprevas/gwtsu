// Compiled from OpenIDToken.gs
public ronin.auth.OpenIDToken extends Object implements AuthenticationToken, IGosuClassObject {
  public String _identity;
  public String _email;
  public String _idProvider;

  public void <init>() {
    this.<init>();



    return;
  }

  public String getIdentity() {
    return this._identity;
  }

  public void setIdentity(String __value_) {
    this._identity = __value_;
    return;
  }

  public String getEmail() {
    return this._email;
  }

  public void setEmail(String __value_) {
    this._email = __value_;
    return;
  }

  public String getIdProvider() {
    return this._idProvider;
  }

  public void setIdProvider(String __value_) {
    this._idProvider = __value_;
    return;
  }

  public Object getCredentials() {
    return this._idProvider;
  }

  public Object getPrincipal() {
    return this._email;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}