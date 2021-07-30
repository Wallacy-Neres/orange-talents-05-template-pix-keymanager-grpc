package br.com.zup.pix.registra

import br.com.zup.pix.exception.KeyExists
import br.com.zup.pix.validation.ErrorAroundHandler
import io.micronaut.data.annotation.Repository
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
@Validated
class NovaChavePixService(@Inject val repository: ChavePixRepository, @Inject val itauClient: ContasDeClientesNoItauClient){

    @Transactional
    fun registra(@Valid novaChave : NovaChavePix) : ChavePix{

        //1. Verifica se chave já existe no sistema
        if(repository.existsByChave(novaChave.chave))
            throw KeyExists("Chave Pix '${novaChave.chave}' existente")

        //2. busca dados da conta no ERP do Itaú
        val response = itauClient.buscaContaPorTipo(novaChave.clienteId!!, novaChave.tipoDeConta!!.name)
        val conta = response.body()?.toModel() ?: throw IllegalStateException("Cliente não encontrado no Itaú")

        //3. Grava no banco de dados
        val chave = novaChave.toModel(conta)
        repository.save(chave)

        return chave
    }
}

