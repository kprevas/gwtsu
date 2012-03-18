// Compiled from CustomHttpException.gs
public abstract ronin.CustomHttpException extends ServletException implements IGosuClassObject {
  public int _status;

  public void <init>(String reason, int status) {
    this.<init>(reason);

    this._status = status;
    return;
  }

  public void <init>(String reason, int status, Exception causedBy) {
    this.<init>(reason, causedBy);

    this._status = status;
    return;
  }

  public void handleException(HttpServletRequest req, HttpServletResponse resp) {
    this.beforeSendError();
    Ronin.log(this.getMessage(), LogLevel.ERROR, "Ronin", this.getCause());
    resp.sendError(this._status, this.getMessage());
    this.afterSendError();
    return;
  }

  public void beforeSendError() {
    return;
  }

  public void afterSendError() {
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