// Compiled from ShiroPrincipalCollection.gs
internal ronin.auth.ShiroPrincipalCollection extends Object implements PrincipalCollection, IGosuClassObject {
  public Object _user;
  public String _name;

  public void <init>() {
    this.<init>();


    return;
  }

  public Object getUser() {
    return this._user;
  }

  public void setUser(Object __value_) {
    this._user = __value_;
    return;
  }

  public String getName() {
    return this._name;
  }

  public void setName(String __value_) {
    this._name = __value_;
    return;
  }

  public List asList() {
    return [[
      *temp0 = new ArrayList();
      *temp0.add(this._name);
      *temp0.add(this._user);
      *temp0
    ]];
  }

  public Set asSet() {
    return [[
      *temp0 = new HashSet();
      *temp0.add(this._name);
      *temp0.add(this._user);
      *temp0
    ]];
  }

  public Collection byType(IType typeparam$T, Class clazz) {
    if (typeparam$T.isAssignableFrom(TypeSystem.get(java.lang.Object.class))) {
      return ((Collection) [[
        *temp0 = new ArrayList();
        *temp0.add(this._name);
        *temp0.add(this._user);
        *temp0
      ]]);
    }
    if (typeparam$T.isAssignableFrom(TypeSystem.get(java.lang.String.class))) {
      return ((Collection) [[
        *temp1 = new ArrayList();
        *temp1.add(this._name);
        *temp1
      ]]);
    }
    return new ArrayList();
  }

  public Collection fromRealm(String realm) {
    return this.asList();
  }

  public Object getPrimaryPrincipal() {
    return this._name;
  }

  public Set getRealmNames() {
    return new HashSet();
  }

  public boolean isEmpty() {
    return false;
  }

  public Object oneByType(IType typeparam$T, Class clazz) {
    return ((Object) TypeAsTransformer.coerceValue(((Object) ArrayAccess.getArrayElement(this.asList(), 0, false)), typeparam$T, TypeVariableCoercer.instance()));
  }

  public Iterator iterator() {
    return this.asList().iterator();
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}