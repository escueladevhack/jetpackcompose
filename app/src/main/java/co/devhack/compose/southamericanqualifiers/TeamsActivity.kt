package co.devhack.compose.southamericanqualifiers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import co.devhack.compose.southamericanqualifiers.model.Team
import co.devhack.compose.southamericanqualifiers.ui.SouthAmericanQualifiersTheme
import co.devhack.compose.southamericanqualifiers.viewmodels.TeamsViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SouthAmericanQualifiersTheme {
                val viewModel = viewModels<TeamsViewModel>().value
                val teamsState = viewModel.teams.observeAsState()
                TeamsScreen(teamsState)
            }
        }
    }

    @Composable
    fun TeamsScreen(teamsState: State<TeamsViewModel.State?>) {
        Surface(color = MaterialTheme.colors.background) {
            when (val state = teamsState.value) {
                is TeamsViewModel.State.Loading -> Text("Loading...")
                is TeamsViewModel.State.Ready -> TeamsList(state.teams)
            }
        }
    }
}

@Composable
fun TeamsList(teams: List<Team>) {
    LazyColumnFor(teams) { team ->
        Text(team.name)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SouthAmericanQualifiersTheme {
        TeamsList(
            listOf(
                Team(1, "Argentina", 0)
            )
        )
    }
}