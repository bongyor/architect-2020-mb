package hu.bongyor.rest

import hu.bongyor.logic.dto.CreateTransferCommand
import hu.bongyor.logic.TransferBean
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import org.kodein.di.generic.instance
import org.kodein.di.ktor.controller.AbstractKodeinController

@KtorExperimentalLocationsAPI
class TransferController(application: Application) : AbstractKodeinController(application) {
    private val bean by instance<TransferBean>()

    override fun Route.getRoutes() {
        get<BankApiRoutes.Transfers> {
            call.respond(HttpStatusCode.OK, bean.listTransfers())
        }

        post<BankApiRoutes.Transfers> {
            val commmand = call.receive<CreateTransferCommand>()
            val transfer = bean.createTransfer(commmand)
            call.respond(HttpStatusCode.Created, transfer)
        }
    }
}