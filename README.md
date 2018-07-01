# JournalApp
A journal application where in users can pen down their thoughts and feelings submitted for the "7 Days of code" challlenge for the GoogleAfiricaScholarship Challenge offered by Udacity and powered by Andela Learning Community (ALC) learners.

[![CircleCI](https://circleci.com/gh/po10cio/JournalApp.svg?style=svg)](https://circleci.com/gh/po10cio/JournalApp)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8ceda2e1681c4b8aa9b44c5da15ddb53)](https://www.codacy.com/app/po10cio/JournalApp?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=po10cio/JournalApp&amp;utm_campaign=Badge_Grade)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Android Studio 3.1.3 or greater.
```

### Installing

Follow this steps if you want get a local copy of the project in your machine.

##### 1. Clone or fork the repository by running the cammand below.
	
	git clone https://github.com/po10cio/JournalApp.git

##### 2. Import the project in AndroidStudio
1. In Android Studio, go to File -> New -> Import project
2. Follew the dialog wizard to choose the folder where you cloned the project and click on open.
3. Androidstudio imports the projects and builds it for you. 

You can now run the project in an Android Emulator or a real Android Device.

## Running the tests

Journal App comes with both Instrumented tests and Unit tests. 

### Instrumentation tests

To run the instrumentaton tests, you need an Android Emulator or a real Android device. Once you have any of this, open a terminal in AndroidStudio and run the command below.

```
./gradlew connectedAndroidTest
```

### Running Local Unit tests
You do not need any device (Emulator of real Android device) to run this test. To run this test, open a terminal and run the command shown below.

```
./gradlew test
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Kotlin](https://kotlinlang.org/) - Koltin for JVM
* [Room Database](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library for Android.
* [Koin](https://github.com/InsertKoinIO/koin) - A pragmatic lightweight dependency injection framework for Kotlin
* [Firebase](https://firebase.google.com/) - For authentication and data persisntence.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

* **Jerry Hanks**

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [PurpleBooth](https://gist.github.com/PurpleBooth) - for the README.md template
* [Arnaud Giuliani](https://android.jlelse.eu/painless-android-testing-with-room-koin-bb949eefcbee)
