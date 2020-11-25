package co.devhack.southamericanqualifiersworkshop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import co.devhack.southamericanqualifiersworkshop.data.Repository
import co.devhack.southamericanqualifiersworkshop.model.Team

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