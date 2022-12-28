package com.pop.apptest.pr.models

import com.google.gson.annotations.SerializedName

data class WorkerByTime(
    @SerializedName("PLNT_CODE") val PLNT_CODE : String,
    @SerializedName("DEPT_CODE") val DEPT_CODE : String,
    @SerializedName("DEPT_NAME") val DEPT_NAME : String,
    @SerializedName("PRDT_TPCD") val PRDT_TPCD : String,
    @SerializedName("PRVS_NAME") val PRVS_NAME : String,
    @SerializedName("MKPD_DATE") val MKPD_DATE : String,
    @SerializedName("PRDT_NMBR") val PRDT_NMBR : String,
    @SerializedName("WORK_DTFM") val WORK_DTFM : String,
    @SerializedName("WORK_TMFM") val WORK_TMFM : String,
    @SerializedName("WORK_DTTO") val WORK_DTTO : String,
    @SerializedName("WORK_TMTO") val WORK_TMTO : String,
    @SerializedName("TMEM_CODE") val TMEM_CODE : String,
    @SerializedName("TMEM_NAME") val TMEM_NAME : String,
    @SerializedName("SEQU_NMBR") val SEQU_NMBR : String,
    @SerializedName("STRT_TIME") val STRT_TIME : String,
    @SerializedName("ENDD_TIME") val ENDD_TIME : String,
    @SerializedName("EMPL_CODE") val EMPL_CODE : String,
    @SerializedName("EMPL_NAME") val EMPL_NAME : String,
    @SerializedName("PRCS_CODE") val PRCS_CODE : String
)
