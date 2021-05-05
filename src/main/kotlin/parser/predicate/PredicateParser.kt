package parser.predicate

import ParseResult
import parser.FIParser

internal object PredicateParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        return (argsList.size == 1 &&
                returnType == BOOLEAN)
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        val arg = argsList[0]
        if (arg in fiStdTypeList) return "${arg.capitalize()}${commonName()}" to emptyList()
        return commonName() to argsList.map { it.filterStdType() }
    }
}
