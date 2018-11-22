package edsl.model

import edsl.*
import model.entity.workflow.ResourceType
import model.entity.workflow.graph.VertexType

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

interface ValueObject

interface SessionItem
interface StartupItem : ValueObject
interface WorkflowItem : ValueObject
interface ResourceItem : ValueObject
interface JobItem

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

@JsonClass(generateAdapter = true)
open class StartupCommand(var params: List<KeyValuePair<String, String>> = listOf()) : StartupItem

@JsonClass(generateAdapter = true)
data class WfFSessionDefinition(var created: LocalDateTimeISOFormatted,
  var onStartDefinition: MutableList<StartupCommand> = mutableListOf(),
  var workflowDefinition: WorkflowDefinitionVO = WorkflowDefinitionVO(""),
  var resourcesDefinition: MutableList<Resource> = mutableListOf(),
  var jobsDefinition: MutableList<Job> = mutableListOf(),
  var executionsDefinition: MutableList<ExecutionDefinition> = mutableListOf()
) : ValueObject {

  fun getLocalDateTimeForCreated() : LocalDateTime{
    val isoDateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    val parsedDateTime: LocalDateTime = LocalDateTime.parse(created, isoDateTimeFormatter)
    // println("parsedDate: $parsedDateTime")
    return parsedDateTime
  }

}


@JsonClass(generateAdapter = true)
data class DeleteCommand(val name: String = "delete") : StartupCommand()

@JsonClass(generateAdapter = true)
data class ProvenanceReset(val name: String = "reset provenance"): StartupCommand()

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

@JsonClass(generateAdapter = true)
data class WorkflowDefinitionVO(var name: String = "",
  /* é possível usar @Json para controlar o nome do atributo na serialização */
  @Json(name = "relationItems") var relationItems: List<Relation> = listOf(),
  var udfSpecItems: List<UDFSpec> = listOf(),
  var activityItems: List<Activity> = listOf(),
  var workflowResultItems: List<WorkflowResult> = listOf()
) : ValueObject

data class WorkflowDefinition(var name: String, var workflowItems: List<WorkflowItem> = listOf())

@JsonClass(generateAdapter = true)
data class ExecutionDefinition(var executionsParams: MutableList<KeyValuePair<String, String>>): ValueObject

@JsonClass(generateAdapter = true)
data class Relation(var name: String, var schemaName: String = "", var schema: String = "") : WorkflowItem

@JsonClass(generateAdapter = true)
data class UDFSpec(var name: String, var signature: String = "") : WorkflowItem

@JsonClass(generateAdapter = true)
data class Activity(var name: String, var type: VertexType = VertexType.NullVertex, var params: List<String> = listOf()) : WorkflowItem

@JsonClass(generateAdapter = true)
data class WorkflowResult(var name: String, var params: List<String> = listOf()) : WorkflowItem

@JsonClass(generateAdapter = true)
data class Resource(var name: String, var title: String = "", var udfName: String = "", var type: ResourceType = ResourceType.EMPTY, var uri: String = "", var code: String = "") : ResourceItem

@JsonClass(generateAdapter = true)
open class ConfigItem(var name: String)

@JsonClass(generateAdapter = true)
data class Job(var name: String, var title: String = "", var config: List<Link> = listOf()) : JobItem

@JsonClass(generateAdapter = true)
data class Link(var params: List<String> = listOf(), var type: LinkType = LinkType.NULL) : ConfigItem("link")

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

data class Config(val template: Int, val configuration: String)

data class DataSource(val name: String,
                      var title: String = "",
                      var description: String = "",
                      val index: Int = -1, val queue: Int = -1, val dataSourceType: Int = -1,
                      val item: Int = -1, val measurementUnit: Int = -1, val info: String = "",
                      val terms: List<Int>? = null,
                      var configs: List<Config>? = null)

data class DataSourceGroup(val dataSorces: List<DataSource>, var rules: String = "", var debug: Boolean = false)

data class Rule(val code: String, var title: String = "")

data class InstallationInfo(val projectName: String, var projectTitle: String = "",
                            var instanceName: String = "", var instanceTitle: String = "", var locale: String = "")

// item name "Itaipu" title "Itaipu Binacional" desc "Esta é ..." term "4301"
data class Item(val name: String, var title: String = "", var desc: String = "", var term: String = "")

