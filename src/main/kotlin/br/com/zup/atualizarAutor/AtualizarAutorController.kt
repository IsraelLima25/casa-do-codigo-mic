package br.com.zup.atualizarAutor

import br.com.zup.cadastrarNovoAutor.AutorRepository
import br.com.zup.detalharAutor.DetalheAutorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put

@Controller("/autores")
class AtualizarAutorController(val autorRepository: AutorRepository) {

    @Put("/{idAutor}")
    fun atualiza(@PathVariable idAutor: Long, descricao: String): HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(idAutor)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()
        autor.descricao = descricao
        autorRepository.update(autor)
        return HttpResponse.ok(
            DetalheAutorResponse(
                email = autor.email, nome = autor.nome, descricao = autor.descricao
            )
        )
    }
}