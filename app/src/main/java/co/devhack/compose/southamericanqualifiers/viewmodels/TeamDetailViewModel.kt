package co.devhack.compose.southamericanqualifiers.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import co.devhack.compose.southamericanqualifiers.data.Repository
import co.devhack.compose.southamericanqualifiers.model.Player
import co.devhack.compose.southamericanqualifiers.model.Team

class TeamDetailViewModel : ViewModel() {

    fun detail(id: Int) = liveData {
        emit(State.Loading)

        val team = Repository.getTeam(id)
        val players = Repository.getPlayers(id)
        emit(State.Ready(team, players))
    }

    sealed class State {
        object Loading : State()
        data class Ready(
            val team: Team,
            val players: List<Player>
        ) : State()
    }

}