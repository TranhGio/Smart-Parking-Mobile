package com.davie.smartparking.ui.onboarding

import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.davie.smartparking.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var introBinding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        introBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(introBinding.root)

        introBinding.webview.run {
            settings.javaScriptEnabled = true
            loadUrl("https://khanhld2k1.github.io/iot_fe/")
            webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url!!)
                    return true
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && introBinding.webview.canGoBack()) {
            introBinding.webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}