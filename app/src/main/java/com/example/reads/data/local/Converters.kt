package com.example.reads.data.local

import androidx.room.TypeConverter
import com.example.reads.data.model.AccessInfo
import com.example.reads.data.model.SaleInfo
import com.example.reads.data.model.SearchInfo
import com.example.reads.data.model.VolumeInfo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun infoToJson(info: AccessInfo): String = Json.encodeToString(info)

    @TypeConverter
    fun infoFromJson(json: String): AccessInfo = Json.decodeFromString(json)

    @TypeConverter
    fun saleInfoToJson(saleInfo: SaleInfo): String = Json.encodeToString(saleInfo)

    @TypeConverter
    fun saleInfoFromJson(json: String): SaleInfo = Json.decodeFromString(json)

    @TypeConverter
    fun searchInfoToJson(searchInfo: SearchInfo): String = Json.encodeToString(searchInfo)

    @TypeConverter
    fun searchInfoFromJson(json: String): SearchInfo = Json.decodeFromString(json)

    @TypeConverter
    fun volumeInfoToJson(volumeInfo: VolumeInfo): String = Json.encodeToString(volumeInfo)

    @TypeConverter
    fun volumeInfoFromJson(json: String): VolumeInfo = Json.decodeFromString(json)
}