package com.metu.fers.application.controller.exception

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.builder.ToStringBuilder

class ErrorResponse {
    var additionalInfo: String = ""
    var isSuccess: Boolean = false
    var parameters = mutableListOf<ErrorParametersResponse>()
    var statusCode: Int = 0

    constructor()

    constructor(additionalInfo: String = "", isSuccess: Boolean = false, statusCode: Int = 0, vararg exceptionMessage: String?) {
        if (StringUtils.isNotBlank(additionalInfo)) this.additionalInfo = additionalInfo
        this.isSuccess = isSuccess
        this.statusCode = statusCode

        if (!exceptionMessage.isNullOrEmpty()) {
            exceptionMessage.forEach {
                parameters.add(ErrorParametersResponse(content = it!!))
            }
        }
    }

    fun addError(errorParameters: ErrorParametersResponse) {
        parameters.add(errorParameters)
    }

    override fun toString() = ToStringBuilder.reflectionToString(this)!!
}
