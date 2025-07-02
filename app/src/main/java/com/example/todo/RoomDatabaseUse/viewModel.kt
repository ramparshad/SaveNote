package com.example.todo.RoomDatabaseUse

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VMClass(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(application, database::class.java, "note_database").build()

    private val noteDao = db.itemDao()
    val items: Flow<List<ItemEntity>> = noteDao.getAll()

    fun insert(item: ItemEntity) = viewModelScope.launch {
        noteDao.insert(item)
    }

    fun delete(item: ItemEntity) = viewModelScope.launch {
        noteDao.delete(item)
    }

//----------------------for composable components------------------------------------------------
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    fun UpdateTitle(newTitle:String){
        _title.value= newTitle
    }
//----------------------------------------------------------------------

    private val _description = MutableStateFlow("")
    val description= _description.asStateFlow()

    fun UpdateDesc(newDesc:String){
        _description.value=newDesc
    }
}
