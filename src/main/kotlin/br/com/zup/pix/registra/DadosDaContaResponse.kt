package br.com.zup.pix.registra

data class DadosDaContaResponse(

    val tipo: String,
    val instituicao: InstituicaoResponse,
    val agencia: String,
    val numero: String,
    val titular: TitularResponse
) {

    fun toModel(): ContaAssociada {
        return ContaAssociada(
            instituicao = this.instituicao.nome,
            nomeDoTitular = this.titular.nome,
            cpfTitular = this.titular.cpf,
            agencia = this.agencia,
            numeroDeConta = this.numero
        )
    }

    data class TitularResponse(val nome: String, val cpf: String)
    data class InstituicaoResponse(val nome: String, val ispb: String)
}
