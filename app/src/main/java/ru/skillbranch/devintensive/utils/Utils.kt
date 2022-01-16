package ru.skillbranch.devintensive.utils

import androidx.core.graphics.translationMatrix
import java.util.*

object Utils {

    private val dictionary = mapOf(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'с' to "s",
        'т' to "t",
        'у' to 'u',
        'ф' to "f",
        'х' to "h",
        'ц' to "c",
        'ч' to "ch",
        'ш' to "sh",
        'щ' to "sh'",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        when {
            fullName == "" -> {
                firstName = null
                lastName = null
            }

            fullName == " " -> {
                firstName = null
                lastName = null
            }
        }

        return firstName to lastName

    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var fName = firstName?.trimStart()?.firstOrNull()?.uppercaseChar()
        var sName = lastName?.trimStart()?.firstOrNull()?.uppercaseChar()
        return when {
            fName == null && sName == null -> null
            fName == null -> sName.toString()
            sName == null -> fName.toString()
            else -> fName.toString() + sName.toString()
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var res = ""

        for (i in payload) {
            when {
                i == ' ' -> res += divider
                !dictionary.containsKey(i.lowercaseChar()) -> res += i
                dictionary.containsKey(i.lowercaseChar()) -> {
                    if (i.isLowerCase())
                        res += dictionary[i]
                    else {
                        var temp = dictionary[i.lowercaseChar()].toString()
                        if (temp.length > 1) {
                            res += temp[0].uppercaseChar()
                            for (j in 1 until temp.length) {
                                res += temp[j]
                            }
                        } else res += temp.uppercase(Locale.getDefault())
                    }
                }

            }
        }
        return res
    }
}