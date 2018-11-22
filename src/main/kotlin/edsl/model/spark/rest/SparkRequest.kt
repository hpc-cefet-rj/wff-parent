package edsl.model.spark.rest

import util.ModelUtil
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

object SparkRequestSubmissionHelper {
  // Preciso gerar algo assim:
  //  curl -X POST http://rukbat:6066/v1/submissions/create --header "Content-Type:application/json;charset=UTF-8" --data '{
  //    "action": "CreateSubmissionRequest",
  //    "appResource": "file:///home/spark/spark/examples/jars/spark-examples_2.11-2.3.0.jar",
  //    "clientSparkVersion": "2.3.0",
  //    "appArgs": [ "10" ],
  //    "environmentVariables" : {
  //      "SPARK_ENV_LOADED": "1"
  //    },
  //    "mainClass": "org.apache.spark.examples.SparkPi",
  //    "sparkProperties": {
  //      "spark.jars": "file:///home/spark/spark/examples/jars/spark-examples_2.11-2.3.0.jar",
  //      "spark.driver.supervise": "false",
  //      "spark.executor.memory": "512m",
  //      "spark.driver.memory": "512m",
  //      "spark.submit.deployMode": "cluster",
  //      "spark.app.name": "SparkPi submited by REST",
  //      "spark.master": "spark://rukbat:6066"
  //    }
  //  }'

  // TODO: usar o moshi para estruturar o objeto. Imprimir com adapter.indent("  ")

  @JvmStatic
  fun main(args: Array<String>) {
    val hostName = "rukbat"
    println("""

      Exemplo de parâmetros para o programa:
        action        Informar CreateSubmissionResponse
        appResource   Informar por exemplo: /usr/local/spark/examples/jars/spark-examples_2.11-2.3.0.jar
        mainClass     Informar por exemplo: org.apache.spark.examples.SparkPi
        appArg1       Informar o parâmetro para o programa, caso exista
      """.trimIndent())

    val action      = args[0]
    val appResource = args[1]
    val mainClass   = args[2]
    val appArg1     = args[3]

    println("""

      Parâmetros aceitos para o programa:
        action      = '${args[0]}'
        appResource = '${args[1]}'
        mainClass   = '${args[2]}'
        appArg1     = '${args[3]}'
      """.trimIndent())

    val appArgsAsJSONStr = """"appArgs": [ "${appArg1}" ],"""

    // Distribution of Executors, Cores and Memory for a Spark Application running in Yarn:
    // spark-submit --class <CLASS_NAME> --num-executors ? --executor-cores ? --executor-memory ?

    println("""

    curl -X POST http://${hostName}:6066/v1/submissions/create --header "Content-Type:application/json;charset=UTF-8" --data '{
      "action": "${action}",
      "appResource": "file://${appResource}",
      "clientSparkVersion": "2.3.0",
      ${appArgsAsJSONStr}
      "environmentVariables" : {
        "SPARK_ENV_LOADED": "1"
      },
      "mainClass": "${mainClass}",
      "sparkProperties": {
        "spark.jars": "file://${appResource}",
        "spark.driver.supervise": "false",
        "spark.executor.memory": "512m",
        "spark.driver.memory": "512m",
        "spark.submit.deployMode": "cluster",
        "spark.app.name": "SparkPi submited by REST",
        "spark.master": "spark://${hostName}:6066",
        "spark.eventLog.enabled": "true",
        "spark.eventLog.dir": "file:///tmp/wff-events",
        "spark.ui.enabled": "true",
        "spark.deploy.spreadOut": "false",
        "spark.deploy.defaultCores": "2",
        "spark.cores.max": "4"
      }
    }'
    """.trimIndent())

  }

}





