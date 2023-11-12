package com.kkh.statemanagementtest.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kkh.statemanagementtest.ui.theme.PRIMARY_ORANGE
import com.kkh.statemanagementtest.ui.theme.SECONDARY_ORANGE
import com.kkh.statemanagementtest.ui.util.CustomRadioQuestion
import com.kkh.statemanagementtest.ui.util.DefaultButton

@Composable
fun FinalizedInfoSection(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        CustomRadioQuestion(
            questionTitle = "Is your daily exposure to sun is limited? ",
            radioOptions = listOf("Yes", "No")
        ) {

        }
        CustomRadioQuestion(
            questionTitle = "Do you current smoke (tobacco or marijuana)? ",
            radioOptions = listOf("Yes", "No")
        ) {

        }
        CustomRadioQuestion(
            questionTitle = "On average, how many alcoholic beverages do you have in a week? ",
            radioOptions = listOf("0 - 1", "2 - 5", "5+")
        ) {

        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        )
        {
            DefaultButton(
                btnText = "Got my personalized vitamin",
                isEnable = true,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = PRIMARY_ORANGE,
                    disabledContainerColor = SECONDARY_ORANGE,
                    disabledContentColor = Color.DarkGray

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp)
            ) {
                viewModel.printOutPutResult()
            }
        }
    }
}