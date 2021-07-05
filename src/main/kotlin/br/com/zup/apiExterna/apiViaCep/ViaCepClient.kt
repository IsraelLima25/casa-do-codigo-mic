package br.com.zup.apiExterna.apiViaCep

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

// xml funcionando com republica virtual. return text_xml!!
//@Client("http://cep.republicavirtual.com.br/web_cep.php?cep=91010000&formato=xml")
@Client("http://viacep.com.br/ws")
interface ViaCepClient {
    @Get("/{cep}/json")
    fun consultarEndereco(
        @PathVariable cep: String
    ): HttpResponse<EnderecoViaCepResponse>
}