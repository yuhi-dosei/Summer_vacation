package com.example.summer_vacation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val tag = "test.retrofit2"


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(GitHubClient::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service.getUser("square")
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) { // ステータスコード200番以上300番未満のときtrue
                        //Success !!
                        val user: User? = response.body()
                        if (user != null) {
                            Log.d(tag, user.login)
                            Log.d(tag, user.type)
                            Log.d(tag, user.name)
                        }
                    } else {
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }
            })
    }
}
