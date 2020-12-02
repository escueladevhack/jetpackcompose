package co.devhack.compose.southamericanqualifiers.data

import co.devhack.compose.southamericanqualifiers.R
import co.devhack.compose.southamericanqualifiers.model.Player
import co.devhack.compose.southamericanqualifiers.model.PlayerPosition
import co.devhack.compose.southamericanqualifiers.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

object Repository {

    private const val DELAY = 1000L

    private val teams = listOf(
        Team(1, "Argentina", R.drawable.ic_launcher_background),
        Team(2, "Bolivia", R.drawable.ic_launcher_background),
        Team(3, "Brasil", R.drawable.ic_launcher_background),
        Team(4, "Chile", R.drawable.ic_launcher_background),
        Team(5, "Colombia", R.drawable.ic_launcher_background),
        Team(6, "Ecuador", R.drawable.ic_launcher_background),
        Team(7, "Paraguay", R.drawable.ic_launcher_background),
        Team(8, "Perú", R.drawable.ic_launcher_background),
        Team(9, "Uruguay", R.drawable.ic_launcher_background),
        Team(10, "Venezuela", R.drawable.ic_launcher_background),
    )

    val argentinaPlayers = listOf(
        // Argentina
        Player(1, "Lionel", "Messi", PlayerPosition.FORWARD, 1, uriPhoto = R.drawable.messi),
        Player(2, "Paulo", "Dybala", PlayerPosition.FORWARD, 1, uriPhoto = R.drawable.messi),
        Player(3, "Sergio", "Agüero", PlayerPosition.FORWARD, 1, uriPhoto = R.drawable.messi),
        Player(4, "Lautaro", "Martinez", PlayerPosition.FORWARD, 1, uriPhoto = R.drawable.messi),
        Player(5, "Franco", "Armani", PlayerPosition.GOALKEEPER, 1, uriPhoto = R.drawable.messi),
        Player(6, "Lucas", "Ocampos", PlayerPosition.MIDFIELDER, 1, uriPhoto = R.drawable.messi),
        Player(7, "Rodrigo", "De Paul", PlayerPosition.MIDFIELDER, 1, uriPhoto = R.drawable.messi),
        Player(8, "Leandro", "Paredes", PlayerPosition.MIDFIELDER, 1, uriPhoto = R.drawable.messi),
        Player(9, "Marcos", "Acuña", PlayerPosition.MIDFIELDER, 1, uriPhoto = R.drawable.messi),
        Player(10, "Nicolas", "Otamendi", PlayerPosition.DEFENDER, 1, uriPhoto = R.drawable.messi),
        Player(11, "Nicolas", "Tagliafico", PlayerPosition.DEFENDER, 1, uriPhoto = R.drawable.messi),
        Player(12, "Lucas", "Martinez Quarta", PlayerPosition.DEFENDER, 1, uriPhoto = R.drawable.messi),
        Player(13, "Gonzalo", "Montiel", PlayerPosition.DEFENDER, 1, uriPhoto = R.drawable.messi),
        Player(14, "Lucas", "Alario", PlayerPosition.FORWARD, 1, uriPhoto = R.drawable.messi),
        Player(15, "Nehuén", "Perez", PlayerPosition.DEFENDER, 1, uriPhoto = R.drawable.messi)
    )

    val boliviaPlayers = listOf(
        Player(16, "Carlos", "Lampe", PlayerPosition.GOALKEEPER, 2),
        Player(17, "José", "Sagredo", PlayerPosition.DEFENDER, 2),
        Player(18, "Gabriel", "Valverde", PlayerPosition.DEFENDER, 2),
        Player(19, "José", "Carrasco", PlayerPosition.DEFENDER, 2),
        Player(20, "Jesús Manuel", "Sagredo Chávez", PlayerPosition.DEFENDER, 2),
        Player(21, "Bruno", "Miranda", PlayerPosition.MIDFIELDER, 2),
        Player(22, "Luis Fernando", "Saldías Muños", PlayerPosition.MIDFIELDER, 2),
        Player(23, "Diego", "Wayar", PlayerPosition.MIDFIELDER, 2),
        Player(24, "Antonio", "Bustamante", PlayerPosition.MIDFIELDER, 2),
        Player(25, "Christian", "Árabe", PlayerPosition.MIDFIELDER, 2),
        Player(26, "César", "Menacho", PlayerPosition.FORWARD, 2),
        Player(27, "Guimer", "Justiniano", PlayerPosition.DEFENDER, 2),
        Player(28, "Boris", "Cespedes", PlayerPosition.MIDFIELDER, 2),
        Player(29, "Jhasmani", "Campos", PlayerPosition.MIDFIELDER, 2),
        Player(30, "Franz", "Gonzales", PlayerPosition.MIDFIELDER, 2)
    )

    private val players = listOf(
        // Argentina
        Player(1, "Lionel", "Messi", PlayerPosition.FORWARD, 1),
        Player(2, "Paulo", "Dybala", PlayerPosition.FORWARD, 1),
        Player(3, "Sergio", "Agüero", PlayerPosition.FORWARD, 1),
        Player(4, "Lautaro", "Martinez", PlayerPosition.FORWARD, 1),
        Player(5, "Franco", "Armani", PlayerPosition.GOALKEEPER, 1),
        Player(6, "Lucas", "Ocampos", PlayerPosition.MIDFIELDER, 1),
        Player(7, "Rodrigo", "De Paul", PlayerPosition.MIDFIELDER, 1),
        Player(8, "Leandro", "Paredes", PlayerPosition.MIDFIELDER, 1),
        Player(9, "Marcos", "Acuña", PlayerPosition.MIDFIELDER, 1),
        Player(10, "Nicolas", "Otamendi", PlayerPosition.DEFENDER, 1),
        Player(11, "Nicolas", "Tagliafico", PlayerPosition.DEFENDER, 1),
        Player(12, "Lucas", "Martinez Quarta", PlayerPosition.DEFENDER, 1),
        Player(13, "Gonzalo", "Montiel", PlayerPosition.DEFENDER, 1),
        Player(14, "Lucas", "Alario", PlayerPosition.FORWARD, 1),
        Player(15, "Nehuén", "Perez", PlayerPosition.DEFENDER, 1),

        // Bolivia
        Player(16, "Carlos", "Lampe", PlayerPosition.GOALKEEPER, 2),
        Player(17, "José", "Sagredo", PlayerPosition.DEFENDER, 2),
        Player(18, "Gabriel", "Valverde", PlayerPosition.DEFENDER, 2),
        Player(19, "José", "Carrasco", PlayerPosition.DEFENDER, 2),
        Player(20, "Jesús Manuel", "Sagredo Chávez", PlayerPosition.DEFENDER, 2),
        Player(21, "Bruno", "Miranda", PlayerPosition.MIDFIELDER, 2),
        Player(22, "Luis Fernando", "Saldías Muños", PlayerPosition.MIDFIELDER, 2),
        Player(23, "Diego", "Wayar", PlayerPosition.MIDFIELDER, 2),
        Player(24, "Antonio", "Bustamante", PlayerPosition.MIDFIELDER, 2),
        Player(25, "Christian", "Árabe", PlayerPosition.MIDFIELDER, 2),
        Player(26, "César", "Menacho", PlayerPosition.FORWARD, 2),
        Player(27, "Guimer", "Justiniano", PlayerPosition.DEFENDER, 2),
        Player(28, "Boris", "Cespedes", PlayerPosition.MIDFIELDER, 2),
        Player(29, "Jhasmani", "Campos", PlayerPosition.MIDFIELDER, 2),
        Player(30, "Franz", "Gonzales", PlayerPosition.MIDFIELDER, 2),
    )

    suspend fun getTeams() = withContext(Dispatchers.IO) {
        delay(DELAY)
        teams
    }

    suspend fun getTeam(id: Int) = withContext(Dispatchers.IO) {
        delay(DELAY)
        teams.first { it.id == id }
    }

    suspend fun getPlayers(teamId: Int) = withContext(Dispatchers.IO) {
        delay(DELAY)
        players.filter { it.teamId == teamId }
    }

    suspend fun getPlayer(playerId: Int) = withContext(Dispatchers.IO) {
        delay(DELAY)
        players.first { it.id == playerId }
    }
}