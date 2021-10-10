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
