package parser.operator

import ParseResult
import parser.FIParser

object UnaryOperatorParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        return (argsList.size == 1 &&
                argsList[0] == returnType)
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        val arg = argsList[0]
        if (arg in fiStdTypeList) return "${arg.capitalize()}${commonName()}" to emptyList()
        return commonName() to argsList
    }
}
