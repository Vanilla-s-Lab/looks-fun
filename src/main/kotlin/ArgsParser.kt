import assert.assert
import parser.SupplierParser
import parser.consumer.BiConsumerParser
import parser.consumer.ConsumerParser
import parser.function.BiFunctionParser
import parser.function.FunctionParser
import parser.operator.BinaryOperatorParser
import parser.operator.UnaryOperatorParser
import parser.predicate.BiPredicateParser
import parser.predicate.PredicateParser

object ArgsParser {
    // TODO: 2021/4/26 Does Kotlin / Node.js has constant of them...?
    const val SEPARATOR = "->"
    const val WHITE_SPACE = ' '.toString()
    const val NO_ARGS = "()"

    private val allFIParser = listOf(
        UnaryOperatorParser, BinaryOperatorParser,
        PredicateParser, BiPredicateParser,
        FunctionParser, BiFunctionParser, SupplierParser,
        ConsumerParser, BiConsumerParser
    )

    /**
     * Check the arguments array, we needed format is [n x "TypeA", SEPARATOR, "TypeB"].
     * So array length should be >= 3, and SEPARATOR should be second to last.
     * Also, if args.first() is "()", this means no more args so second element should be SEPARATOR.
     *
     * @param args The input args, each arg should not blank or contains WHITE_SPACE.
     * @return true if meet our requirement.
     */
    fun isValid(args: List<String>): Boolean {
        assert(args.all { !it.contains(WHITE_SPACE) }, AssertionError())
        assert(args.all { it.isNotBlank() }, AssertionError())

        val argsSize = args.size

        // If array contains 1+ SEPARATOR, indexOf will get the first.
        if (argsSize < 3 || args.indexOf(SEPARATOR) != argsSize - 2) return false

        if (args.first() == NO_ARGS) {
            // If first element is NO_ARGS, args size should be == 3 and SEPARATOR at index 1.
            return argsSize == 3 && args[1] == SEPARATOR
        }

        return true
    }

    fun parseArgs(rawArgs: RawArgs): ParseResult {
        val (argsList, returnType) = rawArgs
        allFIParser.forEach {
            if (it.check(argsList, returnType))
                return it.parse(argsList, returnType)
        }

        println("No standard functional interface matching. ")
        process.exit(INVALID_ARGUMENT); throw AssertionError()
    }
}
