package com.example.restores

class API {
    companion object {

        private val SERVER = "http://192.168.1.32/crud/"

        val READ = SERVER+"ambil.php"
        val LOGIN = SERVER+"postLogin.php"
        val Register = SERVER+"singup.php"
        val BOKING = SERVER+"booking.php"
        val DATA = SERVER+"ambildata.php"
    }
}