package br.com.zup.pix.registra

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:9091/api/v1/clientes")
@Consumes(MediaType.APPLICATION_JSON)
interface ContasDeClientesNoItauClient {

    @Get("/{clienteId}/contas")
    fun buscaContaPorTipo(@PathVariable clienteId : String, @QueryValue tipo: String) : HttpResponse<DadosDaContaResponse>
}
