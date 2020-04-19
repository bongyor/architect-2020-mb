package hu.bongyor.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

class Account(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Account>(Accounts)
    var balance by Accounts.balance
}


object Accounts: LongIdTable("account") {
    val balance = long("balance")
}
