package br.senai.sp.jandira.deliveryyvy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import br.senai.sp.jandira.deliveryyvy.ui.theme.DeliveryYvyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryYvyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InicialMain()
                }
            }
        }
    }
}

@Composable
fun InicialMain() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.wave_login),
            contentDescription = null,
            Modifier
                .zIndex(-1f)
                .fillMaxWidth()
                .height(533.dp),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier
                    .zIndex(2f)
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.capacete_icon),
                    contentDescription = null,
                    Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
                Text(
                    text = stringResource(id = R.string.yvypora),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
            }
            Text(
                text = stringResource(id = R.string.yvypora),
                color = colorResource(id = R.color.darkgreen_yvy),
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
            Text(
                text = stringResource(id = R.string.yvypora_delivery),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 40.sp
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, LoginActivity()::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green_yvy))
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    Modifier
                        .width(130.dp),
                    color = colorResource(id = R.color.white),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(120.dp))
            Text(
                text = stringResource(id = R.string.signup),
                Modifier.clickable {
                    /*
                        -  TODO
                            -FAZER A ACTIVITY DE CADASTRO
                    */
                },
                color = colorResource(id = R.color.darkgreen_yvy),
                fontSize = 30.sp,
                textDecoration = TextDecoration.Underline
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeliveryYvyTheme {
    }
}