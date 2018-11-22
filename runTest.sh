# cp /Users/admin/.m2/repository/com/squareup/moshi/moshi-kotlin/1.7.0/moshi-kotlin-1.7.0.jar /Users/admin/.m2/repository/com/squareup/moshi/moshi-kotlin-codegen/1.7.0/moshi-kotlin-codegen-1.7.0.jar /Users/admin/.m2/repository/com/squareup/moshi/moshi/1.7.0/moshi-1.7.0.jar /Users/admin/.m2/repository/com/squareup/moshi/moshi-adapters/1.7.0/moshi-adapters-1.7.0.jar libs
# cp /Users/admin/.m2/repository/com/squareup/okio/okio/1.15.0/okio-1.15.0.jar libs
#
MOSHI_JARS=libs/moshi-1.7.0.jar:libs/moshi-kotlin-1.7.0.jar:libs/moshi-adapters-1.7.0.jar:libs/moshi-kotlin-codegen-1.7.0.jar:libs/okio-1.15.0.jar
#
cd target/kotlin-ic/compile/classes
# java edsl.model.spark.rest.SparkRequestSubmissionHelper
export KOTLIN_HOME=/usr/local/kotlinc
java -cp $KOTLIN_HOME/lib/kotlin-runtime.jar:.:$MOSHI_JARS edsl.model.spark.rest.SparkRequestSubmissionHelper $1 $2 $3 $4 $5 $6 

