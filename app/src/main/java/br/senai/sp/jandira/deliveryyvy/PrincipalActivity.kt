package br.senai.sp.jandira.deliveryyvy

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import br.senai.sp.jandira.deliveryyvy.ui.theme.DeliveryYvyTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

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

    val paraty = LatLng(-23.2421, -44.6392)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(paraty, 12f)
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(zIndex = -1f),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = paraty),
                title = "Paraty",
                snippet = "Marker in the best place in the world"
            )
        }
        CardOrder(order = true)
        SliderProducts()
    }

}

@Composable
fun CardOrder(order: Boolean) {
    var stateCard by rememberSaveable {
        mutableStateOf(order)
    }

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
                            onClick = { stateCard = stateCard },
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
}

@Composable
fun CardEntregador() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            Modifier
                .width(190.dp)
                .height(150.dp),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = colorResource(id = R.color.lightgreen_yvy),
            border = BorderStroke(
                2.dp, color = Color(R.color.darkgreen_yvy),
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
                        colorFilter = ColorFilter.tint(Color(R.color.yellow_star))

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

                Card(
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp), shape = CircleShape
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
    Column(verticalArrangement = Arrangement.Bottom) {
        Card(
            Modifier
                .width(190.dp)
                .height(170.dp),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color(R.color.lightgreen_yvy),
            border = BorderStroke(2.dp, color = Color(R.color.darkgreen_yvy))

        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.destination), fontSize = 18.sp)
                    Text(text = "Rua Gloria N°45  - Jandira", fontSize = 14.sp)
                }
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.estimated_time), fontSize = 18.sp)
                    Text(text = "42 min", fontSize = 14.sp)
                }
            }


        }
    }


}

@Composable
fun RapidCard() {
    Card(
        Modifier
            .width(550.dp)
            .height(50.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(R.color.lightgreen_yvy),
        border = BorderStroke(3.dp, Color(R.color.darkgreen_yvy))
    ) {
        Row(
            Modifier.fillMaxWidth(),
//            .background(Color(R.color.lightgreen_yvy)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row() {
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
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    Modifier
                        .width(24.dp)
                        .height(24.dp),
                    colorFilter = ColorFilter.tint(Color(R.color.yellow_star))

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
fun SliderProducts (){


}


@Preview()
@Composable
fun Preview() {
    DeliveryYvyTheme {
//        CardEntregador()
//        CardRoute()
//        RapidCard()
        SliderProducts()
    }
}
