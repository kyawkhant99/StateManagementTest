package com.kkh.statemanagementtest.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kkh.statemanagementtest.ui.theme.PRIMARY_BG
import com.kkh.statemanagementtest.ui.theme.PRIMARY_ORANGE
import com.kkh.statemanagementtest.ui.theme.SECONDARY_ORANGE
import com.kkh.statemanagementtest.ui.util.CustomAnnotatedString
import com.kkh.statemanagementtest.ui.util.DefaultButton

@Composable
fun CheckBoxItem(item: DietUiModel, onCheckChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = item.isSelected,
            onCheckedChange = onCheckChange,
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = PRIMARY_ORANGE,
                uncheckedColor = Color.DarkGray,
                checkmarkColor = Color.White
            )
        )
        Text(text = item.name)
    }
}

@Composable
fun DietsSection(onPrevious: () -> Unit, onNext: () -> Unit, viewModel: OnBoardingViewModel) {

    val diets by viewModel.dietList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        CustomAnnotatedString(questionTitle = "Select the diets you follow. ")

        diets.forEach { item ->
            CheckBoxItem(item = item, onCheckChange = {
                if (item.id == 0)
                    viewModel.checkNoneDiet(it)
                else if (!it)
                    viewModel.uncheckDiet(item.name)
                else viewModel.checkDiet(item.name)
            })
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PRIMARY_BG)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(0.75f)
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DefaultButton(
                    btnText = "Back",
                    isEnable = true,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = PRIMARY_ORANGE,
                        containerColor = Color.Transparent,
                        disabledContainerColor = SECONDARY_ORANGE,
                        disabledContentColor = Color.DarkGray

                    ),
                    modifier = Modifier.width(100.dp)
                ) {
                    onPrevious()
                }
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
                ) {
                    viewModel.updateDiets()
                    onNext()
                }
            }
        }

    }

}

val DietUIModelList = listOf(
    DietUiModel(0, "None"),
    DietUiModel(1, "Vegan"),
    DietUiModel(2, "Vegetarian"),
    DietUiModel(3, "Pescatarian"),
    DietUiModel(4, "Strict Paleo"),
    DietUiModel(5, "Ketogenic"),
    DietUiModel(6, "Plant Based"),
)

data class DietUiModel(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)