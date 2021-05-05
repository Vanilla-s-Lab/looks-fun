package parser.consumer

import ParseResult
import parser.FIParser

internal object ConsumerParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        return (argsList.size == 1 &&
                returnType == VOID)
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        val arg = argsList[0]
        if (arg in stdTypeList) return "${arg.capitalize()}${commonName()}" to emptyList()
        return commonName() to argsList.map { it.filterStdType() }
    }
}