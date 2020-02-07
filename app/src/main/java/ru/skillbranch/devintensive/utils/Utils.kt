package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName : String?) : Pair<String?, String?>{
        val parts : List<String>? = fullName?.split(" ", limit=2)
        val firstName = if (parts?.getOrNull(0)=="") null else parts?.getOrNull(0)
        val lastName = if (parts?.getOrNull(1)=="") null else parts?.getOrNull(1)
        //return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        //TODO
        return " "
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