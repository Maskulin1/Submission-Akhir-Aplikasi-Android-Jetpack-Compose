package com.reihan.finaljetmoviesapp.ui.screen.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reihan.finaljetmoviesapp.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(400.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_person),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.profile_name),
                    style = TextStyle(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold
                )
            }

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_email),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                val emailUrl = stringResource(R.string.profile_email)
                Text(
                    text = stringResource(R.string.profile_email),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Gray,
                    modifier = Modifier.clickable {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:$emailUrl")
                        }
                        context.startActivity(emailIntent)
                    }
                )
            }

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.profile_birth),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Black
                )
            }

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_university),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.profile_university),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Black
                )
            }

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.profile_location),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_github),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                val githubUrl = stringResource(R.string.profile_github)
                Text(
                    text = stringResource(R.string.profile_github),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                        context.startActivity(intent)
                    }
                )
            }

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_linkedin),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                val linkedinUrl = stringResource(R.string.profile_linkedin)
                Text(
                    text = stringResource(R.string.profile_linkedin),
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}