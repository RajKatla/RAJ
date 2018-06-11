Service layer bean does the following:

Scans configured directory in file system and returns the file metadata --> filename, file mime type, file size, file extension

Provides facility to retrieve certain supported mime type files: excel and csv are supported currently


#selenium and cucumber framework do the following:

Use the above service layer bean to get supported files (excel or csv are supported, from input directory)

Using above service layer gets the supported MIME type files and  go through the file and read vehicle registration details in the file.

Opens webpage : https://www.gov.uk/get-vehicle-information-from-dvla and go through all vehicles from excel/csv file.

On the Vehicle details page asserts the details (Make/Color) match with expected output in excel/csv file.


#To run tests use below command:
mvn clean test

#application.properties:
directory and web drivers paths are externalised in properties.

#resources/files
CSV and Excel files are under this directory

#screenshots are located under screenshots directory

cucumber
feature files are located in test/resources/features
step definitions are in cucumber package


