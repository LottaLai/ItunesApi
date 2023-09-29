package com.lotta.itunesapi.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MediaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(model: MediaModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(model: MediaModel)

    @Delete
    fun delete(model: MediaModel)

    @Query("SELECT * FROM favorites ORDER BY `releaseDate` DESC")
    fun getAll(): Flowable<MutableList<MediaModel>>

    @Query("DELETE FROM favorites")
    fun deleteAll()

}