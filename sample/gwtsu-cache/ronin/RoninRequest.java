// Compiled from RoninRequest.gs
public ronin.RoninRequest extends Object implements IReentrant, IGosuClassObject {
  public String _prefix;
  public HttpServletResponse _resp;
  public HttpServletRequest _req;
  public HttpMethod _method;
  public Map _sessionMap;
  public String _referrer;
  public Trace _trace;
  public MethodReference _formTarget;
  public Stack _templateTraceStack;
  public RoninRequest _parentRequest;

  public void <init>(String pref, HttpServletResponse resp, HttpServletRequest req, HttpMethod method, Map sessionMap, String ref) {
    this.<init>();








    this._templateTraceStack = new Stack();

    this._prefix = pref;
    this._resp = resp;
    this._req = req;
    this._method = method;
    this._sessionMap = sessionMap;
    this._referrer = ref;
    if (Ronin.isTraceEnabled()) {
      this._trace = new Trace();
    }
    return;
  }

  public String getPrefix() {
    return this._prefix;
  }

  public HttpServletResponse getHttpResponse() {
    return this._resp;
  }

  public HttpServletRequest getHttpRequest() {
    return this._req;
  }

  public HttpMethod getHttpMethod() {
    return this._method;
  }

  public Map getHttpSession() {
    return this._sessionMap;
  }

  public String getReferrer() {
    return this._referrer;
  }

  public Trace getTrace() {
    return this._trace;
  }

  public MethodReference getFormTarget() {
    return this._formTarget;
  }

  public void setFormTarget(MethodReference __value_) {
    this._formTarget = __value_;
    return;
  }

  public Writer getWriter() {
    return this._resp.getWriter();
  }

  public void enter() {
    this._parentRequest = Ronin.getCurrentRequest();
    Ronin.setCurrentRequest(this);
    return;
  }

  public void exit() {
    Ronin.setCurrentRequest(this._parentRequest);
    return;
  }

  internal void beforeRenderTemplate(IType template) {
    if (Ronin.isTraceEnabled()) {
      elt = Ronin.getCurrentTrace().withMessage(((String) AdditiveExpression.evaluate(TypeSystem.get(java.lang.String.class), template.getName(), ".render()", TypeSystem.get(java.lang.String.class), TypeSystem.get(java.lang.String.class), true, false, false)), true);
      elt.enter();
      this._templateTraceStack.push(elt);
    }
    return;
  }

  internal void afterRenderTemplate(IType template) {
    if (Ronin.isTraceEnabled()) {
      elt = ((TraceElement) this._templateTraceStack.pop());
      elt.exit();
    }
    return;
  }

  internal void checkXSRF() {
    if (((Object) this._sessionMap.get(IRoninUtilsEnhancement.getXSRFTokenName())) != null) {
      if (EqualityExpressionTransformer.evaluate(this._req.getParameter(IRoninUtilsEnhancement.getXSRFTokenName()), TypeSystem.get(java.lang.String.class), false, ((String) TypeAsTransformer.coerceValue(((Object) this._sessionMap.get(IRoninUtilsEnhancement.getXSRFTokenName())), TypeSystem.get(java.lang.String.class), StringCoercer.instance())), TypeSystem.get(java.lang.String.class))) {
        *temp0 = "Missing or invalid authenticity token.";
        if ((*temp0 instanceof Throwable)) {
          throw ((Throwable) *temp0);
        } else {
          throw new EvaluationException(((String) *temp0));
        }
      }
    }
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