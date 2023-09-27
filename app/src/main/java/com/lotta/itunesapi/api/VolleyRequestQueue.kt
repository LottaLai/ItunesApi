package com.lotta.itunesapi.api

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyRequestQueue {
    companion object {
        var requestQueue: RequestQueue? = null
            private set

        fun buildRequestQueue(context: Context) : RequestQueue =
            requestQueue ?: synchronized(this) {
                requestQueue ?: Volley.newRequestQueue(context.applicationContext)
                    .also {
                        requestQueue = it
                    }
            }
    }
}