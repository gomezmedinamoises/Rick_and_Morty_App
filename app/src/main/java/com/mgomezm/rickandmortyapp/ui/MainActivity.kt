package com.mgomezm.rickandmortyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mgomezm.rickandmortyapp.R
import com.mgomezm.rickandmortyapp.databinding.ActivityMainBinding
import com.mgomezm.rickandmortyapp.model.Character
import com.mgomezm.rickandmortyapp.network.RetrofitInstance
import com.mgomezm.rickandmortyapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getCharacter(19)

        viewModel.characterLiveData.observe(this) { response ->

            if (response == null) {
                Toast.makeText(this@MainActivity, "Network call was unsuccessful", Toast.LENGTH_LONG).show()
                return@observe
            }

            Toast.makeText(this@MainActivity, "Network call was successful", Toast.LENGTH_LONG).show()
            Picasso.get().load(response.image).into(binding.headerImageView)

            binding.id.text = response.id.toString()
            binding.fullName.text = response.name

            if (response.gender.equals("male", true)) {
                binding.genderImageView.setImageResource(R.drawable.ic_male_24)
            } else {
                binding.genderImageView.setImageResource(R.drawable.ic_female_24)
            }

            binding.status.text = response.status
            binding.originText.text = response.origin.name
            binding.speciesText.text = response.species
            binding.created.text = response.created

        }

//        rickAndMortyService.getCharacterById(7).enqueue(object : Callback<Character> {
//            override fun onResponse(call: Call<Character>, response: Response<Character>) {
//                Log.i("MainActivity", response.toString())
//                if (response.isSuccessful) {
//                    Toast.makeText(this@MainActivity, "Network call was successful", Toast.LENGTH_LONG).show()
//                    Picasso.get().load(response.body()?.image).into(binding.headerImageView)
//
//                    binding.id.text = response.body()?.id.toString()
//                    binding.fullName.text = response.body()?.name
//
//                    if (response.body()?.gender.equals("male", true)) {
//                        binding.genderImageView.setImageResource(R.drawable.ic_male_24)
//                    } else {
//                        binding.genderImageView.setImageResource(R.drawable.ic_female_24)
//                    }
//
//                    binding.status.text = response.body()?.status
//                    binding.originText.text = response.body()?.origin?.name ?: "Unknown"
//                    binding.speciesText.text = response.body()?.species
//                    binding.created.text = response.body()?.created
//                } else {
//                    Toast.makeText(this@MainActivity, "Network call was unsuccessful", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Character>, t: Throwable) {
//                Log.i("MainActivity", t.message ?: "Null message")
//            }
//        })
    }
}