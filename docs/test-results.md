\# Test Results



\# Parcel Locker Management System



\## 1. Purpose of This Document



This document summarizes the current automated testing results for the \*\*Parcel Locker Management System\*\* project.



The purpose is to provide a clear overview of the test execution, code coverage, mutation testing results, and integration testing results.



\---



\## 2. Environment



The tests were executed locally with the following environment:



```text

Operating system: Windows

Java version: Java 17

Build tool: Maven

Database for local app: H2

Database for real integration tests: PostgreSQL via Testcontainers

Docker: Docker Desktop

```



\---



\## 3. Test Commands Used



\### Standard Test Suite



```bash

mvn -U clean test

```



This command runs:



\* unit tests;

\* H2 repository integration tests;

\* application service tests;

\* Swing end-to-end tests.



\### Full Verification



```bash

mvn -U clean verify

```



On Windows, the following environment variables were used for Testcontainers:



```powershell

$env:DOCKER\_HOST="npipe:////./pipe/docker\_engine"

$env:DOCKER\_API\_VERSION="1.44"

mvn -U clean verify

```



This command runs:



\* standard tests;

\* PostgreSQL Testcontainers integration tests;

\* JaCoCo report generation.



\### Mutation Testing



```bash

mvn -U org.pitest:pitest-maven:mutationCoverage

```



This command runs PIT mutation testing.



\---



\## 4. Standard Test Results



The standard test suite completed successfully.



Current result:



```text

Tests run: 16

Failures: 0

Errors: 0

Skipped: 0

Result: BUILD SUCCESS

```



The standard test suite includes:



\* `ParcelLockerServiceTest`

\* `ParcelLockerApplicationServiceTest`

\* `RepositoryIntegrationTest`

\* `MainFrameE2ETest`



\---



\## 5. Unit Test Results



Unit tests verify the core business logic.



Main tested class:



```text

ParcelLockerService

```



The unit tests cover business rules such as:



\* assigning parcels to locker cells;

\* rejecting occupied locker cells;

\* rejecting locker cells with a wrong size;

\* validating pickup codes;

\* collecting parcels;

\* releasing locker cells after collection.



Current result:



```text

Tests run: 8

Failures: 0

Errors: 0

Skipped: 0

```



\---



\## 6. Application Service Test Results



Application service tests verify complete use cases at the application layer.



Main tested class:



```text

ParcelLockerApplicationService

```



The application service tests cover:



\* customer registration;

\* locker cell creation;

\* parcel creation;

\* parcel assignment;

\* parcel collection;

\* duplicate customer phone number validation;

\* duplicate locker cell number validation;

\* no available locker cell scenario.



Current result:



```text

Tests run: 4

Failures: 0

Errors: 0

Skipped: 0

```



\---



\## 7. H2 Repository Integration Test Results



Repository integration tests verify JPA/Hibernate persistence behavior using H2 in-memory databases.



Main tested repositories:



```text

CustomerRepository

ParcelRepository

LockerCellRepository

```



These tests verify:



\* entity persistence;

\* repository queries;

\* entity relationships;

\* transaction behavior.



Current result:



```text

Tests run: 3

Failures: 0

Errors: 0

Skipped: 0

```



\---



\## 8. Swing End-to-End Test Results



The Swing end-to-end test verifies the main user workflow through the graphical interface.



Main tested class:



```text

MainFrameE2ETest

```



The E2E test performs the following workflow:



```text

Register customer

Create locker cell

Create parcel

Assign parcel

Collect parcel

Verify final state

```



Current result:



```text

Tests run: 1

Failures: 0

Errors: 0

Skipped: 0

```



This confirms that the Swing UI is connected correctly to the application service and the persistence layer.



\---



\## 9. PostgreSQL Testcontainers Integration Test Results



The PostgreSQL integration tests verify the persistence layer using a real PostgreSQL database started by Testcontainers.



Main tested class:



```text

RepositoryPostgresIT

```



The test uses:



```text

PostgreSQL Docker container

Testcontainers

Hibernate

JPA

```



Current result:



```text

Tests run: 2

Failures: 0

Errors: 0

Skipped: 0

Result: BUILD SUCCESS

```



The successful execution confirms that the repository and persistence logic work with PostgreSQL, not only with H2.



\---



\## 10. JaCoCo Coverage Results



JaCoCo was used to generate the code coverage report.



Report location:



```text

target/site/jacoco/index.html

```



Current coverage summary:



```text

Instruction coverage: 87%

Branch coverage:      52%

Classes analyzed:     20

Methods covered:      142 / 165

Lines covered:        525 / 598

```



The report confirms that most of the application code is executed by automated tests.



Branch coverage is lower than line coverage because branch coverage requires testing alternative paths such as both successful and failing conditions.



\---



\## 11. PIT Mutation Testing Results



PIT was used to evaluate the quality of the test suite.



Report location:



```text

target/pit-reports/index.html

```



Current mutation testing summary:



```text

Line coverage for mutated classes: 82%

Generated mutations:              104

Killed mutations:                 74

Mutation coverage:                71%

Mutations with no coverage:       19

Test strength:                    87%

```



Interpretation:



\* \*\*Generated mutations\*\* means how many code changes PIT created.

\* \*\*Killed mutations\*\* means how many of those changes were detected by the tests.

\* \*\*Mutation coverage\*\* shows how many generated mutations were killed.

\* \*\*Test strength\*\* shows how effective the tests were for the code that was actually covered.



The result shows that the test suite detects many behavioral changes, not only executes lines of code.



\---



\## 12. Summary of Current Quality Metrics



Current project status:



```text

Standard tests:        PASS

PostgreSQL IT tests:   PASS

Swing E2E test:        PASS

JaCoCo report:         GENERATED

PIT mutation testing:  PASS

Maven verify:          BUILD SUCCESS

```



Current numerical summary:



```text

Standard tests:        16 tests, 0 failures, 0 errors

PostgreSQL IT tests:   2 tests, 0 failures, 0 errors

JaCoCo instruction:    87%

JaCoCo branch:         52%

PIT mutation coverage: 71%

PIT test strength:     87%

```



\---



\## 13. Notes About Warnings



Some warnings may appear during test execution.



Examples:



```text

H2Dialect does not need to be specified explicitly

PostgreSQLDialect does not need to be specified explicitly

relation does not exist, skipping

sequence does not exist, skipping

```



These warnings do not indicate test failure.



The PostgreSQL warnings appear because Hibernate uses `create-drop` schema generation. It first tries to drop tables and sequences before creating them. In a fresh Testcontainers database, those objects do not exist yet, so PostgreSQL reports that they are skipped.



The build is considered successful only if Maven finishes with:



```text

BUILD SUCCESS

```



\---



\## 14. Conclusion



The current test results show that the project has a strong automated testing foundation.



The project successfully demonstrates:



\* unit testing;

\* integration testing with H2;

\* real PostgreSQL integration testing with Testcontainers;

\* Swing end-to-end testing;

\* code coverage with JaCoCo;

\* mutation testing with PIT.



The results support the goal of the project: to demonstrate automated software testing techniques on a small but meaningful Java Swing desktop application.



