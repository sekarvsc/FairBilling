# FairBilling

The main file is FairBilling.java.

# Setup
You must be able to work with GitHub repositories.
Clone repository.
 git clone https://github.com/sekarvsc/FairBilling.git

# Eclipse Instructions
Prerequisites:
  Install Eclipse and optionally the GitHub plugin.

Import Java Project into Eclipse workspace

Click on Run > Run configurations
Navigate to your Java Application's configuration section
In the Arguments tab, add the "Path of input file" as a Program argument
Run

Right-click on project
Run As > Java Application

# Instruction to generate Jar file
Right click on the java project and select "Export"
Select "JAR File" and click on Next button
Under "Select the export destination" option, give destination folder path where jar need to generated
In "JAR Manifest Specification" page, need to select Main class of our project under "Select the class of the application entry point:"
Then give finish button. Jar will get generated.

# Instruction to execute jar in command prompt
Navigate the jar generated location using command prompt.
Give below command:
  java -jar FairBilling.jar C:\Users\Chandrasekar\Desktop\FairBilling\input.txt
