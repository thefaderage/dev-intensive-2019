package ru.skillbranch.devintensive

import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

class UserClassTest {
    @Test
    fun test_instance() {
        val user2 = User("2", "John", "Cena")

    }

    @Test
    fun test_factory(){
//        val user = User.makeUser("Jonh Cena")
//        val user2 = User.makeUser("Jonh Wick")
        val user = User.makeUser("John Wick")
        val user2 = user.copy(id="2","Cena", lastVisit = Date() )
        print("$user \n$user2" )

    }

    @Test
    fun test_toInitials(){
        Utils.toInitials("John", "Weak")
    }

    @Test
    fun test_decomposition(){
        var user = User.makeUser("John Weak")

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }

    @Test
    fun test_copy(){
        val user = User.makeUser("John Weak")
        val user2 = user.copy(lastVisit = Date())
        var user3 = user.copy(lastName = "Cena", lastVisit = Date().add(-2, TimeUnits.SECOND))
        var user4 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println("""
            
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
            
        """.trimIndent())
    }

    @Test
    fun test_data_mapping(){
        val user = User.makeUser("Белоусов Вадим")
        val userView = user.toUserView()
        println(user)

        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Белосов Вадим")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text here", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any img here", type = "image")

        println(textMessage.formatMessage())
        println(imgMessage.formatMessage())

        when(imgMessage) {
            is BaseMessage -> println("this is base message")
            is TextMessage -> println("this is text message")
            is ImageMessage -> println("this is image message")



        }
    }
}