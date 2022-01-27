package com.example.movieviews.presentation.ui.utils

import androidx.fragment.app.Fragment
import androidx.test.rule.ActivityTestRule
import com.example.movieviews.R
import com.example.movieviews.presentation.ui.activity.MainActivity
import org.junit.Assert

class FragmentTestRule<F : Fragment>(fragmentClass: Class<F>) : ActivityTestRule<MainActivity>(
    MainActivity::class.java,
    true,
    false
) {
    private val mFragmentClass: Class<F> = fragmentClass
    private var mFragment: F? = null

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity.runOnUiThread {
            try {
                //Instantiate and insert the fragment into the container layout
                val fragmentManager = activity.supportFragmentManager
                val transactionFragment = fragmentManager.beginTransaction()
                mFragment = mFragmentClass.newInstance()
                mFragment?.let { fragment ->
                    transactionFragment.replace(R.id.nav_host_fragment, fragment)
                }
                transactionFragment.commit()
            } catch (e: InstantiationException) {
                Assert.fail(
                    String.format(
                        "%s: Could not insert %s into MainActivity: %s",
                        javaClass.simpleName,
                        mFragmentClass.simpleName,
                        e.message
                    )
                )
            }
        }
    }

    fun getFragment(): F? {
        return mFragment
    }
}