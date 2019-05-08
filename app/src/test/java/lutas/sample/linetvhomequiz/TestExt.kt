package lutas.sample.linetvhomequiz

import io.reactivex.Single
import lutas.sample.linetvhomequiz.model.DramaEntity


fun createDramaList(num: Int): List<DramaEntity> {
    val list = mutableListOf<DramaEntity>()
    for (i in 1..num) {
        list.add(createDrama(i))
    }
    return list
}

fun createDrama(id: Int): DramaEntity {
    return DramaEntity(
        id, "test$id", 23562274,
        "2017-11-23T02:04:39.000Z","", 4.4526f
    )
}