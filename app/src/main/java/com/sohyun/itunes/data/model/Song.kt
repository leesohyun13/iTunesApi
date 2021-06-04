package com.sohyun.itunes.data.model

import com.google.gson.annotations.SerializedName

data class Song (
    @SerializedName("wrapperType")
    val wrapperType: String,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("artistId")
    val artistId: Int,
    @SerializedName("collectionId")
    val collectionId: Int,
    @SerializedName("trackId")
    val trackId: Int,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("trackName")
    val trackName: String,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String,
    @SerializedName("collectionPrice")
    val collectionPrice: Float,
    @SerializedName("trackPrice")
    val trackPrice: Float,
    @SerializedName("discCount")
    val discCount: Int,
    @SerializedName("trackCount")
    val trackCount: Int,
    @SerializedName("trackNumber")
    val trackNumber: Int,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Long,
    @SerializedName("country")
    val country: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String
)
