package com.kkh.statemanagementtest.ui.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kkh.statemanagementtest.ui.theme.PRIMARY_ORANGE
import com.kkh.statemanagementtest.ui.theme.SECONDARY_ORANGE

@Composable
fun DefaultButton(
    btnText: String,
    modifier: Modifier,
    isEnable: Boolean,
    colors: ButtonColors,
    onClick: ()->Unit
   ){
    Button(
        enabled = isEnable,
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        contentPadding = PaddingValues(0.dp),
        colors = colors,
        modifier = modifier
    ) {
        Text(text = btnText, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun CustomButtonPreview(){
    DefaultButton(
        btnText = "Next",
        isEnable = true,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = PRIMARY_ORANGE,
            disabledContainerColor = SECONDARY_ORANGE,
            disabledContentColor = Color.DarkGray

        ),
        modifier = Modifier.width(100.dp)
    ){

    }
}

