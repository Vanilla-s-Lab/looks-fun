package parser.operator

import ParseResult
import parser.FIParser

object BinaryOperatorParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        return (argsList.size == 2 &&
                argsList.distinct().size == 1 &&
                argsList[0] == returnType)
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        val arg = argsList[0]
        if (arg in fiStdTypeList) return "${arg.capitalize()}${commonName()}" to emptyList()
        return commonName() to argsList.distinct().map { it.filterStdType() }
    }
}
