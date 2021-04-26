import ArgsParser.SEPARATOR
import ArgsParser.WHITE_SPACE
import ArgsParser.isValid
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
        assertFailsWith<AssertionError> { isValid(listOf(WHITE_SPACE, SEPARATOR, WHITE_SPACE)) }
        assertFailsWith<AssertionError> { isValid(listOf("", SEPARATOR, "")) }

        assertFalse { isValid(listOf()) }
        assertFalse { isValid(listOf(TA, TB)) }
        assertFalse { isValid(listOf(TA, SEPARATOR, TB, TC)) }
        assertFalse { isValid(listOf(TA, SEPARATOR, SEPARATOR, TB)) }

        assertTrue { isValid(listOf(TA, SEPARATOR, TB)) }
        assertTrue { isValid(listOf(TA, TB, SEPARATOR, TC)) }
    }
}
