package config

uses ronin.*
uses ronin.config.*
uses ronin.console.*

class RoninConfig extends DefaultRoninConfig {

  construct(m : ApplicationMode, an : RoninServlet) {
    super(m, an)
    if(m == DEVELOPMENT) {
      AdminConsole.start()
      db.model.Database.JdbcUrl = "jdbc:h2:file:runtime/h2/devdb"
    } else if( m == TESTING ) {
      db.model.Database.JdbcUrl = "jdbc:h2:file:runtime/h2/testdb"
    } else if( m == STAGING ) {
      db.model.Database.JdbcUrl = "jdbc:h2:file:runtime/h2/stagingdb"
    } else if( m == PRODUCTION ) {
      db.model.Database.JdbcUrl = "jdbc:h2:file:runtime/h2/proddb"
    }
  }

}