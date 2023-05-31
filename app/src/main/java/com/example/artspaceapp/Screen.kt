package com.example.artspaceapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtWork(
    @DrawableRes val imageId: Int,
    @StringRes val titleId: Int,
    @StringRes val authorId: Int,
    @StringRes val yearId: Int
)

sealed class Screen(val currentArtWork: ArtWork){
    object HorseWoman: Screen(currentArtWork = ArtWork(R.drawable.horse_woman, R.string.horse_woman_title, R.string.horse_woman_author, R.string.horse_woman_year))
    object Tarakanova: Screen(currentArtWork = ArtWork(R.drawable.tarakanova, R.string.tarakanova_title, R.string.tarakanova_author, R.string.tarakanova_year))
    object Alenushka: Screen(currentArtWork = ArtWork(R.drawable.alenushka, R.string.alenushka_title, R.string.alenushka_author, R.string.alenushka_year))
}
