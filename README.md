# Configuración de Appium-Demo App

## Paso 1
	Descargar instalar Android Studio:
	https://developer.android.com/studio/

 - Instalar JDK
 - Configurar las variables de entorno ANDROID_HOME
   Ejm: ANDROID_HOME  --> C:\Users\HP\AppData\Local\Android\Sdk 
  

## Paso 2
	Descargar e instalar el Appium Desktop version 1.6.3:
	https://github.com/appium/appium-desktop/releases/tag/v1.6.3
 
 - appium-desktop-setup-1.6.3.exe
 - Iniciar el appium "Start..."

## Paso 3
 - Instalar el driver de la marca del smartphone (Android)
 - Instalar Google Maps en el smartphone
 - Activar modo desarrollador en el dispositivo del smartphone
 - Conectar el smartphone con el cable usb
 - Abrir la ventana de comandos (CMD) y ejecutara el comando: "adb devices" para obtener el device id del smartphone conectado.

## Paso 4
	Descargar el demo app from:
	https://github.com/gariem/appium-demo

	![Screenshot](sdk-andr.png)

	- Abrir el codigo fuente del proyecto de la aplicación appium-java al Android IDE
	- Modificar el appium-java/src/test/java/org/gariem/samples/setup/DeviceSetup.java y poner el device id obtenido.
	- Ejecultar el comando en el Proyecto para la prueba de la demo: gradlew clean test
	 
