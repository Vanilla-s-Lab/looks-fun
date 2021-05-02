package parser

import ParseResult
import assert.assert
import assert.assert.AssertionError

abstract class FIParser {
    companion object {
        val stdTypeList = listOf(Int, Long, Double)
            .map { it::class.simpleName!!.toLowerCase() }

        private const val COMMON_SUFFIX = "Parser"
        private const val INTEGER = "Integer"

        val BOOLEAN = Boolean::class.simpleName!!.toLowerCase()
    }

    protected fun commonName(): String = this::class.simpleName!!.substringBefore(COMMON_SUFFIX)
    abstract fun check(argsList: List<String>, returnType: String): Boolean
    protected abstract fun internalParse(argsList: List<String>, returnType: String): ParseResult

    fun parse(argsList: List<String>, returnType: String): ParseResult {
        assert(check(argsList, returnType), AssertionError())
        return internalParse(argsList, returnType)
    }

    protected fun String.toWrapperName(): String {
        assert(this.isNotEmpty() && this.isNotEmpty(), AssertionError())

        if (this in stdTypeList)
            return if (this == Int::class.simpleName) INTEGER
            else this.capitalize()

        return this
    }
}
