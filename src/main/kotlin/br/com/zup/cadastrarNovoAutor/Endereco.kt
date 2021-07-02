package br.com.zup.cadastrarNovoAutor

import br.com.zup.apiExterna.apiViaCep.EnderecoViaCepResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoViaCepResponse, val numero: String) {

    val logradouro = enderecoResponse.logradouro
    val localidade = enderecoResponse.localidade
    val uf = enderecoResponse.uf

}
