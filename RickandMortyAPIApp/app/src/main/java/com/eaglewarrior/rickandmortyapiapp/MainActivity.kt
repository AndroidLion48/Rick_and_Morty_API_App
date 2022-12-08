package com.eaglewarrior.rickandmortyapiapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView

class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }


    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.characterByIdLiveData.observe(this) { response ->

            epoxyController.characterResponse = response
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful Network Call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }
        viewModel.refreshCharacter(26)

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}
//            nameTextView.text = response.name
//            aliveTextView.text = response.status
//            speciesTextView.text = response.species
//            originTextView.text = response.origin.name
//
//            if (response.gender.equals("male", true)) {
//                genderImageView.setImageResource(R.drawable.ic_male_24)
//            } else {
//                genderImageView.setImageResource(R.drawable.ic_female_24)
//            }

//            Picasso.get()
//                .load(response.image)
//                .error(R.drawable.ic_male_24)
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .fit()
//                .into(headerImageView)
//
//            Glide.with(headerImageView)
//                .load(response.image)
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .fitCenter()
//                .into(headerImageView)
//            }
//        }
//    }

//        NetworkLayer.apiClient.getCharacterById(432)
//        rickAndMortyService.getCharacterById(548)
//            .enqueue(object : Callback<GetCharacterByIdResponse> {
//
//                override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
//                    Log.i("MainActivity", response.toString())
//
//                    if (!response.isSuccessful) {
//                        Toast.makeText(this@MainActivity, "Unsuccessfull network call!", Toast.LENGTH_LONG)
//                        return
//                    }
//
//                    val body = response.body()!!
//
//                    nameTextView.text = body.name
//                    aliveTextView.text = body.status
//                    speciesTextView.text = body.species
//                    originTextView.text = body.origin.name
//
//                    if (body.gender.equals("male", true)) {
//                        genderImageView.setImageResource(R.drawable.ic_male_24)
//                    } else {
//                        genderImageView.setImageResource(R.drawable.ic_female_24)
//                    }
//
////                Picasso.get()
////                    .load(body.image)
////                    .error(R.drawable.ic_male_24)
////                    .placeholder(R.drawable.ic_launcher_foreground)
////                    .fit()
////                    .into(headerImageView)
//
//                    Glide.with(headerImageView)
//                        .load(body.image)
//                        .placeholder(R.drawable.ic_launcher_foreground)
//                        .fitCenter()
//                        .into(headerImageView)
//
//                }
//
//                override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
//                    Log.i("MainActivity", t.message ?: "Null message")
//                }
//            })
//    }
