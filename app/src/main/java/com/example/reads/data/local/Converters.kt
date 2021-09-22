package com.example.reads.data.local

import androidx.room.TypeConverter
import com.example.reads.data.model.AccessInfo
import com.example.reads.data.model.SaleInfo
import com.example.reads.data.model.SearchInfo
import com.example.reads.data.model.VolumeInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun infoToJson(info: AccessInfo): String =
        moshi.adapter(AccessInfo::class.java).toJson(info)

    @TypeConverter
    fun infoFromJson(json: String): AccessInfo? =
        moshi.adapter(AccessInfo::class.java).fromJson(json)

    @TypeConverter
    fun saleInfoToJson(saleInfo: SaleInfo): String =
        moshi.adapter(SaleInfo::class.java).toJson(saleInfo)

    @TypeConverter
    fun saleInfoFromJson(json: String): SaleInfo? =
        moshi.adapter(SaleInfo::class.java).fromJson(json)

    @TypeConverter
    fun searchInfoToJson(searchInfo: SearchInfo?): String =
        moshi.adapter(SearchInfo::class.java).toJson(searchInfo)

    @TypeConverter
    fun searchInfoFromJson(json: String): SearchInfo? =
        moshi.adapter(SearchInfo::class.java).fromJson(json)

    @TypeConverter
    fun volumeInfoToJson(volumeInfo: VolumeInfo): String =
        moshi.adapter(VolumeInfo::class.java).toJson(volumeInfo)

    @TypeConverter
    fun volumeInfoFromJson(json: String): VolumeInfo? =
        moshi.adapter(VolumeInfo::class.java).fromJson(json)
}