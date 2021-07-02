package br.com.zup.cadastrarNovoAutor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:8080/cep/")
interface EnderecoClient {

    @Get("{cep}")
    fun consultar(@QueryValue cep:String) : HttpResponse<EnderecoResponse>

}