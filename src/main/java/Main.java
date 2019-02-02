public class Main {
  
  public static void main(String[] args) {
    var configReader = new ConfigReader();
    var config = configReader.loadReaderConfiguration();
    var outputFileGenerator =
        new OutputFileGenerator(config.getRecords(), config.getOutputFilePath());
    
    var generatedFileContent = outputFileGenerator.generateOutputFile();

    System.out.println("Generated output file content:\n" + generatedFileContent);
  }
}
