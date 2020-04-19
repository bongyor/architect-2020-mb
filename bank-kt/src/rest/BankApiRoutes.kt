package hu.bongyor.rest

import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location

@KtorExperimentalLocationsAPI
@Location("/bank/api") class BankApiRoutes {
    @Location("/transfers") class Transfers(@Suppress("unused") val bankApiRoutes: BankApiRoutes = BankApiRoutes())
    @Location("/accounts") class Accounts(@Suppress("unused") val bankApiRoutes: BankApiRoutes = BankApiRoutes())
}
