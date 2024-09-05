package com.jdev.jdevcompose.ui

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jdev.jdevcompose.R
import kotlinx.coroutines.launch


@Composable
fun SimpleRecycleView() {

    val users = listOf("Jdev", "Tadeo", "Gaby", "Ale")
    LazyColumn {
        item {
            Text(text = "Header 1")
        }
        items(7) {
            Text(text = "Este es el item $it")
        }
        item {
            Text(text = "Header 2")
        }
        items(users) {
            Text(text = "Hola $it")
        }
    }
}
@Preview(
    showSystemUi = true
)
@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            verticalArrangement =  Arrangement.spacedBy(8.dp),
            state = rvState,
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperheroes()) { hero ->
                ItemHero(
                    superhero = hero,
                    onItemSelected = {
                        Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }

        if(showButton) {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                onClick = {

                    coroutineScope.launch {
                        rvState.animateScrollToItem(0)
                    }

                }
            ) {
                Text(text = "Button")
            }
        }

    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(getSuperheroes()) { hero ->
            ItemHero(
                superhero = hero,
                onItemSelected = {
                    Toast.makeText(context, it.superheroName, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun ItemHero(
    superhero: Superhero,
    onItemSelected: (Superhero) -> Unit
) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            //.width(200.dp)
            .clickable {
                onItemSelected(superhero)
            }
    ) {

        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "hero avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )
        }
    }
}

private fun getSuperheroes(): List<Superhero> {

    return listOf(
        Superhero(
            superheroName = "Spiderman",
            realName = "Petter Parker",
            publisher = "Marvel",
            photo = R.drawable.spiderman
        ),
        Superhero(
            superheroName = "Wolverine",
            realName = "James Howlett",
            publisher = "Marvel",
            photo = R.drawable.logan
        ),
        Superhero(
            superheroName = "Batman",
            realName = "Bruce Wayne",
            publisher = "DC",
            photo = R.drawable.batman
        ),
        Superhero(
            superheroName = "Thor",
            realName = "Thor Odinson",
            publisher = "Marvel",
            photo = R.drawable.thor
        ),
        Superhero(
            superheroName = "Flash",
            realName = "Jay Garrick",
            publisher = "DC",
            photo = R.drawable.flash
        ),
        Superhero(
            superheroName = "Green Lantern",
            realName = "Alan Scoot",
            publisher = "DC",
            photo = R.drawable.green_lantern
        ),
        Superhero(
            superheroName = "Wonder Woman",
            realName = "Pricess Diana",
            publisher = "DC",
            photo = R.drawable.wonder_woman
        ),
    )

}

data class Superhero(
    var superheroName: String,
    var realName: String,
    var publisher: String,
    @DrawableRes var photo: Int
)