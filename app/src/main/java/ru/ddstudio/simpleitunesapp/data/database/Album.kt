package ru.ddstudio.simpleitunesapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.ddstudio.simpleitunesapp.data.database.BaseEntity
import java.io.Serializable
import java.util.*

@Entity(tableName = "album")
data class Album (
    @ColumnInfo(name = "wrapper_type")
    override var wrapperType : String,
    @ColumnInfo(name = "collection_type")
    val collectionType : String,
    @ColumnInfo(name = "artist_id")
    val artistId : Int,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "collection_id")
    val collectionId: Long,
    @ColumnInfo(name = "amg_artist_id")
    val amgArtistId: Long,
    @ColumnInfo(name = "artist_name")
    val artistName: String?,
    @ColumnInfo(name = "collection_name")
    val collectionName: String,
    @ColumnInfo(name = "collection_censored_name")
    val collectionCensoredName: String?,
    @ColumnInfo(name = "artistViewUrl")
    val artistViewUrl: String?,
    @ColumnInfo(name = "collection_view_url")
    val collectionViewUrl: String?,
    @ColumnInfo(name = "artwork_url_60")
    val artworkUrl60: String?,
    @ColumnInfo(name = "artwork_url_100")
    val artworkUrl100: String?,
    @ColumnInfo(name = "collection_price")
    val collectionPrice: Double?,
    @ColumnInfo(name = "collection_explicitness")
    val collectionExplicitness: String?,
    @ColumnInfo(name = "track_count")
    val trackCount: Int?,
    val copyright: String?,
    val country: String?,
    val currency: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: Date,
    @ColumnInfo(name = "primary_genre_name")
    val primaryGenreName: String?
) : BaseEntity(), Serializable