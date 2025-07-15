package com.tops.androidfundamentals.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeModel(
    val id: Int,
    val name: String,
    val role: String

): Parcelable
