package br.senai.sp.jandira.deliveryyvy

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle

import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import br.senai.sp.jandira.deliveryyvy.models.User
import br.senai.sp.jandira.deliveryyvy.services.datastore.UserStore


import br.senai.sp.jandira.deliveryyvy.ui.theme.DeliveryYvyTheme
import com.google.android.gms.maps.GoogleMap

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson


import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local


class PrincipalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryYvyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Map()
                }
            }
        }
    }

}




@Composable
fun Map() {

    val origin = LatLng(-23.528720, -46.897987) // SENAI
    val paraty = LatLng(-23.2421, -44.6392) // Paraty
        
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(zIndex = -1f)
                .background(colorResource(id = R.color.green_yvy)),
        ){
            Image(painter = painterResource(id =R.drawable.logo_no_name_transparent), contentDescription = null )
        }
        CardOrder(
            order = true,
            origin_lat = origin.latitude.toString(),
            origin_long = origin.longitude.toString(),
            destination_lat = paraty.latitude.toString(),
            destination_long = paraty.longitude.toString()
        )

    }

}

@Composable
fun CardOrder(
    order: Boolean,
    origin_lat: String,
    origin_long: String,
    destination_lat: String,
    destination_long: String
) {
    var stateCard by rememberSaveable {
        mutableStateOf(order)
    }
    var stateDecision by rememberSaveable {
        mutableStateOf(false)
    }
    var stateProduct by rememberSaveable {
        mutableStateOf(false)
    }
    var _origin_lat = origin_lat
    var _origin_long = origin_long
    var _destination_lat = destination_lat
    var _destination_long = destination_long
    val context = LocalContext.current

    if (stateCard) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
        ) {}
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .height(465.dp)
                    .zIndex(zIndex = 1f)
                    .padding(17.dp), backgroundColor = colorResource(
                    id = R.color.lightgreen_yvy
                ),
                shape = RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp)
            ) {
                Column(
                    Modifier.padding(start = 20.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        text = stringResource(id = R.string.new_delivery),
                        color = colorResource(
                            id = R.color.darkgreen_yvy
                        ),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = stringResource(id = R.string.start),
                        color = colorResource(
                            id = R.color.darkgreen_yvy
                        ),
                        fontSize = 24.sp,
                    )
                    Text(
                        text = "Rua São Bento - Jandira",
                        Modifier.padding(start = 30.dp),
                        color = colorResource(
                            id = R.color.darkgreen_yvy
                        ),
                        fontSize = 18.sp,
                    )
                    Text(
                        text = stringResource(id = R.string.destination),
                        color = colorResource(
                            id = R.color.darkgreen_yvy
                        ),
                        fontSize = 24.sp,
                    )
                    Text(
                        text = "Rua São Bento - Jandira",
                        Modifier.padding(start = 30.dp),
                        color = colorResource(
                            id = R.color.darkgreen_yvy
                        ),
                        fontSize = 18.sp,
                    )
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Button(
                            onClick = {
                                stateCard = false
                                stateDecision = true
                            },
                            colors = ButtonDefaults.buttonColors(colorResource(R.color.green_yvy))
                        ) {
                            Text(
                                text = stringResource(id = R.string.accepted),
                                color = colorResource(
                                    id = R.color.white
                                ),
                                fontSize = 25.sp
                            )
                        }
                        Button(
                            onClick = { stateCard = !stateCard },
                            colors = ButtonDefaults.buttonColors(colorResource(R.color.red_button))
                        ) {
                            Text(
                                text = stringResource(id = R.string.decline), color = colorResource(
                                    id = R.color.white
                                ), fontSize = 25.sp
                            )
                        }
                    }
                }
            }
        }

    }
    if (stateDecision) {

        stateDecision = false
        stateProduct = true

        val intencao = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                "http://maps.google.com/maps?saddr=" + _origin_lat + " " + _origin_long + " " + "&daddr="
                        + _destination_lat + " " + _destination_long
            )
        )
        context.startActivity(intencao)
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
            }) {
                Text(text = "balao")
            }
            Row(
                Modifier
                    .height(250.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    Modifier
                        .fillMaxWidth()
                ) {
                    CardsRoute()
                }

            }

        }
    }
    if (stateProduct) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SlidingBarProduct()
        }

    }

}

@Composable
fun CardEntregador() {
    val context = LocalContext.current
    Card(
        Modifier
            .width(190.dp)
            .height(150.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = colorResource(id = R.color.lightgreen_yvy),
        border = BorderStroke(
            2.dp, color = colorResource(R.color.darkgreen_yvy),
        )
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = null,
                    Modifier
                        .width(46.dp)
                        .height(46.dp)
                        .padding(start = 10.dp)
                )
                Text(
                    text = "João",
                    Modifier.padding(start = 10.dp),
                    color = Color(R.color.darkgreen_yvy),
                    fontSize = 28.sp
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp),
                    colorFilter = ColorFilter.tint(colorResource(R.color.yellow_star))

                )
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp),

                    )
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
                Text(text = "4.2", color = Color(R.color.darkgreen_yvy), fontSize = 20.sp)
            }




        }
    }
}


@Composable
fun RapidCard() {
    val context = LocalContext.current
    Card(
        Modifier
            .width(550.dp)
            .height(80.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = colorResource(id = R.color.lightgreen_yvy),
        border = BorderStroke(3.dp, Color(R.color.darkgreen_yvy))
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = null,
                    Modifier
                        .width(56.dp)
                        .height(56.dp)
                )
                Text(
                    text = "João",
                    Modifier.padding(start = 10.dp),
                    color = colorResource(R.color.darkgreen_yvy),
                    fontSize = 32.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp),
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.yellow_star))
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp),

                    )
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(text = "4.2", color = colorResource(R.color.darkgreen_yvy), fontSize = 24.sp)
                Spacer(modifier = Modifier.padding(start = 15.dp))
                Card(
                    modifier = Modifier
                        .height(55.dp)
                        .width(55.dp)
                        .clickable {
                            val intent = Intent(context, DeliveryChat()::class.java)
                            context.startActivity(intent)
                        },
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_email),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun CardRoute() {
    Card(
        Modifier
            .width(200.dp)
            .height(200.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = colorResource(R.color.lightgreen_yvy),
        border = BorderStroke(2.dp, color = colorResource(R.color.darkgreen_yvy))

    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.destination), fontSize = 24.sp)
                Text(text = "Rua Gloria N°45", fontSize = 20.sp)
            }
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.estimated_time), fontSize = 24.sp)
                Text(text = "42 min", fontSize = 20.sp)
            }
        }


    }
}


@Composable
fun SlidingBarProduct() {

    var sliderPosition by remember { mutableStateOf(0.09f) }
    var sliderVisibility by remember { mutableStateOf(true) }

    if (sliderVisibility) {


        RapidCard()
        Card(
            Modifier
                .height(150.dp)
                .fillMaxWidth(0.97f)
                .padding(top = 15.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = stringResource(id = R.string.collected_products) + "?",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .width(362.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                0.0f to colorResource(id = R.color.lightgreen_yvy),
                                0.5f to colorResource(R.color.green_yvy),
                                1.0f to colorResource(R.color.dark_darkgreen),
                                startX = 0f,
                                endX = Float.POSITIVE_INFINITY
                            )
                        )
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                val position = (change.position.x - dragAmount.x) / size.width
                                sliderPosition = position.coerceIn(0.09f, 0.9f)
                                sliderVisibility = true
                            }
                        }
                        .drawWithContent {
                            drawContent()
                            drawCircle(
                                color = Color.White,
                                radius = 20.dp.toPx(),
                                center = Offset(size.width * sliderPosition, size.height / 2)
                            )
                        }
                ) {}
            }
        }

    }



    if (sliderPosition == 0.9f) {
        sliderVisibility = false
        SlidingBarOrder()
    }


}

@Composable
fun SlidingBarOrder() {
    var sliderVisibility by remember { mutableStateOf(true) }
    var sliderPosition by remember { mutableStateOf(0.09f) }


    if (sliderVisibility) {
        RapidCard()
        Card(
            Modifier
                .height(150.dp)
                .fillMaxWidth(0.97f)
                .padding(top = 15.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = stringResource(id = R.string.finish_delivery),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .width(362.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                0.0f to Color.White,
                                0.5f to Color.Red,
                                1.0f to Color(243, 43, 43),
                                startX = 0f,
                                endX = Float.POSITIVE_INFINITY
                            )
                        )
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                val position = (change.position.x - dragAmount.x) / size.width
                                sliderPosition = position.coerceIn(0.09f, 0.9f)
                            }
                        }
                        .drawWithContent {
                            drawContent()
                            drawCircle(
                                color = Color.White,
                                radius = 20.dp.toPx(),
                                center = Offset(size.width * sliderPosition, size.height / 2)
                            )
                        }
                ) {
                }
            }
        }

    }
    if (sliderPosition == 0.9f) {
        sliderVisibility = false

    }


}

@Composable
fun CardsRoute() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CardEntregador()
        CardRoute()
    }

}

@Composable
fun CallPreview() {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SlidingBarProduct()
        //SlidingBarOrder()
    }
}


@Preview()
@Composable
fun Preview() {
    DeliveryYvyTheme {
        CallPreview()
    }
}
