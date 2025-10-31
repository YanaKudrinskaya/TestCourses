package com.yanakudrinskaya.data.network

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

internal class MockInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return when {
            chain.request().url.encodedPath.contains("/courses") -> {
                val jsonString = loadJsonFromAssets("assets/data/courses.json")
                Response.Builder()
                    .code(200)
                    .message("Success")
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .body(jsonString.toResponseBody("application/json".toMediaTypeOrNull()))
                    .build()
            }

            else -> {
                Response.Builder()
                    .code(404)
                    .message("Not Found")
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .build()
            }
        }
    }

    private fun loadJsonFromAssets(filePath: String): String {
        return try {
            val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
            inputStream?.bufferedReader().use { it?.readText() } ?: "{\"courses\":[]}"
        } catch (e: Exception) {
            "{\"courses\":[]}"
        }
    }
}