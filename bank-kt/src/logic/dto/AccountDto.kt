package hu.bongyor.logic.dto

import hu.bongyor.dao.Account

data class AccountDto(val id: Long, val balance: Long) {
    constructor(account: Account): this(account.id.value, account.balance)
}