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
    fun insert(model: Track)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(model: Track)

    @Delete
    fun delete(model: Track)

    @Query("SELECT * FROM favorites ORDER BY `releaseDate` DESC")
    fun getAll(): Flowable<MutableList<Track>>

    @Query("DELETE FROM favorites")
    fun deleteAll()

}