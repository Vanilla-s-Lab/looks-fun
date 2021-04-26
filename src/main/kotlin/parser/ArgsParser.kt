package parser

import assert.assert

// TODO: 2021/4/26 Does Kotlin / Node.js has constant of them...?
const val SEPARATOR = '-'.toString()
const val WHITE_SPACE = ' '

/**
 * Check the arguments array, we needed format is [n x "TypeA", SEPARATOR, "TypeB"].
 * So array length should be >= 3, and SEPARATOR should be second to last.
 *
 * @param args The input args, each arg should not blank or contains WHITE_SPACE.
 * @return true if meet our requirement.
 */
fun argsValid(args: List<String>): Boolean {
    assert(args.all { !it.contains(WHITE_SPACE) }, AssertionError("Some of args contains \" \"! "))
    assert(args.all { it.isNotBlank() }, AssertionError("Some of args are blank! "))

    val argsSize = args.size
    return !(argsSize < 3 || args.indexOf(SEPARATOR) != argsSize - 2)
}
