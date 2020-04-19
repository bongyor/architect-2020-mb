package hu.bongyor.dao

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class InitDb(testing: Boolean = false) {
    init {
        if (testing) {
            Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
        } else {
            Database.connect(
                url = "jdbc:postgresql://localhost:5432/architect",
                user = "nbadmin",
                password = "masterkey"
            )
        }
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.createMissingTablesAndColumns(Transfers)
            SchemaUtils.createMissingTablesAndColumns(Accounts)
        }
    }
}