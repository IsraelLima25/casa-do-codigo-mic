package br.com.zup.apiExterna.apiViaCep

data class EnderecoViaCepResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String
) {

}
