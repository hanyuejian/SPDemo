package com.demo.spdemo

import com.hanyue.fastsave.SPModel

data class SomeSave(var boolean: Boolean = false, var num: Int? = 0, var string: String? = "") : SPModel()