package com.example.mytodolist.data

enum class State(val displayName: String) {
    PENDING("Pending"), IN_PROGRESS("In Progress"), DONE("Done");

    companion object {
        fun fromDisplayName(displayName: String): State? {
            return entries.find {
                it.displayName == displayName
            }
        }
    }
}