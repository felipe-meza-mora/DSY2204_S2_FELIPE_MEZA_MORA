package com.example.dyf.data

import java.io.Serializable

data class UserData(
    val rut: String,
    val nombreCompleto: String,
    val correo: String,
    val password: String,
    val recibirNotificaciones: String
) : Serializable