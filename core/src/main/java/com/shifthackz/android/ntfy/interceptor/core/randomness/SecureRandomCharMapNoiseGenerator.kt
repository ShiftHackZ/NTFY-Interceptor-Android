package com.shifthackz.android.ntfy.interceptor.core.randomness

import java.security.SecureRandom

class SecureRandomCharMapNoiseGenerator {

    private val random = SecureRandom()

    private val invisibleChars = listOf(
        '\u200B', // Zero Width Space
        '\u200C', // Zero Width Non-Joiner
        '\u200D', // Zero Width Joiner
        '\u2060', // Word Joiner
        '\u2061', // Function Application
        '\u2062', // Invisible Times
        '\u2063', // Invisible Separator
        '\uFEFF', // Byte Order Mark (ZWNBSP)
        '\u180E', // Mongolian Vowel Separator (deprecated but still invisible)
        '\u115F', // Hangul Choseong Filler
        '\u1160'  // Hangul Jungseong Filler
    )

    fun obfuscate(input: String): String {
        val noiseLength = 5 + random.nextInt(7)
        val noise = buildString {
            repeat(noiseLength) {
                append(invisibleChars[random.nextInt(invisibleChars.size)])
            }
        }
        return input + noise
    }
}
