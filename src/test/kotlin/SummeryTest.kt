import ArgsParser.SEPARATOR
import kotlin.test.Test
import kotlin.test.assertTrue

class SummeryTest {
    private fun fullTest(vararg args: String): String {
        assertTrue(args.all { it.isNotEmpty() })
        assertTrue(args.all { it.isNotBlank() })

        val parseResult = ArgsParser.parseArgs(args.asList().toRaw())
        return parseResult.string()
    }

    companion object {
        private const val UO = "UnaryOperator"
        private const val BO = "BinaryOperator"

        private const val P = "Predicate"
        private const val BP = "BiPredicate"

        private const val F = "Function"
        private const val BF = "BiFunction"

        private const val I = "Int"
        private const val L = "Long"
        private const val D = "Double"
        private const val O = "Object"
        private const val B = "Boolean"

        private const val T1 = "Type1"
        private const val T2 = "Type2"
        private const val T3 = "Type3"
        private const val S = SEPARATOR
        private const val TO = "To"
    }

    private val i = I.toLowerCase()
    private val l = L.toLowerCase()
    private val d = D.toLowerCase()
    private val b = B.toLowerCase()

    @Test
    internal fun testOperator() {
        assertTrue(fullTest(i, S, i) == "$I$UO")
        assertTrue(fullTest(l, S, l) == "$L$UO")
        assertTrue(fullTest(d, S, d) == "$D$UO")
        assertTrue(fullTest(O, S, O) == "$UO<$O>")

        assertTrue(fullTest(i, i, S, i) == "$I$BO")
        assertTrue(fullTest(l, l, S, l) == "$L$BO")
        assertTrue(fullTest(d, d, S, d) == "$D$BO")
        assertTrue(fullTest(O, O, S, O) == "$BO<$O>")
    }

    @Test
    internal fun testPredicate() {
        assertTrue(fullTest(i, S, b) == "$I$P")
        assertTrue(fullTest(l, S, b) == "$L$P")
        assertTrue(fullTest(d, S, b) == "$D$P")
        assertTrue(fullTest(O, S, b) == "$P<$O>")

        assertTrue(fullTest(T1, T2, S, b) == "$BP<$T1, $T2>")
    }

    @Test
    internal fun testFunction() {
        assertTrue(fullTest(i, S, O) == "$I$F<$O>")
        assertTrue(fullTest(l, S, O) == "$L$F<$O>")
        assertTrue(fullTest(d, S, O) == "$D$F<$O>")
        assertTrue(fullTest(T1, S, T2) == "$F<$T1, $T2>")

        assertTrue(fullTest(O, S, i) == "$TO$I$F<$O>")
        assertTrue(fullTest(O, S, l) == "$TO$L$F<$O>")
        assertTrue(fullTest(O, S, d) == "$TO$D$F<$O>")

        assertTrue(fullTest(i, S, l) == "$I$TO$L$F")
        assertTrue(fullTest(i, S, d) == "$I$TO$D$F")
        assertTrue(fullTest(l, S, i) == "$L$TO$I$F")
        assertTrue(fullTest(l, S, d) == "$L$TO$D$F")
        assertTrue(fullTest(d, S, i) == "$D$TO$I$F")
        assertTrue(fullTest(d, S, l) == "$D$TO$L$F")

        assertTrue(fullTest(T1, T2, S, i) == "$TO$I$BF<$T1, $T2>")
        assertTrue(fullTest(T1, T2, S, l) == "$TO$L$BF<$T1, $T2>")
        assertTrue(fullTest(T1, T2, S, d) == "$TO$D$BF<$T1, $T2>")
        assertTrue(fullTest(T1, T2, S, T3) == "$BF<$T1, $T2, $T3>")
    }
}