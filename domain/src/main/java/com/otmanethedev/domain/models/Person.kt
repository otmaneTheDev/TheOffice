package com.otmanethedev.domain.models

class Person(id: Int, val name: String) : OfficeModel(id) {
    var assignedDesks = listOf<Desk>()
}