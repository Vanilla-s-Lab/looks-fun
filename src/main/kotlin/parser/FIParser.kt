package parser

import ParseResult
import assert.assert
import assert.assert.AssertionError

/**
 * Each subclass represents a type of standard interface.
 * Override unless Java released new standard functional interface.
 *
 */
abstract class FIParser {
    internal companion object {
        private fun Any.lowerClassName() = this::class.simpleName!!
            .removeSuffix("CompanionObject").toLowerCase()

        protected val fiStdTypeList = listOf(Int, Double)
            .map { it.lowerClassName() }
            .toMutableList().apply { add("long") }

        // https://www.w3schools.com/java/java_wrapper_classes.asp
        // Long is a special case, its class.simpleName is just "Long".
        protected val stdTypeList = listOf(Byte, Short, Int, Float, Double, Boolean, Char)
            .map { it.lowerClassName() }.toMutableList().apply { add("long") }

        protected const val VOID = "void"
        private const val COMMON_SUFFIX = "Parser"
        protected val BOOLEAN = Boolean::class.simpleName!!.toLowerCase()
    }

    /**
     * A "common name" of this functional interface, such as "Consumer" for "IntConsumer".
     * Default behavior assume subclass named as "${commonName}Parser".
     */
    protected fun commonName(): String = this::class.simpleName!!.substringBefore(COMMON_SUFFIX)

    /**
     * For each type of functional interface, check the input can be parse to.
     */
    internal abstract fun check(argsList: List<String>, returnType: String): Boolean

    /**
     * After checking, parse it to best matching expression.
     * public parse method promised args valid from check().
     */
    protected abstract fun internalParse(argsList: List<String>, returnType: String): ParseResult

    internal fun publicParse(argsList: List<String>, returnType: String): ParseResult {
        assert(check(argsList, returnType), AssertionError())
        return internalParse(argsList, returnType)
    }

    /**
     * Cast Java standard type name to wrapped Type name.
     * Will be using in generic args when returning parse result.
     *
     * @return wrapped class name if it is basic type, otherwise return to itself.
     */
    protected fun String.filterStdType(): String {
        assert(this.isNotEmpty() && this.isNotEmpty(), AssertionError())

        if (this in stdTypeList) {
            if (this == Int.lowerClassName()) return "Integer"
            if (this == Char.lowerClassName()) return "Character"
            return this.capitalize()
        }

        return this
    }
}
