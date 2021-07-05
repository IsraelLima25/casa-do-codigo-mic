package br.com.zup.detalharAutor

import br.com.zup.cadastrarNovoAutor.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/autores")
class DetalharAutorController(val autorRepository: AutorRepository) {

    @Get
    fun listarAutores(): HttpResponse<List<DetalheAutorResponse>> {
        val autores = autorRepository.findAll()
        val listDetalheAutoresResponse = autores.map { autor ->
            DetalheAutorResponse(
                email = autor.email, nome = autor.nome, descricao = autor.descricao
            )
        }
        return HttpResponse.ok(listDetalheAutoresResponse)
    }

}