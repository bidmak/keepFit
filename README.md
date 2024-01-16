# Keep Fit: Fitness Activity Goal Setting & Recording on Mobile Devices

## Coursework Overview

The "Keep Fit" app, developed as part of a coursework for the University of Strathclyde, aims to assist users in setting and achieving fitness goals by manually recording their walking activities. This Android app, implemented in Kotlin and Jetpack Compose, focuses on simplicity, enabling users to set walking goals, record daily activities, view historical records, and manage multiple goals.

## Functionality

### 1. Goal Activity Recording

- Users can set a daily walking goal in terms of the number of steps.
- Manual recording of daily activity allows users to input the number of steps walked.
- The app displays the day's status, including the target steps, steps walked, and the percentage of the goal completed.

### 2. Multiple Goal Setting

- Users can create multiple walking goals, each with a specific number of steps.
- Only one goal is considered active at a time (current goal for the day).
- Users can view, delete, or edit goals, but editing is allowed only for goals that are not currently active.
- Users can change the currently active goal at any time, and recorded activity is transferred to the newly selected goal.
- The day's status displays the name of the currently active goal.

### 3. Activity History

- The app persistently stores daily activity and goals over time.
- Users can view a history of recorded steps, including the current day, and the proportion of the daily goal completed.
- The user can clear the activity history, starting afresh from that point onwards.

### 4. Historical Activity Recording

- Users can record activity on a selected date, adding to the existing activity for that day.
- Users can also select or change the goal for historical dates.
- The app reflects any changes made in the activity history.

### 5. User Preferences and Settings

- Users can control the app's behavior through settings.
- Switch between normal and historical activity recording modes.
- Set whether goals are editable or not; even when not editable, goals can be deleted.

## Usage Instructions

1. **Install Kotlin and Android Studio:**
   - Ensure Kotlin and Android Studio are installed on your development machine.

2. **Clone the Repository:**
   ```bash
   git clone [repository_url]

3. **Open in Android Studio:**
   - Open the project in Android Studio.

4. **Build and Run:**
   - Build the project and run it on an Android emulator or a physical device.

5. **Explore the App:**
   - Use the app to set goals, record activities, view history, and manage preferences.

## Acknowledgments

This coursework was completed as part of a University of Strathclyde academic assignment.

## Authors

- [Samuel Makanjuola]

## License

This coursework is subject to the University of Strathclyde's academic policies.
  
