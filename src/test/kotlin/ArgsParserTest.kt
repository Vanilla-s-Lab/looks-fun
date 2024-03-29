import ArgsParser.NO_ARGS
import ArgsParser.SEPARATOR
import ArgsParser.WHITE_SPACE
import ArgsParser.isValid
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ArgsParserTest {
    companion object {
        private const val TA = "TypeA"
        private const val TB = "TypeB"
        private const val TC = "TypeC"

        private const val WS = WHITE_SPACE
    }

    @Test
    internal fun testArgsValid() {
        // Actually, it fails with AssertionError, but seems it only can get Error.
        assertFailsWith<IllegalArgumentException> { isValid(listOf(WS, SEPARATOR, WS)) }
        assertFailsWith<IllegalArgumentException> { isValid(listOf("", SEPARATOR, "")) }

        assertFalse { isValid(listOf()) }
        assertFalse { isValid(listOf(TA, TB)) }
        assertFalse { isValid(listOf(TA, SEPARATOR, TB, TC)) }
        assertFalse { isValid(listOf(TA, SEPARATOR, SEPARATOR, TB)) }

        assertFalse { isValid(listOf(NO_ARGS, NO_ARGS, TB)) }
        assertTrue { isValid(listOf(NO_ARGS, SEPARATOR, TB)) }

        assertTrue { isValid(listOf(TA, SEPARATOR, TB)) }
        assertTrue { isValid(listOf(TA, TB, SEPARATOR, TC)) }
    }
}
