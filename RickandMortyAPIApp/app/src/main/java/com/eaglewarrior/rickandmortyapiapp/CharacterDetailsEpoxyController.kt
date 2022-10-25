package com.eaglewarrior.rickandmortyapiapp

import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.Glide
import com.eaglewarrior.rickandmortyapiapp.databinding.ModelCharacterDetailsDataPointBinding
import com.eaglewarrior.rickandmortyapiapp.databinding.ModelCharacterDetailsHeaderBinding
import com.eaglewarrior.rickandmortyapiapp.databinding.ModelCharacterDetailsImageBinding
import com.eaglewarrior.rickandmortyapiapp.epoxy.LoadingEpoxyModel
import com.eaglewarrior.rickandmortyapiapp.epoxy.ViewBindingKotlinModel
import com.eaglewarrior.rickandmortyapiapp.network.response.GetCharacterByIdResponse

/**
 * Created by Clarence E Moore on 2022-10-15.
 *
 * Description:
 *
 *
 */
class CharacterDetailsEpoxyController : EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var characterResponse: GetCharacterByIdResponse? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (characterResponse == null) {
            return
        }

        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status
        ).id("header").addTo(this)

        ImageEpoxyModel(
            imageUrl = characterResponse!!.image
        ).id("image").addTo(this)

        DataPointEpoxyModel(
            title = "Origin",
            description = characterResponse!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
        ).id("data_point_1").addTo(this)

    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status

            if (gender.equals("male", true)) {
                genderImageView.setImageResource(R.drawable.ic_male_24)
            } else {
                genderImageView.setImageResource(R.drawable.ic_female_24)
            }
        }
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image) {

        override fun ModelCharacterDetailsImageBinding.bind() {
            Glide.with(headerImageView)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fitCenter()
                .into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            originLabelTextView.text = title
            originTextView.text = description
            speciesLabelTextView.text = title
            speciesTextView.text =description
        }
    }
}
