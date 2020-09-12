package com.baharudin.bwamov.modul

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Play (
    var nama : String = "",
    var url : String=""
): Parcelable
