package com.kkh.statemanagementtest.ui.entrance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kkh.statemanagementtest.R
import com.kkh.statemanagementtest.ui.theme.PRIMARY_ORANGE
import com.kkh.statemanagementtest.ui.theme.SECONDARY_ORANGE
import com.kkh.statemanagementtest.ui.util.DefaultButton


@Preview(showBackground = true)
@Composable
fun EntranceScreen(
    onNavigateToOnBoarding: () -> Unit = {}
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 40.dp)
    ) {

        Text(
            text = "Welcome to DailyVita",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Hello we are here to make your life healthier and happier",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.home_survey_ic),
            contentDescription = "Entrance Survey Image"
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "We will ask couple of questions to better understand your vitamin need",
            fontSize = 20.sp
        )
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            DefaultButton(
                btnText = "Get Started",
                isEnable = true,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = PRIMARY_ORANGE,
                    disabledContainerColor = SECONDARY_ORANGE,
                    disabledContentColor = Color.DarkGray

                ),
                onClick = {
                    onNavigateToOnBoarding()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}