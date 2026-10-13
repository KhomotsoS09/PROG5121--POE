# PROG5121 POE - Khomotso Swafo ST10523846

## Project Overview
This repository contains both PROG5121 POE Part 1 and Part 2 in one repo as required by IIE.

Part 1: User Registration and Login validation
Part 2: QuickChat Messaging Application

## Part 1 - Registration Features (Login.java)
- checkUserName: Validates underscore and <=5 chars
- checkPasswordComplexity: 8 chars, capital, digit, special char
- checkCellPhoneNumber: +27 format validation
- registerUser, loginUser, returnLoginStatus

## Part 2 - Messaging Features (Message.java & LoginApp.java)
- Send Messages with recipient validation
- Create MessageID (10 digits random)
- Create MessageHash
- Menu: 1) Send 2) Show messages 3) Quit
- Store messages in array/JSON

## How to Run
Run LoginApp.java
Follow JOptionPane prompts:
1. Register -> 2. Login -> 3. Messaging Menu

## Testing
JUnit tests in LoginTest.java - All tests passed for Part 1 validation.

## Repository Structure
- Login.java
- LoginApp.java
- Message.java
- README.md

## Student
Khomotso Swafo - ST10523846
GitHub: KhomotsoS09
