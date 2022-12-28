package com.pop.apptest.pr

import com.pop.apptest.pr.models.Line
import com.pop.apptest.pr.models.TimeByWO
import com.pop.apptest.pr.models.WorkerByTime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PrApi {

    @GET("lines")
    suspend fun getLineInfo(
        @Query("plant") plant : String = "2000"
    ) : Response<List<Line>>

    @GET("timesbywo")
    suspend fun getTimesByWo(
        @Query("plant") plant : String,
        @Query("dept") dept : String,
        @Query("date") date : String,
    ) : Response<List<TimeByWO>>

    @GET("workersbytime")
    suspend fun getWorkersByTime(
        @Query("plant") plant : String,
        @Query("dept") dept : String,
        @Query("date") date : String,
    ) : Response<List<WorkerByTime>>


    @GET("lines")
    fun getLineInfoWithNoSuspend(
        @Query("plant") plant : String = "2000"
    ) : Response<List<Line>>


}