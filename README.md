# TennisCounter

This is an app for Android that keeps track of the score during a tennis match.

It keeps track of points, games, sets, faults and it enters in deuce or tie-break modality when necessary.

The app supports the following languages:
- English;
- French;
- Italian.

The app supports both portrait and landscape mode, with two differents layout (activities) that switches between automatically.

This project is currently on the 0.86 version, relesaed on 28/04/2017.

## Previous versions

### V0.86 - 28/04/2017
#### Improvements:
- resources for XML used massively;
- first time I cleaned the app.

### V0.85 - 12/04/2017
#### Improvements:
- Change of layout, colours and style (added );
- "customborder.xml" renamed as "rounded_border.xml";
- added "buttons_border.xml" for buttons;
- added app icon.

### V0.80 - 31/03/2017
#### Improvements:
- resolved a bug that doesn't previously allowed to visualize "GAME", "SET" and "MATCH" notifications after a double fault from the opponent;
- added toast messages for game, set and match attribution; the strings for these toasts are not hardcoded but stored as strings;
- added string.xml for italian and french translations;
- there is a new method called "displayNotificationPlayerX" that I used to store the values of the variables "notificationA" and "notificationB" (that I created for saving and restoring activity). This method improves the coeherence of the code and reduce repetition;
- now able to fully save state and restore it (for example, in case of rotation of the screen);
- activity_main.xml modified: added a "Sets" Textview and changed some attributes around;
- created "customborder.xml" in the drawable folder and used to make borders around sets of players;
- created "land\activity_main.xml" in "layout/activity_main.xml" as a new layout for landscape mode.

### V0.75 - 14/03/2017
#### Improvements:
- structure of the app is completely revised;
- the bug about the "GAME" stuck display in the previous version is now resolved;
- when any player scores a game, a set or a match, this infromation is now displayed in the notification area;
- when the match ends, there is no way to score points or faults, until the reset button is pushed;
- now when a fault is done immediatly after that the opponent scored a game or a set, "game" does not stand on the screen;
- "Tie - Break" mode is now on and working;
- added the "resultMatch" method to calculate who is the winner and give the "MATCH" message to the player that has more sets scored.

### V0.70 - 05/03/2017
#### Improvements:
- The points are displayed in "love-15-30-40-Deuche-Adv." modality;
- now the reset button resets all scores of the sets and games and all relevant functional variables;
- the resetGame function has been created and now games resets automatically counted when the appropriate score is reached.
#### Problems to fix:
- When a game ends, the scoreName displayed for the winner is stucked to "GAME" until he realizes another point.

### V0.65 - 04/03/2017
#### Improvements:
- Now the app is scrollable thanks to the use ofe ScrollView;
- faults are notified only when the amount of faults is equal to 1.

### V0.60 - 03/03/2017
#### Improvements:
- App is now able to give a point to the adversary when 2 faults are made.
