package config

uses db.roblog.*
uses ronin.*
uses ronin.config.*
uses ronin.console.*

class RoninConfig extends DefaultRoninConfig {

  construct(m : ApplicationMode, an : RoninServlet) {
    super(m, an)
    if(m == DEVELOPMENT) {
      db.roblog.Database.JdbcUrl = "jdbc:h2:file:runtime/h2/devdb"
    } else if( m == TESTING ) {
      db.roblog.Database.JdbcUrl = "jdbc:h2:file:runtime/h2/testdb"
    } else if( m == STAGING ) {
      db.roblog.Database.JdbcUrl = "jdbc:h2:file:runtime/h2/stagingdb"
    } else if( m == PRODUCTION ) {
      db.roblog.Database.JdbcUrl = "jdbc:h2:file:runtime/h2/proddb"
    }
    AuthManager = createDefaultAuthManager(
      \ username -> User.selectLike(new User(){:Name = username}).first(),
      \ identity, email, idProvider -> User.getOrCreateByOpenID(identity, email, idProvider),
      User#Name, User#Hash, User#Salt
    )
    AdminConsole.start({"admin"})
  }

}