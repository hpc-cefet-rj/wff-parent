package model.entity.workflow

// Os Resources podem ficar armazenados no Github como GIST.
// Use algo assim: curl --user "joao-parana" https://api.github.com/gists
// que retorna algo assim abaixo. um Array de GISTs
// [
//   {
//     "url": "https://api.github.com/gists/30fa32d916720d1f10577ac53fa097d8",
//     "html_url": "https://gist.github.com/30fa32d916720d1f10577ac53fa097d8",
//     "files": {
//       "Workflow01UDAF.scala": {
//         "filename": "Workflow01UDAF.scala",
//         "type": "text/plain",
//         "language": "Scala",
//         "raw_url": "https://gist.githubusercontent.com/joao-parana/30fa32d916720d1f10577ac53fa097d8/raw/d3c9d304b5e54640e2722633e5c2588be6ad33ef/Workflow01UDAF.scala",
//         "size": 2182
//       }
//     }, ...
//   }
// ]
//
// Com este array pode criar uma lista de recursos identificando os arquivos existentes
// e usar raw_url para obter o fonte na versão correta.


// A ordem é muito importante pois isto é persistido como ordinal,
// portanto não é permitido inserir elementos no meio ou inicio da
// lista sem causar erros catastróficos.
enum class ResourceType {
  EMPTY,
  CSV_FILE,
  TVS_FILE,
  JSON_FILE,
  XML_FILE,
  PARQUET_TABLE,
  JDBC_TABLE,
  SCALA_SOURCE_CODE,
  SCALA_CLASS, // Used for Optimizations and User Defined Aggregate Function
  SCALA_FUNCTION,
  KOTLIN_SOURCE_CODE,
  KOTLIN_FUNCTION,
  PYTHON_SOURCE_CODE,
  PYTHON_FUNCTION,
  R_SOURCE_CODE,
  R_FUNCTION,
  GO_SOURCE_CODE,
  GO_FUNCTION
}
