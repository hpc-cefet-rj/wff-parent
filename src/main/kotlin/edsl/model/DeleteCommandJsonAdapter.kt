// Code generated by moshi-kotlin-codegen. Do not edit.
package edsl.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import edsl.KeyValuePair
import java.lang.NullPointerException
import kotlin.String
import kotlin.collections.List

class DeleteCommandJsonAdapter(moshi: Moshi) : JsonAdapter<DeleteCommand>() {
    private val options: JsonReader.Options = JsonReader.Options.of("name", "params")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val listOfKeyValuePairOfStringStringAdapter: JsonAdapter<List<KeyValuePair<String, String>>> =
            moshi.adapter<List<KeyValuePair<String, String>>>(Types.newParameterizedType(List::class.java, Types.newParameterizedType(KeyValuePair::class.java, String::class.java, String::class.java)), kotlin.collections.emptySet(), "params")

    override fun toString(): String = "GeneratedJsonAdapter(DeleteCommand)"

    override fun fromJson(reader: JsonReader): DeleteCommand {
        var name: String? = null
        var params: List<KeyValuePair<String, String>>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> name = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                1 -> params = listOfKeyValuePairOfStringStringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'params' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = DeleteCommand()
        result = result.copy(
                name = name ?: result.name)
        result.params = params ?: result.params
        return result
    }

    override fun toJson(writer: JsonWriter, value: DeleteCommand?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("name")
        stringAdapter.toJson(writer, value.name)
        writer.name("params")
        listOfKeyValuePairOfStringStringAdapter.toJson(writer, value.params)
        writer.endObject()
    }
}
