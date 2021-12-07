package com.example.core.model.network

open class ErrorResponse(
    var timestamp: String?,
    var status: Int?,
    var error: String?,
    var message: String?,
    var path: String?
)