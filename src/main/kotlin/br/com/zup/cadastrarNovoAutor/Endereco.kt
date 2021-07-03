package br.com.zup.cadastrarNovoAutor

import br.com.zup.apiExterna.apiViaCep.EnderecoRepublicaResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoRepublicaResponse, val numero: String) {

    val logradouro = enderecoResponse.logradouro
    val localidade = enderecoResponse.localidade
    val uf = enderecoResponse.uf

}
