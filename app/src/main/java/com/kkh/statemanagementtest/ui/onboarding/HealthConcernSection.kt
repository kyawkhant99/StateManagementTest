package com.kkh.statemanagementtest.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kkh.statemanagementtest.ui.theme.DragDropList
import com.kkh.statemanagementtest.ui.theme.PRIMARY_DARK_BLUE
import com.kkh.statemanagementtest.ui.util.CustomAnnotatedString

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HealthConcernSection(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    viewModel: OnBoardingViewModel
) {

    val healthConcerns by viewModel.healthConcernListState.collectAsState()

    val prioritiesList by viewModel.priorityList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomAnnotatedString(questionTitle = "Select the top health concerns.")
        Text(text = "(upto 5)", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        FlowRow {
            healthConcerns.onEach {
                HealthConcernChipItem(it) { selected ->
                    viewModel.onSelectChip(selected)
                }
            }
        }
        Text(text = "Prioritize", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        DragDropList(
            items = prioritiesList,
            onNext = {
                viewModel.updateHealthConcerns()
                onNext()
            },
            onPrevious = onPrevious,
            onMove = { fromIndex, toIndex ->
                viewModel.onReorderPriorities(fromIndex, toIndex)
            }
        )
    }
}

val HealthConcernList = listOf(
    HealthConcernUiModel(1, "Sleep"),
    HealthConcernUiModel(2, "Immunity"),
    HealthConcernUiModel(3, "Stress"),
    HealthConcernUiModel(4, "Joint Support"),
    HealthConcernUiModel(5, "Digestion"),
    HealthConcernUiModel(6, "Mood"),
    HealthConcernUiModel(7, "Energy"),
    HealthConcernUiModel(8, "Hair, Nail , Skin"),
    HealthConcernUiModel(9, "Weight Loss"),
    HealthConcernUiModel(10, "Fitness"),
)

data class HealthConcernUiModel(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)

@Composable
@Preview(showBackground = true)
fun HealthConcernChipItem(
    model: HealthConcernUiModel = HealthConcernUiModel(0, "Test"),
    onChipSelect: (HealthConcernUiModel) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .border(
                1.dp,
                if (!model.isSelected) PRIMARY_DARK_BLUE else Color.White,
                RoundedCornerShape(50)
            )
            .clip(RoundedCornerShape(50))
            .clickable {
                onChipSelect(model)
            }
            .background(if (model.isSelected) PRIMARY_DARK_BLUE else Color.White)
            .padding(vertical = 10.dp, horizontal = 14.dp)

    ) {
        Text(
            text = model.name, color = if (model.isSelected) Color.White else PRIMARY_DARK_BLUE,
            modifier = Modifier,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}