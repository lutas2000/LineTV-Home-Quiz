package lutas.sample.linetvhomequiz

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import org.junit.Test
import java.io.StringReader

class JsonTest {

    @Test
    fun test() {
        val string = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"drama_id\": 1,\n" +
                "      \"name\": \"致我們單純的小美好\",\n" +
                "      \"total_views\": 23562274,\n" +
                "      \"created_at\": \"2017-11-23T02:04:39.000Z\",\n" +
                "      \"thumb\": \"https://i.pinimg.com/originals/61/d4/be/61d4be8bfc29ab2b6d5cab02f72e8e3b.jpg\",\n" +
                "      \"rating\": 4.4526\n" +
                "    }\n" +
                "  ]\n" +
                "}"
//        val gson = Gson()
        val reader = StringReader(string)
        val jsonReader = JsonReader(reader)
        jsonReader.beginObject()
        val result = jsonReader.nextName()
        jsonReader.endObject()

        println(result)
    }
}