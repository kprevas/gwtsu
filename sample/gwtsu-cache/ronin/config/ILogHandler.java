// Compiled from ILogHandler.gs
public abstract interface ronin.config.ILogHandler extends Object implements IGosuClassObject {

  public abstract void log(Object msg, LogLevel level, String component, Throwable exception) {
  }

}