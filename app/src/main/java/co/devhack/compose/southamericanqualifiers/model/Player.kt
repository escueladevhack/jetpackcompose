package co.devhack.compose.southamericanqualifiers.model

import androidx.annotation.DrawableRes
import co.devhack.compose.southamericanqualifiers.R

data class Player(
    val id: Int,
    val name: String,
    val surname: String,
    val position: PlayerPosition,
    val teamId: Int,
    val matchPlayed: Int = 5,
    @DrawableRes val uriPhoto: Int = R.drawable.default_player,
)

enum class PlayerPosition {
    GOALKEEPER,
    DEFENDER,
    MIDFIELDER,
    FORWARD
}