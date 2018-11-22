// Code generated by moshi-kotlin-codegen. Do not edit.
package edsl.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.NullPointerException
import kotlin.String

class UDFSpecJsonAdapter(moshi: Moshi) : JsonAdapter<UDFSpec>() {
    private val options: JsonReader.Options = JsonReader.Options.of("name", "signature")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    override fun toString(): String = "GeneratedJsonAdapter(UDFSpec)"

    override fun fromJson(reader: JsonReader): UDFSpec {
        var name: String? = null
        var signature: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> name = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                1 -> signature = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'signature' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = UDFSpec(
                name = name ?: throw JsonDataException("Required property 'name' missing at ${reader.path}"))
        result = result.copy(
                signature = signature ?: result.signature)
        return result
    }

    override fun toJson(writer: JsonWriter, value: UDFSpec?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("name")
        stringAdapter.toJson(writer, value.name)
        writer.name("signature")
        stringAdapter.toJson(writer, value.signature)
        writer.endObject()
    }
}