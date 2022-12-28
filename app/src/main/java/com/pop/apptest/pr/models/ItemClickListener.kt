package com.pop.apptest.pr.models

import com.pop.apptest.Adater
import com.pop.apptest.NameInfo

interface ItemClickListener {
    fun onItemClick(item : NameInfo, index : Int, adapter : Adater)
}