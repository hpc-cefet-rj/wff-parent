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
import model.entity.workflow.graph.VertexType

class ActivityJsonAdapter(moshi: Moshi) : JsonAdapter<Activity>() {
    private val options: JsonReader.Options = JsonReader.Options.of("name", "type", "params")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val vertexTypeAdapter: JsonAdapter<VertexType> =
            moshi.adapter<VertexType>(VertexType::class.java, kotlin.collections.emptySet(), "type")

    private val listOfStringAdapter: JsonAdapter<List<String>> =
            moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java), kotlin.collections.emptySet(), "params")

    override fun toString(): String = "GeneratedJsonAdapter(Activity)"

    override fun fromJson(reader: JsonReader): Activity {
        var name: String? = null
        var type: VertexType? = null
        var params: List<String>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> name = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                1 -> type = vertexTypeAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'type' was null at ${reader.path}")
                2 -> params = listOfStringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'params' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = Activity(
                name = name ?: throw JsonDataException("Required property 'name' missing at ${reader.path}"))
        result = result.copy(
                type = type ?: result.type,
                params = params ?: result.params)
        return result
    }

    override fun toJson(writer: JsonWriter, value: Activity?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("name")
        stringAdapter.toJson(writer, value.name)
        writer.name("type")
        vertexTypeAdapter.toJson(writer, value.type)
        writer.name("params")
        listOfStringAdapter.toJson(writer, value.params)
        writer.endObject()
    }
}
