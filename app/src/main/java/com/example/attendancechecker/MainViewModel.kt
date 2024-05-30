package com.example.attendancechecker

import androidx.lifecycle.ViewModel
import com.example.attendancechecker.navgraph.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {




    var startDestination = Routes.Trainings.route
        private set



}