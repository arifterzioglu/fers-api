package com.metu.fers.application.controller.exception

import org.apache.commons.lang3.builder.ToStringBuilder

class ErrorParametersResponse(
    var content: String,
) {
    override fun toString() = ToStringBuilder.reflectionToString(this)!!
}
