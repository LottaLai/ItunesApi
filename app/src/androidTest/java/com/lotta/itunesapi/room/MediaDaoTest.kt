package com.lotta.itunesapi.room

import androidx.room.Dao
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MediaDaoTest {
    private lateinit var database: DaoDatabase
    private lateinit var dao: MediaDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DaoDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.favoritesDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertItem() {
        val track = Track(
            artistId = 123,
            artistName = "Artist Name",
            artistViewUrl = "https://example.com",
            artworkUrl100 = "https://example.com/artwork100.jpg",
            artworkUrl30 = "https://example.com/artwork30.jpg",
            artworkUrl60 = "https://example.com/artwork60.jpg",
            collectionCensoredName = "Collection Name",
            collectionExplicitness = "Explicit",
            collectionId = 456,
            collectionName = "Collection Name",
            collectionPrice = 9.99,
            collectionViewUrl = "https://example.com/collection",
            country = "US",
            currency = "USD",
            discCount = 1,
            discNumber = 1,
            kind = "song",
            previewUrl = "https://example.com/preview",
            primaryGenreName = "Genre",
            releaseDate = "2023-01-01",
            trackCensoredName = "Track Name",
            trackCount = 1,
            trackExplicitness = "Explicit",
            trackId = 789,
            trackName = "Track Name",
            trackNumber = 1,
            trackPrice = 0.99,
            trackTimeMillis = 300000,
            trackViewUrl = "https://example.com/track",
            wrapperType = "track"
        )

        dao.insert(track)

        dao.getAll().subscribe({
            assertThat(it).contains(track)
        }, {
            println("GET ALL: $it")
        })
    }
}