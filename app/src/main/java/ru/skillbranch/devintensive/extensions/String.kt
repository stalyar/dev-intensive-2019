package ru.skillbranch.devintensive.extensions

import java.lang.StringBuilder

fun String.truncate(value: Int = 16):String{
    if (this.trimEnd().length < value) return this.trimEnd()
    var res = this.subSequence(0, value)
    res = res.trimEnd()
    res = "$res..."
    return res
}

fun String.stripHtml():String{
    var res = StringBuilder("")
    //remove odd spaces
    var isFirstSpace = false
    for (char in this){
        if (char==' '){
            if (isFirstSpace){
                continue
            }
            else {
                isFirstSpace = true
                res.append(char)
            }
        }
        else {
            isFirstSpace = false
            res.append(char)
        }
    }
    //remove <tag>
    val stringNoOddSpaces = res.toString()
    res = StringBuilder("")
    var isLessSign = false
    for (char in stringNoOddSpaces){
        if (char=='<'){
            isLessSign = true
            continue
        }
        else if (char=='>' && isLessSign){
            isLessSign=false
            continue
        }
        else {
            if (isLessSign) continue
            res.append(char)
        }
    }
    //remove escape sings
    var stringNoTags = res.toString()
    stringNoTags = stringNoTags.replace("&amp;", "&")
    stringNoTags = stringNoTags.replace("&lt;", "<")
    stringNoTags = stringNoTags.replace("&gt;", ">")
    stringNoTags = stringNoTags.replace("&rsquo;", "'")
    stringNoTags = stringNoTags.replace("&quot;", "\"")

    return stringNoTags
}