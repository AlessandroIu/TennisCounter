package com.example.android.tenniscounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Tracks the score for both Player A and B.
    private int scorePlayerA = 0;
    private int scorePlayerB = 0;

    //Tracks the number of faults for both Player A and B.
    private int faultsPlayerA = 0;
    private int faultsPlayerB = 0;

    //Tracks the game scores in the sets for both Player A and B.
    private int set1PlayerA = 0;
    private int set2PlayerA = 0;
    private int set3PlayerA = 0;
    private int set1PlayerB = 0;
    private int set2PlayerB = 0;
    private int set3PlayerB = 0;

    //Tracks the number of sets won by both players A and B
    private int setsWonByPlayerA = 0;
    private int setsWonByPlayerB = 0;

    //Tracks the current set of the game
    private int setOfTheGame = 1;


    //If a game is on 40-40 situation, then this variable enables "deuce" modality
    private boolean deuce = false;

    //If a game is scored, this variable helps to keep track of
    private boolean newGame = false;

    //If a set is on 6-6 situation, then this variable enables "Tie-Break" mode
    private boolean tieBreak = false;

    //If the match ends, this variable prevents to score points and faults by clicking buttons until reset button is pressed
    private boolean endMatch = false;

    //Set strings for notification value to implement in the notification TextViewv
    private String notificationA = "";
    private String notificationB = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //With this instance, we wanna save any any data before we are about to change orientation, that we wanna go to re-render when the new orientation appears

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the user's current game state
        outState.putInt("SCORE_PLAYER_A", scorePlayerA);
        outState.putInt("SCORE_PLAYER_B", scorePlayerB);
        outState.putInt("FAULTS_PLAYER_A", faultsPlayerA);
        outState.putInt("FAULTS_PLAYER_B", faultsPlayerB);
        outState.putInt("SET_1_PLAYER_A", set1PlayerA);
        outState.putInt("SET_2_PLAYER_A", set2PlayerA);
        outState.putInt("SET_3_PLAYER_A", set3PlayerA);
        outState.putInt("SET_1_PLAYER_B", set1PlayerB);
        outState.putInt("SET_2_PLAYER_B", set2PlayerB);
        outState.putInt("SET_3_PLAYER_B", set3PlayerB);
        outState.putInt("SETS_WON_BY_PLAYER_A", setsWonByPlayerA);
        outState.putInt("SETS_WON_BY_PLAYER_B", setsWonByPlayerB);
        outState.putInt("SET_OF_THE_GAME", setOfTheGame);

        outState.putBoolean("DEUCE", deuce);
        outState.putBoolean("NEW_GAME", newGame);
        outState.putBoolean("TIE_BREAK", tieBreak);
        outState.putBoolean("END_MATCH", endMatch);

        outState.putString("NOTIFICATION_A", notificationA);
        outState.putString("NOTIFICATION_B", notificationB);

        //Call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            scorePlayerA = savedInstanceState.getInt("SCORE_PLAYER_A");
            scorePlayerB = savedInstanceState.getInt("SCORE_PLAYER_B");
            faultsPlayerA = savedInstanceState.getInt("FAULTS_PLAYER_A");
            faultsPlayerB = savedInstanceState.getInt("FAULTS_PLAYER_B");
            set1PlayerA = savedInstanceState.getInt("SET_1_PLAYER_A");
            set2PlayerA = savedInstanceState.getInt("SET_2_PLAYER_A");
            set3PlayerA = savedInstanceState.getInt("SET_3_PLAYER_A");
            set1PlayerB = savedInstanceState.getInt("SET_1_PLAYER_B");
            set2PlayerB = savedInstanceState.getInt("SET_2_PLAYER_B");
            set3PlayerB = savedInstanceState.getInt("SET_3_PLAYER_B");
            setsWonByPlayerA = savedInstanceState.getInt("SETS_WON_BY_PLAYER_A");
            setsWonByPlayerB = savedInstanceState.getInt("SETS_WON_BY_PLAYER_B");
            setOfTheGame = savedInstanceState.getInt("SET_OF_THE_GAME");

            deuce = savedInstanceState.getBoolean("DEUCE");
            newGame = savedInstanceState.getBoolean("NEW_GAME");
            tieBreak = savedInstanceState.getBoolean("TIE_BREAK");
            endMatch = savedInstanceState.getBoolean("END_MATCH");

            notificationA=savedInstanceState.getString("NOTIFICATION_A");
            notificationB=savedInstanceState.getString("NOTIFICATION_B");

            //        Display the value of members stored
            //Start with displaying the notifications vales stored (not the faults value stored,
            //otherwise it will overwrite other notifications - for example "GAME" - stored)

            displayNotificationsForPlayerA(notificationA);
            displayNotificationsForPlayerB(notificationB);
            displayPointsForPlayerA(scorePlayerA);
            displayPointsForPlayerB(scorePlayerB);

            TextView scoreViewSet1A = (TextView) findViewById(R.id.player_a_score_set1);
            scoreViewSet1A.setText(String.valueOf(set1PlayerA));
            TextView scoreViewSet2A = (TextView) findViewById(R.id.player_a_score_set2);
            scoreViewSet2A.setText(String.valueOf(set2PlayerA));
            TextView scoreViewSet3A = (TextView) findViewById(R.id.player_a_score_set3);
            scoreViewSet3A.setText(String.valueOf(set3PlayerA));
            TextView scoreViewSet1B = (TextView) findViewById(R.id.player_b_score_set1);
            scoreViewSet1B.setText(String.valueOf(set1PlayerB));
            TextView scoreViewSet2B = (TextView) findViewById(R.id.player_b_score_set2);
            scoreViewSet2B.setText(String.valueOf(set2PlayerB));
            TextView scoreViewSet3B = (TextView) findViewById(R.id.player_b_score_set3);
            scoreViewSet3B.setText(String.valueOf(set3PlayerB));

        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Increase the score for Player A by a point.
     */
    public void pointForPlayerA(View v) {
        if (!endMatch) {
            scorePlayerA = scorePlayerA + 1;
            faultsPlayerA = 0;
            displayFaultsForPlayerA(faultsPlayerA);
            faultsPlayerB = 0;
            displayFaultsForPlayerB(faultsPlayerB);
            if (!tieBreak) {
                if (!deuce) {
                    switch (scorePlayerA) {
                        case 3: {
                            if (scorePlayerB == 3) {
                                deuce = true;
                            }
                            break;
                        }
                        case 4: {
                            if (scorePlayerA - scorePlayerB > 1) {
                                newGame = true;
                            } else if (scorePlayerA == scorePlayerB) {
                                deuce = true;
                            }
                            break;
                        }
                    }
                }
                if (deuce) {
                    if (scorePlayerA - scorePlayerB > 1) {
                        newGame = true;
                    }
                }
            }
            if (tieBreak) {
                if ((scorePlayerA>6)&&(scorePlayerA-scorePlayerB>1)) {
                    newGame = true;
                }
            }
            displayPointsForPlayerA(scorePlayerA);
            if (newGame) {
                addGameForPlayerA(null);
                resetGame(null);
            }
        }
    }

    /**
     * Increase the faults for Player A by a one.
     */

    public void faultForPlayerA(View V) {
        if (!endMatch) {
            faultsPlayerA = faultsPlayerA + 1;
            displayFaultsForPlayerA(faultsPlayerA);
            displayFaultsForPlayerB(faultsPlayerB);
            if (faultsPlayerA == 2) {
                pointForPlayerB(null);
            }
        }
    }

    /**
     * Displays if there is a fault for Player A.
     */
    public void displayFaultsForPlayerA(int faults) {
        String faultsName = "";
        switch (faults) {
            case 1: {
                faultsName = "Fault";
                break;
            }
        }
        displayNotificationsForPlayerA(faultsName);
    }

    /**
     * Displays the given score for Player A.
     */
    public void displayPointsForPlayerA(int score) {
        String scoreName = "";
        if (!tieBreak) {
            if (!deuce) {
                switch (score) {
                    case 0: {
                        scoreName = "Love";
                        break;
                    }
                    case 1: {
                        scoreName = "15";
                        break;
                    }
                    case 2: {
                        scoreName = "30";
                        break;
                    }
                    case 3: {
                        scoreName = "40";
                        break;
                    }
                }
            }
            else {
                if (score == scorePlayerB) {
                    scoreName = "Deuce";
                    TextView scoreView = (TextView) findViewById(R.id.player_b_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else if (score - scorePlayerB == 1) {
                    scoreName = "Adv.";
                    TextView scoreView = (TextView) findViewById(R.id.player_b_score);
                    scoreView.setText(String.valueOf(""));
                }
            }
        }
        else {
            scoreName=(String.valueOf(scorePlayerA));
        }
        TextView scoreView = (TextView) findViewById(R.id.player_a_score);
        scoreView.setText(String.valueOf(scoreName));
    }

    /**
     * Increase the score of the set for Player A by 1 point.
     */
    public void addGameForPlayerA(View V) {

        //show a game attribution for player A message as toast
        Toast.makeText(this, getString(R.string.game_for_player_a), Toast.LENGTH_SHORT).show();

        // Create a local variable named "notificationName" to send a value to the "displayNotificationsForPlayerA" method
        String notificationName = "GAME";

        deuce = false;
        tieBreak = false;
        if (setOfTheGame == 1) {
            set1PlayerA++;
            TextView scoreView = (TextView) findViewById(R.id.player_a_score_set1);
            scoreView.setText(String.valueOf(set1PlayerA));
            if ((set1PlayerA == 6 && set1PlayerA - set1PlayerB > 1) || (set1PlayerA == 7)) {
                setsWonByPlayerA++;
                setOfTheGame = 2;

                //show a set attribution for player A message as toast
                Toast.makeText(this, getString(R.string.set_for_player_a), Toast.LENGTH_SHORT).show();

                notificationName = "SET";

            } else {
                if (set1PlayerA == 6 && set1PlayerB ==6){
                    tieBreak=true;
                }
            }
        } else if (setOfTheGame == 2) {
            set2PlayerA++;
            TextView scoreView = (TextView) findViewById(R.id.player_a_score_set2);
            scoreView.setText(String.valueOf(set2PlayerA));
            if ((set2PlayerA == 6 && set2PlayerA - set2PlayerB > 1) || (set2PlayerA == 7)) {
                setsWonByPlayerA++;
                setOfTheGame = 3;

                //show a set attribution for player A message as toast
                Toast.makeText(this, getString(R.string.set_for_player_a), Toast.LENGTH_SHORT).show();

                notificationName = "SET";

            } else {
                if (set2PlayerA == 6 && set2PlayerB ==6){
                    tieBreak=true;
                }
            }
        } else if (setOfTheGame == 3) {
            set3PlayerA++;
            TextView scoreView = (TextView) findViewById(R.id.player_a_score_set3);
            scoreView.setText(String.valueOf(set3PlayerA));
            if ((set3PlayerA == 6 && set3PlayerA - set3PlayerB > 1) || (set3PlayerA == 7)) {
                setsWonByPlayerA++;
                setOfTheGame = 4;

                //show a set attribution for player A message as toast
                Toast.makeText(this, getString(R.string.set_for_player_a), Toast.LENGTH_SHORT).show();

                notificationName = "SET";

            } else {
                if (set3PlayerA == 6 && set3PlayerB ==6){
                    tieBreak=true;
                }
            }
        }

        displayNotificationsForPlayerA(notificationName);

        if (setOfTheGame == 4) {
            endMatch = true;
            resultMatch(null);
        }

    }

    /**
     * Displays if there is a notification to show for Player A.
     * I split the method "displayFaults" with this other one, to obtain a method that manage
     * the final message sent by all notification in the app to the corresponding TextView
     */

    public void displayNotificationsForPlayerA(String aNotificationForA) {
        notificationA = aNotificationForA;
        TextView notificationsView = (TextView) findViewById(R.id.player_a_notifications);
        notificationsView.setText(String.valueOf(notificationA));
    }

    /**
     * Increase the score for Player B by a point.
     */
    public void pointForPlayerB(View v) {
        if (!endMatch) {
            scorePlayerB = scorePlayerB + 1;
            faultsPlayerA = 0;
            displayFaultsForPlayerA(faultsPlayerA);
            faultsPlayerB = 0;
            displayFaultsForPlayerB(faultsPlayerB);
            if (!tieBreak) {
                if (!deuce) {
                    switch (scorePlayerB) {
                        case 3: {
                            if (scorePlayerA == 3) {
                                deuce = true;
                            }
                            break;
                        }
                        case 4: {
                            if (scorePlayerB - scorePlayerA > 1) {
                                newGame = true;
                            } else if (scorePlayerB == scorePlayerA) {
                                deuce = true;
                            }
                            break;
                        }
                    }
                }
                else {
                    if (scorePlayerB - scorePlayerA > 1) {
                        newGame = true;
                    }
                }
            }
            else {
                if ((scorePlayerB>6)&&(scorePlayerB-scorePlayerA>1)) {
                    newGame = true;
                }
            }
            displayPointsForPlayerB(scorePlayerB);
            if (newGame) {
                addGameForPlayerB(null);
                resetGame(null);
            }
        }
    }

    /**
     * Increase the faults for Player B by a one.
     */

    public void faultForPlayerB(View V) {
        if (!endMatch) {
            faultsPlayerB = faultsPlayerB + 1;
            displayFaultsForPlayerB(faultsPlayerB);
            displayFaultsForPlayerA(faultsPlayerA);
            if (faultsPlayerB == 2) {
                pointForPlayerA(null);
            }
        }
    }

    /**
     * Displays if there is a fault for Player B.
     */
    public void displayFaultsForPlayerB(int faults) {
        String faultsName = "";
        switch (faults) {
            case 1: {
                faultsName = "Fault";
                break;
            }
        }
        displayNotificationsForPlayerB(faultsName);
    }

    /**
     * Displays the given score for Player B.
     */
    public void displayPointsForPlayerB(int score) {
        String scoreName = "";
        if (!tieBreak) {
            if (!deuce) {
                switch (score) {
                    case 0: {
                        scoreName = "Love";
                        break;
                    }
                    case 1: {
                        scoreName = "15";
                        break;
                    }
                    case 2: {
                        scoreName = "30";
                        break;
                    }
                    case 3: {
                        scoreName = "40";
                        break;
                    }
                }
            }
            if (deuce) {
                if (score == scorePlayerA) {
                    scoreName = "Deuce";
                    TextView scoreView = (TextView) findViewById(R.id.player_a_score);
                    scoreView.setText(String.valueOf(scoreName));
                } else if (score - scorePlayerA == 1) {
                    scoreName = "Adv.";
                    TextView scoreView = (TextView) findViewById(R.id.player_a_score);
                    scoreView.setText(String.valueOf(""));
                }
            }
        }
        if (tieBreak) {
            scoreName=(String.valueOf(scorePlayerB));
        }
        TextView scoreView = (TextView) findViewById(R.id.player_b_score);
        scoreView.setText(String.valueOf(scoreName));
    }

    /**
     * Increase the score of the set for Player B by 1 point.
     */
    public void addGameForPlayerB(View V) {

        //show a game attribution for player B message as toast
        Toast.makeText(this, getString(R.string.game_for_player_b), Toast.LENGTH_SHORT).show();

        // Create a local variable named "notificationName" to send a value to the "displayNotificationsForPlayerA" method
        String notificationName = "GAME";

        deuce = false;
        tieBreak = false;
        if (setOfTheGame == 1) {
            set1PlayerB++;
            TextView scoreView = (TextView) findViewById(R.id.player_b_score_set1);
            scoreView.setText(String.valueOf(set1PlayerB));
            if ((set1PlayerB == 6 && set1PlayerB - set1PlayerA > 1) || (set1PlayerB == 7)) {
                setsWonByPlayerB++;
                setOfTheGame = 2;

                //show a set attribution for player B message as toast
                Toast.makeText(this, getString(R.string.set_for_player_b), Toast.LENGTH_SHORT).show();

                notificationName = "SET";

            } else {
                if (set1PlayerB == 6 && set1PlayerA ==6){
                    tieBreak=true;
                }
            }
        } else if (setOfTheGame == 2) {
            set2PlayerB++;
            TextView scoreView = (TextView) findViewById(R.id.player_b_score_set2);
            scoreView.setText(String.valueOf(set2PlayerB));
            if ((set2PlayerB == 6 && set2PlayerB - set2PlayerA > 1) || (set2PlayerB == 7)) {
                setsWonByPlayerB++;
                setOfTheGame = 3;

                //show a set attribution for player B message as toast
                Toast.makeText(this, getString(R.string.set_for_player_b), Toast.LENGTH_SHORT).show();

                notificationName = "SET";

            } else {
                if (set2PlayerB == 6 && set2PlayerA ==6){
                    tieBreak=true;
                }
            }
        } else if (setOfTheGame == 3) {
            set3PlayerB++;
            TextView scoreView = (TextView) findViewById(R.id.player_b_score_set3);
            scoreView.setText(String.valueOf(set3PlayerB));
            if ((set3PlayerB == 6 && set3PlayerB - set3PlayerA > 1) || (set3PlayerB == 7)) {
                setsWonByPlayerB++;
                setOfTheGame = 4;

                //show a set attribution for player B message as toast
                Toast.makeText(this, getString(R.string.set_for_player_b), Toast.LENGTH_SHORT).show();

                notificationName = "SET";

            } else {
                if (set3PlayerB == 6 && set3PlayerA ==6){
                    tieBreak=true;
                }
            }
        }

        displayNotificationsForPlayerB(notificationName);

        if (setOfTheGame == 4) {
            endMatch = true;
            resultMatch(null);
        }

    }

    /**
     * Displays if there is a notification to show for Player B.
     * I split the method "displayFaults" with this other one, to obtain a method that manage
     * the final message sent by all notification in the app to the corresponding TextView
     */

    public void displayNotificationsForPlayerB (String aNotificationForB){
        notificationB = aNotificationForB;
        TextView notificationsView = (TextView) findViewById(R.id.player_b_notifications);
        notificationsView.setText(String.valueOf(notificationB));
    }

    /**
     * Reset both scores for Player A and B.
     */
    public void resetGame(View v) {
        newGame = false;
        scorePlayerA = 0;
        scorePlayerB = 0;
        displayPointsForPlayerA(scorePlayerA);
        displayPointsForPlayerB(scorePlayerB);
    }

    public void resultMatch(View v) {

        String notificationName = "MATCH";

        if (setsWonByPlayerA>setsWonByPlayerB) {

            //show a match attribution for player A message as toast
            Toast.makeText(this, getString(R.string.match_for_player_a), Toast.LENGTH_SHORT).show();

            //Set the "MATCH" string as notification for player A and an empty string for player B
            displayNotificationsForPlayerA(notificationName);
            displayNotificationsForPlayerB("");
        } else if (setsWonByPlayerB>setsWonByPlayerA) {

            //show a match attribution for player B message as toast
            Toast.makeText(this, getString(R.string.match_for_player_b), Toast.LENGTH_SHORT).show();

            //Set the "MATCH" string as notification for player B and an empty string for player A
            displayNotificationsForPlayerB(notificationName);
            displayNotificationsForPlayerA("");
        }

    }

    /**
     * Reset both scores for Player A and B.
     */
    public void resetScore(View v) {
        setOfTheGame = 1;
        newGame = false;
        tieBreak = false;
        deuce = false;
        endMatch = false;
        setsWonByPlayerA=0;
        setsWonByPlayerB=0;

        scorePlayerA = 0;
        scorePlayerB = 0;
        faultsPlayerA = 0;
        faultsPlayerB = 0;
        displayPointsForPlayerA(scorePlayerA);
        displayPointsForPlayerB(scorePlayerB);
        displayFaultsForPlayerA(faultsPlayerA);
        displayFaultsForPlayerB(faultsPlayerB);

        set1PlayerA = 0;
        set2PlayerA = 0;
        set3PlayerA = 0;
        set1PlayerB = 0;
        set2PlayerB = 0;
        set3PlayerB = 0;
        TextView scoreView = (TextView) findViewById(R.id.player_a_score_set1);
        scoreView.setText(String.valueOf(set1PlayerA));
        scoreView = (TextView) findViewById(R.id.player_a_score_set2);
        scoreView.setText(String.valueOf(set2PlayerA));
        scoreView = (TextView) findViewById(R.id.player_a_score_set3);
        scoreView.setText(String.valueOf(set3PlayerA));
        scoreView = (TextView) findViewById(R.id.player_b_score_set1);
        scoreView.setText(String.valueOf(set1PlayerB));
        scoreView = (TextView) findViewById(R.id.player_b_score_set2);
        scoreView.setText(String.valueOf(set2PlayerB));
        scoreView = (TextView) findViewById(R.id.player_b_score_set3);
        scoreView.setText(String.valueOf(set3PlayerB));


    }
}