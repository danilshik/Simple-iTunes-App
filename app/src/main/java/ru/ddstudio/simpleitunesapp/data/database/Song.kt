package ru.ddstudio.simpleitunesapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.ddstudio.simpleitunesapp.data.database.BaseEntity
import java.util.*

@Entity(tableName = "song")
data class Song(
    @ColumnInfo(name = "wrapper_type")
    override var wrapperType : String,
    val kind: String,
    @ColumnInfo(name = "artist_id")
    val artistId: Int,
    @ColumnInfo(name = "collection_id")
    val collectionId: Int,
    @PrimaryKey
    @ColumnInfo(name = "track_id")
    val trackId: Int,

    @ColumnInfo(name = "artist_name")
    val artistName: String,

    @ColumnInfo(name = "collection_name")
    val collectionName: String,

    @ColumnInfo(name = "track_name")
    val trackName: String,

    @ColumnInfo(name = "collection_censored_name")
    val collectionCensoredName: String?,

    @ColumnInfo(name = "track_censored_name")
    val trackCensoredName: String?,

    @ColumnInfo(name = "artist_view_url")
    val artistViewUrl: String?,

    @ColumnInfo(name = "collection_view_url")
    val collectionViewUrl: String?,

    @ColumnInfo(name = "track_view_url")
    val trackViewUrl: String?,

    @ColumnInfo(name = "preview_url")
    val previewUrl: String?,

    @ColumnInfo(name = "artwork_url_30")
    val artworkUrl30: String?,
    @ColumnInfo(name = "artwork_url_60")
    val artworkUrl60: String?,
    @ColumnInfo(name = "artwork_url_100")
    val artworkUrl100: String?,
    @ColumnInfo(name = "collection_price")
    val collectionPrice: Double?,
    @ColumnInfo(name = "trackPrice")
    val trackPrice: Double?,
    @ColumnInfo(name = "release_date")
    val releaseDate: Date,
    @ColumnInfo(name = "collection_explicitness")
    val collectionExplicitness: String?,
    @ColumnInfo(name = "track_explicitness")
    val trackExplicitness: String?,
    @ColumnInfo(name = "disc_count")
    val discCount: Int?,
    @ColumnInfo(name = "disc_number")
    val discNumber: Int?,
    @ColumnInfo(name = "track_count")
    val trackCount: Int?,
    @ColumnInfo(name = "track_number")
    val trackNumber: Int?,
    @ColumnInfo(name = "track_time_millis")
    val trackTimeMillis: Long?,
    val country: String?,
    val currency: String?,
    @ColumnInfo(name = "primary_genre_name")
    val primaryGenreName: String?,
    @ColumnInfo(name = "is_streamable")
    val isStreamable: Boolean?
 ) : BaseEntity()