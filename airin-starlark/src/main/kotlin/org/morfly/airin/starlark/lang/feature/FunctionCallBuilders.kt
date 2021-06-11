/*
 * Copyright 2021 Pavlo Stavytskyi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.morfly.airin.starlark.lang.feature

import org.morfly.airin.starlark.elements.*
import org.morfly.airin.starlark.lang.*
import org.morfly.airin.starlark.lang.api.StatementsHolder
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

// FIXME
// ===== Function call statements =====

/**
 * Registers function statement in the Starlark file.
 */
fun StatementsHolder.registerFunctionCallStatement(name: String, args: Set<Argument> = emptySet()) {
    statements += ExpressionStatement(VoidFunctionCall(name, args))
}

/**
 * Registers function statement in the Starlark file.
 */
fun StatementsHolder.registerFunctionCallStatement(name: String, args: Map<String, *>) {
    registerFunctionCallStatement(name, Arguments(args))
}

/**
 * Registers function statement in the Starlark file.
 */
inline fun <T : FunctionCallContext> StatementsHolder.registerFunctionCallStatement(
    name: String, context: T, body: T.() -> Unit
) {
    val args = context.apply(body).fargs
    registerFunctionCallStatement(name, args)
}

// ===== Function call expressions =====

/**
 * Builds a function call expression that returns string type.
 */
fun stringFunctionCall(name: String, args: Set<Argument> = emptySet()): StringType =
    StringFunctionCall(name, args)

/**
 * Builds a function call expression that returns string type.
 */
fun stringFunctionCall(name: String, args: Map<String, *>): StringType =
    StringFunctionCall(name, args = Arguments(args))

/**
 * Builds a function call expression that returns list type.
 */
fun <T> listFunctionCall(name: String, args: Set<Argument> = emptySet()): List<T> =
    ListFunctionCall(name, args)

/**
 * Builds a function call expression that returns list type.
 */
fun <T> listFunctionCall(name: String, args: Map<String, *>): List<T> =
    ListFunctionCall(name, args = Arguments(args))

/**
 * Builds a function call expression that returns list type.
 */
fun <T> tupleFunctionCall(name: String, args: Set<Argument> = emptySet()): TupleType =
    TupleFunctionCall(name, args)

/**
 * Builds a function call expression that returns list type.
 */
fun <T> tupleFunctionCall(name: String, args: Map<String, *>): TupleFunctionCall =
    TupleFunctionCall(name, args = Arguments(args))

/**
 * Builds a function call expression that returns dictionary type.
 */
fun dictFunctionCall(name: String, args: Set<Argument> = emptySet()): Map<Key, Value> =
    DictionaryFunctionCall(name, args)

/**
 * Builds a function call expression that returns dictionary type.
 */
fun dictFunctionCall(name: String, args: Map<String, *>): Map<Key, Value> =
    DictionaryFunctionCall(name, args = Arguments(args))

/**
 * Builds a function call expression that returns integer type.
 */
fun numberFunctionCall(name: String, args: Set<Argument> = emptySet()): NumberType =
    NumberFunctionCall(name, args)

/**
 * Builds a function call expression that returns integer type.
 */
fun numberFunctionCall(name: String, args: Map<String, *>): NumberType =
    numberFunctionCall(name, args = Arguments(args))

/**
 * Builds a function call expression that returns boolean type.
 */
fun booleanFunctionCall(name: String, args: Set<Argument> = emptySet()): BooleanType =
    BooleanFunctionCall(name, args)

/**
 * Builds a function call expression that returns boolean type.
 */
fun booleanFunctionCall(name: String, args: Map<String, *>): BooleanType =
    booleanFunctionCall(name, args = Arguments(args))

/**
 * Function call expression return type if which is specified by the caller.
 *
 * Note: if the function you're building has a specific determined type DO NOT use this builder.
 */
@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> functionCallExpression(name: String, args: Set<Argument> = emptySet()): T =
    when {
        StringType::class.java.isAssignableFrom(T::class.java) -> StringFunctionCall(name, args)
        List::class.java.isAssignableFrom(T::class.java) -> ListFunctionCall<Value>(name, args)
        Map::class.java.isAssignableFrom(T::class.java) -> DictionaryFunctionCall<Key, Value>(name, args)
        NumberType::class.java.isAssignableFrom(T::class.java) -> NumberFunctionCall(name, args)
        TupleType::class.java.isAssignableFrom(T::class.java) -> TupleFunctionCall(name, args)
        Comparable::class.java.isAssignableFrom(T::class.java) -> {
            val type = typeOf<T>().arguments.first().type
            when (type?.classifier as? KClass<*>) {
                Boolean::class -> BooleanFunctionCall(name, args)
                else -> AnyFunctionCall(name, args)
            }
        }
        else -> AnyFunctionCall(name, args)
    } as T

/**
 * Function call expression return type if which is specified by the caller.
 *
 * Note: if the function you're building has a specific determined type DO NOT use this builder.
 */
inline fun <reified T> functionCallExpression(name: String, args: Map<String, *>): T =
    functionCallExpression(name, args = Arguments(args))