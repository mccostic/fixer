package com.dovohmichael.fixerapp.presentation_converter

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dovohmichael.fixerapp.presentation_common.state.Error
import com.dovohmichael.fixerapp.presentation_common.state.Loading
import com.dovohmichael.fixerapp.presentation_common.state.UiState
import java.lang.reflect.Modifier

@Composable
fun <T : Any> ConvertedScreen(trailingText:String,value:String,state: UiState<T>, onSuccess: @Composable (T) -> Unit) {
    when (state) {
        is UiState.Initial -> {
            TextField(
                value = value,
                readOnly = true,
                enabled = false,
                onValueChange = { newValue -> {}},

                singleLine = true,
                trailingIcon = {
                    Text(
                        text = trailingText,
                        color = MaterialTheme.colors.secondaryVariant,
                        fontWeight = FontWeight.Bold
                    )
                },
                shape = RoundedCornerShape(6.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.DarkGray,
                    backgroundColor = MaterialTheme.colors.secondary,
                    disabledTextColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onSecondary
                ),
                //keyboard should only show numbers
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        is UiState.Loading -> {
            TextField(
                value = value,
                readOnly = true,
                enabled = false,
                onValueChange = { newValue -> {}},

                singleLine = true,
                trailingIcon = {
                    Text(
                        text = trailingText,
                        color = MaterialTheme.colors.secondaryVariant,
                        fontWeight = FontWeight.Bold
                    )
                },
                shape = RoundedCornerShape(6.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.DarkGray,
                    backgroundColor = MaterialTheme.colors.secondary,
                    disabledTextColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onSecondary
                ),
                //keyboard should only show numbers
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        is UiState.Error -> {

            TextField(
                value = state.errorMessage,
                readOnly = true,
                enabled = false,
                onValueChange = { newValue -> {}},

                singleLine = true,
                trailingIcon = {
                    Text(
                        text = trailingText,
                        color = MaterialTheme.colors.secondaryVariant,
                        fontWeight = FontWeight.Bold
                    )
                },
                shape = RoundedCornerShape(6.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.DarkGray,
                    backgroundColor = MaterialTheme.colors.secondary,
                    disabledTextColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.onSecondary
                ),
                //keyboard should only show numbers
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        is UiState.Success -> {
            onSuccess(state.data)
        }
    }
}