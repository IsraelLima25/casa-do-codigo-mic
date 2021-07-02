package br.com.zup.cadastrarNovoAutor

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid novoAutorRequest: NovoAutorRequest) {
        val autor = novoAutorRequest.toModel()
        autorRepository.save(autor)
    }
}