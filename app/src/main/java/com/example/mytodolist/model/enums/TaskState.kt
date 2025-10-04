package com.example.mytodolist.model.enums

enum class TaskState(val displayName: String) {
    PENDING("Pending"), IN_PROGRESS("In Progress"), DONE("Done");

    companion object {
        fun fromDisplayName(displayName: String): TaskState? {
            return entries.find {
                it.displayName == displayName
            }
        }
    }
}