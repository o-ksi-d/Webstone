package com.cod5.webstone

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import com.cod5.webstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    class MyJs(private val self:MainActivity) {
        @JavascriptInterface
        fun cb(data: String): String {
            return self.JNIcb(data)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.theview.settings.javaScriptEnabled = true
        binding.theview.addJavascriptInterface(MyJs(this), "Android")
        binding.theview.loadUrl("file:///android_asset/index.html")
    }

    external fun JNIcb(s:String): String
    companion object {
        init {
            System.loadLibrary("webstone")
        }
    }
}