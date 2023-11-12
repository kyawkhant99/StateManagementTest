package com.kkh.statemanagementtest.ui.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kkh.statemanagementtest.ui.onboarding.OnBoardingSection.Allergy
import com.kkh.statemanagementtest.ui.onboarding.OnBoardingSection.Diets
import com.kkh.statemanagementtest.ui.onboarding.OnBoardingSection.FinalizedInfo
import com.kkh.statemanagementtest.ui.onboarding.OnBoardingSection.HealthConcern
import com.kkh.statemanagementtest.ui.theme.PRIMARY_DARK_BLUE

enum class OnBoardingSection {
    HealthConcern, Diets, Allergy, FinalizedInfo
}

@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel, onNavigateBack: () -> Unit) {

    var selectedSection by remember {
        mutableStateOf(HealthConcern)
    }

    var isSlideLeftIn by remember {
        mutableStateOf(true)
    }

    val slideDirection by remember {
        derivedStateOf {
            (if (isSlideLeftIn) AnimatedContentTransitionScope.SlideDirection.Left else AnimatedContentTransitionScope.SlideDirection.Right)
        }
    }

    var progress by remember {
        mutableFloatStateOf(0.25f)
    }

    LaunchedEffect(key1 = selectedSection) {
        val currentIndex = OnBoardingSection.values().indexOf(selectedSection)
        progress = currentIndex.plus(1) * 0.25f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        AnimatedContent(targetState = selectedSection, label = "sections",
            transitionSpec = {
                slideIntoContainer(slideDirection) togetherWith
                        slideOutOfContainer(slideDirection)
            }
        ) {
            when (it) {
                HealthConcern -> HealthConcernSection(
                    onNext = {
                        selectedSection = Diets
                        isSlideLeftIn = true
                    },
                    onPrevious = {
                        onNavigateBack()
                    },
                    viewModel = viewModel
                )

                Diets -> DietsSection(
                    onNext = {
                        isSlideLeftIn = true
                        selectedSection = Allergy

                    },
                    onPrevious = {
                        isSlideLeftIn = false
                        selectedSection = HealthConcern

                    },
                    viewModel = viewModel
                )

                Allergy -> SpecificAllergiesSection(
                    onNext = {
                        isSlideLeftIn = true
                        selectedSection = FinalizedInfo
                    },
                    onPrevious = {
                        isSlideLeftIn = false
                        selectedSection = Diets
                    },
                    viewModel = viewModel
                )

                FinalizedInfo -> FinalizedInfoSection(
                    onNext = {

                    },
                    onPrevious = {
                        isSlideLeftIn = false
                        selectedSection = Allergy
                    },
                    viewModel = viewModel
                )
            }

        }


        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            color = PRIMARY_DARK_BLUE,
            trackColor = Color.White
        )
    }
}



