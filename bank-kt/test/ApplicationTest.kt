package hu.bongyor

import com.google.gson.Gson
import hu.bongyor.dao.InitDb
import hu.bongyor.logic.dto.CreateAccountCommand
import hu.bongyor.logic.dto.CreateTransferCommand
import hu.bongyor.rest.BankApiRoutes
import io.ktor.http.*
import io.ktor.locations.*
import kotlin.test.*
import io.ktor.server.testing.*

@KtorExperimentalLocationsAPI
class ApplicationTest {
    private val gson = Gson()

    @Test
    fun testRoot() {
        InitDb(true)

        withTestApplication({ moduleRun(true) }) {
            createAccount(
                1000L,
                """
                {
                  "id": 1,
                  "balance": 1000
                }
                """)
            createAccount(
                2000L,
                """
                {
                  "id": 2,
                  "balance": 2000
                }
                """)
            createTransfer(
                CreateTransferCommand(1, 2, 500),
                """
                {
                  "src": 1,
                  "dest": 2,
                  "ammount": 500,
                  "result": "ok"
                }
                """
            )
            listTransfers(
                """
                [
                  {
                    "src": 1,
                    "dest": 2,
                    "ammount": 500,
                    "result": "ok"
                  }
                ]
                """
            )
            listAccounts(
                """
                [
                  {
                    "id": 1,
                    "balance": 500
                  },
                  {
                    "id": 2,
                    "balance": 2500
                  }
                ]
                """
            )
            createTransfer(
                CreateTransferCommand(1, 2, 1500),
                """
                {
                  "src": 1,
                  "dest": 2,
                  "ammount": 1500,
                  "result": "error"
                }
                """
            )
            listAccounts(
                """
                [
                  {
                    "id": 1,
                    "balance": 500
                  },
                  {
                    "id": 2,
                    "balance": 2500
                  }
                ]
                """
            )
            listTransfers(
                """
                [
                  {
                    "src": 1,
                    "dest": 2,
                    "ammount": 500,
                    "result": "ok"
                  }
                ]
                """
            )
        }
    }

    private fun TestApplicationEngine.listTransfers(expectedReturnJson: String) {
        handleRequest(HttpMethod.Get, application.locations.href(BankApiRoutes.Transfers())).apply {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(expectedReturnJson.trimIndent(), response.content)
        }
    }

    private fun TestApplicationEngine.listAccounts(expectedReturnJson: String) {
        handleRequest(HttpMethod.Get, application.locations.href(BankApiRoutes.Accounts())).apply {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(expectedReturnJson.trimIndent(), response.content)
        }
    }

    private fun TestApplicationEngine.createTransfer(createCommand: CreateTransferCommand, expectedReturnJson: String) {
        handleRequest(HttpMethod.Post, application.locations.href(BankApiRoutes.Transfers())) {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(gson.toJson(createCommand))
        }.apply {
            assertEquals(HttpStatusCode.Created, response.status())
            assertEquals(expectedReturnJson.trimIndent(), response.content)
        }
    }

    private fun TestApplicationEngine.createAccount(balance: Long, expectedReturnJson: String) {
        handleRequest(HttpMethod.Post, application.locations.href(BankApiRoutes.Accounts())) {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(gson.toJson(CreateAccountCommand(balance)))
        }.apply {
            assertEquals(HttpStatusCode.Created, response.status())
            assertEquals(expectedReturnJson.trimIndent(), response.content)
        }
    }
}
