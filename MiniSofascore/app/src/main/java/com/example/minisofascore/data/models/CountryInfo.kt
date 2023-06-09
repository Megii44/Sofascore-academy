package com.example.minisofascore.data.models

data class CountryInfo(
    val name: String,
    val topLevelDomain: List<String>,
    val alpha2Code: String,
    val alpha3Code: String,
    val callingCodes: List<String>,
    val capital: String,
    val altSpellings: List<String>,
    val subregion: String,
    val region: String,
    val population: Long,
    val latlng: List<Double>,
    val demonym: String,
    val area: Double,
    val gini: Double,
    val timezones: List<String>,
    val borders: List<String>,
    val nativeName: String,
    val numericCode: String,
    val flags: Flags,
    val currencies: List<Currency>,
    val languages: List<Language>,
    val translations: Translations,
    val flag: String,
    val regionalBlocs: List<RegionalBloc>,
    val cioc: String,
    val independent: Boolean
): java.io.Serializable

data class Flags(
    val svg: String,
    val png: String
): java.io.Serializable

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
): java.io.Serializable

data class Language(
    val iso639_1: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
): java.io.Serializable

data class Translations(
    val br: String,
    val pt: String,
    val nl: String,
    val hr: String,
    val fa: String,
    val de: String,
    val es: String,
    val fr: String,
    val ja: String,
    val it: String,
    val hu: String
): java.io.Serializable

data class RegionalBloc(
    val acronym: String,
    val name: String
): java.io.Serializable
