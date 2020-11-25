package co.devhack.southamericanqualifiersworkshop.ui.activities

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import co.devhack.southamericanqualifiersworkshop.R
import co.devhack.southamericanqualifiersworkshop.model.Player
import co.devhack.southamericanqualifiersworkshop.model.PlayerPosition
import co.devhack.southamericanqualifiersworkshop.ui.commons.SouthAmericanQualifiersWorkshopTheme
import java.util.*

class DetailTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailTeamActivityUI(
                nameTeam = "Argentina",
                flagTeam = R.drawable.ic_launcher_background,
                matchesWon = 5,
                matchesDraw = 5,
                matchesLost = 3,
                players = mutableListOf<Player>().apply {
                    add(
                        Player(
                            id = 1,
                            name = "Juan G",
                            surname = "Gomez",
                            position = PlayerPosition.DEFENDER,
                            teamId = 1
                        )
                    )
                    add(
                        Player(
                            id = 1,
                            name = "Matias ",
                            surname = "Torres",
                            position = PlayerPosition.FORWARD,
                            teamId = 1
                        )
                    )
                    add(
                        Player(
                            id = 1,
                            name = "Pelufo",
                            surname = "Gomez",
                            position = PlayerPosition.MIDFIELDER,
                            teamId = 1
                        )
                    )
                }
            )
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
    SouthAmericanQualifiersWorkshopTheme {
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
                    Text(stringResource(id = R.string.south_american_qualifiers))
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.SportsSoccer,
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
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
    modifier: Modifier = Modifier,
    nameTeam: String,
    flagTeam: Int,
    matchesWon: Int,
    matchesLost: Int,
    matchesDraw: Int,
    players: List<Player>
) {
    Column(modifier = modifier) {
        DetailHeaderTeam(nameTeam = nameTeam, flagTeam = flagTeam)
        DividerDetailTeam()
        DetailMatchTeam(
            matchesWon = matchesWon,
            matchesLost = matchesLost,
            matchesDraw = matchesDraw
        )
        DividerDetailTeam()
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
fun DividerDetailTeam() {
    Divider(
        color = Color.Blue,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    )
}

@Composable
fun DetailHeaderTeam(modifier: Modifier = Modifier, nameTeam: String, flagTeam: Int) {
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
                    style = MaterialTheme.typography.body2
                )
            }
            Image(
                vectorResource(id = flagTeam),
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
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

}

@Composable
fun Players(modifier: Modifier = Modifier, players: List<Player>) {
    LazyColumnFor(items = players,
        modifier = modifier.fillMaxSize(),
        itemContent = { player ->
            PlayerItem(player = player)
        })
}

@Composable
fun PlayerItem(modifier: Modifier = Modifier, player: Player) {
    Card(
        modifier = modifier
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
            ImagePlayerItem(uriPhoto = player.uriPhoto)
            InfoPlayerItem(player = player)
            InfoPlayerMatchesItem(player = player)
        }
    }
}

@Composable
fun ImagePlayerItem(modifier: Modifier = Modifier, @DrawableRes uriPhoto: Int) {
    Surface(
        modifier = modifier.preferredSize(50.dp),
        shape = CircleShape,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    ) {
        //Image(vectorResource(uriPhoto))
        //TODO: imagen del jugador
    }
}

@Composable
fun InfoPlayerItem(modifier: Modifier = Modifier, player: Player) {
    Column(
        modifier = modifier
            .padding(start = 8.dp)
    ) {
        Text(player.name, fontWeight = FontWeight.Bold)
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Text(player.position.name, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun InfoPlayerMatchesItem(modifier: Modifier = Modifier, player: Player) {
    Column(
        modifier = modifier
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

@Preview(showBackground = true)
@Composable
fun PreviewDetailTeam() {
    DetailTeamActivityUI(
        nameTeam = "Argentina",
        flagTeam = R.drawable.ic_launcher_background,
        matchesWon = 5,
        matchesDraw = 5,
        matchesLost = 3,
        players = mutableListOf<Player>().apply {
            add(
                Player(
                    id = 1,
                    name = "Juan G",
                    surname = "Gomez",
                    position = PlayerPosition.DEFENDER,
                    teamId = 1
                )
            )

            add(
                Player(
                    id = 1,
                    name = "Matias ",
                    surname = "Torres",
                    position = PlayerPosition.FORWARD,
                    teamId = 1
                )
            )

            add(
                Player(
                    id = 1,
                    name = "Pelufo",
                    surname = "Gomez",
                    position = PlayerPosition.MIDFIELDER,
                    teamId = 1
                )
            )
        }
    )
}