package parser

import ParseResult

object SupplierParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        return argsList.isEmpty()
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        if (returnType in stdTypeList.toMutableList().apply { add(BOOLEAN) })
            return "${returnType.capitalize()}${commonName()}" to emptyList()

        return commonName() to listOf(returnType).map { it.filterStdType() }
    }
}
