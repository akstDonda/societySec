package com.nothing.secad

data class Society(
    var name: String,
    var email: String,
    var password: String,
    var uid: String,

    var isBuilding: Boolean,

    var parking: Number,
    var elevators: Number,
    var watchMan: Number,
    var garden: Number,
    var temple: Number,
    var waterTank: Number,
    var totalHouses: Number,

    var expectedPricePerHouse: Number
)
