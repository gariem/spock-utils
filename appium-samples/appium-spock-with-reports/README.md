
Internediate example with google maps using Spock, data pipes, and custom reports.

## Requirements

In addition to the General Configuration which [can be found here](..), running this example requires building and installing the `spock-utils` project in your local repository. Follow [these steps](../../spock-utils#build-with-gradle) to build and install the `spock-utils` project.

- Make sure your Android device is connected. Developer options and USB debugging must be enabled.
- Start the Appium server in default mode (local).

## Running the example

- Get your device ID by running the `adb devices` command in a terminal window.

- Modify the `DeviceSetup.java` file inside the `src/test/java/org/gariem/samples/setup` folder. Replace the value for the `udid` capability at line 19.

- In a terminal window, change directory to the folder containing the `build.gradle` file and run this comand:

```
	./gradlew clean test //for linux/mac
	
	OR
	
	gradlew clean test //for windows
```
