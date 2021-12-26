package com.example.movieviews.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.rules.ExpectedException.none
import kotlin.jvm.Throws

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    private val expectedValue = 6

//    second execute
//    @Rule : To give rules to testing
    @get:Rule
    var thrown: ExpectedException? = none()

//    first execute
//    Annotation before is used to make all preparations before the test begins,
//    or before running the annotated method of the test
    @Before
    fun init() {
        mainViewModel = MainViewModel()
    }

//   after execute function with annotation @Test
    @Test
    fun calculate() {
        val width = "1"
        val length = "2"
        val height = "3"
        mainViewModel.calculate(width, height, length)
        assertEquals(expectedValue, mainViewModel.result)
    }



    @Test
    @Throws(NumberFormatException::class)
    fun doubleInputCalculateTest() {
        val width = "1"
        val length = "2.4"
        val height = "3"
        thrown?.expect(NumberFormatException::class.java)
        thrown?.expectMessage("For input string: \"2.4\"")
        mainViewModel.calculate(width, height, length)
    }

    @Test
    @Throws(NumberFormatException::class)
    fun characterInputCalculateTest() {
        val width = "1"
        val length = "A"
        val height = "3"
        thrown?.expect(NumberFormatException::class.java)
        thrown?.expectMessage("For input string: \"A\"")
        mainViewModel.calculate(width, height, length)
    }

    @Test
    @Throws(NumberFormatException::class)
    fun emptyInputCalculateTest() {
        val width = "1"
        val length = ""
        val height = "3"
        thrown?.expect(NumberFormatException::class.java)
        thrown?.expectMessage("For input string: \"\"")
        mainViewModel.calculate(width, height, length)
    }
}