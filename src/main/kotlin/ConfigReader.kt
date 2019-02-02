import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.BufferedReader
import java.io.FileReader

fun main() {
    val objectMapper = ObjectMapper(YAMLFactory())

    val bufferedReader = BufferedReader(FileReader("src/main/resources/config.yml"))
    val configFile = objectMapper.readValue(bufferedReader, Config::class.java)

    println("config: $configFile")
}