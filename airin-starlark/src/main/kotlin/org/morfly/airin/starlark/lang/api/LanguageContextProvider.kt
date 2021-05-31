package org.morfly.airin.starlark.lang.api


/**
 *
 */
interface LanguageContextProvider<out C : LanguageContext> {

    /**
     *
     */
    fun newContext(): C
}