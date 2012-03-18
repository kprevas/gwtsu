// Compiled from SessionMap.gs
public ronin.SessionMap extends Object implements Map, IGosuClassObject {
  public HttpSession _session;

  public void <init>(HttpSession session) {
    this.<init>();

    this._session = session;
    return;
  }

  public void clear() {
    throw new UnsupportedOperationException();
  }

  public boolean containsKey(Object key) {
    keys = [[
      *temp0 = this._session;
      (*temp0 == null ? ((Enumeration) null) : *temp0.getAttributeNames())
    ]];
    while (keys.hasMoreElements()) {
      if (EqualityExpressionTransformer.evaluate(keys.nextElement(), TypeSystem.get(java.lang.Object.class), true, key, TypeSystem.get(java.lang.Object.class))) {
        return true;
      }
    }
    return false;
  }

  public boolean containsValue(Object value) {
    keys = [[
      *temp0 = this._session;
      (*temp0 == null ? ((Enumeration) null) : *temp0.getAttributeNames())
    ]];
    while ([[
      *temp1 = keys;
      (*temp1 == null ? false : *temp1.hasMoreElements())
    ]]) {
      if (EqualityExpressionTransformer.evaluate(this._session.getAttribute(((String) TypeAsTransformer.coerceValue(keys.nextElement(), TypeSystem.get(java.lang.String.class), StringCoercer.instance()))), TypeSystem.get(java.lang.Object.class), true, value, TypeSystem.get(java.lang.Object.class))) {
        return true;
      }
    }
    return false;
  }

  public Set entrySet() {
    throw new UnsupportedOperationException();
  }

  public Object get(Object key) {
    return [[
      *temp0 = this._session;
      (*temp0 == null ? ((Object) null) : *temp0.getAttribute(((String) TypeAsTransformer.coerceValue(key, TypeSystem.get(java.lang.String.class), StringCoercer.instance()))))
    ]];
  }

  public boolean isEmpty() {
    return [[
      *temp1 = [[
        *temp0 = this._session;
        (*temp0 == null ? ((Enumeration) null) : *temp0.getAttributeNames())
      ]];
      (*temp1 == null ? false : *temp1.hasMoreElements())
    ]];
  }

  public Set keySet() {
    throw new UnsupportedOperationException();
  }

  public Object put(String key, Object value) {
    oldVal = [[
      *temp0 = this._session;
      (*temp0 == null ? ((Object) null) : *temp0.getAttribute(key))
    ]];
    [[
      *temp1 = this._session;
      if (*temp1 != null) {
        *temp1.setAttribute(key, value);
      }

    ]];
    return oldVal;
  }

  public volatile bridge synthetic Object put(Object p0, Object p1) {
    return this.put(((String) p0), p1);
  }

  public void putAll(Map m) {
    [[
      *temp1 = m;
      *temp2 = TypeSystem.get(java.lang.String.class);
      *temp3 = TypeSystem.get(java.lang.Object.class);
      *temp4 = [[
        *temp0 = new block_0_(this);
        *temp0._returnType = TypeSystem.get(java.lang.Object.class);
        *temp0
      ]];
      if (*temp1 == null) {
        throw new NullPointerException();
      }
      CoreMapEnhancement.eachKeyAndValue(*temp1, *temp2, *temp3, *temp4)
    ]];
    return;
  }

  public Object remove(Object key) {
    oldVal = [[
      *temp0 = this._session;
      (*temp0 == null ? ((Object) null) : *temp0.getAttribute(((String) TypeAsTransformer.coerceValue(key, TypeSystem.get(java.lang.String.class), StringCoercer.instance()))))
    ]];
    [[
      *temp1 = this._session;
      if (*temp1 != null) {
        *temp1.removeAttribute(((String) TypeAsTransformer.coerceValue(key, TypeSystem.get(java.lang.String.class), StringCoercer.instance())));
      }

    ]];
    return oldVal;
  }

  public int size() {
    count = 0;
    keys = [[
      *temp0 = this._session;
      (*temp0 == null ? ((Enumeration) null) : *temp0.getAttributeNames())
    ]];
    while (keys.hasMoreElements()) {
      keys.nextElement();
      count = count + 1;
    }
    return count;
  }

  public Collection values() {
    throw new UnsupportedOperationException();
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}