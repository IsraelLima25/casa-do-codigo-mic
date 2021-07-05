package br.com.zup.cadastrarNovoAutor

import br.com.zup.apiExterna.apiViaCep.ViaCepClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastrarNovoAutorController(
    val autorRepository: AutorRepository,
    val viaCepClient: ViaCepClient
) {

    @Post
    fun cadastrar(@Body @Valid novoAutorRequest: NovoAutorRequest): HttpResponse<Any> {

        // consultando via cep para capturar endereço
        val enderecoResponse = viaCepClient.consultarEndereco(novoAutorRequest.cep)
        val autor = novoAutorRequest.toModel(
            enderecoResponse.body()!!)

        autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }
}