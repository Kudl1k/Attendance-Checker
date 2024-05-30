package com.example.attendancechecker.presentation.trainings_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.attendancechecker.presentation.groups_screen.GroupsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TrainingsScreenViewModel @Inject constructor(

): ViewModel() {

    private var _state = mutableStateOf(TrainingsScreenState())
    val state: State<TrainingsScreenState> = _state


    fun onEvent(event: TrainingsScreenEvent){
        when(event){
            else -> {

            }
        }
    }



}