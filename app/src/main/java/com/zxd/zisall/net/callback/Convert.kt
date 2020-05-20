package com.zxd.zisall.net.callback

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.JsonReader
import java.lang.reflect.Type

class Convert {
    companion object {
        @Throws(JsonIOException::class, JsonSyntaxException::class)
        fun <T> fromJson(
            reader: JsonReader?,
            typeOfT: Type?
        ): T {
            return create().fromJson(reader, typeOfT)
        }

        private fun create(): Gson {
            return GsonHolder.gson
        }

        fun toJson(src: Any?): String? {
            return create().toJson(src)
        }

        fun toJson(src: Any?, typeOfSrc: Type?): String? {
            return create().toJson(src, typeOfSrc)
        }

        private object GsonHolder {
            val gson = Gson()
        }
    }
}