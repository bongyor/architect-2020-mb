package hu.bongyor.logic

import hu.bongyor.dao.Transfer
import hu.bongyor.logic.dto.CreateTransferCommand
import hu.bongyor.logic.dto.IllegalCreditException
import hu.bongyor.logic.dto.TransferDto
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.ktor.kodein

class TransferBean(override val kodein: Kodein) : KodeinAware {
    private val accountBean by instance<AccountBean>()

    fun createTransfer(command: CreateTransferCommand): TransferDto {
        val transfer = transaction {
            val transfer = Transfer.new {
                src = command.src
                dest = command.dest
                amount = command.amount
                result = "ok"
            }
            try {
                accountBean.credit(command.src, -command.amount)
                accountBean.credit(command.dest, command.amount)
            } catch (e: IllegalCreditException) {
                transfer.result = "error"
                rollback()
            }

            transfer
        }
        return TransferDto(transfer)
    }

    fun listTransfers(): List<TransferDto> {
        return transaction {
            Transfer.all().map { TransferDto(it) }.toList()
        }
    }
}