package com.moneytap.praveen.wiki.controller

import com.moneytap.praveen.wiki.model.WikiPojo
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by cheluva on 24/06/18.
 */
interface ApiInterface {

    @GET("api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10")
    fun getWikiResponse(@Query("gpssearch") search_query: String): Call<WikiPojo>

}