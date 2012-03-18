// Compiled from ShiroRealm.gs
public ronin.auth.ShiroRealm extends AuthorizingRealm implements IGosuClassObject {
  public IFunction1 _getUser;
  public IFunction3 _getOrCreateUserByOpenID;
  public PropertyReference _nameProp;
  public PropertyReference _passProp;
  public PropertyReference _saltProp;
  public PropertyReference _rolesProp;

  public void <init>(IFunction1 getUser, IFunction3 getOrCreateUserByOpenID, PropertyReference userName, PropertyReference userPassword, PropertyReference userSalt, PropertyReference userRoles, String hashAlgorithm, int hashIterations) {
    this.<init>([[
      *temp0 = new OpenIDAwareCredentialsMatcher(hashAlgorithm);
      *temp0.setStoredCredentialsHexEncoded(false);
      *temp0.setHashIterations(hashIterations);
      *temp0
    ]]);






    this._getUser = getUser;
    this._getOrCreateUserByOpenID = getOrCreateUserByOpenID;
    this._nameProp = userName;
    this._passProp = userPassword;
    this._saltProp = userSalt;
    this._rolesProp = userRoles;
    return;
  }

  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    return (this._rolesProp == null ? new SimpleAuthorizationInfo() : new SimpleAuthorizationInfo([[
      *temp1 = ((Iterable) this._rolesProp.get([[
        *temp0 = ((Object) ArrayAccess.getArrayElement(principals.asList(), 0, false));
        ((*temp0 instanceof ShiroPrincipalCollection) ? ((ShiroPrincipalCollection) *temp0) : ((ShiroPrincipalCollection) TypeAsTransformer.coerceValue(*temp0, TypeSystem.getByFullName("ronin.auth.ShiroPrincipalCollection", "_default_"), RuntimeCoercer.instance())))
      ]].getUser()));
      (*temp1 == null ? ((Set) null) : [[
        *temp2 = *temp1;
        *temp3 = TypeSystem.get(java.lang.String.class);
        if (*temp2 == null) {
          throw new NullPointerException();
        }
        CoreIterableEnhancement.toSet(*temp2, *temp3)
      ]])
    ]]));
  }

  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
    if (([[
      *temp0 = token;
      (*temp0 == null ? false : TypeSystem.getByFullName("ronin.auth.OpenIDToken", "_default_").isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]] && this._getOrCreateUserByOpenID != null)) {
      user = ((Object) this._getOrCreateUserByOpenID.invoke([[
        *temp1 = token;
        ((*temp1 instanceof OpenIDToken) ? ((OpenIDToken) *temp1) : ((OpenIDToken) TypeAsTransformer.coerceValue(*temp1, TypeSystem.getByFullName("ronin.auth.OpenIDToken", "_default_"), RuntimeCoercer.instance())))
      ]].getIdentity(), [[
        *temp2 = token;
        ((*temp2 instanceof OpenIDToken) ? ((OpenIDToken) *temp2) : ((OpenIDToken) TypeAsTransformer.coerceValue(*temp2, TypeSystem.getByFullName("ronin.auth.OpenIDToken", "_default_"), RuntimeCoercer.instance())))
      ]].getEmail(), [[
        *temp3 = token;
        ((*temp3 instanceof OpenIDToken) ? ((OpenIDToken) *temp3) : ((OpenIDToken) TypeAsTransformer.coerceValue(*temp3, TypeSystem.getByFullName("ronin.auth.OpenIDToken", "_default_"), RuntimeCoercer.instance())))
      ]].getIdProvider()));
      if (user == null) {
        throw new UnknownAccountException();
      }
      return new SimpleAuthenticationInfo([[
        *temp4 = new ShiroPrincipalCollection();
        *temp4.setUser(user);
        *temp4.setName(((String) this._nameProp.get(user)));
        *temp4
      ]], new SimpleByteSource(""), new SimpleByteSource(""), this.getName());
    } else {
      user = ((Object) this._getUser.invoke(((String) TypeAsTransformer.coerceValue(token.getPrincipal(), TypeSystem.get(java.lang.String.class), StringCoercer.instance()))));
      if (user == null) {
        throw new UnknownAccountException();
      }
      return new SimpleAuthenticationInfo([[
        *temp5 = new ShiroPrincipalCollection();
        *temp5.setUser(user);
        *temp5.setName(((String) this._nameProp.get(user)));
        *temp5
      ]], ((String) this._passProp.get(user)), new SimpleByteSource(Base64.decode(((String) this._saltProp.get(user)))), this.getName());
    }
  }

  public boolean supports(AuthenticationToken token) {
    return (this.supports(token) || [[
      *temp0 = token;
      (*temp0 == null ? false : TypeSystem.getByFullName("ronin.auth.OpenIDToken", "_default_").isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]);
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    return builder.getAnnotations();
  }

}