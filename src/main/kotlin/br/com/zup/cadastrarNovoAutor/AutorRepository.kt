package br.com.zup.cadastrarNovoAutor

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

    //Query methods
    //fun findByEmail(email: String): Optional<Autor>

    //Query explicit
    @Query("FROM Autor a WHERE a.email=:email")
    fun buscaPoremail(email: String): Optional<Autor>

    fun existsByEmail(cpf: String): Boolean
}