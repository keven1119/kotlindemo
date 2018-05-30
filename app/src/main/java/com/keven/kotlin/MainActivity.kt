package com.keven.kotlin

import android.os.Bundle
import android.os.Environment
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.keven.kotlin.R.id.textview_home
import com.tencent.bugly.crashreport.CrashReport

import com.tencent.tinker.lib.tinker.TinkerInstaller
import com.tinkerpatch.sdk.TinkerPatch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        TinkerPatch.with().fetchPatchUpdate(true)
        //
        //        TextView textView = findViewById(R.id.textview);

//                textview.setText("这是没有bug版本");
        val textView = findViewById<TextView>(R.id.textview_home)
        val button = findViewById<Button>(R.id.button_exception)

        textView.text = "kotlin 补丁成功"

        button.setOnClickListener({v ->
            try {
                throw NullPointerException()
            }catch(e:Throwable){
                CrashReport.postCatchedException(e)
            }})



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}
