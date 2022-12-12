package com.example.chooseyourdish.feature.meal_details.usecase

import com.example.chooseyourdish.core.data.model.mealdb.Meal
import com.example.chooseyourdish.core.data.usecase.UseCaseSuspend
import com.example.data.meal.MealRepository
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<String, Meal?> {

    override suspend fun execute(params: String): Meal? {
        return repository.getMealDetails(params)?.let {
                Meal(
                    id = it.idMeal.orEmpty(),
                    dateModified = it.dateModified.orEmpty(),
                    area = it.strArea.orEmpty(),
                    category = it.strCategory.orEmpty(),
                    creativeCommonsConfirmed = it.strCreativeCommonsConfirmed.orEmpty(),
                    drinkAlternate = it.strDrinkAlternate.orEmpty(),
                    imageSource = it.strImageSource.orEmpty(),
                    ingredient1 = it.strIngredient1.orEmpty(),
                    ingredient10 = it.strIngredient10.orEmpty(),
                    ingredient11 = it.strIngredient11.orEmpty(),
                    ingredient12 = it.strIngredient12.orEmpty(),
                    ingredient13 = it.strIngredient13.orEmpty(),
                    ingredient14 = it.strIngredient14.orEmpty(),
                    ingredient15 = it.strIngredient15.orEmpty(),
                    ingredient16 = it.strIngredient16.orEmpty(),
                    ingredient17 = it.strIngredient17.orEmpty(),
                    ingredient18 = it.strIngredient18.orEmpty(),
                    ingredient19 = it.strIngredient19.orEmpty(),
                    ingredient2 = it.strIngredient2.orEmpty(),
                    ingredient20 = it.strIngredient20.orEmpty(),
                    ingredient3 = it.strIngredient3.orEmpty(),
                    ingredient4 = it.strIngredient4.orEmpty(),
                    ingredient5 = it.strIngredient5.orEmpty(),
                    ingredient6 = it.strIngredient6.orEmpty(),
                    ingredient7 = it.strIngredient7.orEmpty(),
                    ingredient8 = it.strIngredient8.orEmpty(),
                    ingredient9 = it.strIngredient9.orEmpty(),
                    instructions = it.strInstructions.orEmpty(),
                    meal = it.strMeal.orEmpty(),
                    mealThumb = it.strMealThumb.orEmpty(),
                    measure1 = it.strMeasure1.orEmpty(),
                    measure10 = it.strMeasure10.orEmpty(),
                    measure11 = it.strMeasure11.orEmpty(),
                    measure12 = it.strMeasure12.orEmpty(),
                    measure13 = it.strMeasure13.orEmpty(),
                    measure14 = it.strMeasure14.orEmpty(),
                    measure15 = it.strMeasure15.orEmpty(),
                    measure16 = it.strMeasure16.orEmpty(),
                    measure17 = it.strMeasure17.orEmpty(),
                    measure18 = it.strMeasure18.orEmpty(),
                    measure19 = it.strMeasure19.orEmpty(),
                    measure2 = it.strMeasure2.orEmpty(),
                    measure20 = it.strMeasure20.orEmpty(),
                    measure3 = it.strMeasure3.orEmpty(),
                    measure4 = it.strMeasure4.orEmpty(),
                    measure5 = it.strMeasure5.orEmpty(),
                    measure6 = it.strMeasure6.orEmpty(),
                    measure7 = it.strMeasure7.orEmpty(),
                    measure8 = it.strMeasure8.orEmpty(),
                    measure9 = it.strMeasure9.orEmpty(),
                    source = it.strSource.orEmpty(),
                    tags = it.strTags.orEmpty(),
                    youtube = it.strYoutube.orEmpty()
                )
            }
    }
}