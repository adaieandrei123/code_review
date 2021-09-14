package com.netguru.codereview.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
//Model for ShopiListItemResponse
data class ShopListItemResponse(
    val itemId: String,
    val name: String,
    val quantity: Double
)
