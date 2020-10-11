package co.devhack.compose.southamericanqualifiers.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import co.devhack.compose.southamericanqualifiers.data.Repository
import co.devhack.compose.southamericanqualifiers.model.Team

class TeamsViewModel : ViewModel() {

    val teams = liveData {
        emit(State.Loading)
        emit(State.Ready(Repository.getTeams()))
    }

    sealed class State {
        object Loading : State()
        data class Ready(val teams: List<Team>) : State()
    }
}