package com.stovepos.stoveorderingandroidapp.model

import io.realm.kotlin.types.RealmObject
import org.mongodb.kbson.ObjectId

class User : RealmObject{
    var _id: ObjectId = ObjectId.invoke()
    var ownerId: String = ""
    var pictureUrl: String = ""
    var name: String = ""
    var email: String = ""

}