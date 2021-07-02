package br.com.zup.cadastrarNovoAutor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid novoAutorRequest: NovoAutorRequest):HttpResponse<Any> {
        val autor = novoAutorRequest.toModel()
        autorRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }
}