package co.devhack.compose.southamericanqualifiers.model

import androidx.annotation.DrawableRes

data class Player(
    val id: Int,
    val name: String,
    val surname: String,
    val position: PlayerPosition,
    val teamId: Int,
    val matchPlayed: Int = 5,
    @DrawableRes val uriPhoto: Int = 0,
)

enum class PlayerPosition {
    GOALKEEPER,
    DEFENDER,
    MIDFIELDER,
    FORWARD
}