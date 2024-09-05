package com.jdev.jdevcompose.twitter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdev.jdevcompose.R


@Preview(
    showSystemUi = true,
    device = Devices.PHONE
)
@Composable
fun TwitterPostScreenPrev() {

    TwitterPostScreen()
}

@Composable
fun TwitterPostScreen() {

    Column(
        Modifier
            .background(Color(0xFF212121))
            .fillMaxSize()
    ) {
        Row(
            Modifier.padding(16.dp)
        ) {
            SmallPictureDua()
            TwitterPost()
        }
        PostActions()
        HorizontalDivider()
    }


}

@Composable
fun TwitterPost(
) {
    Column {

        PostInfo()
        PostText()
        PostPhoto()

    }
}

@Composable
fun PostActions() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        CommentButton()

        RetuitButton()

        LikeAction()


    }
}

@Composable
fun CommentButton() {
    var totalCount by remember {
        mutableStateOf(1)
    }

    var commented by remember {
        mutableStateOf(false)
    }

    BadgedBox(
        badge = {

            Badge(
                containerColor = Color.Transparent,
                contentColor = Color.White,
            ) {
                Text(totalCount.toString())
            }

        }
    ) {
        Image(
            painter = painterResource(id = if (commented) R.drawable.ic_chat_filled else R.drawable.ic_chat),
            contentDescription = "ic_chat",
            colorFilter = ColorFilter.tint(if (commented) Color.White else Color.LightGray),
            modifier = Modifier.clickable {
                commented = !commented

                if(commented) {
                    totalCount++
                }
                else {
                    totalCount--
                }
            }
        )

    }
}

@Composable
fun LikeAction() {

    var totalCount by remember {
        mutableStateOf(1)
    }

    var liked by remember {
        mutableStateOf(false)
    }

    BadgedBox(
        badge = {

            Badge(
                containerColor = Color.Transparent,
                contentColor = Color.LightGray,
            ) {
                Text(totalCount.toString())
            }

        }
    ) {

        Image(
            painter = painterResource(id = if (liked) R.drawable.ic_like_filled else R.drawable.ic_like),
            contentDescription = "ic_like",
            colorFilter = if(liked) ColorFilter.tint(Color.Red) else ColorFilter.tint(Color.LightGray),
            modifier = Modifier.clickable {
                liked = !liked

                if(liked) {
                    totalCount++
                }
                else {
                    totalCount--
                }
            }
        )
    }
}


@Composable
fun RetuitButton() {

    var totalCount by remember {
        mutableStateOf(1)
    }

    var retuited by remember {
        mutableStateOf(false)
    }

    BadgedBox(
        badge = {

            Badge(
                containerColor = Color.Transparent,
                contentColor = Color.White,
            ) {
                Text(totalCount.toString())
            }

        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_rt),
            contentDescription = "ic_rt",
            colorFilter = if(retuited) ColorFilter.tint(Color.Green) else ColorFilter.tint(Color.LightGray),
            modifier = Modifier.clickable {
                retuited = !retuited

                if(retuited) {
                    totalCount++
                }
                else {
                    totalCount--
                }
            }
        )

    }
}

@Composable
fun PostPhoto() {
    Image(
        painter = painterResource(id = R.drawable.dualipa),
        contentDescription = "dua",
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))

    )

}

@Composable
fun PostText() {
    Text(
        text = "I love jdev. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sit amet augue turpis. Nullam non justo non massa condimentum condimentum eu vitae nunc. ",
        fontWeight = FontWeight.Normal,
        color = Color.White,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun PostInfo() {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row {
            Text(
                text = "Dua Lipa",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "@dua 4h",
                color = Color.LightGray
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_dots),
            contentDescription = "dots",
            colorFilter = ColorFilter.tint(Color.White)
        )


    }
}


@Composable
fun SmallPictureDua() {
    Image(
        painter = painterResource(id = R.drawable.dl),
        contentDescription = "dua",
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,

        )
}
