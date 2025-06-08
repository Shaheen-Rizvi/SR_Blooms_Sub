package com.example.sr_blooms.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CategoryPictures(
    @DrawableRes val imageResourceId: Int,
    @StringRes val stringResourceId: Int,
)
