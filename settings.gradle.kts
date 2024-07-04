dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ChooseYourDish"

include(":app")

include(":data:categories")
include(":data:meal")
include(":data:network")

include(":core:data")
include(":core:base")
include(":core:ui")

include(":feature:random_meal")
include(":feature:categories")
include(":benchmark")
include(":feature:category_meals")
include(":feature:meal_details")
include(":feature:search")
