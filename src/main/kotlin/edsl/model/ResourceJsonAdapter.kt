// Code generated by moshi-kotlin-codegen. Do not edit.
package edsl.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.NullPointerException
import kotlin.String
import model.entity.workflow.ResourceType

class ResourceJsonAdapter(moshi: Moshi) : JsonAdapter<Resource>() {
    private val options: JsonReader.Options =
            JsonReader.Options.of("name", "title", "udfName", "type", "uri", "code")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val resourceTypeAdapter: JsonAdapter<ResourceType> =
            moshi.adapter<ResourceType>(ResourceType::class.java, kotlin.collections.emptySet(), "type")

    override fun toString(): String = "GeneratedJsonAdapter(Resource)"

    override fun fromJson(reader: JsonReader): Resource {
        var name: String? = null
        var title: String? = null
        var udfName: String? = null
        var type: ResourceType? = null
        var uri: String? = null
        var code: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> name = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                1 -> title = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'title' was null at ${reader.path}")
                2 -> udfName = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'udfName' was null at ${reader.path}")
                3 -> type = resourceTypeAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'type' was null at ${reader.path}")
                4 -> uri = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'uri' was null at ${reader.path}")
                5 -> code = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'code' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = Resource(
                name = name ?: throw JsonDataException("Required property 'name' missing at ${reader.path}"))
        result = result.copy(
                title = title ?: result.title,
                udfName = udfName ?: result.udfName,
                type = type ?: result.type,
                uri = uri ?: result.uri,
                code = code ?: result.code)
        return result
    }

    override fun toJson(writer: JsonWriter, value: Resource?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("name")
        stringAdapter.toJson(writer, value.name)
        writer.name("title")
        stringAdapter.toJson(writer, value.title)
        writer.name("udfName")
        stringAdapter.toJson(writer, value.udfName)
        writer.name("type")
        resourceTypeAdapter.toJson(writer, value.type)
        writer.name("uri")
        stringAdapter.toJson(writer, value.uri)
        writer.name("code")
        stringAdapter.toJson(writer, value.code)
        writer.endObject()
    }
}
