// Compiled from IErrorHandler.gs
public abstract interface ronin.config.IErrorHandler extends Object implements IGosuClassObject {

  public abstract void on404(FourOhFourException e, HttpServletRequest req, HttpServletResponse resp) {
  }

  public abstract void on500(FiveHundredException e, HttpServletRequest req, HttpServletResponse resp) {
  }

}