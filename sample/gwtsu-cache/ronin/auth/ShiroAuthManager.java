// Compiled from ShiroAuthManager.gs
public ronin.auth.ShiroAuthManager extends Object implements IAuthManager, IGosuClassObject {
  public String _hashAlgorithm;
  public int _hashIterations;
  public SecurityManager _consoleSM;
  public String _realmType;
  public static SecureRandomNumberGenerator _rng;

  internal static void <clinit>() {
    ShiroAuthManager._rng = new SecureRandomNumberGenerator();
    return;
  }

  public void <init>(AuthorizingRealm realm, String hashAlgorithm, int hashIterations, IRoninConfig cfg) {
    this.<init>();




    this._hashAlgorithm = hashAlgorithm;
    this._hashIterations = hashIterations;
    this._realmType = [[
      *temp0 = realm;
      (*temp0 == null ? TypeSystem.get(void.class) : GosuRuntimeMethods.typeof(*temp0))
    ]].getName();
    filter = new ShiroFilter(realm);
    cfg.getFilters().add(cfg.initFilter(filter));
    this._consoleSM = new DefaultSecurityManager(realm);
    return;
  }

  public String getCurrentUserName() {
    subject = SecurityUtils.getSubject();
    if (subject.isAuthenticated()) {
      principal = ((Object) ArrayAccess.getArrayElement(subject.getPrincipals().asList(), 0, false));
      if ([[
        *temp0 = principal;
        (*temp0 == null ? false : TypeSystem.getByFullName("ronin.auth.ShiroPrincipalCollection", "_default_").isAssignableFrom(TypeSystem.getFromObject(*temp0)))
      ]]) {
        return [[
          *temp1 = principal;
          ((*temp1 instanceof ShiroPrincipalCollection) ? ((ShiroPrincipalCollection) *temp1) : ((ShiroPrincipalCollection) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("ronin.auth.ShiroPrincipalCollection", "_default_"), RuntimeCoercer.instance())))
        ]].getName();
      } else {
        return ((String) TypeAsTransformer.coerceValue(principal, TypeSystem.get(java.lang.String.class), StringCoercer.instance()));
      }
    }
    return null;
  }

  public Object getCurrentUser() {
    subject = SecurityUtils.getSubject();
    if (subject.isAuthenticated()) {
      principal = ((Object) ArrayAccess.getArrayElement(subject.getPrincipals().asList(), 0, false));
      if ([[
        *temp0 = principal;
        (*temp0 == null ? false : TypeSystem.getByFullName("ronin.auth.ShiroPrincipalCollection", "_default_").isAssignableFrom(TypeSystem.getFromObject(*temp0)))
      ]]) {
        return [[
          *temp1 = principal;
          ((*temp1 instanceof ShiroPrincipalCollection) ? ((ShiroPrincipalCollection) *temp1) : ((ShiroPrincipalCollection) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("ronin.auth.ShiroPrincipalCollection", "_default_"), RuntimeCoercer.instance())))
        ]].getUser();
      } else {
        return principal;
      }
    }
    return null;
  }

  public boolean currentUserHasRole(String role) {
    subject = SecurityUtils.getSubject();
    return (subject.isAuthenticated() ? subject.hasRole(role) : false);
  }

  public boolean login(String username, String password) {
    try {
      SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
      return true;
    }
    catch( AuthenticationException e) {
      return false;
    }
    catch( UnavailableSecurityManagerException e) {
      if (ThreadContext.getSecurityManager() == null) {
        ThreadContext.bind(this._consoleSM);
        try {
          return *temp0;
        }
        finally {
          ThreadContext.unbindSecurityManager();
        }
      } else {
        return false;
      }
    }
  }

  public boolean openidLogin(String identity, String email, String idProvider) {
    try {
      SecurityUtils.getSubject().login([[
        *temp0 = new OpenIDToken();
        *temp0.setIdentity(identity);
        *temp0.setEmail(email);
        *temp0.setIdProvider(idProvider);
        *temp0
      ]]);
      return true;
    }
    catch( AuthenticationException e) {
      return false;
    }
  }

  public void logout() {
    SecurityUtils.getSubject().logout();
    return;
  }

  public Pair getPasswordHashAndSalt(String password) {
    if (this._hashAlgorithm == null) {
      *temp1 = [[
        *temp0 = new StringBuilder();
        *temp0.append("Can't generate password hash and salt when using an authorizing realm of type ");
        *temp0.append(this._realmType);
        *temp0.append(".");
        *temp0.toString()
      ]];
      if ((*temp1 instanceof Throwable)) {
        throw ((Throwable) *temp1);
      } else {
        throw new EvaluationException(((String) *temp1));
      }
    }
    salt = ShiroAuthManager._rng.nextBytes();
    hash = new SimpleHash(this._hashAlgorithm, password, salt, this._hashIterations).toBase64();
    return Pair.make(hash, salt.toBase64());
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}