package hu.bongyor.logic

import hu.bongyor.dao.Account
import hu.bongyor.logic.dto.AccountDto
import hu.bongyor.logic.dto.CreateAccountCommand
import hu.bongyor.logic.dto.IllegalCreditException
import org.jetbrains.exposed.sql.transactions.transaction

class AccountBean {
    fun listAccounts(): List<AccountDto> {
        return transaction {
            Account.all().map { AccountDto(it) }.toList()
        }
    }

    fun createAccount(command: CreateAccountCommand): AccountDto {
        val newAccount = transaction {
            Account.new {
                balance = command.balance
            }
        }
        return AccountDto(newAccount)
    }

    fun credit(accountId: Long, amount: Long) {
        transaction {
            val account = Account.findById(accountId) ?: throw IllegalCreditException("Nem létező számla")
            account.balance += amount
            if (account.balance < 0) {
                throw IllegalCreditException("Nincs elég fedezet a számlán")
            }
        }
    }
}