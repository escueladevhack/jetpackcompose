package co.devhack.compose.southamericanqualifiers.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview

@Composable
fun Contador(modifier: Modifier = Modifier) {
    val counter = remember { mutableStateOf(0) }
    Text("counter: ${counter.value}", modifier = modifier.clickable {
        counter.value += 1
    })
}

@Preview(showBackground = true)
@Composable
fun ContadorPreview() {
    Column {
        Contador()
        Contador()
        Contador()
        Contador()
    }
}