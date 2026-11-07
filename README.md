# PROG5121 POE - Khomotso Swafo ST10523846

QuickChat Messaging Application - Part 1, Part 2 & Part 3

## Repository Structure

* Part1/
    * Login.java - validation for username, password, cellphone
    * LoginApp.java - main for registration & login
* Part2/
    * Login.java
    * LoginApp.java
    * Message.java - messaging functionality
    * MessageTest.java - JUnit tests
* Part3/
    * Login.java - validation for username, password, cellphone
    * LoginApp.java - main for registration, login & messaging menu
    * Message.java - messaging, store to JSON, search, delete, display report
    * MessageTest.java - JUnit tests for Part 3
    * storedMessages.json - saved messages
    * pom.xml - Maven dependencies (JUnit, JSON)
* Output-Registration successful
* README.md

## How to Run

### Part 1
Run Part1/LoginApp.java Follow JOptionPane prompts: Register -> Login

### Part 2
Run Part2/LoginApp.java Follow prompts: 1. Register -> 2. Login -> 3. Messaging Menu

### Part 3
Run Part3/LoginApp.java Follow prompts: 1. Register -> 2. Login -> 3. Messaging Menu
In Part 3 you can:
* Send messages
* Store messages to storedMessages.json
* View sent messages / stored messages
* Search messages by ID or recipient
* Delete messages
* Display longest message & full report

## Testing

* Part 1: Manual validation for username, password, cellphone
* Part 2: JUnit tests in Part2/MessageTest.java All tests passed for Message class
* Part 3: JUnit tests in Part3/MessageTest.java All tests passed for Message class, storing, searching and reporting

## Student
Name: Khomotso Swafo Student No: ST10523846 Module: PROG5121 GitHub: KhomotsoS09
