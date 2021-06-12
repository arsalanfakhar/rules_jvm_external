@file:Suppress("SpellCheckingInspection", "unused")

package template


fun bazelrc(
    javaToolchainTarget: String
    /**
     *
     */
) = """
    build --java_toolchain //:$javaToolchainTarget
    build --host_java_toolchain //:$javaToolchainTarget

    # Enable d8 merger
    build --define=android_dexmerger_tool=d8_dexmerger

    # Flags for the D8 dexer
    build --define=android_incremental_dexing_tool=d8_dexbuilder
    build --define=android_standalone_dexing_tool=d8_compat_dx
    build --nouse_workers_with_dexbuilder

    mobile-install --start_app
""".trimIndent()