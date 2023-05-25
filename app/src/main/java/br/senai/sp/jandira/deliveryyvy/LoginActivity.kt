package br.senai.sp.jandira.deliveryyvy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.deliveryyvy.api.commons.getDetailsOfUser
import br.senai.sp.jandira.deliveryyvy.ui.theme.DeliveryYvyTheme
import br.senai.sp.jandira.deliveryyvy.api.commons.auth
import br.senai.sp.jandira.deliveryyvy.dto.Credentials
import br.senai.sp.jandira.deliveryyvy.services.datastore.UserStore
import br.senai.sp.jandira.deliveryyvy.services.datastore.TokenStore
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryYvyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login() {
    var clickToLogin by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var isEmailError by remember {
        mutableStateOf(false)
    }
    var isPasswordErrorEmpty by remember {
        mutableStateOf(false)
    }
    val inputsFocusRequest = FocusRequester()

    val mMaxLength = 8
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";


    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.login),
            color = colorResource(id = R.color.green_yvy),
            fontSize = 48.sp
        )
        Spacer(modifier = Modifier.padding(top = 30.dp))
        Text(
            text = stringResource(id = R.string.email),
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(
            value = emailState,
            onValueChange = {
                isEmailError = if (it.isEmpty()) {
                    true
                } else {
                    it[it.length - 1]
                    false
                }
                emailState = it
            },
            isError = isEmailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = colorResource(id = R.color.gray_input))
        )
        if (isEmailError) {
            Text(
                text = stringResource(id = R.string.email_error),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(
            text = stringResource(id = R.string.password),
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        TextField(
            value = passwordState, onValueChange = {

                if (it.isEmpty()) {
                    isPasswordErrorEmpty = true
                } else {
                    it.get(it.length - 1)
                    isPasswordErrorEmpty = false
                }
                passwordState = it
            },
            trailingIcon = {
                val img = if (passwordVisibility) {
                    Icons.Filled.Visibility
                } else Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = img,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = colorResource(id = R.color.gray_input))
        )
        if (isPasswordErrorEmpty) {
            Text(
                text = stringResource(id = R.string.message_error_pass1),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.padding(top = 30.dp))

        val scope = rememberCoroutineScope()

        Button(
            onClick = {
                val credentials = Credentials(emailState, passwordState)

                auth(credentials) {
                    Log.d("teste", it.token)
                    if (it.token.isNotEmpty()) {
                        scope.launch {
                            val dataStore = TokenStore(context)
                            dataStore.saveToken(it.token)
                            scope.launch {
                                getDetailsOfUser(it.token) { user ->
                                    val userStore = UserStore(context)
                                    scope.launch {
                                        val gson = Gson()
                                        userStore.saveDetails(gson.toJson(user))
                                        val intent = Intent(
                                            context,
                                            PrincipalActivity()::class.java
                                        )
                                        context.startActivity(intent)
                                    }
                                }
                            }
                        }
                        // OPEN NEXT Activity
//                        val intent = Intent(context, InicialScreen()::class.java)
//                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, "Nao foi possivel fazer Login!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                val intent = Intent(
                    context,
                    PrincipalActivity()::class.java
                )
                context.startActivity(intent)
            },
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

