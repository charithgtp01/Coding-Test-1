package lnbti.charithgtp01.codetest1

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import lnbti.charithgtp01.codetest1.ui.contacts.MainActivity
import lnbti.charithgtp01.codetest1.ui.newcontact.NewContactActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MainActivityTest {

//    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
        // Launch the MainActivity using Robolectric
//        scenario = ActivityScenario.launch(MainActivity::class.java)
        // Create an instance of your activity
        activity = Robolectric.buildActivity(MainActivity::class.java).create().get()

    }

    @Test
    fun testFloatingActionButtonClickNavigatesToNextActivity() {
        // Click on the FloatingActionButton
//        scenario.onActivity { activity ->
//            activity.binding.floatingActionButton.performClick()
//        }
//
//        // Check if the next activity is started
//        scenario.onActivity { activity ->
//            val startedIntent = Robolectric.shadowOf(activity).peekNextStartedActivity()
//            val expectedIntent = Intent(activity, NewContactActivity::class.java)
//            // Assert that the started activity matches the expected intent
//            assert(startedIntent.component == expectedIntent.component)
//        }

        // Perform an action that triggers navigation to the second activity
        // For example, clicking a button or performing some action
        // Replace this with the actual action in your app
        // Here, we simulate a button click using Espresso for demonstration purposes
        Espresso.onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(ViewActions.click())

        // Wait for the SecondActivity to be launched
        ActivityScenario.launch(NewContactActivity::class.java)

        Assert.assertEquals(activity.javaClass, NewContactActivity::class.java)
    }

    @Test
    fun testNavigationToSecondActivity() {
        // Create an Intent to navigate to SecondActivity
        val intent = Intent(activity, NewContactActivity::class.java)
        activity.startActivity(intent)

        // Now, you can assert that the transition occurred as expected
        val expectedActivityName = NewContactActivity::class.java.name
        val actualActivityName = Shadows.shadowOf(activity).nextStartedActivity.component?.className

        assert(expectedActivityName == actualActivityName)
    }

//    @Test
//    fun testOnCreate() {
//        scenario.onActivity {activity->
//            // Simulate the call to onCreate with a null Bundle
//            val savedInstanceState: Bundle? = null
//            activity.onCreate(savedInstanceState)
//
//            // Add your assertions to verify the behavior of the onCreate method
//            // For example, you can check if viewModel, binding, and other components are initialized as expected
//            // Replace these assertions with your actual verification logic
//            assert(activity.binding != null)
//            assert(activity.viewModel != null)
//            // Add more assertions as needed
//        }
//
//    }


}