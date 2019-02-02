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
  private String type;
  private List<String> values = new ArrayList();
  private boolean randomness;

  private Field() {
    // empty constructor used by jackson
  }
}
