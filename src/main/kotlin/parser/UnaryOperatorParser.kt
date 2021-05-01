package parser

import ParseResult

object UnaryOperatorParser : FIParser() {
    override fun commonName(): String = "UnaryOperator"

    override fun check(argsList: List<String>, returnType: String): Boolean {
        return argsList.size == 1 && argsList[0] == returnType
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        val (arg) = argsList
        val resultName = if (arg in stdTypeList) arg.capitalize() + commonName() else arg
        return resultName to listOf(returnType)
    }
}
