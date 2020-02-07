package ru.skillbranch.devintensive.utils

import java.util.*
import kotlin.text.StringBuilder

object Utils {

    fun parseFullName(fullName : String?) : Pair<String?, String?>{
        val parts : List<String>? = fullName?.split(" ", limit=2)
        val firstName = if (parts?.getOrNull(0)=="") null else parts?.getOrNull(0)
        val lastName = if (parts?.getOrNull(1)=="") null else parts?.getOrNull(1)
        //return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String?, divider: String = " "): String {

        val (firstName, lastName) = parseFullName(payload)

        var resRu:String? = if (firstName!=null && lastName!=null) "$firstName$divider$lastName"
        else if (firstName!=null && lastName==null) "$firstName"
        else if (firstName==null && lastName!=null) "$lastName"
        else null

        val resEn = StringBuilder()
        var letterEn = ""

        if (resRu!=null){
            resRu=resRu.toLowerCase()
            var isDivider = false
            for(letter in resRu){
                letterEn = when (letter){
                    'а' -> "a"
                    'б' -> "b"
                    'в' -> "v"
                    'г' -> "g"
                    'д' -> "d"
                    'е' -> "e"
                    'ё' -> "e"
                    'ж' -> "zh"
                    'з' -> "z"
                    'и' -> "i"
                    'й' -> "i"
                    'к' -> "k"
                    'л' -> "l"
                    'м' -> "m"
                    'н' -> "n"
                    'о' -> "o"
                    'п' -> "p"
                    'р' -> "r"
                    'с' -> "s"
                    'т' -> "t"
                    'у' -> "u"
                    'ф' -> "f"
                    'х' -> "h"
                    'ц' -> "c"
                    'ч' -> "ch"
                    'ш' -> "sh"
                    'щ' -> "sh'"
                    'ъ' -> ""
                    'ы' -> "i"
                    'ь' -> ""
                    'э' -> "e"
                    'ю' -> "yu"
                    'я' -> "ya"
                    else -> "$letter"
                }
                if (isDivider) {
                    letterEn=letterEn.capitalize()
                    isDivider=false
                }
                if (letterEn.equals(divider)) isDivider = true
                resEn.append(letterEn)
            }
        }

        return resEn.toString().capitalize()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstInitial:Char? = when (firstName){
            ""," ", null -> null
            else -> firstName?.first()?.toUpperCase()
        }
        val lastInitial:Char? = when (lastName){
            ""," ", null -> null
            else -> lastName?.first()?.toUpperCase()
        }
        val res:String? = if (firstInitial!=null && lastInitial!=null) "$firstInitial$lastInitial"
                            else if (firstInitial!=null && lastInitial==null) "$firstInitial"
                            else if (firstInitial==null && lastInitial!=null) "$lastInitial"
                            else null

        return res
    }
}