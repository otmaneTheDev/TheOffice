package com.otmanethedev.theoffice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.otmanethedev.domain.models.Person
import com.otmanethedev.theoffice.R
import com.otmanethedev.theoffice.components.models.MenuItem

@Composable
fun PersonComponent(
    modifier: Modifier = Modifier,
    person: Person,
    onAssignDesk: () -> Unit,
    onDeletePerson: (Person) -> Unit
) {
    var isDropdownOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray.copy(alpha = 0.25f))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_person),
                contentDescription = "",
                tint = Color.Black
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = person.name,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { isDropdownOpen = true },
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_kebab_menu),
                contentDescription = "",
                tint = Color.Black
            )
        }

        if (person.assignedDesks.isNotEmpty()) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Assigned desk")

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = person.assignedDesks, key = { it.id }) {
                    DeskComponent(
                        desk = it,
                        showMenu = false
                    )
                }
            }
        }

        DropdownMenu(
            expanded = isDropdownOpen,
            onDismissRequest = { isDropdownOpen = false }
        ) {
            DropdownMenuItem(
                modifier = Modifier
                    .clickable {
                        isDropdownOpen = false
                        onAssignDesk()
                    },
                menuItem = MenuItem(icon = R.drawable.ic_desk, text = "Assign desk")
            )

            DropdownMenuItem(
                modifier = Modifier
                    .clickable {
                        isDropdownOpen = false
                    },
                menuItem = MenuItem(icon = R.drawable.ic_pencil, text = "Edit profile")
            )

            DropdownMenuItem(
                modifier = Modifier
                    .clickable {
                        isDropdownOpen = false
                        onDeletePerson(person)

                    },
                menuItem = MenuItem(icon = R.drawable.ic_trash, text = "Delete profile")
            )
        }
    }
}

@Preview
@Composable
private fun PersonComponentPreview() {
    PersonComponent(
        person = Person(id = 0, name = "Dwight"),
        onAssignDesk = {},
        onDeletePerson = {}
    )
}