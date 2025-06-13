# Automation Practice APIApp - Selenium Framework

## Features
- Verifies Creating/Reading/Updating/Deleting user with ID: {user.id}
- Parallel test execution in Chrome and Firefox
- Scalable, maintainable framework (Page Object Model)
- Detailed reporting 
- Optional Dockerized test execution

## Setup Steps
1. Clone the repo
2. Install dependencies: `mvn clean install` or `mvn test`
3. (Optional) Start Docker Grid: `docker-compose up -d`

## Run Tests
- Standard: `mvn test -DsuiteXmlFile=testng.xml`
- Docker: Configure RemoteWebDriver and run using the Grid

## Reports
- Available in `allure-report` 
