// Code generated by moshi-kotlin-codegen. Do not edit.
package edsl.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.NullPointerException
import kotlin.String
import kotlin.collections.List

class LinkJsonAdapter(moshi: Moshi) : JsonAdapter<Link>() {
    private val options: JsonReader.Options = JsonReader.Options.of("params", "type", "name")

    private val listOfStringAdapter: JsonAdapter<List<String>> =
            moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java), kotlin.collections.emptySet(), "params")

    private val linkTypeAdapter: JsonAdapter<LinkType> =
            moshi.adapter<LinkType>(LinkType::class.java, kotlin.collections.emptySet(), "type")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    override fun toString(): String = "GeneratedJsonAdapter(Link)"

    override fun fromJson(reader: JsonReader): Link {
        var params: List<String>? = null
        var type: LinkType? = null
        var name: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> params = listOfStringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'params' was null at ${reader.path}")
                1 -> type = linkTypeAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'type' was null at ${reader.path}")
                2 -> name = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = Link()
        result = result.copy(
                params = params ?: result.params,
                type = type ?: result.type)
        result.name = name ?: result.name
        return result
    }

    override fun toJson(writer: JsonWriter, value: Link?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("params")
        listOfStringAdapter.toJson(writer, value.params)
        writer.name("type")
        linkTypeAdapter.toJson(writer, value.type)
        writer.name("name")
        stringAdapter.toJson(writer, value.name)
        writer.endObject()
    }
}
