package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id:String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit: Date? = null,
    val isOnline:Boolean = false

) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

//    constructor(id: String, firstName: String?, lastName: String?) : this(id, "John", "Doe", 0)

    init {
        println("It`s Alive!!! \n" +
                "${if (lastName === "Doe") "His name id $firstName $lastName" else "And his is $firstName $lastName!!! "}\n")
    }

    companion object Factory{
        private var lastId:Int = -1
        fun makeUser(fullName:String?) : User{
            lastId++

           val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName=firstName, lastName=lastName)
        }
    }

    class Builder(){
        var id:String = "1"
        var firstName:String? = "Name"
        var lastName:String? = "Surname"
        var avatar:String? = "BaseUrl"
        var rating:Int = 0
        var respect:Int = 0
        var lastVisit: Date? = null
        var isOnline:Boolean = false

        fun id(value:String) : Builder{
            this.id = value
            return this
        }

        fun firstName(value: String?) : Builder{
            this.firstName = value
            return this
        }

        fun lastName(value: String?) : Builder{
            this.lastName = value
            return this
        }

        fun avatar(value: String?) : Builder{
            this.avatar = value
            return this
        }

        fun rating(value: Int) : Builder{
            this.rating = value
            return this
        }

        fun respect(value: Int) : Builder{
            this.respect = value
            return this
        }

        fun lastVisit(value: Date? = Date()) : Builder{
            this.lastVisit = value
            return this
        }

        fun isOnline(value: Boolean = false) : Builder{
            this.isOnline = value
            return this
        }

        fun build() : User = User(
                id = id,
                firstName = firstName,
                lastName = lastName,
                avatar = avatar,
                rating = rating,
                respect = respect,
                lastVisit = lastVisit,
                isOnline = isOnline)
        }

    }

