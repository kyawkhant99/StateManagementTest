package com.kkh.statemanagementtest.ui.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkh.statemanagementtest.model.Allergy
import com.kkh.statemanagementtest.model.Diet
import com.kkh.statemanagementtest.model.HealthConcern
import com.kkh.statemanagementtest.model.OutputItem
import com.kkh.statemanagementtest.ui.theme.move
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnBoardingViewModel : ViewModel() {


    private val _healthConcernListState: MutableStateFlow<List<HealthConcernUiModel>> =
        MutableStateFlow(
            HealthConcernList
        )

    val healthConcernListState = _healthConcernListState.asStateFlow()

    private val _allergyListState = MutableStateFlow(AllergyList)

    val allergyListState = _allergyListState.asStateFlow()

    private val _dietList = MutableStateFlow(DietUIModelList)

    val dietList = _dietList.asStateFlow()

    private val _priorityList: MutableStateFlow<List<HealthConcernUiModel>> =
        MutableStateFlow(emptyList())

    val priorityList = _priorityList.asStateFlow()

    private val _outputInfo: MutableStateFlow<OutputItem> = MutableStateFlow(OutputItem())

    val outPutInfo = _outputInfo.asStateFlow()

    init {
        viewModelScope.launch {
            healthConcernListState.collectLatest { result ->
                _priorityList.value = result.filter { it.isSelected }
            }
        }
    }

    fun updateHealthConcerns() {
        val healthConcerns = _priorityList.value.map {
            HealthConcern(
                id = it.id,
                name = it.name,
                priority = _priorityList.value.indexOf(it).plus(1)
            )
        }
        _outputInfo.update {
            it.copy(healthConcerns = healthConcerns)
        }
    }

    fun printOutPutResult() {
        Log.d("OutputResult", "printOutPutResult: ${outPutInfo.value}")
    }

    fun updateAllergies() {
        _outputInfo.update {
            it.copy(
                allergies = allergyListState.value.filter { it.isSelected }.map { Allergy(id = it.id, name = it.name) }
            )
        }
    }

    fun updateDiets() {
        _outputInfo.update { output ->
            output.copy(
                diets = dietList.value.filter { it.isSelected && it.id != 0 }
                    .map { Diet(it.id, it.name) }
            )
        }
    }

    fun checkDiet(name: String) {
        _dietList.update { current ->
            if (current[0].isSelected) return
            current.map { model ->
                if (model.name == name) {
                    model.copy(isSelected = true)
                } else model
            }
        }
    }

    fun checkNoneDiet(isCheck: Boolean) {
        _dietList.update { current ->

            current.map { model ->
                if (model.id == 0) model.copy(isSelected = isCheck) else
                    model.copy(isSelected = false)
            }
        }
    }

    fun uncheckDiet(name: String) {
        _dietList.update { current ->

            current.map { model ->
                if (model.name == name) {
                    model.copy(isSelected = false)
                } else model
            }
        }
    }

    fun onSelectChip(model: HealthConcernUiModel) {
        _healthConcernListState.update {
            it.map { if (model == it) it.copy(isSelected = !model.isSelected) else it }
        }
    }

    fun onAllergySelect(allergy: String) {
        _allergyListState.update {
            it.map { if (it.name == allergy) it.copy(isSelected = true) else it }
        }
    }

    fun onAllergyUnselect(allergy: String) {
        _allergyListState.update {
            it.map { if (it.name == allergy) it.copy(isSelected = false) else it }
        }
    }

    fun onReorderPriorities(from: Int, to: Int) {
        _priorityList.update {
            it.toMutableList().move(from, to)
        }
    }


}