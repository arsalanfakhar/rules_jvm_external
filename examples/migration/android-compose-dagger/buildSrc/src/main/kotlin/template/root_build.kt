package template

import org.morfly.airin.starlark.lang.BUILD
import org.morfly.airin.starlark.lang.bazel
import org.morfly.airin.starlark.library.`package`
import org.morfly.airin.starlark.library.alias


fun root_build(
    /**
     *
     */
) = BUILD.bazel {
    `package`(default_visibility = list["//visibility:public"])

    alias(
        name = "java_toolchain",
        actual = "//tools/java:java_toolchain",
    )

    alias(
        name = "kotlin_toolchain",
        actual = "//tools/kotlin:kotlin_toolchain",
    )

    alias(
        name = "room_runtime",
        actual = "//artifacts:room_runtime",
    )

    alias(
        name = "room_ktx",
        actual = "//artifacts:room_ktx",
    )

    alias(
        name = "kotlin_reflect",
        actual = "//artifacts:kotlin_reflect",
    )

    alias(
        name = "jetpack_compose_compiler_plugin",
        actual = "//tools/android:jetpack_compose_compiler_plugin",
    )

    alias(
        name = "androidx_room_room_compiler_library",
        actual = "//tools/android:androidx_room_room_compiler_library",
    )

    load("@dagger//:workspace_defs.bzl", "dagger_rules")

    "dagger_rules"()
}