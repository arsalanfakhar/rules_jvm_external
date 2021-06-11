package template

import org.morfly.airin.starlark.lang.BUILD
import org.morfly.airin.starlark.lang.bazel
import org.morfly.airin.starlark.library.aar_import
import org.morfly.airin.starlark.library.artifact
import org.morfly.airin.starlark.library.java_import


fun artifacts_build(
    /**
     *
     */
) = BUILD.bazel("artifacts") {
    load("@rules_android//android:rules.bzl", "android_binary")
    load("@rules_jvm_external//:defs.bzl", "artifact")

    aar_import(
        name = "room_runtime",
        aar = "room-runtime-2.3.0.aar",
        visibility = list["//:__pkg__"],
        deps = list[
                artifact("androidx.sqlite:sqlite"),
                artifact("androidx.arch.core:core-common"),
                artifact("androidx.lifecycle:lifecycle-livedata-ktx"),
        ],
    )

    aar_import(
        name = "room_ktx",
        aar = "room-ktx-2.3.0.aar",
        visibility = list["//:__pkg__"],
    )

    java_import(
        name = "kotlin_reflect",
        jars = list[
                "kotlin-reflect-1.5.10.jar_desugared.jar",
        ],
        visibility = list["//:__pkg__"],
    )
}