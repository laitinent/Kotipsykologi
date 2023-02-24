package com.example.kotipsykologi

import android.R.string
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotipsykologi.ui.theme.KotipsykologiTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotipsykologiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    // add column and text input

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Kotipsykologi")
        var value by rememberSaveable { mutableStateOf("") }
        var answer by rememberSaveable { mutableStateOf("") }

        TextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Kerro huolesi") }
        )
        Text(text = answer)
        Button(onClick = {
        /*TODO*/
            answer = getAnswer(value)
            value = ""
        }) {
            Text(text ="Valmis")
        }
    }

}

/**
 * Actual logic
 * @param value input text
 * @return answer
 */
fun getAnswer(value: String): String {
    val alut = arrayOf("Miksi ", "Kerro lisää siitä, että ", "Miltä sinusta tuntuu, kun ")
    var loppuN = 0;
    var index=-1
    val sanat = value.lowercase().split(" ").toMutableList();
    for (i in sanat.indices)
    {
        if (sanat[i].endsWith("ni"))
        {
            index = sanat[i].lastIndexOf("ni")
            sanat[i] = sanat[i].substring(0, index) + "si"
        }
        if (sanat[i].startsWith("min"))
        {
            sanat[i] = "sin" + sanat[i].substring(3)
        }
        else
        {
            if (sanat[i].endsWith("n") && sanat[i] != "hän")
            {
                sanat[i] = sanat[i].substring(0, sanat[i].length - 1)+ "t"
            }
        }
        if (sanat[i]== "en")
        {
            sanat[i] = "et";
        }
    }
    var retval=""
    if (value.isNotEmpty()) {
        retval = sanat.joinToString(" ")
        retval = alut.random() + retval;
        //retval = retval[0].toString().uppercase() + retval.substring(1)  // first letter uppercase
    }
    return retval

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotipsykologiTheme {
        Greeting("Android")
    }
}