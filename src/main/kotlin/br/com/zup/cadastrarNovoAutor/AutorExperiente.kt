package br.com.zup.cadastrarNovoAutor

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [AutorValidator::class])
annotation class AutorExperiente(val message: String = "Autor inexperiente")

@Singleton
class AutorValidator : ConstraintValidator<AutorExperiente, Int> {
    override fun isValid(
        value: Int?,
        annotationMetadata: AnnotationValue<AutorExperiente>,
        context: ConstraintValidatorContext
    ): Boolean {
        return value!!.compareTo(50) >= 0
    }

}
