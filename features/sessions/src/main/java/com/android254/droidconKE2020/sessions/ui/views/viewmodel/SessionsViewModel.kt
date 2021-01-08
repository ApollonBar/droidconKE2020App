package com.android254.droidconKE2020.sessions.ui.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android254.droidconKE2020.sessions.ui.views.adapter.DummySession
import com.android254.droidconKE2020.sessions.ui.views.models.DaySession

class SessionsViewModel : ViewModel() {
    private val _daySessions = MutableLiveData<List<DaySession>>()
    val daySessions: LiveData<List<DaySession>>
        get() = _daySessions



    private val _sessionId = MutableLiveData<Long>()
    val sessionId: LiveData<Long>
        get() = _sessionId

    private val _removedSessionItem = MutableLiveData<DummySession>()
    val removedSessionItem: LiveData<DummySession>
        get() = _removedSessionItem

    fun getDaySessions() {
        val daySession1 = DaySession("06", "Day 1")
        val daySession2 = DaySession("07", "Day 2")
        val daySession3 = DaySession("08", "Day 3")
        val daySessionsList = listOf(daySession1, daySession2, daySession3)
        _daySessions.value = daySessionsList
    }


    //TODO remove unused code

    fun onSessionItemClicked(sessionId: Long) {
        _sessionId.value = sessionId
    }

    fun onRemoveSavedSession(session: DummySession){
        _removedSessionItem.value = session
    }



}