package parser.function

import ParseResult
import parser.FIParser

internal object FunctionParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        return (returnType != VOID &&
                argsList.size == 1 &&
                argsList[0] != returnType)
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        val arg = argsList[0]

        val argFlag = arg in fiStdTypeList
        val rTFlag = returnType in fiStdTypeList

        if (argFlag && rTFlag) { // <T, R> are std type, consider (TypeA)To(TypeB)Function.
            val (first, second) = listOf(arg, returnType).map { it.capitalize() }
            return "${first}To$second${commonName()}" to emptyList()
        }

        if (argFlag) // Only arg is stdType, use "TypeAFunction" instead.
            return "${arg.capitalize()}${commonName()}" to listOf(returnType).map { it.filterStdType() }

        if (rTFlag) // Only return type is stdType, use "ToTypeAFunction" instead.
            return "To${returnType.capitalize()}${commonName()}" to listOf(arg).map { it.filterStdType() }

        // Common situation, Function<T, R> is [(T) -> R].
        return commonName() to listOf(argsList[0], returnType).map { it.filterStdType() }
    }
}
