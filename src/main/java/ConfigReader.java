import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;

class ConfigReader {

  private static final String CONFIG_FILE_PATH = "src/main/resources/config.yml";

  private final ObjectMapper objectMapper;

  ConfigReader() {
    objectMapper = new ObjectMapper(new YAMLFactory());
  }

  @SneakyThrows
  Config loadReaderConfiguration() {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG_FILE_PATH))) {
      return objectMapper.readValue(bufferedReader, Config.class);
    }
  }
}
