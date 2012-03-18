// Compiled from AdminConsole.gs
public ronin.console.AdminConsole extends Object implements IGosuClassObject {

  public void <init>() {
    this.<init>();
    return;
  }

  public static void start(String; authorizedUsers$$unboxedParam, int port) {
    authorizedUsers = [[
      *temp0 = new String;[1];
      *temp0[0] = authorizedUsers$$unboxedParam;
      *temp0
    ]];
    ssh = SshServer.setUpDefaultServer();
    ssh.setPort(port);
    ssh.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hostkey.ser"));
    ssh.setShellFactory(new GosuShellFactory());
    if (authorizedUsers[0] != null) {
      ssh.setPasswordAuthenticator(new AnonymouS__0(authorizedUsers));
    } else {
      ssh.setPasswordAuthenticator(new AnonymouS__1());
    }
    ssh.start();
    return;
  }

  internal static boolean isLocalDevModeConnection(ServerSession serverSession) {
    if (EqualityExpressionTransformer.evaluate(Ronin.getMode(), TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), false, ApplicationMode.DEVELOPMENT, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"))) {
      return false;
    }
    address = serverSession.getIoSession().getRemoteAddress();
    if ([[
      *temp0 = address;
      (*temp0 == null ? false : TypeSystem.get(java.net.InetSocketAddress.class).isAssignableFrom(TypeSystem.getFromObject(*temp0)))
    ]]) {
      return (((InetSocketAddress) address).getAddress().isAnyLocalAddress() || ((InetSocketAddress) address).getAddress().isLoopbackAddress());
    }
    return false;
  }

  public synthetic IType getIntrinsicType() {
    return GosuRuntimeMethods.getType(this);
  }

  public static Map $evalAnnotations() {
    builder = new AnnotationMap();
    builder.startAnnotationInfoForFeature("start(java.lang.String[], int)");
    builder.addGosuAnnotation(new Param("authorizedUsers", "(Optional) The usernames of the users who are allowed to access the console.
Defaults to null, which means that in development mode, all username/password combinations will be
accepted, and in any other mode, no logins will be allowed."));
    builder.addGosuAnnotation(new Param("port", "(Optional) The port on which to start the admin console; defaults to 8022."));
    return builder.getAnnotations();
  }

}