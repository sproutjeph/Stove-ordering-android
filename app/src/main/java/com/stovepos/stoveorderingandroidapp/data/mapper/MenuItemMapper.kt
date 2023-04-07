package com.stovepos.stoveorderingandroidapp.data.mapper

import com.stovepos.stoveorderingandroidapp.data.local.MenuCatButtonModel
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemModel
import com.stovepos.stoveorderingandroidapp.data.local.VenueInfoModel
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuCat
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuItem
import com.stovepos.stoveorderingandroidapp.data.remote.venue_info_dto.VenueInfoItem

fun MenuItem.toMenuItemModel(): MenuItemModel{
    return MenuItemModel(
        itemImagesJson = itemImages_json?: emptyList(),
        itemOptionsJson = itemOptions_json?: emptyList(),
        itemMods = item_mods,
        itemName = item_name,
        itemPrice = item_price,
        itemId = itemid,
        menuCatId = menucatid,
        modPrompt = mod_prompt,
        taxRate = taxrate,
        itemQty = 1

    )
}


fun MenuCat.toMenuCatButton(): MenuCatButtonModel {
    return MenuCatButtonModel(
        menuName = menu_name,
        menuCat = menucat,
        sortOrder = sortorder
    )
}

fun VenueInfoItem.toVenueInfo():VenueInfoModel{
    return VenueInfoModel(
        venueName = venuename,
        venueId = venueid,
        venueSubName = venuesubname,
        venueCat = venuecat,
        venueSettings = venueSettings,
        venueBannerUrlMobile = venuebannerurl_mobile.toString(),
        venueBannerUrlWeb = venuebannerurl_web.toString(),
        thermalReceiptFooter = thermalreceipt_footer,
        thermalReceiptHeader = thermalreceipt_header,
        timezoneName = timezonename,
        state = state?: "",
        taxRate = taxrate?:"",
        deliverDelay = deliver_delay?: "",
        venuePayType = venue_pay_type?: "",
        venueStatus = venuestatus?: 1,
    )
}

