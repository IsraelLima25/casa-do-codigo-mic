package br.com.zup.cadastrarNovoAutor

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/autores")
class AutorController {

    @Post
    fun cadastrarAutor(@Body novoAutorRequest: NovoAutorRequest) {
        println(novoAutorRequest)
    }
}