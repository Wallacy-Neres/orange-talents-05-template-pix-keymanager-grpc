package br.com.zup.pix.registra

import br.com.zup.TipoDeChave
import br.com.zup.TipoDeConta
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class ChavePix(

    @ValidaUUID
    @field:NotNull
    @Column(nullable = false)
    val clienteId: UUID,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipo: br.com.zup.pix.registra.TipoDeChave,

    @field:NotBlank
    @Column(unique = true, nullable = false)
    val chave: String,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoDeConta: TipoDeConta?,

    @field:Valid
    @Embedded
    val conta: ContaAssociada

) {

    @Id
    @GeneratedValue
    val id: UUID? = null

    @Column(nullable = false)
    val criadoEm : LocalDateTime = LocalDateTime.now()


    override fun toString(): String {
        return "ChavePix(clienteId=$clienteId, tipo=$tipo, chave='$chave', tipoDeConta=$tipoDeConta, conta=$conta, id=$id, criadoEm=$criadoEm)"
    }


}
