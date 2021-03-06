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
import kotlin.collections.MutableList

class WfFSessionDefinitionJsonAdapter(moshi: Moshi) : JsonAdapter<WfFSessionDefinition>() {
    private val options: JsonReader.Options =
            JsonReader.Options.of("created", "onStartDefinition", "workflowDefinition", "resourcesDefinition", "jobsDefinition", "executionsDefinition")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "created")

    private val mutableListOfStartupCommandAdapter: JsonAdapter<MutableList<StartupCommand>> =
            moshi.adapter<MutableList<StartupCommand>>(Types.newParameterizedType(MutableList::class.java, StartupCommand::class.java), kotlin.collections.emptySet(), "onStartDefinition")

    private val workflowDefinitionVOAdapter: JsonAdapter<WorkflowDefinitionVO> =
            moshi.adapter<WorkflowDefinitionVO>(WorkflowDefinitionVO::class.java, kotlin.collections.emptySet(), "workflowDefinition")

    private val mutableListOfResourceAdapter: JsonAdapter<MutableList<Resource>> =
            moshi.adapter<MutableList<Resource>>(Types.newParameterizedType(MutableList::class.java, Resource::class.java), kotlin.collections.emptySet(), "resourcesDefinition")

    private val mutableListOfJobAdapter: JsonAdapter<MutableList<Job>> =
            moshi.adapter<MutableList<Job>>(Types.newParameterizedType(MutableList::class.java, Job::class.java), kotlin.collections.emptySet(), "jobsDefinition")

    private val mutableListOfExecutionDefinitionAdapter: JsonAdapter<MutableList<ExecutionDefinition>> =
            moshi.adapter<MutableList<ExecutionDefinition>>(Types.newParameterizedType(MutableList::class.java, ExecutionDefinition::class.java), kotlin.collections.emptySet(), "executionsDefinition")

    override fun toString(): String = "GeneratedJsonAdapter(WfFSessionDefinition)"

    override fun fromJson(reader: JsonReader): WfFSessionDefinition {
        var created: String? = null
        var onStartDefinition: MutableList<StartupCommand>? = null
        var workflowDefinition: WorkflowDefinitionVO? = null
        var resourcesDefinition: MutableList<Resource>? = null
        var jobsDefinition: MutableList<Job>? = null
        var executionsDefinition: MutableList<ExecutionDefinition>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> created = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'created' was null at ${reader.path}")
                1 -> onStartDefinition = mutableListOfStartupCommandAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'onStartDefinition' was null at ${reader.path}")
                2 -> workflowDefinition = workflowDefinitionVOAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'workflowDefinition' was null at ${reader.path}")
                3 -> resourcesDefinition = mutableListOfResourceAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'resourcesDefinition' was null at ${reader.path}")
                4 -> jobsDefinition = mutableListOfJobAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'jobsDefinition' was null at ${reader.path}")
                5 -> executionsDefinition = mutableListOfExecutionDefinitionAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'executionsDefinition' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = WfFSessionDefinition(
                created = created ?: throw JsonDataException("Required property 'created' missing at ${reader.path}"))
        result = result.copy(
                onStartDefinition = onStartDefinition ?: result.onStartDefinition,
                workflowDefinition = workflowDefinition ?: result.workflowDefinition,
                resourcesDefinition = resourcesDefinition ?: result.resourcesDefinition,
                jobsDefinition = jobsDefinition ?: result.jobsDefinition,
                executionsDefinition = executionsDefinition ?: result.executionsDefinition)
        return result
    }

    override fun toJson(writer: JsonWriter, value: WfFSessionDefinition?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("created")
        stringAdapter.toJson(writer, value.created)
        writer.name("onStartDefinition")
        mutableListOfStartupCommandAdapter.toJson(writer, value.onStartDefinition)
        writer.name("workflowDefinition")
        workflowDefinitionVOAdapter.toJson(writer, value.workflowDefinition)
        writer.name("resourcesDefinition")
        mutableListOfResourceAdapter.toJson(writer, value.resourcesDefinition)
        writer.name("jobsDefinition")
        mutableListOfJobAdapter.toJson(writer, value.jobsDefinition)
        writer.name("executionsDefinition")
        mutableListOfExecutionDefinitionAdapter.toJson(writer, value.executionsDefinition)
        writer.endObject()
    }
}
