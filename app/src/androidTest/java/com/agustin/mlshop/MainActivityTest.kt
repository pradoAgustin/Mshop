package com.agustin.mlshop

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.agustin.mlshop.commons.AcceptanceTest
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Test


class MainActivityTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {
    val CATEGORY_INDEX_CHILD = 0

    @Test
    fun uglyMiniTest() {
        recyclerCommonFlow(getRecyclerClickActionForMainActivity())
    }

    private fun recyclerCommonFlow(viewAction: ViewAction) {
        waitForRecyclerLoad()
        selectItemFromRecyclerView(viewAction)
    }

    private fun waitForRecyclerLoad() {
        var recycler = testRule.activity.recyclerView
        while (recycler.childCount == 0) {
        }
    }

    private fun selectItemFromRecyclerView(viewAction: ViewAction) {
        Espresso.onView(withId(testRule.activity.recyclerView.id))
                .perform(viewAction)

        Espresso.onView(checkThat.withRecyclerView(testRule.activity.recyclerView.id).atPosition(CATEGORY_INDEX_CHILD)).perform(ViewActions.click())
    }

    private   fun getRecyclerClickActionForMainActivity() =
            RecyclerViewActions.scrollToPosition<MainActivity.MainProductsRecyclerViewHolder>(CATEGORY_INDEX_CHILD)

}



