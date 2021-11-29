package com.example.hiltcoroutinesflow.model.response

import java.io.Serializable

data class BiometricRegistResponse(
    val cifId: Long,
    val cifCode: String,
    val cifName: String,
    val applicationId: String,
    val personId: String,
    val createdBy: String,
    val updatedBy: String,
    val deletedBy: String,
    val versionTemplate: String
//"versionTemplate": null,
//"createdAt": [
//2021,
//11,
//26,
//9,
//14,
//43,
//797342683
//],
//"updatedBy": "co-code",
//"updatedAt": [
//2021,
//11,
//26,
//9,
//14,
//43,
//797573941
//],
//"deletedBy": null,
//"deletedAt": null
) : Serializable