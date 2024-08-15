package com.otmanethedev.theoffice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.otmanethedev.domain.models.Keyboard
import com.otmanethedev.theoffice.R

@Composable
fun KeyboardComponent(
    modifier: Modifier = Modifier,
    keyboard: Keyboard
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Green.copy(alpha = 0.25f))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_keyboard),
            contentDescription = "",
            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = keyboard.model,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun ScreenComponentPreview() {
    KeyboardComponent(keyboard = Keyboard(id = 0, model = "AOC"))
}