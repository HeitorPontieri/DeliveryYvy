package br.senai.sp.jandira.deliveryyvy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.deliveryyvy.ui.theme.DeliveryYvyTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryYvyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login() {

    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }



    Column {
        Text(
            text = stringResource(id = R.string.login),
            color = colorResource(id = R.color.green_yvy),
            fontSize = 48.sp
        )
        Text(
            text = stringResource(id = R.string.email),
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(value = emailState, onValueChange = { emailState = it })
        Text(
            text = stringResource(id = R.string.password),
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(
            value = passwordState, onValueChange = { passwordState = it },
            Modifier.clickable {passwordVisibility = true},
            trailingIcon = {
                val img = if (passwordVisibility) {
                    Icon(
                        painter = painterResource(id = R.drawable.eyeslash),
                        contentDescription = null
                    )
                } else Icon(
                    painter = painterResource(id = R.drawable.eye),
                    contentDescription = null
                )
            },
        )
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(3.dp, color = colorResource(id = R.color.green_yvy))
        ) {
            Text(
                text = stringResource(id = R.string.login),
                Modifier
                    .width(130.dp),
                color = colorResource(id = R.color.green_yvy),
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

