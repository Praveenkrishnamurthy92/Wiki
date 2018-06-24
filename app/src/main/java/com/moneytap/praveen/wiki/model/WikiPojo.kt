package com.moneytap.praveen.wiki.model

import com.google.gson.annotations.Expose

/**
 * Created by cheluva on 24/06/18.
 */
public class WikiPojo() {
    @Expose
    var query: WikiItemsObj? = null

    class WikiItemsObj() {
        @Expose
        var pages: ArrayList<WikiItem>? = null
    }

    class WikiItem() {
        @Expose
        var index = 0
        @Expose
        var title = ""
        @Expose
        var thumbnail: Thumbnail? = null
        @Expose
        var terms: Terms? = null

        class Thumbnail() {
            @Expose
            var source: String = ""
        }

        class Terms() {
            @Expose
            var description: ArrayList<String> = arrayListOf()
        }
    }
}