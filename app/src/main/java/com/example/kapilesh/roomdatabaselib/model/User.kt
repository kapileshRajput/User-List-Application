package com.example.kapilesh.roomdatabaselib.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
): Parcelable {
}