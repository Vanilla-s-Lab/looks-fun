package parser.function

import ParseResult
import parser.FIParser

object BiFunctionParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        val allArgs = argsList.toMutableList().apply { add(returnType) }
        return (returnType != VOID &&
                argsList.size == 2 && // Has 2 args, and all args not equal at the same time.
                allArgs.toSet().size != 1) // Because If that's the case, use BinaryOperator instead.
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        if (returnType in stdTypeList) // To TypeA BiFunction <T, U>.
            return "To${returnType.capitalize()}${commonName()}" to argsList.map { it.filterStdType() }

        val allArgs = argsList.toMutableList().apply { add(returnType) }
        return commonName() to allArgs.map { it.filterStdType() } // CommonName with <T, U, R>.
    }
}
