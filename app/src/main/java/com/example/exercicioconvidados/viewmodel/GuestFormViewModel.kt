package com.example.exercicioconvidadosroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exercicioconvidadosroom.model.Guest
import com.example.exercicioconvidadosroom.repository.GuestFormRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    val guests: MutableLiveData<Guest> = MutableLiveData<Guest>()

    private var repository = GuestFormRepository(application)

    fun insert(guest: Guest){
        repository.insert(guest)
    }

    fun selectItemForUpdate(guestId: Int){
        var r = repository.selectItemForUpdate(guestId)
        guests.postValue(r)
    }

    fun update(guest:Guest){
        repository.update(guest)
    }

}