package br.senai.sp.jandira.deliveryyvy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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