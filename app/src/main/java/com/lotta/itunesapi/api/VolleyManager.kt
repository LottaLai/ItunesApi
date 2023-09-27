package com.lotta.itunesapi.api

import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject
import javax.inject.Inject

class VolleyManager @Inject constructor(
    private val requestQueue: RequestQueue
) {
    fun getRequest(
        url: String,
        timeout: Int = 0,
        needCache: Boolean = false
    ): Observable<JSONObject> {
        return Observable.create { emitter ->
            val jsonObjectRequest =
                object : JsonObjectRequest(Method.GET, url, null,
                    { response ->
                        emitter.onNext(response)
                        emitter.onComplete()
                    }, { error ->
                        emitter.onError(error)
                    }) {
                }.apply {
                    setShouldCache(needCache)
                    retryPolicy = DefaultRetryPolicy(
                        timeout,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    )
                }
            requestQueue.add(jsonObjectRequest)
        }.subscribeOn(Schedulers.io())
    }
}