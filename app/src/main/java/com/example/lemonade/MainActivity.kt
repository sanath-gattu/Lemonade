package com.example.lemonade

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.magnifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
  LemonadeApp(context = this)

            }
        }
    }
}




fun getNextStep(currentStep: Int) : Int {

  return  when(currentStep) {

        R.string.lemon_tree -> R.string.lemon
      R.string.lemon -> R.string.glass_of_lemonade
      R.string.glass_of_lemonade -> R.string.empty_glass
      R.string.empty_glass -> R.string.lemon_tree
        else -> R.string.lemon_tree
    }

}

@Composable
fun LemonadeApp(context: Context){

    var tapCount = 0
    var  currentState by remember { mutableStateOf(R.string.lemon_tree) }
    var currentText by remember {
        mutableStateOf(R.string.tap_lemon_tree)
    }

    var imageResource = when(currentState){
        R.string.lemon_tree -> R.drawable.lemon_tree
        R.string.lemon -> R.drawable.lemon_squeeze
        R.string.glass_of_lemonade -> R.drawable.lemon_drink
        R.string.empty_glass -> R.drawable.lemon_restart
        else -> R.string.lemon_tree
    }
    currentText = when(currentState){
        R.string.lemon_tree -> R.string.tap_lemon_tree
        R.string.lemon -> R.string.keep_tapping_lemon
        R.string.glass_of_lemonade -> R.string.tap_lemonade
        R.string.empty_glass -> R.string.tap_empty_glass
        else -> R.string.tap_lemon_tree
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()

    ) {

        Image(painter = painterResource(id = imageResource), contentDescription = stringResource(R.string.lemon_tree) , modifier = Modifier.clickable {
            if(currentState == R.string.lemon && tapCount < 5){

                tapCount ++


                Toast.makeText( context, "Tapped ${tapCount.toString()}", LENGTH_SHORT).show()


            }else{
                currentState = getNextStep(currentState)
            }
        })
        Spacer(modifier = Modifier.height(16.dp) )
        Text(stringResource(currentText))

    }



}