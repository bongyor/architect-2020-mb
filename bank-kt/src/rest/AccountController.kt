package hu.bongyor.rest

import hu.bongyor.logic.AccountBean
import hu.bongyor.logic.dto.CreateAccountCommand
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
class AccountController(application: Application) : AbstractKodeinController(application) {
    private val bean by instance<AccountBean>()

    override fun Route.getRoutes() {
        get<BankApiRoutes.Accounts> {
            call.respond(HttpStatusCode.OK, bean.listAccounts())
        }

        post<BankApiRoutes.Accounts> {
            val command = call.receive<CreateAccountCommand>()
            val transfer = bean.createAccount(command)
            call.respond(HttpStatusCode.Created, transfer)
        }
    }
}