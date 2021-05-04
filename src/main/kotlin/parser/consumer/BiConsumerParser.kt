package parser.consumer

import ParseResult
import parser.FIParser
import kotlin.math.abs

object BiConsumerParser : FIParser() {
    override fun check(argsList: List<String>, returnType: String): Boolean {
        return (argsList.size == 2 &&
                returnType == VOID)
    }

    override fun internalParse(argsList: List<String>, returnType: String): ParseResult {
        val probeStdTypeIndex = argsList.indexOfFirst { it in stdTypeList }
        if (probeStdTypeIndex != -1) { // Found at least one type is std.
            val anotherIndex = abs((argsList.size - 1) - probeStdTypeIndex)
            return "Obj${argsList[probeStdTypeIndex].capitalize()}${commonName()}" to
                    listOf(argsList[anotherIndex]).map { it.filterStdType() }
        }
        return commonName() to argsList.map { it.filterStdType() }
    }
}