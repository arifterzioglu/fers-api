package com.metu.fers.application.controller.exception

import com.metu.fers.domain.exception.CustomerAlreadyCreatedException
import com.metu.fers.domain.exception.CustomerNotFoundException
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.exception.ExceptionUtils
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier

@RestControllerAdvice
class GlobalControllerExceptionHandler(private val messageSource: MessageSource) {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val tr = Locale("tr")

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(additionalInfo = "MethodArgumentNotValidException", false, HttpStatus.BAD_REQUEST.value())
        prepareBindingResult(exception.bindingResult, errorResponse)
        logger.error("Field validation failed. Caused By:{}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(additionalInfo = "IllegalArgumentException", false, HttpStatus.BAD_REQUEST.value())
        errorResponse.addError(ErrorParametersResponse(content = exception.message!!))
        logger.error("IllegalArgumentException Caused by: {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(exception: BindException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(additionalInfo = "BindException", false, HttpStatus.BAD_REQUEST.value())
        errorResponse.addError(ErrorParametersResponse(content = exception.message))
        logger.error("BindException Caused by: {}", errorResponse)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(exception: NullPointerException): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(additionalInfo = "NullPointerException", false, HttpStatus.INTERNAL_SERVER_ERROR.value())
        val detailResponse = ErrorParametersResponse(content = exception.message!!)
        errorResponse.addError(detailResponse)
        logger.error("Null Pointer Exception caused by: {}", errorResponse, exception)
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(java.lang.Exception::class)
    fun handleGenericException(exception: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(additionalInfo = "Exception", false, HttpStatus.BAD_REQUEST.value())
        errorResponse.addError(ErrorParametersResponse(content = exception.message ?: "Unexpected"))
        logger.error(
            "Unexpected exception occurred. Caused By:{}, stackTrace: {}",
            errorResponse,
            ExceptionUtils.getStackTrace(exception)
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CustomerAlreadyCreatedException::class)
    fun handleCxSearchOrderNotFoundException(exception: CustomerAlreadyCreatedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            additionalInfo = "CustomerAlreadyCreatedException",
            false,
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "${exception.message} Parameters: ${exception.paramaters}"
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(CustomerNotFoundException::class)
    fun handleCxSearchOrderNotFoundException(exception: CustomerNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            additionalInfo = "CustomerNotFoundException",
            false,
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "${exception.message} Parameters: ${exception.paramaters}"
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private fun prepareBindingResult(bindingResult: BindingResult, errorDTO: ErrorResponse) {
        bindingResult.fieldErrors.forEach(Consumer { i: FieldError ->
            errorDTO.addError(
                ErrorParametersResponse(
                    content = getMessage(i.defaultMessage, listOf(i.arguments.toString()), StringUtils.EMPTY),
                )
            )
        })
    }

    private fun getMessage(key: String?, args: List<String>, defaultMessage: String): String {
        return Optional.of(getMessage { messageSource.getMessage(key!!, args.toTypedArray(), tr) })
            .filter { cs: String? -> StringUtils.isNotBlank(cs) }
            .orElseGet { defaultMessage }
    }

    private fun getMessage(supplier: Supplier<String>): String {
        return try {
            supplier.get()
        } catch (e: Exception) {
            logger.warn("Exception occurred : ", e)
            StringUtils.EMPTY
        }
    }
}
