# Build Environment

Maven can be called on different goals.
The maven executable is called `mvn` if you have maven installed.
If you use the included maven wrapper it is called `mvnw`.
See "Build Project" for an example.

Most IDEs support Run Configurations, with wizards for using maven matching to your project.

# Build project

Run `mvn install` (or wrapper call `mvnw install`).

# Run project

Run `java -jar <package-name>.jar`.
The jar can also be executed by most IDEs.

# Testing

Maven install command includes running unit tests (from test/java package).
This creates results under <project dir>/target/surefire-reports

For pretty and insightful HTML reports, run `mvn site`.
