package com.pop.apptest.pr.models

import com.google.gson.annotations.SerializedName

data class Line(
    @SerializedName("PLNT_CODE") val PLNT_CODE : String,
    @SerializedName("DEPT_CODE") val DEPT_CODE : String,
    @SerializedName("DEPT_NAME") var DEPT_NAME : String
)
