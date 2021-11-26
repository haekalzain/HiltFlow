package com.example.hiltcoroutinesflow.model

import java.io.Serializable

data class HiltResponse(
    val total_pages: Long,
    val total: Long
) : Serializable