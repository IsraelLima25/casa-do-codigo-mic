package br.com.zup.deletarAutor

import br.com.zup.cadastrarNovoAutor.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/autores")
class DeletarAutorController(val autorRepository: AutorRepository) {

    @Delete("/{idAutor}")
    fun deletar(@PathVariable idAutor: Long): HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(idAutor)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()
        autorRepository.delete(autor)
        return HttpResponse.ok()
    }
}