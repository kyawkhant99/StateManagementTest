package com.kkh.statemanagementtest.ui.onboarding

import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kkh.statemanagementtest.ui.theme.PRIMARY_BG
import com.kkh.statemanagementtest.ui.theme.PRIMARY_DARK_BLUE
import com.kkh.statemanagementtest.ui.theme.PRIMARY_ORANGE
import com.kkh.statemanagementtest.ui.theme.SECONDARY_ORANGE
import com.kkh.statemanagementtest.ui.util.DefaultButton

@Composable
fun SpecificAllergiesSection(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    viewModel: OnBoardingViewModel
) {

    val allergies by viewModel.allergyListState.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        
        Text(
            text = "Write any specific  allergeies or sensitivity towards specific things. (optional)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PRIMARY_BG)
        ) {
            AutoCompleteDropDown(
                items = allergies.filter { !it.isSelected }.map { it.name },
                onItemSelected = viewModel::onAllergySelect,
                selectedList = allergies.filter { it.isSelected }.map { it.name },
                onItemUnselect = viewModel::onAllergyUnselect
            )
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
                    viewModel.updateAllergies()
                    onNext()
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteDropDown(
    items: List<String>,
    selectedList: List<String>,
    onItemSelected: (String) -> Unit,
    onItemUnselect: (String) -> Unit,
) {
    val selectedItem by remember {
        mutableStateOf("")
    }
    var expanded by remember { mutableStateOf(false) }
    var listItems by remember(items, selectedItem) {
        mutableStateOf(
            if (selectedItem.isNotEmpty()) {
                items.filter { x -> x.startsWith(selectedItem.lowercase(), ignoreCase = true) }
            } else {
                items.toList()
            }
        )
    }
    var selectedText by remember(selectedItem) { mutableStateOf(selectedItem) }

    LaunchedEffect(selectedItem) {
        selectedText = selectedItem
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .clip(RoundedCornerShape(10))
            .background(Color.LightGray)
            .padding(horizontal = 16.dp, vertical = 2.dp)

    ) {
        selectedList.onEach {
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(PRIMARY_DARK_BLUE)
                    .padding(vertical = 10.dp, horizontal = 14.dp)
            ) {
                Text(
                    text = it, color = Color.White,
                    modifier = Modifier,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
            }
        }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedText,
                label = null,
                onValueChange = {
                    if (!expanded) {
                        expanded = true
                    }
                    selectedText = it
                    listItems = if (it.isNotEmpty()) {
                        items.filter { x -> x.startsWith(it.lowercase(), ignoreCase = true) }
                    } else {
                        items.toList()
                    }
                },
                trailingIcon = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .onKeyEvent {
                        return@onKeyEvent if (it.key == Key.Backspace && selectedText.isEmpty() && selectedList.isNotEmpty()) {
                            val last = selectedList[selectedList.size - 1]
                            selectedText = last
                            onItemUnselect(last)
                            true
                        } else false
                    },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent,
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if (listItems.isEmpty()) {
                    DropdownMenuItem(
                        text = { Text(text = "No items found") },
                        onClick = {
                            expanded = false
                        }
                    )
                } else {
                    listItems.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                expanded = false
                                onItemSelected(item)
                                selectedText = ""
                            }
                        )
                    }
                }
            }
        }
    }
}


data class AllergyUiModel(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)

val AllergyList = listOf(
    AllergyUiModel(1, "Milk"),
    AllergyUiModel(2, "Meat"),
    AllergyUiModel(3, "Weat"),
    AllergyUiModel(4, "Nasacort"),
    AllergyUiModel(5, "Nasalide"),
    AllergyUiModel(6, "Nasaonex"),
)