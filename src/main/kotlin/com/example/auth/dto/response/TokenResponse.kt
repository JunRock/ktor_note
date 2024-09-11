package com.example.auth.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(val accessToken: String)
