import ArgsParser.SEPARATOR
import assert.assert
import assert.assert.AssertionError

typealias RawArgs = Pair<List<String>, String>
typealias ParseResult = Pair<String, List<String>>

private const val USAGE =
    """lkf - Looking for Standard Java Functional Interface. 
Example: "lkf int $SEPARATOR long", "lkf long double $SEPARATOR String". 
No Args or Return value: "() $SEPARATOR String", "Object $SEPARATOR void". """

// https://nodejs.org/api/process.html#process_exit_codes
const val INVALID_ARGUMENT = 9

fun main() {
    val argv = process.argv
    val args = argv.slice(2 until argv.size)

    if (!ArgsParser.isValid(args)) {
        console.log(USAGE.trimIndent())
        process.exit(INVALID_ARGUMENT)
    }

    val parseResult = ArgsParser.parseArgs(args.toRaw())
    console.log(parseResult.string())
}

/**
 * Parse user input to get args list & return type.
 * Should be called after check user input.
 *
 * @return RawArgs for further analysis.
 */
private fun List<String>.toRaw(): RawArgs {
    val separatorIndex = this.indexOf(SEPARATOR)
    val returnType = this.last()
    return this.subList(0, separatorIndex) to returnType
}

/**
 * After further analysis, most precisely class will be chosen.
 * This method will return the result in string, like "UnaryOperator<String>".
 *
 * @return a printable string of parse result.
 */
private fun ParseResult.string(): String {
    val (name, args) = this // Function name cannot be blank, but args can.
    assert(name.isNotBlank(), AssertionError())

    // Some of Functional Interfaces does not have any generic args.
    // @see https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
    if (args.isEmpty()) return name

    val stringBuilder = StringBuilder(name)

    stringBuilder.append("<")
    stringBuilder.append(args.joinToString(separator = ", "))
    stringBuilder.append(">")

    return stringBuilder.toString()
}
