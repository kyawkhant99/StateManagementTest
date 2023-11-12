package com.kkh.statemanagementtest.ui.util

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kkh.statemanagementtest.ui.theme.PRIMARY_ORANGE
@Composable
fun CustomAnnotatedString(questionTitle:String) {

    Text(
    buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(questionTitle)
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = PRIMARY_ORANGE,
                fontSize = 20.sp
            )
        ) {
            append("*")
        }
    })
}