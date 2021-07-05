package br.com.zup.apiExterna.apiCepMock

import br.com.zup.cadastrarNovoAutor.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/cep")
class MockCepController {

    @Get("/{cep}")
    fun gerarCepMock(@QueryValue cep: String): HttpResponse<EnderecoResponse> {
        val enderecoResponse = EnderecoResponse(logradouro = "Rua das rosas", localidade = "Campinas", "São Paulo")
        return HttpResponse.ok(enderecoResponse)
    }
}