import assert.assert

object ArgsParser {
    // TODO: 2021/4/26 Does Kotlin / Node.js has constant of them...?
    const val SEPARATOR = "->"
    const val WHITE_SPACE = ' '.toString()

    /**
     * Check the arguments array, we needed format is [n x "TypeA", SEPARATOR, "TypeB"].
     * So array length should be >= 3, and SEPARATOR should be second to last.
     *
     * @param args The input args, each arg should not blank or contains WHITE_SPACE.
     * @return true if meet our requirement.
     */
    fun isValid(args: List<String>): Boolean {
        assert(args.all { !it.contains(WHITE_SPACE) }, AssertionError())
        assert(args.all { it.isNotBlank() }, AssertionError())

        val argsSize = args.size
        // If array contains 1+ SEPARATOR, indexOf will get the first.
        return !(argsSize < 3 || args.indexOf(SEPARATOR) != argsSize - 2)
    }
}
