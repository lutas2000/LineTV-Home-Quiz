package lutas.sample.linetvhomequiz.remote

import io.reactivex.Single
import lutas.sample.linetvhomequiz.model.BaseResponse
import lutas.sample.linetvhomequiz.model.DramaEntity
import retrofit2.http.GET

interface DramaService {

    @GET("/v2/5a97c59c30000047005c1ed2")
    fun getList(): Single<List<DramaEntity>>
}