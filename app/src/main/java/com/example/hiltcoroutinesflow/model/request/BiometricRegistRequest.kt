package com.example.hiltcoroutinesflow.model.request

import java.io.Serializable

data class BiometricRegistRequest(
    val cifId: Int,
    val cifCode: String,
    val cifName: String,
    val applicationId: String,
    val actionBy: String,
    val versionTemplate: String = "co-code",
    val image: String
) : Serializable

data class BiometricVerifyRequest(
    val image: String
) : Serializable