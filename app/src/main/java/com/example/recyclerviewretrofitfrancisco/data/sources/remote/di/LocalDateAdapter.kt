package com.example.recyclerviewretrofitfrancisco.data.sources.remote.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.time.LocalDate

class LocalDateAdapter : JsonAdapter<LocalDate>(){
    @Synchronized
    @FromJson
    override fun fromJson(reader: JsonReader): LocalDate {
        return LocalDate.parse(reader.nextString())
    }

    @Synchronized
    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        writer.value(value.toString())
    }
}