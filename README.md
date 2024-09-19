# Setup

* Use JDK 17 or higher
* Set JAVA_HOME as an environment variable pointing to the JDK directory
* Set ANDROID_HOME as an environment variable pointing to a directory where the android SDKs will be downloaded
* Clone https://github.com/anandbagmar/getting-started-with-appium
* MyFirstTest.java - starts and stops Appium server, creates and quits the Android driver
* Sample apk for testing - VodQA.apk is available here - https://github.com/anandbagmar/getting-started-with-appium/blob/main/src/test/resources/VodQA.apk  (also available in the src\test\resources folder)
* Install node and check the version by running the command `node -v` on the command prompt/terminal
* package.json has all the appium dependencies. Run `npm install` on the command prompt/terminal to install the node packages
* Verify installation by running `appium-doctor`. IF `appium-doctor` command is NOT available, add `./node_modules/.bin` to the path. 
  * Ex: On Mac, run this command in the terminal window: `export PATH=$PATH:./node_modules/.bin`
* Download the latest Appium inspector (based on your OS) from here - https://github.com/appium/appium-inspector/releases/tag/v2024.8.2

# Android setup:
* Setup SDK manager
* Create Emulator with Google Play.
* Sign in to Google Play and update Chrome browser

# To record/implement the test:
* Start appium server manually from the command prompt/terminal - `appium`
* Start Appium inspector
* Create new capabilities in Appium inspector: https://github.com/anandbagmar/getting-started-with-appium/blob/main/src/test/resources/vodqa_capabilities.json
* Start session
  * You may need to provide `Remote Path` as `/wd/hub` in case if you get an error starting the session
