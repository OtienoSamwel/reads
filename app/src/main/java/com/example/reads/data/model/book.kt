package com.example.reads.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


data class Book(
    @PrimaryKey
    val id: Int,
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)

@Entity(tableName = "Item")
data class Item(
    val accessInfo: AccessInfo,
    val etag: String,
    @PrimaryKey
    val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val searchInfo: SearchInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class AccessInfo(
    val accessViewStatus: String,
    val country: String,
    val embeddable: Boolean,
    val epub: Epub,
    val pdf: Pdf,
    val publicDomain: Boolean,
    val quoteSharingAllowed: Boolean,
    val textToSpeechPermission: String,
    val viewability: String,
    val webReaderLink: String
)

@Serializable
data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val saleability: String
)

@Serializable
data class SearchInfo(
    val textSnippet: String
)

@Serializable
data class VolumeInfo(
    val allowAnonLogging: Boolean,
    val authors: List<String>,
    val averageRating: Double,
    val canonicalVolumeLink: String,
    val categories: List<String>,
    val contentVersion: String,
    val description: String,
    val imageLinks: ImageLinks,
    val industryIdentifiers: List<IndustryIdentifier>,
    val infoLink: String,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val panelizationSummary: PanelizationSummary,
    val previewLink: String,
    val printType: String,
    val publishedDate: String,
    val publisher: String,
    val ratingsCount: Int,
    val readingModes: ReadingModes,
    val subtitle: String,
    val title: String
)

@Serializable
data class Epub(
    val acsTokenLink: String,
    val isAvailable: Boolean
)

@Serializable
data class Pdf(
    val acsTokenLink: String,
    val isAvailable: Boolean
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)

@Serializable
data class IndustryIdentifier(
    val identifier: String,
    val type: String
)

@Serializable
data class PanelizationSummary(
    val containsEpubBubbles: Boolean,
    val containsImageBubbles: Boolean
)

@Serializable
data class ReadingModes(
    val image: Boolean,
    val text: Boolean
)