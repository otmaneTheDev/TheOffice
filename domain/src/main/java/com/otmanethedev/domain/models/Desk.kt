package com.otmanethedev.domain.models

class Desk(id: Int, val location: String) : OfficeModel(id) {
    var assignedKeyboards: List<Keyboard> = listOf()
    var assignedScreens: List<Screen> = listOf()
}