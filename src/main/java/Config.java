import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@EqualsAndHashCode
@Getter
class Config {
  private Map<String, Record> records = new HashMap();
  private Path outputFilePath;

  private Config() {
    // empty constructor used by jackson
  }
}

@ToString
@EqualsAndHashCode
@Getter
class Record {
  private List<Field> fields = new ArrayList();
  private int recordCount;

  private Record() {
    // empty constructor used by jackson
  }
}

@ToString
@EqualsAndHashCode
@Getter
class Field {
  private String name;
  private List<String> values = new ArrayList<>();
  private boolean randomness;

  @JsonIgnore private int counter = 0;

  private Field() {
    // empty constructor used by jackson
  }

  String getOrderedValue() {
    if (values.isEmpty()) {
      return "";
    }
    return increaseCounterAndReturnValueFromFieldConfig();
  }

  private String increaseCounterAndReturnValueFromFieldConfig() {
    if (counter >= values.size()) {
      counter = 0;
    }
    String value = values.get(counter);
    counter++;
    return value;
  }
}
