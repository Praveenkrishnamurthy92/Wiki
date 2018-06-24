package com.moneytap.praveen.wiki.view

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.TypedValue
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.Toast
import com.moneytap.praveen.wiki.*
import com.moneytap.praveen.wiki.controller.ApiClient
import com.moneytap.praveen.wiki.controller.ApiInterface
import com.moneytap.praveen.wiki.controller.GridSpacingItemDecoration
import com.moneytap.praveen.wiki.model.WikiPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchWikiActivity : AppCompatActivity() {

    lateinit var new_order_search_view: SearchView
    lateinit var new_order_recycl_view: RecyclerView
    lateinit var itemDecorator: GridSpacingItemDecoration
    lateinit var apiInterface: ApiInterface

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_wiki)

        new_order_recycl_view = findViewById(R.id.new_order_recycl_view)
        new_order_search_view = findViewById(R.id.new_order_search_view)

        itemDecorator = GridSpacingItemDecoration(this, 1, dpToPx(10f), dpToPx(0f), dpToPx(3.2f))
        R.id.new_order_search_view
        new_order_recycl_view.layoutManager = LinearLayoutManager(this)
        new_order_recycl_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        new_order_recycl_view.addItemDecoration(itemDecorator)

        new_order_search_view.queryHint = "Search"
        new_order_search_view.setIconifiedByDefault(false)

        val linearLayout1 = new_order_search_view.getChildAt(0) as LinearLayout
        val linearLayout2 = linearLayout1.getChildAt(2) as LinearLayout
        val linearLayout3 = linearLayout2.getChildAt(1) as LinearLayout

        val autoComplete = linearLayout3.getChildAt(0) as AutoCompleteTextView
        autoComplete.textSize = 11.9f

        fetchResponse("Sachin T")

        new_order_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                fetchResponse(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    fun fetchResponse(query: String) {
        apiInterface = ApiClient.getApiRetrofitClient.create(ApiInterface::class.java)

        var call: Call<WikiPojo> = apiInterface.getWikiResponse(query)

        call.enqueue(object : Callback<WikiPojo> {
            override fun onFailure(call: Call<WikiPojo>?, t: Throwable?) {
                Toast.makeText(this@SearchWikiActivity, "Error Response", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<WikiPojo>?, response: Response<WikiPojo>?) {
                setDataToRecycleView(response?.body()?.query?.pages!!)
            }
        })
    }

    fun setDataToRecycleView(list: ArrayList<WikiPojo.WikiItem>) {
        new_order_recycl_view.adapter = RecyViewAdapter(this, list)
    }

    fun dpToPx(dp: Float): Int {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.resources.getDisplayMetrics()))
    }
}
