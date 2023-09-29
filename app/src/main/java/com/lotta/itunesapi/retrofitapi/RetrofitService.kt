package com.lotta.itunesapi.retrofitapi

import com.lotta.itunesapi.model.MediaModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitService(private val apiService: ApiService) {
//    fun getUsers(): Single<List<MediaModel>> {
//        return apiService.getSearchResults(1, 2)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
}