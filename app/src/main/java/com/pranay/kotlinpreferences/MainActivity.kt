package com.pranay.kotlinpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.pranay.kotlinpreferenceslib.KotlinPreferencesHelper
import java.util.HashSet

class MainActivity : AppCompatActivity() {

    var sharePreferencesHelper: KotlinPreferencesHelper? = null
    lateinit var PREFERENCE_KEY: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        PREFERENCE_KEY = this.applicationInfo.labelRes.toString()
        callExamplesForLib()

    }

    fun callExamplesForLib() {
        // use one of the following ways to instantiate
        sharePreferencesHelper = KotlinPreferencesHelper(this) //this will use default shared preferences
        sharePreferencesHelper = KotlinPreferencesHelper(this, PREFERENCE_KEY) // this will create a named shared preference file
        sharePreferencesHelper = KotlinPreferencesHelper(this, PREFERENCE_KEY, 0) // this will allow you to specify a mode

        // putting values
        sharePreferencesHelper!!.putBoolean("boolKey", true)
        sharePreferencesHelper!!.putInt("intKey", 123)
        sharePreferencesHelper!!.putString("stringKey", "string value")
        sharePreferencesHelper!!.putLong("longKey", 456876451)
        sharePreferencesHelper!!.putFloat("floatKey", 1.51f)

        // putStringSet is supported only for android versions above HONEYCOMB
        val name = HashSet<String>()
        name.add("Pranay")
        name.add("Patel")
        sharePreferencesHelper!!.putStringSet("name", name)

        //getting values
        println(sharePreferencesHelper!!.getBoolean("boolKey"))
        println(sharePreferencesHelper!!.getInt("intKey"))
        println(sharePreferencesHelper!!.getString("stringKey"))
        println(sharePreferencesHelper!!.getLong("longKey"))
        println(sharePreferencesHelper!!.getFloat("floatKey"))

        // getStringSet is supported only for android versions above HONEYCOMB
        sharePreferencesHelper!!.getStringSet("name")


        // getting values with custom default per key
        sharePreferencesHelper!!.getBoolean("boolKey", true)
        sharePreferencesHelper!!.getInt("intKey", -1)
        sharePreferencesHelper!!.getString("stringKey", "my custom default string")
        sharePreferencesHelper!!.getLong("longKey", -222)
        sharePreferencesHelper!!.getFloat("floatKey", -13.76f)

        // getStringSet is supported only for android versions above HONEYCOMB
        sharePreferencesHelper!!.getStringSet("name", HashSet<String>())


        //changing default calues for each datatype
        sharePreferencesHelper!!.isBoolDefaultVal = true
        sharePreferencesHelper!!.floatDefaultVal = -3.6f
        sharePreferencesHelper!!.intDefaultVal = -3
        sharePreferencesHelper!!.longDefaultVal = -999
        sharePreferencesHelper!!.stringDefaultVal = "custom default string"
        sharePreferencesHelper!!.stringSetDefaultVal = HashSet<String>()


        //registering a listener
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            // do what you got to do here
        }

        sharePreferencesHelper!!.registerListener(listener)

        //un-registering the listener
        sharePreferencesHelper!!.unregisterListener(listener)
    }

}
