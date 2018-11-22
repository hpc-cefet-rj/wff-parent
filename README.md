# parent

```bash
mvn install -f pom.xml
mvn install -f pom-common.xml
```

> ATENÇÃO: é necessário copiar os Adaptadores JSON gerados pelo **kapt** para o diretório correto toda vez que forem alterados. E depois devemos fazer o build com clean install novamente !

Para verificar se existe alguma diferença entre a versão gerada e a versão contida no projeto, execute:

```bash
target/generated-sources/kaptKotlin/compile/edsl/model/ src/main/kotlin/edsl/model/
```

Não pode aparecer na lista nenhum arquivo cujo nome seja `*JsonAdapter.kt`

```bash
cp -R target/generated-sources/kaptKotlin/compile/* src/main/kotlin/
mvn clean install -f pom-common.xml
```

> A propósito, arquivos `*JsonAdapter.kt` obsoletos no projeto podem causar erros de compilação. Se isso ocorrer, remova sem dó nem piedade pois são gerados novamente e basta copiar para o projeto.

```bash
rm src/main/kotlin/edsl/model/*JsonAdapter.kt
mvn clean install -f pom-common.xml && \
    cp -R target/generated-sources/kaptKotlin/compile/* src/main/kotlin/  && \
    diff  target/generated-sources/kaptKotlin/compile/edsl/model/ src/main/kotlin/edsl/model/
mvn clean install -f pom-common.xml
```

A shell `rebuildWithAdapters.sh` foi criada para executar este procedimento.

Para ver todas as classes geradas pelo **kapt**, execute:

```bash
find target/generated-sources/kaptKotlin/compile -type f | grep "JsonAdapter.kt$"
```
