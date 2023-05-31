package com.lmkhant.diary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lmkhant.diary.data.DiaryDatabase
import com.lmkhant.diary.data.model.Diary
import com.lmkhant.diary.repository.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel(application: Application) : AndroidViewModel(application){

    val diaryList : LiveData<List<Diary>>

    private val diaryRepository: DiaryRepository
    init {
        val diaryDao = DiaryDatabase.getInstance(application).diaryDao()
        diaryRepository = DiaryRepository(diaryDao)
        diaryList = diaryRepository.allDiaries
    }

    fun load(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        diaryRepository.load(id)
    }

    fun insert(diary: Diary) = viewModelScope.launch(Dispatchers.IO){
        diaryRepository.insert(diary)
    }

    fun delete(diary: Diary) = viewModelScope.launch(Dispatchers.IO) {
        diaryRepository.delete(diary)
    }
    fun update(diary: Diary) = viewModelScope.launch(Dispatchers.IO) {
        diaryRepository.update(diary)
    }
}