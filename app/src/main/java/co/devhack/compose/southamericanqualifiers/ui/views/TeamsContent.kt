package co.devhack.compose.southamericanqualifiers.ui.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import co.devhack.compose.southamericanqualifiers.R
import co.devhack.compose.southamericanqualifiers.model.Team
import java.util.Locale

@Composable
fun TeamContent(
    modifier: Modifier = Modifier,
    playedMatches: Int,
    totalMatches: Int,
    teams: List<Team>,
    onTeamClicked: (Team) -> Unit = {}
) {
    Column(modifier = modifier) {
        HeaderQualifies()
        Divider(
            color = Color.Blue,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
        DetailMatchesQualifiers(
            teamsSize = teams.size,
            playedMatches = playedMatches,
            totalMatches = totalMatches
        )
        Divider(
            color = Color.Blue,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
        Text(
            stringResource(R.string.teams),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Teams(teams = teams) {
            onTeamClicked(it)
        }
    }
}

@Composable
fun HeaderQualifies(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp),
        elevation = 8.dp,
        border = BorderStroke(2.dp, Color.Blue)
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                Text(
                    text = stringResource(id = R.string.south_american_qualifiers)
                        .toUpperCase(Locale.ROOT),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2
                )
                Icon(
                    Icons.Filled.SportsSoccer,
                    tint = colorResource(id = R.color.purple_700)
                )
            }
        }
    }
}

@Composable
fun DetailMatchesQualifiers(
    modifier: Modifier = Modifier,
    teamsSize: Int,
    playedMatches: Int,
    totalMatches: Int,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
        border = BorderStroke(2.dp, Color.Blue)
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(stringResource(R.string.teams), fontWeight = FontWeight.Bold)
                Text(
                    text = teamsSize.toString(),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(stringResource(R.string.match_played), fontWeight = FontWeight.Bold)
                Text(
                    text = "$playedMatches / $totalMatches",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun Teams(
    modifier: Modifier = Modifier,
    teams: List<Team>,
    onTeamClicked: (Team) -> Unit
) {
    LazyColumnFor(
        items = teams,
        modifier = modifier.fillMaxWidth()) { item ->
        TeamItem(item) {
            onTeamClicked(it)
        }
    }
}

@Composable
fun TeamItem(team: Team, callback: (Team) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
        border = BorderStroke(2.dp, Color.Blue)
    ) {
        Row(
            Modifier.padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
                .clickable(onClick = {
                    callback.invoke(team)
                }),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ImageTeamItem(team.flag)
            InfoTeamItem(team)
        }
    }
}

@Composable
fun ImageTeamItem(@DrawableRes uriFlagTeam: Int) {
    Surface(
        modifier = Modifier.preferredSize(50.dp),
        shape = CircleShape,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    ) {
        //Image(vectorResource(uriFlagTeam))
        //TODO: bandera
    }
}

@Composable
fun InfoTeamItem(team: Team) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Text(
            team.name, fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TeamStatistics(
                matchesLabel = stringResource(R.string.matches_won),
                statistic = team.matchesWon
            )
            TeamStatistics(
                matchesLabel = stringResource(R.string.matches_draw),
                statistic = team.matchesLost
            )
            TeamStatistics(
                matchesLabel = stringResource(R.string.matches_draw),
                statistic = team.matchesDraw
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTeams() {
    TeamContent(
        playedMatches = 10,
        totalMatches = 30,
        teams = mutableListOf<Team>().apply {
            add(Team(1, "Argentina", 0, 1, 2, 3))
            add(Team(2, "Bolivia", 0, 1, 2, 3))
            add(Team(3, "Brasil", 0, 1, 2, 3))
            add(Team(4, "Chile", 0, 1, 2, 3))
            add(Team(5, "Colombia", 0, 1, 2, 3))
        }
    )
}