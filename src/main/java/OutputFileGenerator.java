import lombok.SneakyThrows;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

@Value
class OutputFileGenerator {

  private static final String CSV_FILE_SEPARATOR = ",";
  private static final String NEW_LINE_SEPARATOR = "\r\n";

  private Logger logger = Logger.getLogger(getClass().getName());
  
  private Map<String, Record> recordsConfig;
  private Path outputFilePath;

  @SneakyThrows
  List<String> generateOutputFile() {
    List<String> generatedRecords = generateRecords();
    try (var outputFileWriter = new BufferedWriter(new FileWriter(outputFilePath.toFile()))) {
      generatedRecords.stream()
          .map(this::appendNewLineRecordSeparator)
          .forEach(record -> writeRecord(outputFileWriter, record));
    }
    return generatedRecords;
  }

  @NotNull
  private String appendNewLineRecordSeparator(String record) {
    return record + NEW_LINE_SEPARATOR;
  }

  private List<String> generateRecords() {
    return recordsConfig.entrySet().stream()
        .map(
            recordConfig ->
                generateNumberOfSimilarRecords(
                    recordConfig.getValue().getFields(), recordConfig.getValue().getRecordCount()))
        .flatMap(List::stream)
        .collect(toList());
  }

  private List<String> generateNumberOfSimilarRecords(List<Field> fieldsConfig, int recordsCount) {
    var generatedRecords = new ArrayList<String>();
    for (int i = 0; i < recordsCount; i++) {
      generatedRecords.add(buildRecordFromFieldsConfig(fieldsConfig));
    }
    return generatedRecords;
  }

  private String buildRecordFromFieldsConfig(List<Field> recordFieldsConfig) {
    var generatedRecord = new StringJoiner(CSV_FILE_SEPARATOR);
    recordFieldsConfig.stream()
        .map(fieldConfig -> getGeneratedRecordFieldValue(fieldConfig))
        .forEach(generatedRecord::add);

    return generatedRecord.toString();
  }

  private String getGeneratedRecordFieldValue(Field fieldConfig) {
    var fieldConfiguredValues = new ArrayList<>(fieldConfig.getValues());
    if (fieldConfiguredValues.isEmpty()) {
      return "";
    }

    if (fieldConfig.isRandomness()) {
      shuffle(fieldConfiguredValues);
    }
    return fieldConfiguredValues.get(0);
  }

  @SneakyThrows
  private void writeRecord(BufferedWriter outputFileWriter, String record) {
    logger.log(Level.INFO, "Writing record: " + record + "");
    outputFileWriter.write(record);
  }
}
