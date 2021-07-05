package br.com.zup.cadastrarNovoAutor

import br.com.zup.apiExterna.apiViaCep.EnderecoViaCepResponse
import io.micronaut.core.annotation.Introspected
import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.*


@Introspected
data class NovoAutorRequest(
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String,
    @field:CPF val cpf: String,
    @field:NotNull @field:PositiveOrZero @field:AutorExperiente val quantidadeLivrosPublicados: Int
) {
    fun toModel(enderecoResponse: EnderecoViaCepResponse): Autor {
        val endereco = Endereco(enderecoResponse = enderecoResponse, numero = numero)
        return Autor(
            email = email, nome = nome, descricao = descricao,
            endereco = endereco, quantidadeLivrosPublicados = quantidadeLivrosPublicados
        )
    }
}