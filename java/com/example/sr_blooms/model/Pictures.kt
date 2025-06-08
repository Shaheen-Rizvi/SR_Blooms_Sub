package com.example.sr_blooms.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Pictures(
    @DrawableRes val imageResourceId: Int,
    @DrawableRes val imageResourceId2: Int,
    @StringRes val stringResourceId: Int,
    @StringRes val stringResourceId2: Int,
    @StringRes val price: Int
)
