package org.refurbi.app.domain.model.category


import kotlinx.serialization.SerialName

@Serializable
data class Categories(
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("imageUrl")
    val imageUrl: String = "",
    @SerialName("isVisible")
    val isVisible: Boolean = false,
    @SerialName("name")
    val name: String = ""
)