package com.morlfy.airin.starlark.elements


/**
 *
 */
sealed interface ValueHolder : Element {

    var value: Expression?
}

/**
 *
 */
class DynamicValue(
    override var value: Expression?
) : ValueHolder, Expression {

    override fun <A> accept(visitor: ElementVisitor<A>, indentIndex: Int, mode: PositionMode, accumulator: A) {
        visitor.visit(this, indentIndex, mode, accumulator)
    }
}