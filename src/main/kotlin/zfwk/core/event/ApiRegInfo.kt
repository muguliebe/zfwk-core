package zfwk.core.event

data class ApiRegInfo (
    val className:String,
    val funName:String,
    val method: String, // http method
    val url:String
)

data class ApiRegInfos(
    val list: List<ApiRegInfo>
)
