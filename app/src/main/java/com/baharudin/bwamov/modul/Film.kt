package com.baharudin.bwamov.modul

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    var desc : String? = "",
    var judul : String? = "",
    var poster : String? = "",
    var rating : String? ="",
    var genre : String? ="",
    var director : String?=""
):Parcelable