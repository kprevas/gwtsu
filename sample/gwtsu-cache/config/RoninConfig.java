// Compiled from RoninConfig.gs
public config.RoninConfig extends DefaultRoninConfig implements IGosuClassObject {

  public void <init>(ApplicationMode m, RoninServlet an) {
    this.<init>(m, an);
    if (EqualityExpressionTransformer.evaluate(m, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), true, ApplicationMode.DEVELOPMENT, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"))) {
      AdminConsole.start(((String;) null), 8022);
      GosuRuntimeMethods.setProperty(null, TypeSystem.getByFullName("db.model.Database", "_default_"), "JdbcUrl", "jdbc:h2:file:runtime/h2/devdb");
    } else {
      if (EqualityExpressionTransformer.evaluate(m, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), true, ApplicationMode.TESTING, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"))) {
        GosuRuntimeMethods.setProperty(null, TypeSystem.getByFullName("db.model.Database", "_default_"), "JdbcUrl", "jdbc:h2:file:runtime/h2/testdb");
      } else {
        if (EqualityExpressionTransformer.evaluate(m, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), true, ApplicationMode.STAGING, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"))) {
          GosuRuntimeMethods.setProperty(null, TypeSystem.getByFullName("db.model.Database", "_default_"), "JdbcUrl", "jdbc:h2:file:runtime/h2/stagingdb");
        } else {
          if (EqualityExpressionTransformer.evaluate(m, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"), true, ApplicationMode.PRODUCTION, TypeSystem.getByFullName("ronin.config.ApplicationMode", "_default_"))) {
            GosuRuntimeMethods.setProperty(null, TypeSystem.getByFullName("db.model.Database", "_default_"), "JdbcUrl", "jdbc:h2:file:runtime/h2/proddb");
          }
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