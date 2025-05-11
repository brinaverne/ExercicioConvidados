package com.example.exercicioconvidadosroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercicioconvidadosroom.model.Guest
import com.example.exercicioconvidadosroom.repository.GuestFormRepository

class PresentViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GuestFormRepository(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<Guest>>()
    val guests: LiveData<List<Guest>> = listAllGuests

    fun getAll(){
        listAllGuests.postValue(repository.select(true))
    }

    fun delete(guestId:Int){
        repository.delete(guestId)
    }

    fun update(guest: Guest){
        repository.update(guest)
    }
}