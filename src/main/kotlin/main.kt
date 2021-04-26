private const val USAGE =
    """lkf <TypeA> (1+) - <TypeC>, 
Example: lkf int - long, lkf long double - String. """

// https://nodejs.org/api/process.html#process_exit_codes
const val INVALID_ARGUMENT = 9

fun main() {
    val argv = process.argv
    val args = argv.slice(2 until argv.size)

    if (!argsValid(args)) {
        console.log("Usage: ${USAGE.trimIndent()}")
        process.exit(INVALID_ARGUMENT)
    }
}
