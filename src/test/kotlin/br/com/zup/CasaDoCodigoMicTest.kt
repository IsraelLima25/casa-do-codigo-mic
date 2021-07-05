package br.com.zup

import br.com.zup.apiExterna.apiViaCep.EnderecoViaCepResponse
import br.com.zup.cadastrarNovoAutor.Autor
import br.com.zup.cadastrarNovoAutor.AutorRepository
import br.com.zup.cadastrarNovoAutor.Endereco
import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest(rollback = false, transactionMode = TransactionMode.SINGLE_TRANSACTION,
transactional = false)
class CasaDoCodigoMicTest {

    @Inject
    lateinit var autorRepository: AutorRepository

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        autorRepository.deleteAll()
        autor = Autor(
            email = "sizenando@gmail.com",
            nome = "sizenando",
            descricao = "entusiasta html",
            endereco =
            Endereco(
                EnderecoViaCepResponse(logradouro = "Rua das rosas", localidade = "Salvador", uf = "Ba"),
                numero = "20E"
            ),
            quantidadeLivrosPublicados = 50
        )
    }

    @Test
    fun `deve inserir um novo autor`() {
        autorRepository.save(autor)

        assertEquals(1, autorRepository.count())
    }

    @Test
    fun `deve encontrar autor por email`() {
        val autorSalvo = autorRepository.save(autor)
        val encontrado = autorRepository.existsByEmail(autorSalvo.email)

        assertTrue(encontrado)
    }
}
