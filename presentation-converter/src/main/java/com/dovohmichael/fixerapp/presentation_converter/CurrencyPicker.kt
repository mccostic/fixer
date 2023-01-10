package com.dovohmichael.fixerapp.presentation_converter

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun CurrencyPicker(
    modifier: Modifier,
    readOnly: Boolean,
    enabled: Boolean,
    defaultSymbol: String,
    currencies:List<CurrencyListItemModel>,
    onSymbolSelected: (String) -> Unit
) {

    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Box {

        OutlinedTextField(
            modifier = modifier
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(6.dp)
                )
                .width(150.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            readOnly = readOnly,
            value = defaultSymbol,
            textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.SemiBold),
            singleLine = true,
            onValueChange = { newInput ->
               // defaultSymbol = newInput
                onSymbolSelected(newInput)

            },
            trailingIcon = {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) "less" else "more",
                    Modifier
                        .clip(CircleShape)
                        .clickable(enabled = enabled) { isExpanded = !isExpanded },
                    tint = MaterialTheme.colors.onSecondary
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.onSecondary,
                textColor = Color.DarkGray
            ),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .height(250.dp)
        ) {
            currencies.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        //selectedSymbol = item.iso
                        onSymbolSelected(item.iso)
                        isExpanded = false
                    }
                ) {
                    Text(text = item.iso, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                }
            }
        }
    }
}