package com.example.groupware.connectDB

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.core.net.toUri
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.groupware.managerScreen.CenterInfo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface ApiService {
    @Multipart
    @POST("saveimg.php")
    fun uploadImages(
        @Part picture1: MultipartBody.Part?,
        @Part picture2: MultipartBody.Part?,
        @Part picture3: MultipartBody.Part?,
        @Part picture4: MultipartBody.Part?,
        @Part picture5: MultipartBody.Part?
    ): Call<ResponseBody>
}

object RetrofitClient {
    private const val BASE_URL = "http://192.168.45.237/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

fun uriToMultipartBodyPart(context: Context, uri: String, partName: String): MultipartBody.Part? {
    val file = File(context.cacheDir, uri.toUri().lastPathSegment ?: return null)
    context.contentResolver.openInputStream(uri.toUri())?.use { inputStream ->
        file.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }

    val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
    return MultipartBody.Part.createFormData(partName, file.name, requestFile)
}

fun sendImgServer(
    imageUris: MutableList<String>,
    centerInfo: MutableState<CenterInfo>,
    context: Context
) {
    val parts = imageUris.mapIndexed { index, uri ->
        uriToMultipartBodyPart(context, uri, "picture${index + 1}")
    }
    val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    val apiService = RetrofitClient.instance
    val call = apiService.uploadImages(
        parts.getOrNull(0),
        parts.getOrNull(1),
        parts.getOrNull(2),
        parts.getOrNull(3),
        parts.getOrNull(4)
    )


    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                val responseString = response.body()?.string()
                Log.d("Response", responseString ?: "Response is null")

                // 서버 응답을 JSON으로 파싱
                try {
                    val jsonResponse = responseString?.let { JSONObject(it) }

                    // Declare a mutable list to store file paths
                    val filePaths = mutableListOf("", "", "", "", "")

                    // 각 이미지의 업로드 상태를 출력
                    for (i in 1..5) {
                        val pictureKey = "picture$i"
                        val filePath = jsonResponse?.optString(pictureKey, "No file uploaded")
                        filePaths[i-1] = (filePath ?: "")
                        Log.d("File Path", "$pictureKey: $filePath")
                    }

                    val responseListener = com.android.volley.Response.Listener<String> { response ->
                        // Handle response here
                        println("Response: $response")
                    }
                    val errorListener = com.android.volley.Response.ErrorListener { error ->
                        // Handle error here
                        println("Error: ${error.message}")
                    }

                    val registerRequest = CenterRegistRequest(
                        "ttttest1231231123213123",
                        "test",
                        2,
                        "ss",
                        "sss",
                        "ss",
                        "Y",
                        "1",
                        filePaths[0],
                        filePaths[1],
                        filePaths[2],
                        filePaths[3],
                        filePaths[4],
                        responseListener,
                        errorListener)

                requestQueue.add(registerRequest)

                } catch (e: JSONException) {
                    Log.e("Error", "Failed to parse JSON: ${e.message}")
                }
            } else {
                Log.e("Error", "Upload failed: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.e("Error", "Upload failed: ${t.message}")
        }
    })
}

