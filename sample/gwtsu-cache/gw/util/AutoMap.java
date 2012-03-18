// Compiled from AutoMap.gs
public gw.util.AutoMap extends Object implements Map, IGosuClassObject {
  public IFunction1 _defaultReturnVal;
  public Map _map;
  internal final synthetic IType typeparam$K;
  internal final synthetic IType typeparam$V;

  public void <init>(IType typeparam$K, IType typeparam$V, Map mapToWrap, IFunction1 defaultReturnVal) {
    this.typeparam$K = typeparam$K;
    this.typeparam$V = typeparam$V;
    this.<init>();


    this._map = mapToWrap;
    this._defaultReturnVal = defaultReturnVal;
    return;
  }

  public void <init>(IType typeparam$K, IType typeparam$V, IFunction1 defaultReturnVal) {
    this.typeparam$K = typeparam$K;
    this.typeparam$V = typeparam$V;
    this.<init>();


    this._map = new HashMap();
    this._defaultReturnVal = defaultReturnVal;
    return;
  }

  public IFunction1 getDefaultReturnValue() {
    return this._defaultReturnVal;
  }

  public void setDefaultReturnValue(IFunction1 __value_) {
    this._defaultReturnVal = __value_;
    return;
  }

  public Object get(Object key) {
    val = this._map.get(key);
    if (val != null) {
      return val;
    } else {
      val = ((Object) this._defaultReturnVal.invoke(((Object) TypeAsTransformer.coerceValue(key, this.typeparam$K, TypeVariableCoercer.instance()))));
      this._map.put(((Object) TypeAsTransformer.coerceValue(key, this.typeparam$K, TypeVariableCoercer.instance())), val);
      return val;
    }
  }

  public boolean equals(Object o) {
    return this._map.equals(o);
  }

  public int hashCode() {
    return this._map.hashCode();
  }

  public boolean isEmpty() {
    return this._map.isEmpty();
  }

  public void clear() {
    this._map.clear();
    return;
  }

  public boolean containsKey(Object p0) {
    return this._map.containsKey(p0);
  }

  public boolean containsValue(Object p0) {
    return this._map.containsValue(p0);
  }

  public Set entrySet() {
    return this._map.entrySet();
  }

  public Set keySet() {
    return this._map.keySet();
  }

  public Object put(Object p0, Object p1) {
    return this._map.put(p0, p1);
  }

  public void putAll(Map p0) {
    this._map.putAll(p0);
    return;
  }

  public Object remove(Object p0) {
    return this._map.remove(p0);
  }

  public int size() {
    return this._map.size();
  }

  public Collection values() {
    return this._map.values();
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this).getParameterizedType([[
      *temp0 = new IType[2];
      *temp0[0] = this.typeparam$K;
      *temp0[1] = this.typeparam$V;
      *temp0
    ]]);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}