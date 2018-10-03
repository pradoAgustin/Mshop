package com.agustin.mlshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        setActionBarIfRequired()
    }

    private fun setActionBarIfRequired() {
        val actionBar = supportActionBar
        if (shouldDisplayActionBar()) {
            actionBar?.also {
                it.elevation = 4.0F
                it.setDisplayHomeAsUpEnabled(true)
            }
        } else {
            actionBar?.hide()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    protected fun shouldDisplayActionBar(): Boolean {
        return true
    }

    abstract fun getLayoutResourceId(): Int

}