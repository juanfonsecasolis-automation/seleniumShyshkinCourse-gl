# seleniumShyshkinCourse-gl

2021 Juan M. Fonseca-Solis

Project to store the assignments requested in https://gorillalogic.udemy.com/course/advanced-selenium-webdriver/

## Project setup
Using Eclipse IDE:
* Create a Maven project with no archetype
* Right click on pom.xml > Maven > Add dependency:

| GroupId                 | ArtifactId    | Version | Scope |
|-------------------------|---------------|---------|-------|
| org.testng              | testng        | 6.14.3  | test  |
| org.seleniumhq.selenium | selenium-java | 3.14.0  | test  |

* Right click on pom.xml > Maven > Add plugin:

| GroupId                  | ArtifactId            | Version |
|--------------------------|-----------------------|---------|
| org.apache.maven.plugins | maven-compiler-plugin | 3.8.0   |
| org.apache.maven.plugins | maven-surefire-plugin | 2.22.0  |

* Help > Eclipse Marketplace... > Search "TestNG" and install it.
* Compile as pom.xml > right click > Maven > Update Project

### WebDrivers
* [Linux Chrome WebDriver](https://chromedriver.storage.googleapis.com/index.html?path=92.0.4515.107/).
* [Linux Firefox WebDriver](https://github.com/mozilla/geckodriver/releases/tag/v0.30.0)
* Once you have downloaded them in the src/test/resources folder make sure to assign the proper permissions using `chmod a+x <executableFilename>`.

## Full-stack test automation framework
Should be:
* Well documented
* Fully tested (never trust the test that never fails)
* Fully coded (QA should learn to code)
* Support different tests (resolutions, mobile, desktop, API, browser versions)
* Cross-platform (Mac, Linux, Windows)
* Parallel testing
* Extendable and customizable

## To run the project
Go to src/test/resources > LoginTests.xml > right-click > RunAs > TestNG suite