# Intent Attacks Practice App

An Android application designed for practicing and understanding various types of intent-based attacks and vulnerabilities. This app provides a practical environment for learning about Android security concepts through hands-on exercises using the HexTree AttackSurface Vulnerable App.

## Features

- Multiple flag-based challenges (All 41 flags)
- Fragment-based architecture for better organization
- Navigation drawer for easy access to different challenges
- Real-time feedback through Toast messages and Logcat
- Error handling and user feedback
- Clean and intuitive UI (Using Ai :"D)

## Prerequisites

- Android Studio (latest version recommended)
- Android SDK (minimum API level 21)
- Android device or emulator running Android 5.0 or higher (Tested on Android 10)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/A-Z4ki/hextree-attack-surface.git
```

2. Open the project in Android Studio

3. Build and run the application on your device or emulator

### Or 
1. Download the APK from the releases.
## Project Structure

The project follows a fragment-based architecture:

- `MainActivity.java`: Main entry point, handles navigation and fragment management
- `FlagXXFragment.java`: Individual challenge fragments
- `fragment_flagXX.xml`: Layout files for each challenge
- `nav_menu.xml`: Navigation menu configuration

## Usage

1. Launch the application
2. Use the navigation drawer (swipe from left edge or tap menu icon) to access different challenges
3. Each challenge has a button that triggers the specific exploit
4. Results and feedback are shown through Toast messages
5. Check Logcat for detailed information and debugging

## Caveats
1. The application in the releases can't solve Flag11, and Flag12 as the Activities for them are commented in the manifest. So to solve Flag11 or Flag12 you need to uncomment it in the manifest.
2. Some exploits will get you the flag but will not call the success() method in the target app


## Support

For support, please open an issue in the GitHub repository or contact the maintainers.

## Disclaimer

This application is for educational purposes only. The authors are not responsible for any misuse or damage caused by this program. Always practice responsible security testing and follow ethical guidelines. 
