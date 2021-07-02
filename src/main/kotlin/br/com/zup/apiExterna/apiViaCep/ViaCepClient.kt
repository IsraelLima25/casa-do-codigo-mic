package br.com.zup.apiExterna.apiViaCep

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("http://viacep.com.br/ws")
interface ViaCepClient {

    @Get("/{cep}/{extensao}")
    fun consultarEndereco(
        @PathVariable cep: String,
        @PathVariable extensao: String
    ):HttpResponse<EnderecoViaCepResponse>

}