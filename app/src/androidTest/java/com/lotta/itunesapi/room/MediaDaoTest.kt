package com.lotta.itunesapi.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*


@RunWith(AndroidJUnit4::class)
@SmallTest
class MediaDaoTest {
    private lateinit var database: DaoDatabase
    private lateinit var dao: MediaDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            DaoDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.favoritesDao()
    }

    @After
    @Throws(Exception::class)
    fun teardown() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTrack() {
        val t = Track(
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
        dao.insert(t)
        val d = dao.get(t.trackId)
        assertThat(d).isEqualTo(t)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndDeleteAndGetTrack() {
        val t = Track(
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
        dao.insert(t)
        dao.delete(t)
        val d = dao.get(t.trackId)
        assertThat(d).isNull()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndUpdateTrack() {
        val b = Track(
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
        val a = Track(
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
            collectionPrice = 10.9,
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
        dao.insert(b)
        dao.update(a)
        val d = dao.get(b.trackId)
        assertThat(d).isEqualTo(a)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAllTrack() {
        val b = Track(
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
        val a = Track(
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
            collectionPrice = 10.9,
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
            trackId = 790,
            trackName = "Track Name",
            trackNumber = 1,
            trackPrice = 0.99,
            trackTimeMillis = 300000,
            trackViewUrl = "https://example.com/track",
            wrapperType = "track"
        )
        val list = listOf(a, b)
        dao.insert(a)
        dao.insert(b)
        val d = dao.getAll()
        assertThat(d).containsAnyIn(list)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAllAndDeleteAllTrack() {
        val b = Track(
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
        val a = Track(
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
            collectionPrice = 10.9,
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
            trackId = 790,
            trackName = "Track Name",
            trackNumber = 1,
            trackPrice = 0.99,
            trackTimeMillis = 300000,
            trackViewUrl = "https://example.com/track",
            wrapperType = "track"
        )
        dao.insert(a)
        dao.insert(b)
        dao.deleteAll()
        val d = dao.getAll()
        assertThat(d).hasSize(0)
    }
}