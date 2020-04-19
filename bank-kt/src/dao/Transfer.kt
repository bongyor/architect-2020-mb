package hu.bongyor.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

class Transfer(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Transfer>(Transfers)
    var src by Transfers.src
    var dest by Transfers.dest
    var amount by Transfers.amount
    var result by Transfers.result
}


object Transfers: LongIdTable("transfers") {
    val src = long("tr_src")
    val dest = long("tr_dest")
    val amount = long("amount")
    val result = varchar("result", 200)
}
