package parser

import ParseResult
import assert.assert
import assert.assert.AssertionError

abstract class FIParser {
    companion object {
        private fun Any.lowerClassName() = this::class.simpleName!!.toLowerCase()

        val fiStdTypeList = listOf(Int, Long, Double).map { it.lowerClassName() }

        // https://www.w3schools.com/java/java_wrapper_classes.asp
        val stdTypeList = listOf(Byte, Short, Int, Long, Float, Double, Boolean, Char)
            .map { it.lowerClassName() }

        private const val COMMON_SUFFIX = "Parser"
        val BOOLEAN = Boolean::class.simpleName!!.toLowerCase()
    }

    protected fun commonName(): String = this::class.simpleName!!.substringBefore(COMMON_SUFFIX)
    abstract fun check(argsList: List<String>, returnType: String): Boolean
    protected abstract fun internalParse(argsList: List<String>, returnType: String): ParseResult

    fun parse(argsList: List<String>, returnType: String): ParseResult {
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
        }

        return this
    }
}
