package co.devhack.compose.southamericanqualifiers.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.devhack.compose.southamericanqualifiers.R
import co.devhack.compose.southamericanqualifiers.model.Team
import co.devhack.compose.southamericanqualifiers.ui.views.TeamContent
import co.devhack.compose.southamericanqualifiers.ui.SouthAmericanQualifiersTheme
import co.devhack.compose.southamericanqualifiers.viewmodels.TeamsViewModel

class TeamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = viewModels<TeamsViewModel>().value
            val state = viewModel.teams.observeAsState()

            when(val currentState = state.value) {
                is TeamsViewModel.State.Loading -> CircularProgressIndicator()
                is TeamsViewModel.State.Ready -> TeamActivityUI(5, 5, currentState.teams)
            }
        }
    }
}

@Composable
fun TeamActivityUI(
    playedMatches: Int,
    totalMatches: Int,
    teams: List<Team>,
    modifier: Modifier = Modifier
) {
    SouthAmericanQualifiersTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colors.background
        ) {
            ContentTeam(
                playedMatches = playedMatches,
                totalMatches = totalMatches,
                teams = teams
            )
        }
    }
}

@Composable
fun ContentTeam(
    modifier: Modifier = Modifier,
    playedMatches: Int,
    totalMatches: Int,
    teams: List<Team>
) {
    Scaffold(
        modifier = modifier,
        topBar = { TeamsTopBar() }
    ) { innerPadding ->

        TeamContent(
            modifier = Modifier.padding(innerPadding).padding(8.dp),
            playedMatches = playedMatches,
            totalMatches = totalMatches,
            teams = teams
        )
    }
}

@Composable
fun TeamsTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.south_american_qualifiers))
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.SportsSoccer, tint = colorResource(id = R.color.white))
            }
        }
    )
}
