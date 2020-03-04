



RAILS SAMPLE APP PROJECT

This Application is Online blog sharing site where one person can post information after successful registration. Automation suite is developed to test the application using selenium with Java. 
For Cross browser testing we have used browserstack.

Installation:

1. JAVA with latest or higher than 192 version.
2. Oxygen Eclipse - can be downloaded from https://www.eclipse.org/oxygen/
3. Gradle distribution files set up method
	3.i - Download the gradle:
		wget https://services.gradle.org/distributions/gradle-5.0-bin.zip -P /tmp
		uudo unzip -d /opt/gradle /tmp/gradle-*.zip

	3.ii - SET UP ENVIRONMENT VARIABLE:
	open your text editor and create a new file named gradle.sh inside of the /etc/profile.d/ directory.
		sudo nano /etc/profile.d/gradle.sh
	Paste the following:
		export GRADLE_HOME=/opt/gradle/gradle-5.0
		export PATH=${GRADLE_HOME}/bin:${PATH}

	Make the script executable by issuing the following chmod command:
		sudo chmod +x /etc/profile.d/gradle.sh

	Load the environment variables using the source command:
		source /etc/profile.d/gradle.sh

	3.iii - Verify the Gradle installation:
		gradle -v

4. TestNG plugins can be installed from eclipse -> windows preferences -> install software.
5. Download all required JAR files and load it under project build path.

Execution:

1. Import the project from git and right click on the Login Test class which is placed under automation_suit.tests package 
2. Run as TestNG.
3. After execution under test-output folder --> testReport.html --> open this with system explorer --> you can see the Execution status
4. Execution will happen in browserstack and results can also be viewed under the build


Scope to enhance:

> Central repository to have all JAR files and it can be given in build.gradle under repository function.

> Performance testing using JMeter can also be done to test the site response for high load

