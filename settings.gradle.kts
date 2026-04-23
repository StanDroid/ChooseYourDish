plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "ChooseYourDish"

include(":app")

include(":data:categories")
include(":data:ingredients")
include(":data:meal")
include(":data:network")
include(":data:db")

include(":core:base")
include(":core:ui")

include(":feature:randommeal")
include(":feature:categories")
include(":feature:categorymeals")
include(":feature:mealdetails")
include(":feature:search")

include(":benchmark")
