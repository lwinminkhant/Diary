package com.lmkhant.diary.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lmkhant.diary.data.model.Diary

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(diary : Diary)

    @Query("SELECT * FROM diaries WHERE id =:id")
    fun load(id: Int): LiveData<Diary>?

    @Update
    fun update(diary: Diary)

    @Delete
    fun delete(diary: Diary)

    @Query("select * from diaries")
    fun getAllDiaries(): LiveData<List<Diary>>
}