package co.devhack.compose.southamericanqualifiers.model

import androidx.annotation.DrawableRes

data class Team(
    val id: Int,
    val name: String,
    @DrawableRes val flag: Int,
    val matchesWon: Int = 0,
    val matchesLost: Int = 0,
    val matchesDraw: Int = 0
)