package com.example.unit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit.ui.theme.UnitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContent {
            UnitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}

@Composable
fun UnitConvertor(){
    var outValue by remember {mutableStateOf("")}
    var inValue by remember {mutableStateOf("")}
    var inUnit by remember {mutableStateOf("")}
    var outUnit by remember {mutableStateOf("")}
    var iExpanded by remember {mutableStateOf(false)}
    var oExpanded by remember {mutableStateOf(false)}
    val conversionFactor = remember{ mutableStateOf(0.00) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Convertor")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inValue, 
            onValueChange = {inValue=it},
            label = { Text(text = "Enter value")}
            )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            
            Box{
                Button(onClick = { iExpanded=true }) {
                    if(inUnit==""){
                        Text(text = "Select")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    }else{
                        Text(inUnit)
                    }
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            inUnit="Centimeter"
                            iExpanded=false
                            conversionFactor.value=100.00
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            inUnit="Meters"
                            iExpanded=false
                            conversionFactor.value=1.00
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            inUnit="Feet"
                            iExpanded=false
                            conversionFactor.value=3.28084
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            inUnit="Millimeters"
                            iExpanded=false
                            conversionFactor.value=1000.00
                        }
                    )

                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Box {
                Button(onClick = { oExpanded=true }) {
                    if(outUnit==""){
                        Text(text = "Select")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    }else{
                        Text(outUnit)
                    }
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            outUnit="Centimeter"
                            oExpanded=false
                            conversionFactor.value=100/conversionFactor.value
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            outUnit="Meters"
                            oExpanded=false
                            conversionFactor.value=1/conversionFactor.value
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            outUnit="Feet"
                            oExpanded=false
                            conversionFactor.value=3.28084/conversionFactor.value
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            outUnit="Millimeters"
                            oExpanded=false
                            conversionFactor.value=1000/conversionFactor.value
                        }
                    )

                }
            }

            }

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        Button(onClick = {
            Toast.makeText(context,"Thanks for clicking",Toast.LENGTH_LONG).show()
            outValue=(inValue.toDouble()*conversionFactor.value).toString()
            conversionFactor.value=0.00
        }) {
            Text(text = "Click Me!!")
        }
        OutlinedTextField(value = outValue ,
            onValueChange = {outValue=it},
            label = { Text(text = "Answer")}
            )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
        UnitConvertor()
}