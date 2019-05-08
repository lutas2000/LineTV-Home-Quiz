package lutas.sample.linetvhomequiz.remote

import android.util.Log
import com.google.gson.Gson
import lutas.sample.linetvhomequiz.model.BaseResponse
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResponseConverterFactory(gson: Gson): Converter.Factory() {

    private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        val wrappedType = object : ParameterizedType {
            override fun getActualTypeArguments(): Array<Type> = arrayOf(type)
            override fun getOwnerType(): Type? = null
            override fun getRawType(): Type = BaseResponse::class.java
        }
        val gsonConverter: Converter<ResponseBody, *>? = gsonConverterFactory.responseBodyConverter(wrappedType, annotations, retrofit)
        return ResponseBodyConverter(gsonConverter as Converter<ResponseBody, BaseResponse<Any>>)
    }

    class ResponseBodyConverter<T>(private val converter: Converter<ResponseBody, BaseResponse<T>>) : Converter<ResponseBody, T> {

        @Throws(IOException::class)
        override fun convert(responseBody: ResponseBody): T {
            val response = converter.convert(responseBody)
            return response?.data!!
        }
    }
//    override fun responseBodyConverter(
//        type: Type,
//        annotations: Array<Annotation>,
//        retrofit: Retrofit
//    ): Converter<ResponseBody, *>? {
////        if (type is JSONObject) {
////            Log.d("test", "is JSONObject")
////            return Converter<ResponseBody, ResponseBody> { body ->
//////                body.
////            }
////        }
//        return super.responseBodyConverter(type, annotations, retrofit)
//    }
}