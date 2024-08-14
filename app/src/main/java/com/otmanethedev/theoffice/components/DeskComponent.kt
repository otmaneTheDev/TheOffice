package com.otmanethedev.theoffice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.otmanethedev.domain.models.Desk
import com.otmanethedev.theoffice.R

@Composable
fun DeskComponent(
    modifier: Modifier = Modifier,
    desk: Desk,
    showMenu: Boolean = true,
    onAssignToPerson: (Desk) -> Unit = {},
    onDeletedDesk: (Desk) -> Unit = {}
) {
    var isDropdownOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Yellow.copy(alpha = 0.25f))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_desk),
                contentDescription = "",
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = desk.location,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            if (showMenu) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { isDropdownOpen = true },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_kebab_menu),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }

        DropdownMenu(
            expanded = isDropdownOpen,
            onDismissRequest = { isDropdownOpen = false }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        isDropdownOpen = false
                        onAssignToPerson(desk)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_desk),
                    contentDescription = "Assign to person",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Assign to person")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { isDropdownOpen = false }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_pencil),
                    contentDescription = "Edit desk",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Edit desk")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        isDropdownOpen = false
                        onDeletedDesk(desk)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash),
                    contentDescription = "Delete desk",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Delete desk")
            }
        }

    }
}

@Preview
@Composable
private fun DeskComponentPreview() {
    DeskComponent(desk = Desk(id = 0, location = "AOC"), onAssignToPerson = {}, onDeletedDesk = {})
}