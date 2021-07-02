package br.com.zup.cadastrarNovoAutor

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
class Autor(
    @field:NotBlank @field:Email val email: String?,
    @field:NotBlank val nome: String?,
    @field:NotBlank @field:Size(max = 400) val descricao: String?
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val criadoEm: LocalDateTime = LocalDateTime.now()

}
