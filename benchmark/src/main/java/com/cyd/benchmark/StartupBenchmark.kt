package com.cyd.benchmark

import androidx.benchmark.macro.*
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

@RunWith(AndroidJUnit4::class)
class StartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startupCompilationNone() = startup(CompilationMode.None())

    @Test
    fun startupCompilationPartial() = startup(CompilationMode.Partial())

    @Test
    fun startupCompilationFull() = startup(CompilationMode.Full())

    private fun startup(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "com.cyd",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = compilationMode
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
            device.findObject(By.text("Go to Categories"))
                .clickAndWait(Until.newWindow(), 1_000)
        }
    ) {
        val categoryList = device.findObject(By.res(CategoryListScreenConstants.CATEGORY_LIST))
        val searchCondition = Until.hasObject(By.res(CATEGORY_ITEM))

        categoryList.wait(searchCondition, 5_000)
        categoryList.setGestureMargin(device.displayWidth / 5)
        categoryList.fling(Direction.DOWN)

        device.waitForIdle()
    }
}