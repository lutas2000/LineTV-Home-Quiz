package lutas.sample.linetvhomequiz

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import lutas.sample.linetvhomequiz.local.DramaDao
import lutas.sample.linetvhomequiz.local.MyDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import io.mockk.*
import lutas.sample.linetvhomequiz.remote.DramaService
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DramaRepositoryTest {

//    lateinit var dao: DramaDao
//    lateinit var servise: DramaService
    val dao: DramaDao = mockk()
    val servise: DramaService = mockk()

//    @Before
//    fun createDb() {
//        val context = InstrumentationRegistry.getTargetContext()
//        db = Room.inMemoryDatabaseBuilder(
//            context, MyDatabase::class.java).build()
//        dramaDao = db.getDramaDao()
//
//
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }

    @Test
    fun getDrama_noLocal_noRemote() {
//        every { dao. }
////        dao = mockk()
////        val dao = moc<>()
////        val drama: User = TestUtil.createUser(3).apply {
////            setName("george")
////        }
////        userDao.insert(user)
//        val byName = userDao.findUsersByName("george")
//        assertThat(byName.get(0), equalTo(user))
    }
}