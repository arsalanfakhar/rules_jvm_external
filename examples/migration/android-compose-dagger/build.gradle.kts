// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion by extra("1.6.21")
    val composeVersion by extra("1.2.0-rc02")
    val composeActivityVersion by extra("1.3.0")
    val composeNavigationVersion by extra("2.4.0-alpha05")
    val composeViewModelVersion by extra("1.0.0-alpha07")
    val composePagingVersion by extra("1.0.0-alpha12")
    val composeCoilVersion by extra("1.3.1")
    val daggerVersion by extra("2.38")
    val pagingVersion by extra("3.0.0")
    val retrofitVersion by extra("2.9.0")
    val moshiVersion by extra("1.12.0")
    val roomVersion by extra("2.4.0")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

plugins {
    id("org.morfly.airin")
}

airin {
    templates {

        register<Workspace>()
        register<RootBuild>()
        register<ToolsBuild>()
        register<ThirdPartyBuild>()
        register<AndroidModuleBuild>()
        register<OtherBazelFiles>() // .bazelrc .bazelversion
    }

    artifacts {
        ignored = listOf(
            "com.google.dagger:dagger",
            "com.google.dagger:dagger-compiler"
        )
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}