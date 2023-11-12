package com.kkh.statemanagementtest

enum class ScreenRoute {
    ENTRANCE,
    ONBOARDING,
}

sealed class Screen(val route: String) {
    object ENTRANCE : Screen(ScreenRoute.ENTRANCE.name)
    object ONBOARDING : Screen(ScreenRoute.ONBOARDING.name)
}