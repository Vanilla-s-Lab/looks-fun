package parser.predicate

import ParseResult
import parser.FIParser

object BiPredicateParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        return (argsList.size == 2 &&
                returnType == BOOLEAN)
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        return commonName() to argsList.map { it.filterStdType() }
    }
}
