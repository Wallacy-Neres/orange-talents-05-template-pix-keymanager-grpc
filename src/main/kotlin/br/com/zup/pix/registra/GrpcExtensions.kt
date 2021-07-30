package br.com.zup.pix.registra

import br.com.zup.RegistraChavePixRequest
import br.com.zup.TipoDeConta

fun RegistraChavePixRequest.toModel() : NovaChavePix{
    return NovaChavePix(
        clienteId = clientId,
        tipo = when (tipoDeChave){
            br.com.zup.TipoDeChave.UNKNOWN_TIPO_CHAVE -> null
            else -> TipoDeChave.valueOf(tipoDeChave.name)
        },

        chave = chave,
        tipoDeConta = when (tipoDeConta){
            TipoDeConta.UNKNOWN_TIPO_CONTA -> null
            else -> TipoDeConta.valueOf(tipoDeConta.name)
        }
    )
}