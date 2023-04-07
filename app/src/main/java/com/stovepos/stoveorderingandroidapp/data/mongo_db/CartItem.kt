package com.stovepos.stoveorderingandroidapp.data.mongo_db

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class CartItem: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var itemImages_json: RealmList<String> = realmListOf()
    var itemMods: RealmList<OptionRealm> = realmListOf()
    var itemName: String = ""
    var itemPrice: String = ""
    var itemId: Int = 1
    var menuCatId: Int = 1
    var itemQty:Int = 1

}


class OptionRealm: EmbeddedRealmObject{
    var modName: String = ""
    var modPrice: Double = 0.0
    var modId: String = ""

}