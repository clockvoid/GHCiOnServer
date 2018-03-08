import com.lucciola.GHCi
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GHCiTest {
    lateinit var ghci: GHCi

    @Before
    fun before() {
        ghci = GHCi("ghci")
    }

    @Test
    fun submitProgramTest() {
        val str1: String = ghci.submitProgram("putStrLn \"test\"")
        val str2: String = ghci.submitProgram("putStrLn \"test\"")
        val invalid: String = ghci.submitProgram(":m System.Cmd")
        assertEquals("Prelude> test", str1)
        assertEquals("Prelude> test", str2)
        assertEquals("Invalid program!", invalid)
    }

    @Test
    fun getFunctionsTest() {
        this.ghci.addFunction("hello")
        assertEquals("hello", this.ghci.getFunctions())
    }
}