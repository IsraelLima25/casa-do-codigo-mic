package br.com.zup.cadastrarNovoAutor

import br.com.zup.apiExterna.apiViaCep.EnderecoViaCepResponse
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String
) {
    fun toModel(enderecoResponse: EnderecoViaCepResponse): Autor {
        val endereco = Endereco(enderecoResponse,numero)
        return Autor(email = email, nome = nome, descricao = descricao, endereco = endereco)
    }
}