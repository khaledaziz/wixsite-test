# wixsite-test

Automation project for wixsite site using selenium java- maven- testng- Extent report
**To run the test:
1- clone the repo
2- use the following maven command "mvn clean test" or run directly from the testng.xml file

##To open the html report after execution:
 It is existing in reports/extentReport.html

##The screenshots of each step is existing in the following directory:
screens

## Project structure
1- This is a page object model project

2- The classes of pages are existing in the following path "src/main/java/com.wixsite.test/pages"

3- The classes of tests are existing in the following path "src/test/java/com/wixsite/test"

4- Also, framework base setup classes (i.e. open the browser, tear down, generate report, the main selenium methods, etc.) are in this path "src/main/java/com.wixsite.test/base"

5- App config for setting the desired browser and base url is in this config file "src/test/resources/config.properties"


