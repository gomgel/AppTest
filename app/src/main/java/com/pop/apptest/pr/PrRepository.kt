package com.pop.apptest.pr

import com.pop.apptest.pr.models.Line
import com.pop.apptest.pr.models.TimeByWO
import com.pop.apptest.pr.models.WorkerByTime

class PrRepository ( var datasource : PrDatasource ){

    suspend fun getLineInfo(plant : String) : Result<List<Line>> {
        return datasource.getLineInfo(plant)
    }

    suspend fun getTimesByWo(plant : String, dept : String, date : String) : Result<List<TimeByWO>> {
        return datasource.getTimesByWo(plant, dept, date)
    }

   suspend fun getWorkersByWo(plant : String, dept : String, date : String) : Result<List<WorkerByTime>> {
        return datasource.getWorkersByWo(plant, dept, date)
    }

    fun getLineInfoWithNoSuspend(plant : String) : Result<List<Line>> {
        return datasource.getLineInfoWithNoSuspend(plant)
    }

    suspend fun getWorkersByWoWithAsync(plant : String, dept : String, date : String) : Result<List<WorkerByTime>> {
        return datasource.getWorkersByWo(plant, dept, date)
    }



}