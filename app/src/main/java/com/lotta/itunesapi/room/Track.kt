package com.lotta.itunesapi.room

import android.os.Parcelable
import android.os.Parcel
import androidx.room.Entity

@Entity(tableName = "favorites", primaryKeys = ["trackId"])
data class Track(
    val artistId: Int,
    val artistName: String?,
    val artistViewUrl: String?,
    val artworkUrl100: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val collectionCensoredName: String?,
    val collectionExplicitness: String?,
    val collectionId: Int,
    val collectionName: String?,
    var collectionPrice: Double,
    val collectionViewUrl: String?,
    val country: String?,
    val currency: String?,
    val discCount: Int,
    val discNumber: Int,
    val kind: String?,
    val previewUrl: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val trackCensoredName: String?,
    val trackCount: Int,
    val trackExplicitness: String?,
    val trackId: Int,
    val trackName: String?,
    val trackNumber: Int,
    val trackPrice: Double,
    val trackTimeMillis: Int,
    val trackViewUrl: String?,
    val wrapperType: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(artistId)
        parcel.writeString(artistName)
        parcel.writeString(artistViewUrl)
        parcel.writeString(artworkUrl100)
        parcel.writeString(artworkUrl30)
        parcel.writeString(artworkUrl60)
        parcel.writeString(collectionCensoredName)
        parcel.writeString(collectionExplicitness)
        parcel.writeInt(collectionId)
        parcel.writeString(collectionName)
        parcel.writeDouble(collectionPrice)
        parcel.writeString(collectionViewUrl)
        parcel.writeString(country)
        parcel.writeString(currency)
        parcel.writeInt(discCount)
        parcel.writeInt(discNumber)
        parcel.writeString(kind)
        parcel.writeString(previewUrl)
        parcel.writeString(primaryGenreName)
        parcel.writeString(releaseDate)
        parcel.writeString(trackCensoredName)
        parcel.writeInt(trackCount)
        parcel.writeString(trackExplicitness)
        parcel.writeInt(trackId)
        parcel.writeString(trackName)
        parcel.writeInt(trackNumber)
        parcel.writeDouble(trackPrice)
        parcel.writeInt(trackTimeMillis)
        parcel.writeString(trackViewUrl)
        parcel.writeString(wrapperType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Track> {
        override fun createFromParcel(parcel: Parcel): Track {
            return Track(parcel)
        }

        override fun newArray(size: Int): Array<Track?> {
            return arrayOfNulls(size)
        }
    }

}