package co.devhack.compose.southamericanqualifiers.model

data class Player(
    val id: Int,
    val name: String,
    val surname: String,
    val position: PlayerPosition,
    val teamId: Int
)

enum class PlayerPosition {
    GOALKEEPER,
    DEFENDER,
    MIDFIELDER,
    FORWARD
}