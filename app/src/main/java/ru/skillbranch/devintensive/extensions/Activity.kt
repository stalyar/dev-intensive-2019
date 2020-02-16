package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*


fun Activity.hideKeyboard(){
    val view: View = this.findViewById(android.R.id.content)
    val imm: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
}

fun Activity.isKeyboardOpen():Boolean{

    var isOpen:Boolean = false

    val rootView = root_view
    rootView.viewTreeObserver.addOnGlobalLayoutListener {
        val rect : Rect = Rect()
        rootView.getWindowVisibleDisplayFrame(rect)
        val heightDiff = rootView.rootView.height - (rect.bottom - rect.top)
        if (heightDiff > 100) isOpen = true//is keyboard
    }
    return isOpen
}

fun Activity.isKeyboardClosed():Boolean{

    var isClosed:Boolean = true

    val rootView = root_view
    rootView.viewTreeObserver.addOnGlobalLayoutListener {
        val rect : Rect = Rect()
        rootView.getWindowVisibleDisplayFrame(rect)
        val heightDiff = rootView.rootView.height - (rect.bottom - rect.top)
        if (heightDiff > 100) isClosed = false//is keyboard
    }
    return isClosed

}