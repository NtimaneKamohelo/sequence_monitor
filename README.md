# South African ID Validator

A secure Spring Boot REST API for validating and parsing South African Identity Document (ID) numbers into structured identity information.
The application accepts a South African ID number, validates its structure and checksum, extracts information encoded within the number, and persists the resulting identity record in a MySQL database.
The project is designed as an industry-oriented backend application, demonstrating practices such as layered architecture, database migrations, transaction management, secure hashing, duplicate prevention, auditing, automated testing, and CI/CD.

# Overview

The South African ID Validator is a backend application that processes South African 13-digit identity numbers.

The application is responsible for:

1. Receiving an ID number through a REST API.
2. Validating the ID number's format.
3. Validating the date of birth encoded in the ID.
4. Determining the gender from the sequence portion.
5. Determining the citizenship classification represented by the relevant digit.
6. Validating the obsolete classification digit according to the application's validation rules.
7. Validating the final checksum using the Luhn algorithm.
8. Converting the validated information into a structured identity representation.
9. Generating a secure deterministic hash of the original ID number.
10. Persisting the identity record in MySQL.
11. Preventing duplicate identity records.
12. Recording audit information about identity-processing operations.

The application is intended primarily as a software engineering and portfolio project demonstrating how an identity-processing service can be structured using modern backend development practices.

*Important*: This application should not be considered an official South African government identity verification service. Parsing and validating the structure of an ID number does not prove that an identity actually exists or that the person presenting the number is the legitimate owner.

# Features
## Identity Number Validation

The application validates the South African ID number before attempting to persist information.

Validation includes:

- 13-digit format validation
- Numeric-only validation
- Date-of-birth validation
- Sequence validation
- Gender extraction
- Citizenship extraction
- Obsolete digit handling
- Luhn checksum validation

Invalid ID numbers are rejected rather than being stored as valid identity records.

# Date of Birth Parsing

The first six digits of the ID number represent:

YYMMDD

The application extracts these values and converts them into a LocalDate.

For example:
900315

represents:
15 March 1990

The date parser also validates whether the resulting date is actually valid.

For example:
901332

would not represent a valid calendar date.

# Gender Parsing

The four-digit sequence section of the ID number is used to determine gender according to the application's defined rules.

The sequence range is:

0000 - 4999 → Female
5000 - 9999 → Male

Example:

5800
falls within the male range.

# Prerequisites

Before running the application, install the following software.

## 1. Java

The current project uses Spring Boot 3.x.

Spring Boot 3.x requires Java 17 or newer.

Verify your Java installation:

java -version

You should see Java 17 or a later supported version.

Example:

openjdk version "17.x.x"

## 2. Maven

Verify Maven:

mvn -version

Maven should report the Java version being used by the build.

Example:

Apache Maven 3.x.x
Java version: 17

## 3. MySQL

Install MySQL Server locally.

Verify that MySQL is running before starting the application.

You can test the MySQL connection with:

mysql -u root -p
