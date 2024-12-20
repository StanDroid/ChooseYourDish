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

include(":core:data")
include(":core:base")

include(":core:ui")
include(":feature:random_meal")
include(":feature:categories")
include(":benchmark")
include(":feature:category_meals")
include(":feature:meal_details")
include(":feature:search")
