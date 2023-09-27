package com.lotta.itunesapi.configuration

import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import javax.inject.Inject

class VolleyManager @Inject constructor(
    private val requestQueue: RequestQueue
) {
    fun getRequest(
        url: String,
        timeout: Int = 0,
        needCache: Boolean = false,
        onSuccess: ((String) -> Unit)? = null,
        onFailure: ((String) -> Unit)? = null
    ) {
        val stringRequest = object : StringRequest(Method.GET, url,
            {
                onSuccess?.invoke(it)
            }, {
                onFailure?.invoke(it.networkResponse.data.toString())
            }) {
        }.apply {
            setShouldCache(needCache)
            retryPolicy = DefaultRetryPolicy(
                timeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        }
        requestQueue.add(stringRequest)
    }
}