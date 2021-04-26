import parser.SEPARATOR
import parser.argsValid
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private const val TA = "TypeA"
private const val TB = "TypeB"
private const val TC = "TypeC"

class ArgsParserTest {
    @Test
    fun testArgsValid() {
        // Actually, it fails with AssertionError, but seems it only can get Error.
        assertFailsWith<AssertionError> { argsValid(listOf("  ", SEPARATOR, "  ")) }
        assertFailsWith<AssertionError> { argsValid(listOf("", SEPARATOR, "")) }

        assertFalse { argsValid(listOf()) }
        assertFalse { argsValid(listOf(TA, TB)) }
        assertFalse { argsValid(listOf(TA, SEPARATOR, TB, TC)) }
        assertFalse { argsValid(listOf(TA, SEPARATOR, SEPARATOR, TB)) }

        assertTrue { argsValid(listOf(TA, SEPARATOR, TB)) }
        assertTrue { argsValid(listOf(TA, TB, SEPARATOR, TC)) }
    }
}