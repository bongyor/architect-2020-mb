package employees;

import lombok.val;
import org.flywaydb.core.Flyway;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.sql.DataSource;

@ApplicationScoped
public class DbMigrator {

    @Resource(mappedName = "jdbc/BankDS__pm")
    private DataSource dataSource;

    public void init( @Observes @Initialized( ApplicationScoped.class ) Object init ) {
        val flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }

}
