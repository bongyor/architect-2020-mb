package hu.bongyor

import hu.bongyor.dao.InitDb
import hu.bongyor.logic.AccountBean
import hu.bongyor.logic.TransferBean
import hu.bongyor.rest.AccountController
import hu.bongyor.rest.TransferController
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.locations.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.gson.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.ktor.KodeinFeature
import org.kodein.di.ktor.controller.controller

@KtorExperimentalLocationsAPI
fun main() {
    InitDb()
    embeddedServer(
        Netty,
        watchPaths = listOf("bongyor"),
        port = 8080,
        module = Application::module
    ).apply { start(wait = true) }
}

@KtorExperimentalLocationsAPI
fun Application.module() {
    moduleRun()
}

@KtorExperimentalLocationsAPI
fun Application.moduleRun(testing: Boolean = false) {
    InitDb(testing)
    install(Locations) {}
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(KodeinFeature) {
        bind<TransferBean>() with singleton { TransferBean(kodein) }
        bind<AccountBean>() with singleton { AccountBean() }
    }

    routing {
        locations
        controller { TransferController(instance()) }
        controller { AccountController(instance()) }
    }
}