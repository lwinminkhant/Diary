package com.lmkhant.diary.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lmkhant.diary.data.dao.DiaryDao
import com.lmkhant.diary.data.model.Diary

@Database(entities = [Diary::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    companion object{
        @Volatile
        private var INSTANCE: DiaryDatabase? = null

        fun getInstance(context: Context): DiaryDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        DiaryDatabase::class.java,
                        "diary_database")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}