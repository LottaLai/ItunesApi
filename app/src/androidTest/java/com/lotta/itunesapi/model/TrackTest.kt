package com.lotta.itunesapi.model

import com.google.common.truth.Truth.assertThat
import com.lotta.itunesapi.room.Track
import org.junit.Test

class TrackTest {
    @Test
    fun testParcelable() {
        // Create a sample Track object
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

        // Write the track object to a parcel
        val parcel = android.os.Parcel.obtain()
        track.writeToParcel(parcel, 0)

        // Reset the parcel for reading
        parcel.setDataPosition(0)

        // Create a new track object from the parcel
        val createdFromParcel = Track.CREATOR.createFromParcel(parcel)

        // Verify that the created track object matches the original track object
        assertThat(track).isEqualTo(createdFromParcel)
    }
}