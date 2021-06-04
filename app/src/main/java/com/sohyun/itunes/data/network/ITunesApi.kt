package com.sohyun.itunes.data.network

import com.sohyun.itunes.data.model.SongResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {
    @GET(SUB_PATH_SEARCH)
    suspend fun searchSong(
        @Query(SCHEMA_QUERY_TERM) term: String,
        @Query(SCHEMA_QUERY_ENTITY) entity: String = DEFAULT_ENTITY,
    ): SongResponse

    companion object {
        const val ITUNES_BASE_URL = "https://itunes.apple.com/"
        const val DEFAULT_ENTITY = "song"

        const val SUB_PATH_SEARCH = "search"
        const val SCHEMA_QUERY_TERM = "term"
        const val SCHEMA_QUERY_ENTITY = "entity"
    }
}