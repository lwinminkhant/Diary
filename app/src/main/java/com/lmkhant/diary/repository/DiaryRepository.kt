package com.lmkhant.diary.repository

import com.lmkhant.diary.data.dao.DiaryDao
import com.lmkhant.diary.data.model.Diary

class DiaryRepository(private val diaryDao: DiaryDao) {

    val allDiaries = diaryDao.getAllDiaries()

    suspend fun load(id: Int){
        diaryDao.load(id)
    }
    suspend fun insert(diary: Diary){
        diaryDao.insert(diary)
    }

    suspend fun update(diary: Diary){
        diaryDao.update(diary)
    }

    suspend fun delete(diary: Diary){
        diaryDao.delete(diary)
    }
}