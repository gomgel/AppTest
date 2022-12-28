package com.pop.apptest.pr

import com.pop.apptest.pr.models.Line
import com.pop.apptest.pr.models.TimeByWO
import com.pop.apptest.pr.models.WorkerByTime
import retrofit2.Response
import java.io.IOException

class PrDatasource {


    // 매개변수로 suspend 메소드를 받고 통신성공 여부에 따라 Result 값을 반환하는 함수
    suspend fun <T : Any> safeApiCall(call : suspend () -> Response<T>): Result<T> {
        return try {
            val myResp = call.invoke()

            if (myResp.isSuccessful) {
                Result.Success(myResp.body()!!)
            } else {
                Result.Fail("I/F failed.....")
            }

        } catch (e: Exception) {
            Result.Error(IOException("Raised Exception", e))
        }
    }

    suspend fun getLineInfo(plant: String): Result<List<Line>> {
        return safeApiCall { PrService.getService().getLineInfo(plant) }
    }

    suspend fun getTimesByWo(plant : String, dept : String, date : String): Result<List<TimeByWO>> {
        return safeApiCall { PrService.getService().getTimesByWo(plant, dept, date) }
    }



    suspend fun getWorkersByWo(plant : String, dept : String, date : String): Result<List<WorkerByTime>> {
        return safeApiCall { PrService.getService().getWorkersByTime(plant, dept, date) }
    }



    // 매개변수로 suspend 메소드를 받고 통신성공 여부에 따라 Result 값을 반환하는 함수
    fun <T : Any> safeApiCallWithNoSuspend(call : () -> Response<T>): Result<T> {
        return try {
            val myResp = call.invoke()

            if (myResp.isSuccessful) {
                Result.Success(myResp.body()!!)
            } else {
                Result.Fail("I/F failed.....")
            }

        } catch (e: Exception) {
            Result.Error(IOException("Raised Exception", e))
        }
    }

    fun getLineInfoWithNoSuspend(plant: String): Result<List<Line>> {
        return safeApiCallWithNoSuspend { PrService.getService().getLineInfoWithNoSuspend(plant) }
    }





//    suspend fun getLineInfo(plant: String): Result<List<API_LinInfo>> {
////        return safeApiCall { PrService.getService().getLineInfo(plant) }
////        return safeApiCall { PrService.getService().getLineInfo(plant) }
//
//        try {
//            val response = PrService.getService().getLineInfo("2000")
//            if (response.isSuccessful) {
//
//                return Result.Success(response.body() as List<API_LinInfo>)
//
//            } else {
//                return Result.Fail("faile....")
//            }
//        } catch (e : Exception) {
//            return Result.Error(IOException("Raised Exception", e))
//        }
//
//    }

}