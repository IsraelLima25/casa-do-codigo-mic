package br.com.zup.filtrarAutores

import br.com.zup.apiExterna.apiViaCep.EnderecoViaCepResponse
import br.com.zup.cadastrarNovoAutor.Autor
import br.com.zup.cadastrarNovoAutor.AutorRepository
import br.com.zup.cadastrarNovoAutor.Endereco
import br.com.zup.detalharAutor.DetalheAutorResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {

        val endereco = Endereco(
            EnderecoViaCepResponse(logradouro = "Travessa Alvorada", localidade = "Salvador", uf = "Ba"),
            "10E"
        )

        autor = Autor(
            email = "sizenando@gmail.com", nome = "sizenando", descricao = "entusiasta kotlin",
            endereco = endereco, quantidadeLivrosPublicados = 30
        )

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    fun `deve retornar os detalhes de um autor`() {

        var response = client.toBlocking().exchange(
            "/autores/filter?email=${autor.email}",
            DetalheAutorResponse::class.java
        )

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)

    }
}