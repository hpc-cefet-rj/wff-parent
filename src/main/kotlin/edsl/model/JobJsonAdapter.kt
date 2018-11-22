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

class JobJsonAdapter(moshi: Moshi) : JsonAdapter<Job>() {
    private val options: JsonReader.Options = JsonReader.Options.of("name", "title", "config")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val listOfLinkAdapter: JsonAdapter<List<Link>> =
            moshi.adapter<List<Link>>(Types.newParameterizedType(List::class.java, Link::class.java), kotlin.collections.emptySet(), "config")

    override fun toString(): String = "GeneratedJsonAdapter(Job)"

    override fun fromJson(reader: JsonReader): Job {
        var name: String? = null
        var title: String? = null
        var config: List<Link>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> name = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                1 -> title = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'title' was null at ${reader.path}")
                2 -> config = listOfLinkAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'config' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = Job(
                name = name ?: throw JsonDataException("Required property 'name' missing at ${reader.path}"))
        result = result.copy(
                title = title ?: result.title,
                config = config ?: result.config)
        return result
    }

    override fun toJson(writer: JsonWriter, value: Job?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("name")
        stringAdapter.toJson(writer, value.name)
        writer.name("title")
        stringAdapter.toJson(writer, value.title)
        writer.name("config")
        listOfLinkAdapter.toJson(writer, value.config)
        writer.endObject()
    }
}