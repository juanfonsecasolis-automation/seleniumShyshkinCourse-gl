# seleniumShyshkinCourse-gl

2021 Juan M. Fonseca-Solis. Project to store the assignments requested in https://gorillalogic.udemy.com/course/advanced-selenium-webdriver.

## 1 Pre-requisites
* Install JDK: `sudo apt-get install default-jdk`
* Eclipse: https://www.eclipse.org/downloads/

## 2 Project set-up
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

### 2.1 WebDrivers
* [Linux Chrome WebDriver](https://chromedriver.storage.googleapis.com/index.html?path=92.0.4515.107/).
* [Linux Firefox WebDriver](https://github.com/mozilla/geckodriver/releases/tag/v0.30.0)
* Once you have downloaded them in the src/test/resources folder make sure to assign the proper permissions using `chmod a+x <executableFilename>`.

### 2.1 Paralellism
* Parallelism is configured as easy as modifiying the testng XMLs: `<suite name="NegativeTestSuite" verbose="1" parallel="tests" thread-count="4">`
* To identify properly the logs for each thread we use Log4j instead of the console output.

## 3 Run
* Go to src/test/resources > LoginTests.xml > right-click > RunAs > TestNG suite

## 4 Theory

### 4.1 Full-stack test automation framework
Should be:
* Well documented
* Fully tested (never trust the test that never fails)
* Fully coded (QA should learn to code)
* Support different tests (resolutions, mobile, desktop, API, browser versions)
* Cross-platform (Mac, Linux, Windows)
* Parallel testing
* Extendable and customizable

### 4.2 POM
Advantages:
* Operations are separated form verifications.
* Code is re-used among classes (making TCs shorter).
* Page object methods are user-oriented actions.
* Easier maintenability.

### 4.3 PageFactory
* One problem is that if the page contains dynamic content, the "InitElements" method might produce NPE.

## 5 Jenkins on Docker
Based on [8, 9], the initial setup is:
```
docker build -t jenkins .
docker image pull jenkins
docker volume create jenkinsvol1
docker container run -d -p 8080:8080 -v jenkinsvol1:/var/jenkins_home --name jenkins-local jenkins
docker ps
docker container exec <containerId> sh -c "cat /var/jenkins_home/secrets/initialAdminPassword"
// copy the password (e.g. b277537a477245f596687becb194fd93) and access jenkins on http://localhost:8080
// install default plugins and configure a task to clone the git repository and run mvn clean test
```

To restore previously created containers:
```
docker container kill <containerId>
docker container rm <containerId>
// now run the same "docker container run..." defined above
// go to https://localhost:8080, it should work again with all the same configurations restored
```

## 6 References
1. Apache. Maven, Ivy, Gradle, and SBT Artifacts. URL: https://logging.apache.org/log4j/2.x/maven-artifacts.html (last consulted on 10/16/21).
2. MVNRepository. REST Assured. URL: https://mvnrepository.com/artifact/io.rest-assured/rest-assured
3. RestAssured. GettingStarted. URL: https://github.com/rest-assured/rest-assured/wiki/GettingStarted
4. RestAssured. Usage. URL: https://github.com/rest-assured/rest-assured/wiki/Usage
5. Hansani Jayasekara. Getting started with REST Assured. Aeturnum, Medium. ULR: https://medium.com/aeturnuminc/getting-started-with-rest-assured-a087d806b4ec
6. Tools QA. Validate Response Status using Rest Assured. URL: https://www.toolsqa.com/rest-assured/validate-response-status-using-rest-assured/
7. Guru99. TestNG Groups: Include, Exclude with Example – Selenium Tutorial. URL: https://www.guru99.com/introduction-testng-groups.html
8. TechWorld with Nana. Run Jenkins in Docker Container - Jenkins Pipeline Tutorial for Beginners 1/4. URL: https://youtu.be/pMO26j2OUME
9. Rangle.io. Running Jenkins and Persisting state locally using Docker. URL: https://rangle.io/blog/running-jenkins-and-persisting-state-locally-using-docker-2/
