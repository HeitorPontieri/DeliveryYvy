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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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

    }

}

@Composable
fun CardOrder(order: Boolean) {
    var stateCard by rememberSaveable {
        mutableStateOf(order)
    }
    var stateDecision by rememberSaveable {
        mutableStateOf(!order)
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
                            onClick = { stateDecision = true },
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
            if (stateDecision){
                Column(verticalArrangement = Arrangement.Bottom) {
                    Row() {
                        CardEntregador()
                        CardRoute()
                    }
                }


            }
        }

    }
}

@Composable
fun CardEntregador() {
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

                Card(
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp),
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

@Composable
fun CardRoute() {
        Card(
            Modifier
                .fillMaxWidth()
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
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
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
fun RapidCard() {
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
                Spacer(modifier = Modifier.padding(start = 15.dp))
                Text(text = "4.2", color = colorResource(R.color.darkgreen_yvy), fontSize = 26.sp)
            }
        }
    }
}


@Composable
fun SlidingBarProduct() {

    var sliderPosition by remember { mutableStateOf(0.09f) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        RapidCard()

        Text(
            text = stringResource(id = R.string.collected_products) + "?",
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

@Composable
fun SlidingBarOrder() {

    var sliderPosition by remember { mutableStateOf(0.09f) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        RapidCard()

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
                        0.0f to Color.LightGray,
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

@Composable
fun B (){
    Row(Modifier.fillMaxWidth()) {
        
        CardEntregador()
        Spacer(modifier = Modifier.padding(start = 10.dp))
        CardRoute()
    }
}

@Composable
fun A() {
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
        A()
    }
}
