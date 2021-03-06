package dev.johnoreilly.mortyuicomposekmp.shared

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import dev.johnoreilly.mortyuicomposekmp.GetCharactersQuery
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @InternalCoroutinesApi
    @ApolloExperimental
    @Test
    fun testExample() {
        runBlocking {

            val apolloClient = ApolloClient(
                networkTransport = ApolloHttpNetworkTransport(
                    serverUrl = "https://rickandmortyapi.com/graphql",
                    headers = mapOf(
                        "Accept" to "application/json",
                        "Content-Type" to "application/json",
                    )
                )
            )

            println("BEFORE")
            // execute() returns a Flow, here we only take the first item
            val response = apolloClient.query(GetCharactersQuery(page = Input.optional(0))).execute()

            println("after, response = $response")

            response.collect {
                println(it.data)
            }


        }

    }
}