package com.kkh.statemanagementtest.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kkh.statemanagementtest.ui.theme.PRIMARY_ORANGE

@Composable
fun CustomRadioQuestion(
    questionTitle: String,
    radioOptions: List<String>,
    onClick: (String) -> Unit
) {

    var selectedOption by remember {
        mutableStateOf(radioOptions[0])
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        CustomAnnotatedString(questionTitle = questionTitle)
        radioOptions.forEach { option ->

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = PRIMARY_ORANGE,
                        unselectedColor = Color.DarkGray
                    ),
                    onClick = {
                        selectedOption = option
                        onClick(selectedOption)
                    }
                )
                Text(text = option)
            }

        }
    }

}

@Preview
@Composable
fun CustomRadioQuestionPreview() {
    CustomRadioQuestion(
        questionTitle = "Is your daily exposure to sun is limited? ",
        radioOptions = listOf("Yes", "No")
    ) {

    }
}