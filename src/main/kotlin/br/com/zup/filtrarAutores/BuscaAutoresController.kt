package br.com.zup.filtrarAutores

import br.com.zup.cadastrarNovoAutor.AutorRepository
import br.com.zup.detalharAutor.DetalheAutorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get("/filter")
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isEmpty()) {
            val autores = autorRepository.findAll()
            val resposta = autores.map { autor -> DetalheAutorResponse(email = autor.email, nome = autor.nome, descricao = autor.descricao) }
            return HttpResponse.ok(resposta)
        }
        val possivelAutor = autorRepository.buscaPoremail(email)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autorBusca = possivelAutor.get()
        val detalheAutorResponse =
            DetalheAutorResponse(email = autorBusca.email, nome = autorBusca.nome, descricao = autorBusca.descricao)
        return HttpResponse.ok(detalheAutorResponse)
    }
}