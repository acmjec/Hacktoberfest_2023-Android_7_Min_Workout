package dev.panwar.a7minutesworkout

import android.app.Application
// create the application class
class WorkOutApp: Application() {

    val db:HistoryDatabase by lazy {
        HistoryDatabase.getInstance(this)
    }
}