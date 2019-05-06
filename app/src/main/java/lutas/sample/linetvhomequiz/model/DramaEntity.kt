package lutas.sample.linetvhomequiz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = DramaEntity.TABLE_NAME)
data class DramaEntity (

    @PrimaryKey
    @SerializedName("drama_id")
    val dramaId: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("total_views")
    val totalViews: Long,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("thumb")
    val thumb: String?,

    @SerializedName("rating")
    val rating: Float = 0f
) {
    companion object {
        const val TABLE_NAME = "todo_model"
    }
}