package com.example.picsapp.model

import com.example.picsapp.R

enum class Category(val categoryName: String, val iconResource: Int) {
    BACKGROUNDS("Backgrounds", R.drawable.background_icon),
    FASHION("Fashion", R.drawable.fashion_icon),
    NATURE("Nature", R.drawable.nature_icon),
    SCIENCE("Science", R.drawable.science_icon),
    EDUCATION("Education", R.drawable.education_icon),
    FEELINGS("Feelings", R.drawable.feelings_icon),
    HEALTH("Health", R.drawable.health_icon),
    PEOPLE("People", R.drawable.people_icon),
    RELIGION("Religion", R.drawable.religion_icon),
    PLACES("Places", R.drawable.places_icon),
    ANIMALS("Animals", R.drawable.animals_icon),
    INDUSTRY("Industry", R.drawable.industry_icon),
    COMPUTER("Computer", R.drawable.computer_icon),
    FOOD("Food", R.drawable.food_icon),
    SPORTS("Sports", R.drawable.sports_icon),
    TRANSPORTATION("Transportation", R.drawable.transportation_icon),
    TRAVEL("Travel", R.drawable.travel_icon),
    BUILDINGS("Buildings", R.drawable.buildings_icon),
    BUSINESS("Business", R.drawable.business_icon),
    MUSIC("Music", R.drawable.music_icon)
}