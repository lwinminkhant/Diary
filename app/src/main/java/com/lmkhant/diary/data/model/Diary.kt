package com.lmkhant.diary.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lmkhant.diary.util.Converters
import java.util.*

@Entity(tableName = "diaries")
@TypeConverters(Converters::class)
data class Diary(
    val updatedOn: Date,
    val title: String,
    val description:String
    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}