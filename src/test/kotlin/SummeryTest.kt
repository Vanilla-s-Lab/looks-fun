import ArgsParser.NO_ARGS
import ArgsParser.SEPARATOR
import parser.FIParser.Companion.VOID
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
        private const val S = "Supplier"
        private const val C = "Consumer"
        private const val BC = "BiConsumer"

        private const val I = "Int"
        private const val L = "Long"
        private const val D = "Double"
        private const val O = "Object"
        private const val B = "Boolean"
        private const val N = NO_ARGS
        private const val V = VOID

        private const val T1 = "Type1"
        private const val T2 = "Type2"
        private const val T3 = "Type3"
        private const val SEP = SEPARATOR
        private const val TO = "To"
        private const val OBJ = "Obj"
    }

    private val i = I.toLowerCase()
    private val l = L.toLowerCase()
    private val d = D.toLowerCase()
    private val b = B.toLowerCase()

    @Test
    internal fun testOperator() {
        assertTrue(fullTest(i, SEP, i) == "$I$UO")
        assertTrue(fullTest(l, SEP, l) == "$L$UO")
        assertTrue(fullTest(d, SEP, d) == "$D$UO")
        assertTrue(fullTest(O, SEP, O) == "$UO<$O>")

        assertTrue(fullTest(i, i, SEP, i) == "$I$BO")
        assertTrue(fullTest(l, l, SEP, l) == "$L$BO")
        assertTrue(fullTest(d, d, SEP, d) == "$D$BO")
        assertTrue(fullTest(O, O, SEP, O) == "$BO<$O>")
    }

    @Test
    internal fun testPredicate() {
        assertTrue(fullTest(i, SEP, b) == "$I$P")
        assertTrue(fullTest(l, SEP, b) == "$L$P")
        assertTrue(fullTest(d, SEP, b) == "$D$P")
        assertTrue(fullTest(O, SEP, b) == "$P<$O>")

        assertTrue(fullTest(T1, T2, SEP, b) == "$BP<$T1, $T2>")
    }

    @Test
    internal fun testFunction() {
        assertTrue(fullTest(i, SEP, O) == "$I$F<$O>")
        assertTrue(fullTest(l, SEP, O) == "$L$F<$O>")
        assertTrue(fullTest(d, SEP, O) == "$D$F<$O>")
        assertTrue(fullTest(T1, SEP, T2) == "$F<$T1, $T2>")

        assertTrue(fullTest(O, SEP, i) == "$TO$I$F<$O>")
        assertTrue(fullTest(O, SEP, l) == "$TO$L$F<$O>")
        assertTrue(fullTest(O, SEP, d) == "$TO$D$F<$O>")

        assertTrue(fullTest(i, SEP, l) == "$I$TO$L$F")
        assertTrue(fullTest(i, SEP, d) == "$I$TO$D$F")
        assertTrue(fullTest(l, SEP, i) == "$L$TO$I$F")
        assertTrue(fullTest(l, SEP, d) == "$L$TO$D$F")
        assertTrue(fullTest(d, SEP, i) == "$D$TO$I$F")
        assertTrue(fullTest(d, SEP, l) == "$D$TO$L$F")

        assertTrue(fullTest(T1, T2, SEP, i) == "$TO$I$BF<$T1, $T2>")
        assertTrue(fullTest(T1, T2, SEP, l) == "$TO$L$BF<$T1, $T2>")
        assertTrue(fullTest(T1, T2, SEP, d) == "$TO$D$BF<$T1, $T2>")
        assertTrue(fullTest(T1, T2, SEP, T3) == "$BF<$T1, $T2, $T3>")
    }

    @Test
    internal fun testSupplier() {
        assertTrue(fullTest(N, SEP, i) == "$I$S")
        assertTrue(fullTest(N, SEP, l) == "$L$S")
        assertTrue(fullTest(N, SEP, d) == "$D$S")
        assertTrue(fullTest(N, SEP, b) == "$B$S")
        assertTrue(fullTest(N, SEP, O) == "$S<$O>")
    }

    @Test
    internal fun testConsumer() {
        assertTrue(fullTest(i, SEP, V) == "$I$C")
        assertTrue(fullTest(l, SEP, V) == "$L$C")
        assertTrue(fullTest(d, SEP, V) == "$D$C")
        assertTrue(fullTest(O, SEP, V) == "$C<$O>")

        assertTrue(fullTest(O, i, SEP, V) == "$OBJ$I$BC<$O>")
        assertTrue(fullTest(O, l, SEP, V) == "$OBJ$L$BC<$O>")
        assertTrue(fullTest(O, d, SEP, V) == "$OBJ$D$BC<$O>")
        assertTrue(fullTest(i, O, SEP, V) == "$OBJ$I$BC<$O>")
        assertTrue(fullTest(l, O, SEP, V) == "$OBJ$L$BC<$O>")
        assertTrue(fullTest(d, O, SEP, V) == "$OBJ$D$BC<$O>")

        assertTrue(fullTest(T1, T2, SEP, V) == "$BC<$T1, $T2>")
    }
}
