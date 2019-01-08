# Appium examples

## 1: Java and Android

	Donwload and install the [Oracle JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
	Download and install [Android Studio](https://developer.android.com/studio/)

	After installing, set the ANDROID_HOME environment varibale.
    
    Example for windows:
    `ANDROID_HOME  --> C:\Users\HP\AppData\Local\Android\Sdk`
  
  	Example for Mac:
  	`ANDROID_HOME  --> /Users/emilioadmin/Library/Android/sdk`

## 2: Appium Desktop

	Download and install [Appium Desktop](http://appium.io/)
 	
 	Once Appium Desktop is installed, open the application and click on the "Start Server..." button using the default configuration.

## 3: Device setup

	- Make sure you have installed a proper driver for your device. Follow [these instructions in the Android Developer site](https://developer.android.com/studio/run/oem-usb).
 	- Make sure Google Maps is installed in the device
 	- Enable developer mode in the device. You'll have to unhide it for the first time before allowing USB debugging. For instruction, follow (this Android Developer page)[https://developer.android.com/studio/debug/dev-options#enable].
 	- Plug the device to the USB port.
 	- Open a command window and type `adb devices`. You should see the ID of your connected device. Save the ID for later.

## 4: Review the Examples
	
	- [Basic](appium-java): A simple example with google maps using TestNG and Extend Reports.
	- [Basic with Spock framework](appium-spock): Another simple example using Groovy and the Spock framework with BDD capabilities.
	- [Spock, Data Pipes, and Reports](appium-spock-with-reports): A more sophisticated example Data Pipes and custom reporting provided by the spock-utils library.