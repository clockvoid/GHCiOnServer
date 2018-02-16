import com.lucciola.SessionManager
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SessionManagementTest {
    lateinit var sessionManager: SessionManager

    @Before
    fun before() {
        this.sessionManager = SessionManager()
    }

    @Test
    fun hashCreationTest() {
        for (i in 1..10) {
            println(i)
            val hash1: String = this.sessionManager.makeHash()
            val hash2: String = this.sessionManager.makeHash()
            assertEquals(false, hash1 == hash2)
            Thread.sleep(10)
        }
    }
}