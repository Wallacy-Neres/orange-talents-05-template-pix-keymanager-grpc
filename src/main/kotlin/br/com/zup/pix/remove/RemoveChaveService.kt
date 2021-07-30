package br.com.zup.pix.remove

import br.com.zup.pix.exception.ChavePixNaoEncontradaExcption
import br.com.zup.pix.registra.ChavePixRepository
import io.micronaut.validation.Validated
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Validated
@Singleton
class RemoveChaveService(@Inject val repository: ChavePixRepository) {

    @Transactional
    fun remove(
        @NotBlank(message = "cliente ID com formato inválido") clienteId: String?,
        @NotBlank(message = "pix ID com formato inválido") pixId: String?,
    ){
        val uuidPixId = UUID.fromString(pixId)
        val uuidClienteId = UUID.fromString(clienteId)

        val chave = repository.findByIdAndClienteId(uuidPixId, uuidClienteId)
            .orElseThrow{ throw ChavePixNaoEncontradaExcption("Chave pix não encontrada ou não pertence ao usuario") }

        repository.deleteById(uuidPixId)
    }
}
