package geronimo.don.stackoverflowget

import geronimo.don.stackoverflowget.model.Example
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyRestInterface {
    //@GET("questions?order=desc&sort=activity&tagged={tagged}&site=stackoverflow")
    @GET("questions?order=desc&sort=activity&site=stackoverflow")
    fun doGet(@Query("tagged")tag:String): Call<Example>
}