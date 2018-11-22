package util

import  edsl.*
import  edsl.model.*

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

import java.util.Date
import java.time.LocalDateTime

object ModelUtil {

  fun getWorkflowDefinition(name: String, vo: WorkflowDefinitionVO): WorkflowDefinition {
    var items: MutableList<WorkflowItem> = mutableListOf()
    // , , ,
    for (item in vo.relationItems) {
      items.add(item)
    }
    for (item in vo.udfSpecItems) {
      items.add(item)
    }
    for (item in vo.activityItems) {
      items.add(item)
    }
    for (item in vo.workflowResultItems) {
      items.add(item)
    }
    return WorkflowDefinition(name, items)
  }

  fun createWorkflowDefinitionVO(name: String, items: List<WorkflowItem>): WorkflowDefinitionVO {
    var relationItems: MutableList<Relation> = mutableListOf()
    var udfSpecItems: MutableList<UDFSpec> = mutableListOf()
    var activityItems: MutableList<Activity> = mutableListOf()
    var workflowResultItems: MutableList<WorkflowResult> = mutableListOf()

    for (item in items) {
      when (item.javaClass.name) {
        "edsl.model.Relation" -> relationItems.add(item as Relation)
        "edsl.model.UDFSpec" -> udfSpecItems.add(item as UDFSpec)
        "edsl.model.Activity" -> activityItems.add(item as Activity)
        "edsl.model.WorkflowResult" -> workflowResultItems.add(item as WorkflowResult)
      }
    }
    return WorkflowDefinitionVO(name, relationItems, udfSpecItems, activityItems, workflowResultItems)
  }

  fun <T : ValueObject> getAdapter(t: T): JsonAdapter<T> {
    val moshiBuilder = Moshi.Builder()
    val moshi: Moshi = moshiBuilder.add(KotlinJsonAdapterFactory())
      .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
      .add(LinkType::class.java, EnumJsonAdapter.create(LinkType::class.java))
      .build()
    return moshi.adapter(t.javaClass)
  }

}
