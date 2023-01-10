package com.dovohmichael.fixerapp.presentation_history

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dovohmichael.fixerapp.presentation_common.navigation.HistoryInput

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    modifier: Modifier,
    navController: NavController,
    input: HistoryInput
) {
    Column(modifier = Modifier.padding(24.dp, 0.dp)) {


        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "HISTORY SCREEN=$input", color = Color.Blue)
    }

}