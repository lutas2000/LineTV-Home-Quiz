package lutas.sample.linetvhomequiz

import android.arch.persistence.room.EmptyResultSetException
import lutas.sample.linetvhomequiz.local.DramaDao
import org.junit.Test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import lutas.sample.linetvhomequiz.remote.DramaService
import lutas.sample.linetvhomequiz.repository.DramaRepository
import java.net.UnknownHostException

class DramaRepositoryTest {

    private val dao: DramaDao = mockk(relaxed = true)
    private val service: DramaService = mockk()

    @Test
    fun get_drama_error() {
        every { dao.getDrama(1) } returns createLocalError()
        every { service.getList() } returns createRemoteError()

        val repo = DramaRepository(service, dao)
        val observer = repo.getDrama(1).test()

        observer.assertError(UnknownHostException::class.java)
        verify {
            dao.getDrama(any())
            service.getList()
        }
    }

    @Test
    fun get_drama_local() {
        val dramaObj = createDrama(1)
        every { dao.getDrama(1) } returns Single.just(dramaObj)
        every { service.getList() } returns createRemoteError()

        val repo = DramaRepository(service, dao)
        val observer = repo.getDrama(1).test()

        observer.assertValue(dramaObj)
    }

    @Test
    fun get_drama_remote() {
        val dramaId = 1
        val dramaList = createDramaList(5)
        every { dao.getDrama(dramaId) } returns createLocalError()
        every { service.getList() } returns Single.just(dramaList)

        val repo = DramaRepository(service, dao)
        val observer = repo.getDrama(dramaId).test()

        observer.assertValue(dramaList.find { it.dramaId == 1 })
        verify {
            dao.getDrama(any())
            service.getList()
        }
    }

    @Test
    fun get_drama_list_remote() {
        val dramaList = createDramaList(5)
        every { dao.getAll() } returns createLocalError()
        every { service.getList() } returns Single.just(dramaList)

        val repo = DramaRepository(service, dao)
        val observer = repo.getList().test()

        observer.assertValue(dramaList)
        verify {
            service.getList()
            dao.deleteAll()
            dao.saveAll(any())
        }
    }

    @Test
    fun get_drama_list_local() {
        val dramaList = createDramaList(5)
        every { dao.getAll() } returns Single.just(dramaList)
        every { service.getList() } returns createRemoteError()

        val repo = DramaRepository(service, dao)
        val observer = repo.getList().test()

        observer.assertValue(dramaList)
        verify {
            service.getList()
            dao.getAll()
        }
    }

    @Test
    fun get_drama_list_error() {
        val dramaList = createDramaList(5)
        every { dao.getAll() } returns createLocalError()
        every { service.getList() } returns createRemoteError()

        val repo = DramaRepository(service, dao)
        val observer = repo.getList().test()

        observer.assertError(EmptyResultSetException::class.java)
        verify {
            service.getList()
            dao.getAll()
        }
    }

    /**
     * mock Internet error
     */
    private fun<T> createRemoteError(): Single<T> {
        return Single.error(UnknownHostException())
    }

    /**
     * mock Empty database error
     */
    private fun<T> createLocalError(): Single<T> {
        return Single.error(EmptyResultSetException("mock"))
    }
}