package br.com.zup.cadastrarNovoAutor

import br.com.zup.apiExterna.apiViaCep.EnderecoViaCepResponse
import br.com.zup.apiExterna.apiViaCep.ViaCepClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CadastrarNovoAutorControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    lateinit var viaCepClient: ViaCepClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    fun `deve cadastrar um novo autor`() {
        val novoAutorRequest = NovoAutorRequest(
            email = "sizenando@gmail.com",
            nome = "sizenando",
            descricao = "entusiasta html",
            cep = "41290226",
            numero = "215E",
            cpf = "04918589714",
            quantidadeLivrosPublicados = 200
        )
        val enderecoViaCepResponse =
            EnderecoViaCepResponse(logradouro = "Rua alvorada", localidade = "Travessa B", uf = "Ba")
        Mockito.`when`(viaCepClient.consultarEndereco(novoAutorRequest.cep))
            .thenReturn(HttpResponse.ok(enderecoViaCepResponse))
        val request = HttpRequest.POST("/autores", novoAutorRequest)
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("location"))
        assertTrue(response.header("location")!!.startsWith("/autores"))
    }

    @MockBean(ViaCepClient::class)
    fun enderecoMock(): ViaCepClient {
        return Mockito.mock(ViaCepClient::class.java)
    }
}