package eu.lucifera.luciferasapp

import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {

            // override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            // super.onPageStarted(view, url, favicon)
            // Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT).show()
            // }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Toast.makeText(applicationContext, "Failed to load page", Toast.LENGTH_SHORT).show()
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                // Show error message or proceed anyway (not recommended for production)
                handler?.cancel() // Cancel loading on SSL error
                Toast.makeText(applicationContext, "SSL Certificate error", Toast.LENGTH_SHORT).show()
            }
        }

        // Load your website
        webView.loadUrl("https://lucifera.eu")
    }

    // Handle back button to go back in web history
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}