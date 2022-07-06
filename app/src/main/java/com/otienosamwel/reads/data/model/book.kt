package com.otienosamwel.reads.data.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SearchResult(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)

@kotlinx.serialization.Serializable
data class Item(
    val accessInfo: AccessInfo?,
    @SerialName("etag") val eTag: String?,
    val id: String?,
    val kind: String?,
    val saleInfo: SaleInfo?,
    val searchInfo: SearchInfo?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?
)

@kotlinx.serialization.Serializable
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

@kotlinx.serialization.Serializable
data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val saleability: String
)

@kotlinx.serialization.Serializable
data class SearchInfo(
    val textSnippet: String
)

@kotlinx.serialization.Serializable
data class VolumeInfo(
    val allowAnonLogging: Boolean?,
    val authors: List<String>?,
    val averageRating: Float?,
    val canonicalVolumeLink: String?,
    val categories: List<String>?,
    val contentVersion: String?,
    val description: String?,
    val imageLinks: ImageLinks?,
    val industryIdentifiers: List<IndustryIdentifier>?,
    val infoLink: String?,
    val language: String?,
    val maturityRating: String?,
    val pageCount: Int?,
    val panelizationSummary: PanelizationSummary?,
    val previewLink: String?,
    val printType: String?,
    val publishedDate: String?,
    val publisher: String?,
    val ratingsCount: Int?,
    val readingModes: ReadingModes?,
    val subtitle: String?,
    val title: String?
)

@kotlinx.serialization.Serializable
data class Epub(
    val isAvailable: Boolean
)

@kotlinx.serialization.Serializable
data class Pdf(
    val isAvailable: Boolean
)

@kotlinx.serialization.Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)

@kotlinx.serialization.Serializable
data class IndustryIdentifier(
    val identifier: String,
    val type: String
)

@kotlinx.serialization.Serializable
data class PanelizationSummary(
    val containsEpubBubbles: Boolean,
    val containsImageBubbles: Boolean
)

@kotlinx.serialization.Serializable
data class ReadingModes(
    val image: Boolean,
    val text: Boolean
)