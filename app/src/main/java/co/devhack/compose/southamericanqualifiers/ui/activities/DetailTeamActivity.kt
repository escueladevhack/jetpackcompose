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
import co.devhack.compose.southamericanqualifiers.ui.flagColor
import co.devhack.compose.southamericanqualifiers.ui.views.ContentDetailTeam
import co.devhack.compose.southamericanqualifiers.ui.views.PreviewArgentinaTeam
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
    SouthAmericanQualifiersTheme(darkTheme = false) {
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
