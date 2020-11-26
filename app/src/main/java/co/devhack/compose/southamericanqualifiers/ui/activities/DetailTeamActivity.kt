package co.devhack.compose.southamericanqualifiers.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import co.devhack.compose.southamericanqualifiers.R
import co.devhack.compose.southamericanqualifiers.data.Repository
import co.devhack.compose.southamericanqualifiers.model.Player
import co.devhack.compose.southamericanqualifiers.model.PlayerPosition
import co.devhack.compose.southamericanqualifiers.ui.SouthAmericanQualifiersTheme
import java.util.*

class DetailTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewArgentinaTeam()
        }
    }
}

@Composable
fun DetailTeamActivityUI(
    modifier: Modifier = Modifier,
    nameTeam: String,
    flagTeam: Int,
    matchesWon: Int,
    matchesLost: Int,
    matchesDraw: Int,
    players: List<Player>
) {
    SouthAmericanQualifiersTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colors.background
        ) {
            ContentDetailTeam(
                nameTeam = nameTeam,
                flagTeam = flagTeam,
                matchesWon = matchesWon,
                matchesLost = matchesLost,
                matchesDraw = matchesDraw,
                players = players
            )
        }
    }
}

@Composable
fun ContentDetailTeam(
    modifier: Modifier = Modifier,
    nameTeam: String,
    flagTeam: Int,
    matchesWon: Int,
    matchesLost: Int,
    matchesDraw: Int,
    players: List<Player>
) {
    Scaffold(modifier = modifier,
        topBar = {
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
        }) { innerPadding ->
        BodyContentDetailTeam(
            modifier = Modifier.padding(innerPadding).padding(8.dp),
            nameTeam = nameTeam,
            flagTeam = flagTeam,
            matchesWon = matchesWon,
            matchesLost = matchesLost,
            matchesDraw = matchesDraw,
            players = players
        )
    }
}

@Composable
fun BodyContentDetailTeam(
    modifier: Modifier,
    nameTeam: String,
    flagTeam: Int,
    matchesWon: Int,
    matchesLost: Int,
    matchesDraw: Int,
    players: List<Player>
) {
    Column(modifier = modifier) {
        DetailHeadTeam(nameTeam = nameTeam, flagTeam = flagTeam)
        Divider(
            color = Color.Blue,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
        DetailMatchTeam(
            matchesWon = matchesWon,
            matchesLost = matchesLost,
            matchesDraw = matchesDraw
        )
        Divider(
            color = Color.Blue,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
        Text(
            stringResource(R.string.players),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Players(players = players)
    }
}

@Composable
fun DetailHeadTeam(modifier: Modifier = Modifier, nameTeam: String, flagTeam: Int) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp),
        elevation = 8.dp,
        border = BorderStroke(2.dp, Color.Blue)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                Icon(
                    Icons.Filled.Flag,
                    tint = colorResource(id = R.color.purple_700),
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
                Text(
                    text = nameTeam.toUpperCase(Locale.ROOT),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }
            Image(
                vectorResource(flagTeam),
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun DetailMatchTeam(
    modifier: Modifier = Modifier,
    matchesWon: Int,
    matchesLost: Int,
    matchesDraw: Int
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
            TeamStatistics(
                matchesLabel = stringResource(R.string.matches_won),
                statistic = matchesWon
            )
            TeamStatistics(
                matchesLabel = stringResource(R.string.matches_draw),
                statistic = matchesLost
            )
            TeamStatistics(
                matchesLabel = stringResource(R.string.matches_draw),
                statistic = matchesDraw
            )
        }
    }
}

@Composable
fun TeamStatistics(matchesLabel: String, statistic: Int) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Text(matchesLabel, fontWeight = FontWeight.Bold)
        Text(
            text = statistic.toString(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun Players(modifier: Modifier = Modifier, players: List<Player>) {
    LazyColumnFor(items = players,
        modifier = modifier.fillMaxSize(),
        itemContent = { player ->
            PlayerItem(player)
        })
}

@Composable
fun PlayerItem(player: Player) {
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
                .clickable(onClick = { /* Ignoring onClick */ }),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ImagePlayerItem(player.uriPhoto)
            InfoPlayerItem(player)
            InfoPlayerMatchesItem(player)
        }
    }
}

@Composable
fun ImagePlayerItem(@DrawableRes uriPhoto: Int) {

    val resource = imageResource(id = uriPhoto)
    Image(
        resource,
        modifier = Modifier
            .size(72.dp)
            .padding(4.dp)
            .drawShadow(8.dp, CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun InfoPlayerItem(player: Player) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Text(player.name, fontWeight = FontWeight.Bold)
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Text(player.position.name, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun InfoPlayerMatchesItem(player: Player) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Text(
            stringResource(id = R.string.match_played),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Text(
                player.matchPlayed.toString(),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(
    name = "Argentina",
    group = "1"
)
@Composable
fun PreviewArgentinaTeam() {
    DetailTeamActivityUI(
        nameTeam = "Argentina",
        flagTeam = R.drawable.ic_launcher_background,
        matchesWon = 5,
        matchesDraw = 5,
        matchesLost = 3,
        players = Repository.argentinaPlayers
    )
}