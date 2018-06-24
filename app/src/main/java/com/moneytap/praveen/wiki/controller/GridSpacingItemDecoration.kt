package com.moneytap.praveen.wiki.controller

import android.app.Activity
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by cheluva on 24/06/18.
 */
class GridSpacingItemDecoration : RecyclerView.ItemDecoration {

    private var spanCount: Int = 0
    private var spacing: Int = 0
    private var top: Int = 0
    private var bottom: Int = 0
    private var includeEdge: Boolean = false
    private var activity: Activity? = null

    constructor(activity: Activity, spanCount: Int, spacing_left_right: Int, top: Int, bottom: Int) {
        this.activity = activity
        this.spanCount = spanCount
        this.spacing = spacing_left_right
        this.top = top
        this.bottom = bottom
        this.includeEdge = true
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = this.top
            }
            outRect.bottom = this.bottom // item bottom
        } else {
            outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }
}