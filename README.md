# TestFilesGenerator
Generates files with complex structure

# Requirment
Project require `Java 11`

# How to build
        ./gradlew build
        
# Example config file

`records` 
`A1` - example records description. Value `A1` can be changed to anything else because it's only needed for user to describe which record needs to be placed in output file

`recordCount` - numbers of records generated in output file

`name` - name of field (it's used as description and will not be placed in output file)

`fields` - decribe each column of output file related with record

`values` - values used to placed in output file. 

`randomness` - required values are `true/false` and tell about which value should be placed in output file. Random or ordered from key `values`

Keys `values` for each column or even records can be used to create dependencies between columns
in different fields or records

```
records:
  A1:
    recordCount: 2
    fields:
      - name: id
        values: [0, 1000, 999]
        randomness: true 
      - name: login
        values: [user, admin]
        randomness: true 
  B2:
    recordCount: 5
    fields:
      - name: login 
        values: [user, admin]
        randomness: true
      - name: currency 
        values: [Euro, Pln]
        randomness: true 
outputFilePath: /tmp/generated_file.csv
```
