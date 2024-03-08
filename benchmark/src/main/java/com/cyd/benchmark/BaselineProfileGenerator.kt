package com.cyd.benchmark

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import com.cyd.ui.view.base.CategoryListScreenConstants
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalBaselineProfilesApi::class)
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        rule.collectBaselineProfile(packageName = "com.cyd") {
            startApplicationJourney()
            goToCategoriesJourney()
        }
    }

    private fun MacrobenchmarkScope.startApplicationJourney() {
        pressHome()
        startActivityAndWait()
    }

    private fun MacrobenchmarkScope.goToCategoriesJourney() {
        device.findObject(By.text("Go to Categories"))
            .clickAndWait(Until.newWindow(), 1_000)

        device.findObject(By.res(CategoryListScreenConstants.CATEGORY_LIST)).also {
            it.fling(Direction.DOWN)
            it.fling(Direction.UP)
        }
        device.pressBack()
    }
}