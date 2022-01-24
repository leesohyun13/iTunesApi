package com.sohyun.itunes.data.page

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sohyun.itunes.data.model.Track
import com.sohyun.itunes.data.model.ui.TrackUiData
import com.sohyun.itunes.data.repository.SongRepository
import retrofit2.HttpException
import java.io.IOException

/**
 * iTunes
 * Class: TrackPagingSource
 * Created by leesohyun on 2022/01/02.
 *
 * Description:
 */
class TrackPagingSource(
    private val songRepository: SongRepository,
    private val term: String,
) : PagingSource<Int, TrackUiData>() {
    val TAG = TrackPagingSource::class.java.simpleName
    override fun getRefreshKey(state: PagingState<Int, TrackUiData>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrackUiData> {
        return try {
            val currentPageNumber = params.key ?: 1
            val prevKey = if (currentPageNumber == 1) null else currentPageNumber - 1
            val response = songRepository.searchSong(term, currentPageNumber * CONTENTS_COUNT).convertUiData(prevKey)
            Log.d(TAG,"prevKey $prevKey response $response")
                LoadResult.Page(
                    data = response,
                    prevKey = prevKey,
                    nextKey = if (response.isEmpty()) null else currentPageNumber.plus(1)
                )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private fun List<Track>.convertUiData(prevKey: Int?): List<TrackUiData> {
        return mutableListOf<TrackUiData>().apply {
            if (prevKey == null) add(TrackUiData.Title("$term 검색어"))
            add(TrackUiData.Content(this@convertUiData))
        }
    }

    companion object {
        const val CONTENTS_COUNT = 10
    }
}