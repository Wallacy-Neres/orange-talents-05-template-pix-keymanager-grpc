package br.com.zup.pix.validation

import br.com.zup.pix.exception.ChavePixNaoEncontradaExcption
import br.com.zup.pix.exception.KeyExists
import br.com.zup.pix.exception.KeyRegisterException
import io.grpc.Status
import io.grpc.stub.StreamObserver
import io.micronaut.aop.InterceptorBean
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import io.micronaut.http.client.exceptions.HttpClientResponseException
import javax.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
@InterceptorBean(ErrorAroundHandler::class)
class ExceptionHandlerInterceptor : MethodInterceptor<Any, Any> {
    override fun intercept(context: MethodInvocationContext<Any, Any>): Any? {
        try {
            return context.proceed()
        } catch (e: Exception) {
            e.printStackTrace()
            val responseObserver = context.parameterValues[1] as StreamObserver<*>

            val status = when (e) {
                is ConstraintViolationException -> Status.INVALID_ARGUMENT.withCause(e)
                    .withDescription("Dados invalidos")
                is HttpClientResponseException -> Status.INVALID_ARGUMENT.withCause(e)
                    .withDescription("O ID do cliente não é válido")
                is KeyRegisterException -> Status.NOT_FOUND.withCause(e).withDescription("Cliente não encontrado")
                is KeyExists -> Status.ALREADY_EXISTS.withCause(e).withDescription("Chave já cadastrada")
                is ChavePixNaoEncontradaExcption -> Status.NOT_FOUND.withCause(e).withDescription(e.message)
                else -> Status.UNKNOWN.withCause(e).withDescription("Erro desconhecido")
            }

            responseObserver.onError(status.asRuntimeException())
        }

        return null
    }


}