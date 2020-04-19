package hu.bongyor.logic.dto

import hu.bongyor.dao.Transfer

data class TransferDto(val src: Long, val dest: Long, val ammount: Long, val result: String) {
    constructor(transfer: Transfer): this(transfer.src, transfer.dest, transfer.amount, transfer.result)
}