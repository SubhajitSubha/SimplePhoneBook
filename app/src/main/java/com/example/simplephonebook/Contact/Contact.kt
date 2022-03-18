package com.example.simplephonebook.Contact

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize
import java.lang.NumberFormatException
@Parcelize
@Entity(tableName = "contact")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name: String,
    val number: String
    ):Parcelable


