package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16):String{
    if (this.trimEnd().length < value) return this.trimEnd()
    var mes = this.subSequence(0, value)
    mes = mes.trimEnd()
    mes = "$mes..."
    return mes
}