// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

<<<<<<< HEAD
=======
//    Dagger-hilt
>>>>>>> a4bb5e4469add95a2aa371fe9627d91130333827
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}