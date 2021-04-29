import ArgsParser.SEPARATOR

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
}
