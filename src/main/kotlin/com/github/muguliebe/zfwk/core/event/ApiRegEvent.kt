package com.github.muguliebe.zfwk.core.event

data class ApiRegInfo (
    val className:String,
    val funName:String,
    val method: String, // http method
    val url:String
)

data class ApiRegEvent(
    val list: List<ApiRegInfo>
)
