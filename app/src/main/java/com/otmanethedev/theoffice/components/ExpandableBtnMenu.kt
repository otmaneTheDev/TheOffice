package com.otmanethedev.theoffice.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.otmanethedev.theoffice.R

@Composable
fun ExpandableBtnMenu(
    modifier: Modifier = Modifier,
    addPerson: () -> Unit,
    addDesk: () -> Unit,
    addKeyboard: () -> Unit,
    addScreen: () -> Unit,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isMenuExpanded) {
            Row(
                modifier = Modifier
                    .clickable {
                        isMenuExpanded = false
                        addPerson()
                    }
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_person),
                    contentDescription = "Add person",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "Add person", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .clickable {
                        isMenuExpanded = false
                        addDesk()
                    }
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_desk),
                    contentDescription = "Add desk",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "Add desk", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .clickable {
                        isMenuExpanded = false
                        addKeyboard()
                    }
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_keyboard),
                    contentDescription = "Add person",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "Add keyboard", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .clickable {
                        isMenuExpanded = false
                        addScreen()
                    }
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_screen),
                    contentDescription = "Add person",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "Add screen", color = Color.White)
            }
        }
        Box(
            modifier = Modifier
                .clickable {
                    isMenuExpanded = !isMenuExpanded
                }
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            val icon = if (isMenuExpanded) R.drawable.ic_cross else R.drawable.ic_plus
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = "Add item",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun ExpandableBtnMenuPreview() {
    MaterialTheme {
        ExpandableBtnMenu(
            addPerson = {},
            addDesk = {},
            addKeyboard = {},
            addScreen = {}
        )
    }
}