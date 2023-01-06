package com.otienosamwel.reads.data.model

import android.net.Uri

data class SearchResult(
    val items: List<Item>?,
    val kind: String?,
    val totalItems: Int?
)


data class Item(
    val accessInfo: AccessInfo?,
    val etag: String?,
    val id: String?,
    val kind: String?,
    val saleInfo: SaleInfo?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?
)


data class AccessInfo(
    val accessViewStatus: String?,
    val country: String?,
    val epub: Epub?,
    val pdf: Pdf?
)

data class SaleInfo(
    val country: String?
)

data class VolumeInfo(
    val allowAnonLogging: Boolean?,
    val authors: List<String>?,
    val canonicalVolumeLink: String?,
    val contentVersion: String?,
    val description: String?,
    val imageLinks: ImageLinks?,
    val infoLink: String?,
    val maturityRating: String?,
    val panelizationSummary: PanelizationSummary?,
    val previewLink: String?,
    val publishedDate: String?,
    val publisher: String?,
    val readingModes: ReadingModes?,
    val subtitle: String?,
    val title: String?
)

data class Epub(
    val acsTokenLink: String?,
    val isAvailable: Boolean?
)

data class Pdf(
    val isAvailable: Boolean?
)

data class ImageLinks(
    var smallThumbnail: String?,
    var thumbnail: String?
)

data class PanelizationSummary(
    val containsEpubBubbles: Boolean?,
    val containsImageBubbles: Boolean?
)

data class ReadingModes(
    val image: Boolean?,
    val text: Boolean?
)