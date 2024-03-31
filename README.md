# HW1-TQS
A simple full-stack web application supplied with automated tests.



# maven run sonarqube
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=BusApp \
  -Dsonar.projectName='BusApp' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_cc398a75edca93218b1deeaed26059406e191e0c