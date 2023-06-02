package br.senai.sp.jandira.deliveryyvy.api.commons

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.DesignElements.map
import br.senai.sp.jandira.deliveryyvy.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun RouteScreen(apiKey: String) {
    val webViewRef = remember { WebViewRef() }

    LaunchedEffect(Unit) {
        webViewRef.awaitLoadFinished()

        val origin = LatLng(37.791433, -122.408005) // San Francisco, CA
        val destination = LatLng(37.7577627, -122.4726194) // SFO International Airport

        val url = "https://www.google.com/maps/embed/v1/directions?key=$apiKey&origin=${origin.latitude},${origin.longitude}&destination=${destination.latitude},${destination.longitude}&mode=driving"
        Log.i("url", url)
        webViewRef.webView?.evaluateJavascript(("document.getElementById('map').src = '$url';"),null)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true

                    webViewRef.webView = this
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Adicione seus botões de interface de usuário aqui
    }
}

class WebViewRef {
    var webView: WebView? = null

    private val loadFinished = CompletableDeferred<Unit>()

    suspend fun awaitLoadFinished() {
        loadFinished.await()
    }

    fun onLoadFinished() {
        loadFinished.complete(Unit)
    }
}
