package com.agustin.mlshop.commons

import android.app.Activity
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
abstract class AcceptanceTest<T : Activity>(clazz: Class<T>) {

    @Rule
    @JvmField
    var testRule: ActivityTestRule<T> = getRuleImplementation(clazz)

    open fun getRuleImplementation(clazz: Class<T>): ActivityTestRule<T> {
        return IntentsTestRule(clazz)
    }


    val checkThat: Matchers = Matchers()
    val events: Events = Events()
}
