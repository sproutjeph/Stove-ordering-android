package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*




@Entity(tableName = "venue_tbl")
data class VenueInfoModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val venueName: String,
    val venueId: Int,
    val venueSubName: String,
    val venueCat: Int,
    val venueStatus: Int,
    val venueSettings: String,
    val venueBannerUrlMobile: String,
    val venueBannerUrlWeb: String,
    val thermalReceiptFooter: String,
    val thermalReceiptHeader: String,
    val timezoneName: String,
    val state: String,
    val taxRate: String,
    val venuePayType: String,
    val deliverDelay: String,
    val customerServicePhone: String? = null,
    val customerServiceEmail: String? = null,
    val orderPhone: String? = null,
    val orderEmail: String? = null,
    val contactPhone: String? = null,
    val contactEmail: String? = null,
    )