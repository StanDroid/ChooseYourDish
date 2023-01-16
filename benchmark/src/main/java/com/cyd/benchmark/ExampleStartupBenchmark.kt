package com.cyd.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import com.cyd.ui.view.base.CategoryListScreenConstants
import com.cyd.ui.view.base.CategoryListScreenConstants.CATEGORY_ITEM
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.cyd",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        startUp()
    }

    private fun MacrobenchmarkScope.startUp() {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun goToCategoriesAndScroll() = benchmarkRule.measureRepeated(
        packageName = "com.cyd",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {
            startUp()
            val buttonGoToCategories = device.findObject(By.text("Go to Categories"))
            buttonGoToCategories.click()
        }
    ) {

        val categoryList = device.findObject(By.res(CategoryListScreenConstants.CATEGORY_LIST))
        val searchCondition = Until.hasObject(By.res(CATEGORY_ITEM))
        // Wait until a snack collection item within the list is rendered
        categoryList.wait(searchCondition, 5_000)
        categoryList.setGestureMargin(device.displayWidth / 5)
        categoryList.fling(Direction.DOWN)

        device.waitForIdle()
    }
}