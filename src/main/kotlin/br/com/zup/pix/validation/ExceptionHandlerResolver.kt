package br.com.zup.pix.validation

import io.micronaut.aop.Around

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.TYPE)
@Around
annotation class ErrorAroundHandler


