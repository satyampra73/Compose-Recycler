package com.satyam.composerecycler.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyam.composerecycler.activity.ui.theme.ComposeRecyclerTheme
import com.satyam.composerecycler.model.TvShow
import kotlin.coroutines.Continuation

class InfoActivity : ComponentActivity() {

    companion object{
        private const val TvShowId = "tvshowid"
        fun newIntent(context: Context, tvShow: TvShow) =
            Intent(context, InfoActivity::class.java).apply {
                putExtra(TvShowId, tvShow)
            }
    }

    private val tvShow : TvShow by lazy {
        intent?.getSerializableExtra(TvShowId) as TvShow
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        ViewMoreInfo(tvShow = tvShow)
        }
    }
}

@Composable
fun ViewMoreInfo(tvShow: TvShow){
    val scrollState = rememberScrollState()
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column (
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(10.dp)
        ){
            Image(painter = painterResource(id = tvShow.imageId),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Fit
                )
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = tvShow.name,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = tvShow.overview,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Original release : ${tvShow.year}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "IMDB : ${tvShow.rating}",
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}

