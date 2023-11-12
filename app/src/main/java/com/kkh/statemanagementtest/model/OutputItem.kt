package com.kkh.statemanagementtest.model


data class OutputItem(
    val alcohol: String = "0-1",
    val allergies: List<Allergy> = emptyList(),
    val diets: List<Diet> = emptyList(),
    val healthConcerns: List<HealthConcern> = emptyList(),
    val isDailyExposure: Boolean = true,
    val isSmoke: Boolean = false
)