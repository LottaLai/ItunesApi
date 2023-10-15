//package com.lotta.itunesapi.ui.home
//
//import android.content.Context
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.paging.PagingData
//import androidx.paging.cachedIn
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.google.common.truth.Truth.assertThat
//import com.lotta.itunesapi.MainCoroutineRule
//import com.lotta.itunesapi.api.ApiContainer
////import com.lotta.itunesapi.fake.FakeDataManager
//import com.lotta.itunesapi.fake.FakeMediaRepo
//import com.lotta.itunesapi.model.FilterModel
//import com.lotta.itunesapi.api.ITunesApiService
//import com.lotta.itunesapi.room.DaoDatabase
//import com.lotta.itunesapi.model.Track
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.runBlockingTest
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
//class HomeViewModelTest {
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()
//
//    private lateinit var viewModel: HomeViewModel
//    private lateinit var database: DaoDatabase
////    private lateinit var dataManager: FakeDataManager
//    private lateinit var mediaRepo: FakeMediaRepo
//    private lateinit var apiService: ITunesApiService
//
//    @Before
//    fun setup() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        database = Room.inMemoryDatabaseBuilder(
//            context,
//            DaoDatabase::class.java
//        ).allowMainThreadQueries().build()
//        apiService = Retrofit.Builder()
//            .baseUrl(ApiContainer.endpoint())
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            }).build())
//            .build()
//            .create(ITunesApiService::class.java)
//
////        dataManager = FakeDataManager(database)
//        mediaRepo = FakeMediaRepo(apiService)
////        viewModel = HomeViewModel(dataManager, mediaRepo)
//    }
//
//    @Test
//    fun testSearchTracks() = runBlockingTest {
//        val queryStr = "jack+johnson"
//
//        val expectedPagingData = createExpectedPagingData()
////        var response: PagingData<Track> = PagingData.empty()
//       mediaRepo.getSearchTracksResult(queryStr).cachedIn(mainCoroutineRule)
////        assertThat(response)
//    }
//
//    private fun createExpectedPagingData(): PagingData<Track> {
//        // Create and return the expected PagingData for testing
//        return PagingData.from(
//            listOf(
//                Track(
//                    artistId = 41742672,
//                    artistName = "This Bike Is a Pipe Bomb",
//                    artistViewUrl = "https://music.apple.com/us/artist/this-bike-is-a-pipe-bomb/41742672?uo=4",
//                    artworkUrl100 = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/f3/ee/b3/f3eeb3ff-ca32-273a-15aa-709bdfa64367/mzi.izwiyqez.jpg/100x100bb.jpg",
//                    artworkUrl30 = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/f3/ee/b3/f3eeb3ff-ca32-273a-15aa-709bdfa64367/mzi.izwiyqez.jpg/30x30bb.jpg",
//                    artworkUrl60 = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/f3/ee/b3/f3eeb3ff-ca32-273a-15aa-709bdfa64367/mzi.izwiyqez.jpg/60x60bb.jpg",
//                    collectionCensoredName = "Three Way Tie for a Fifth",
//                    collectionExplicitness = "notExplicit",
//                    collectionId = 263301268,
//                    collectionName = "Three Way Tie for a Fifth",
//                    collectionPrice = 9.99,
//                    collectionViewUrl = "https://music.apple.com/us/album/jack-johnson/263301268?i=263301273&uo=4",
//                    country = "USA",
//                    currency = "USD",
//                    discCount = 1,
//                    discNumber = 1,
//                    kind = "song",
//                    previewUrl = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/fd/bb/38/fdbb38d2-073d-4bc7-68c4-348a0be6d560/mzaf_4150435585996894188.plus.aac.p.m4a",
//                    primaryGenreName = "Alternative",
//                    releaseDate = "2004-06-15T12:00:00Z",
//                    trackCensoredName = "Jack Johnson",
//                    trackCount = 11,
//                    trackExplicitness = "notExplicit",
//                    trackId = 263301273,
//                    trackName = "Jack Johnson",
//                    trackNumber = 1,
//                    trackPrice = 0.99,
//                    trackTimeMillis = 117573,
//                    trackViewUrl = "https://music.apple.com/us/album/jack-johnson/263301268?i=263301273&uo=4",
//                    wrapperType = "track"
//                )
//            )
//        )
//    }
//
//    @Test
//    fun filterListGenerateByListArray() {
//        val keyList = arrayOf("all", "song", "movie")
//        val valueList = arrayOf("全部", "音樂", "電影")
//        val expectedResult = arrayListOf<FilterModel>()
//        for (x in valueList.indices) {
//            val f = FilterModel(keyList[x], valueList[x], x == 0)
//            expectedResult.add(f)
//        }
//        viewModel.filterList(keyList, valueList)
//        val liveData = viewModel.mediaFilterList.value
//        assertThat(liveData).isEqualTo(expectedResult)
//    }
//
//}