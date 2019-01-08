# Spock Utils

This library provides basic data consumption and reporting for Spock by extending Spock Data Pipes and Extent-Reports mechanisms.

## Features

Two main features are currently provided:

- Extends the data pipe mechanism to allow data consumption from Excel and CSV files.
- Provide a common interface for reporting to both Extent-Reports (.html) and Custom (.docx) destinations. 

## Requirements

- Java 8 (JDK)
- Gradle

## Build with gradle

The project can be build with any 4+ gradle version. However, using the provided wrapper is highly recommended. 
Open a command window and execute the following command to build, test, and install the spock-utils library in your local repository:

Linux/Mac:

	./gradlew build install

Windows:
	
	gradlew build install


Once in your local repository, the spock-utils library can be included as a dependency in other projects. Check out the example projects inside [this folder](../appium-samples).


## Usage

Check out the [Examples](../appium-samples) for better reference.