package br.com.zup.detalharAutor

import br.com.zup.cadastrarNovoAutor.Autor

class DetalheAutorResponse(autor: Autor) {

    val email: String = autor.email
    val nome: String = autor.nome
    val descricao: String = autor.descricao

}