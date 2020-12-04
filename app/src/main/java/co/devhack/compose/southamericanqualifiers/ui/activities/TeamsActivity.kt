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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import co.devhack.compose.southamericanqualifiers.R
import co.devhack.compose.southamericanqualifiers.model.Team
import co.devhack.compose.southamericanqualifiers.ui.views.TeamContent
import co.devhack.compose.southamericanqualifiers.ui.SouthAmericanQualifiersTheme
import co.devhack.compose.southamericanqualifiers.ui.views.TeamDetailContent
import co.devhack.compose.southamericanqualifiers.viewmodels.TeamDetailViewModel
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
    val navController = rememberNavController()

    val defaultTitle = stringResource(id = R.string.south_american_qualifiers)
    val titleState = mutableStateOf(defaultTitle)

    Scaffold(
        modifier = modifier,
        topBar = { TeamsTopBar(titleState.value) }
    ) { innerPadding ->

        NavHost(navController, startDestination = "teams") {
            composable(route = "teams") {
                TeamContent(
                    modifier = Modifier.padding(innerPadding).padding(8.dp),
                    playedMatches = playedMatches,
                    totalMatches = totalMatches,
                    teams = teams,
                    onTeamClicked = {
                        navController.navigate("teams/${it.id}?name=${it.name}")
                    }
                )
            }
            composable(route = "teams/{id}?name={name}") {
                val id = it.arguments?.getString("id")?.toIntOrNull() ?: return@composable
                NavigationTeamDetail(id)
            }
        }

        navController.addOnDestinationChangedListener { controller, destination, args ->
            titleState.value = if(destination.id == controller.graph.startDestination) {
                defaultTitle
            } else {
                args?.getString("name") ?: defaultTitle
            }
        }

    }
}

@Composable
fun NavigationTeamDetail(id: Int) {
    val viewModel = remember { TeamDetailViewModel() }
    val data = remember { viewModel.detail(id) }
    val content = data.observeAsState().value ?: return

    when(content) {
        is TeamDetailViewModel.State.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
        is TeamDetailViewModel.State.Ready -> {
            TeamDetailContent(
                nameTeam = content.team.name,
                flagTeam = content.team.flag,
                matchesWon = content.team.matchesWon,
                matchesLost = content.team.matchesLost,
                matchesDraw = content.team.matchesDraw,
                players = content.players
            )
        }
    }

}

@Composable
fun TeamsTopBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.SportsSoccer, tint = colorResource(id = R.color.white))
            }
        }
    )
}
