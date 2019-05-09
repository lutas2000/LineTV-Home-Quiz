package lutas.sample.linetvhomequiz.mobile

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DataUtil {

    fun parseNumWithSuffix(num: Long): String {
        if (num < 1000) return "$num"
        val exp = (Math.log(num.toDouble()) / Math.log(1000.0)).toInt()
        return String.format(
            "%.1f %c",
            num / Math.pow(1000.0, exp.toDouble()),
            "kMGTPE"[exp - 1]
        )
    }

    fun parseIso8601Date(time: String?): String {
        if (time == null) return ""
        return try {
            // TODO 確認是否正確
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            val date = inputFormat.parse(time)
            outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }
}