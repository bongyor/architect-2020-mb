package hu.bongyor.logic.dto

data class CreateTransferCommand(val src: Long, val dest: Long, val amount: Long)