package com.mgomezm.rickandmortyapp

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mgomezm.rickandmortyapp.databinding.ActivityMainBinding
import com.mgomezm.rickandmortyapp.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rickAndMortyService: RickAndMortyService = retrofit
            .create(RickAndMortyService::class.java)

        rickAndMortyService.getCharacterById(7).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                Log.i("MainActivity", response.toString())
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Network call was successful", Toast.LENGTH_LONG).show()
                    Picasso.get().load(response.body()?.image).into(binding.headerImageView)

                    binding.id.text = response.body()?.id.toString()
                    binding.fullName.text = response.body()?.name

                    if (response.body()?.gender.equals("male", true)) {
                        binding.genderImageView.setImageResource(R.drawable.ic_male_24)
                    } else {
                        binding.genderImageView.setImageResource(R.drawable.ic_female_24)
                    }

                    binding.status.text = response.body()?.status
                    binding.originText.text = response.body()?.origin?.name ?: "Unknown"
                    binding.speciesText.text = response.body()?.species
                    binding.created.text = response.body()?.created
                } else {
                    Toast.makeText(this@MainActivity, "Network call was unsuccessful", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null message")
            }
        })
    }
}