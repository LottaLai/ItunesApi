package com.lotta.itunesapi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MediaDao {
    @Query("SELECT * FROM favorites WHERE trackId = :trackId")
    fun get(trackId: Int) : Track?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(model: Track)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(model: Track)

    @Delete
    fun delete(model: Track)

    @Query("SELECT * FROM favorites ORDER BY `releaseDate` DESC")
    fun getAll(): MutableList<Track>

    @Query("DELETE FROM favorites")
    fun deleteAll()

}