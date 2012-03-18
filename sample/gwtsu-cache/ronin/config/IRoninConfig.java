// Compiled from IRoninConfig.gs
public abstract interface ronin.config.IRoninConfig extends Object implements IGosuClassObject {

  public abstract RoninServlet getRoninServlet() {
  }

  public abstract Cache getRequestCache() {
  }

  public abstract Cache getSessionCache() {
  }

  public abstract Cache getApplicationCache() {
  }

  public abstract ApplicationMode getMode() {
  }

  public abstract LogLevel getLogLevel() {
  }

  public abstract boolean isTraceEnabled() {
  }

  public abstract String getDefaultAction() {
  }

  public abstract IType getDefaultController() {
  }

  public abstract List getXSRFLevel() {
  }

  public abstract ServletFileUpload getServletFileUpload() {
  }

  public abstract IAuthManager getAuthManager() {
  }

  public abstract List getFilters() {
  }

  public abstract IErrorHandler getErrorHandler() {
  }

  public abstract ILogHandler getLogHandler() {
  }

  public abstract IURLHandler getURLHandler() {
  }

  public abstract IReturnValueHandler getReturnValueHandler() {
  }

  public abstract IParamConverter getParamConverter() {
  }

  public abstract Set getRestrictedProperties() {
  }

  public abstract MethodReference getLoginRedirect() {
  }

  public abstract Filter initFilter(Filter filter) {
  }

}