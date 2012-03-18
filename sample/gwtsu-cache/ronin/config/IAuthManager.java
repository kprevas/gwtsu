// Compiled from IAuthManager.gs
public abstract interface ronin.config.IAuthManager extends Object implements IGosuClassObject {

  public abstract Object getCurrentUser() {
  }

  public abstract String getCurrentUserName() {
  }

  public abstract boolean currentUserHasRole(String role) {
  }

  public abstract boolean login(String username, String password) {
  }

  public abstract boolean openidLogin(String identity, String email, String idProvider) {
  }

  public abstract void logout() {
  }

  public abstract Pair getPasswordHashAndSalt(String password) {
  }

}