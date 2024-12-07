# BreathWatch - Automated Respiratory Monitoring System

## Project Overview
BreathWatch is an automated respiratory monitoring system designed to track patient breathing and heart rate data. The system supports two types of user accounts: **Patient** and **Clinician**. Patients can enter symptoms and view their vital signs, while clinicians can access data from all patients and monitor for abnormal readings.

## Setup Instructions

### 1. Download and Open the Project
- Download the BreathWatch source files.
- Open the project in **IntelliJ IDEA**.

### 2. Install Required Plugins
To interact with the SQL database, you will need to install the **SimpleSqliteBrowser** plugin:
- Go to **File > Settings > Plugins** in IntelliJ IDEA.
- Search for **SimpleSqliteBrowser** and install it.
- This plugin will allow you to view and interact with the SQLite database, which is included in the source files.

### 3. Set Up the Database
Ensure that the SQLite library is included in the project source files. This will allow the system to store and retrieve patient data.

### 4. Run the Application
- Open the `BreathWatchApp` file in IntelliJ IDEA.
- Run the program to start the application.

### 5. Login Information
Once the program initiates, you will see the login screen. There are 4 user accounts available with the following credentials:

| Username  | Password |
|-----------|----------|
| patient   | 123      |
| patient2  | 456      |
| patient3  | 789      |
| clinician | 123      |

**Note**: Each user’s username is considered their **patientID**, which cannot be changed by the user.

### 6. Patient User Features
- Upon logging in as a patient, the **patientID** will automatically be set to the username.
- Patients can enter symptoms in a text field and input their **breathing rate** and **heart rate**.
- The patient can also use the **"Generate Random Values"** button to generate accurate random numbers for breathing and heart rate.
- The patient view will show the **average** of the breathing and heart rate.
- Once the patient has entered the required information, they can press the **Submit** button to upload the data to the SQLite database.
- After submitting the data, the user can close the application.

### 7. Clinician User Features
- Logging into the clinician account works the same as for the patient accounts.
- The clinician has access to all submissions made by patient accounts.
- The clinician view includes alerts for **abnormal rates** in patient data.

### 8. Exiting the Application
Once the patient or clinician has completed their actions, they can exit the application to close the program.

## Conclusion
BreathWatch is designed to provide automated respiratory monitoring for both patients and clinicians. The system supports storing patient data securely in an SQLite database and provides real-time insights into patient vital signs.

## Project Information
**Course**: SENG 4130 Final Project  
**Project Name**: BreathWatch - Automated Respiratory Monitoring System

## Authors
**Luka Aitken & Toma Aitken**
