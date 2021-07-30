package br.com.zup.pix.registra

import io.micronaut.core.annotation.Introspected
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank

@Embeddable
@Introspected
class ContaAssociada (
    @field:NotBlank
    @field:Column(nullable = false)
    val agencia: String,

    @field:NotBlank
    @field:Column(nullable = false)
    val numeroDeConta: String,

    @field:NotBlank
    @field:Column(nullable = false)
    val nomeDoTitular: String,

    @field:NotBlank
    @field:Column(nullable = false)
    val cpfTitular: String,

    @field:NotBlank
    @field:Column(nullable = false)
    val instituicao: String
    ){
    companion object {
    val ITAU_UNIBANCO_ISPB: String = "60701190"
}
}

