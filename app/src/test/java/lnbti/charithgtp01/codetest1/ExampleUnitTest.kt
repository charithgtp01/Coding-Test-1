package lnbti.charithgtp01.codetest1

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `test calculate two numbers`(){
        // Arrange: Prepare the input values
        val value1 = 5
        val value2 = 7

        // Act: Perform the addition
        val result = value1+value2

        // Assert: Verify the result
        val expectedSum = 12
        Assert.assertEquals(expectedSum, result)
    }
}