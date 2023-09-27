package com.lotta.itunesapi.configuration

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import javax.inject.Inject

class VolleyManager @Inject constructor(
    private val requestQueue: RequestQueue
) {
    fun getRequest(
        url: String
    ){
        val stringRequest =
            object : StringRequest(Method.GET, url, {
            }, {
            }) {

            }
        requestQueue.add(stringRequest)
    }
}