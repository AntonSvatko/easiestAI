package com.univer.carshowroom

import java.time.Year

data class Car(
    val price: Int = 0,
    val brand: String = "",
    val hp: Int = 0,
    val salon: String = "",
    val transmission: String = "",
    val year: Int = 0,
    val accident: Boolean = false

) {
    fun getCars() = listOf(
        Car(
            1000,
                "Ford",
            100,
            "Textile",
            "Mechanic",
            1995,
            true
        ),
        Car(
            10000,
            "BMW",
            350,
            "Leather",
            "Mechanic",
            2005,
            false
        ),
        Car(
            25864,
            "Mercedes",
            300,
            "Leather",
            "Auto",
            2012,
            false
        ),
        Car(
            35000,
            "Tesla",
            350,
            "Leather",
            "Auto",
            2019,
            true
        ),
        Car(
            50000,
            "Tesla",
            500,
            "Leather",
            "Auto",
            2018,
            false
        ),
        Car(
            8000,
            "Volkswagen",
            243,
            "Textile",
            "Auto",
            2007,
            false
        ),Car(
            6000,
            "Toyota",
            220,
            "Textile",
            "Mechanic",
            1999,
            false
        )
    )
}