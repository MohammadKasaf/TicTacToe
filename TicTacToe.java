package org.example.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private boolean playerXTurn = true;
    private Button buttons[][] = new Button[3][3];

    private Label playerXScore;
    private Label playerYScore;

    private int xScore = 0;
    private int yScore = 0;

    private static final String PLAYER_X = "X";
    private static final String PLAYER_Y = "Y";

    private BorderPane createContent() {
        BorderPane root = new BorderPane();

        // set title
        Label title = new Label("Tic Tac Toe");
        root.setStyle("-fx-font-size:24pt; -fx-font-weight:bold");
        root.setTop(title);

        // Board of game
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("-");
                button.setPrefSize(100, 100);
                button.setOnAction(event -> clickButton(button));
                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }
        root.setCenter(gridPane);

        // Score card
        HBox scoreBoard = new HBox(20);
        playerXScore = new Label("Player X: " + xScore);
        playerXScore.setStyle("-fx-font-size:24pt; -fx-font-weight:bold");
        playerYScore = new Label("Player Y: " + yScore);
        playerYScore.setStyle("-fx-font-size:24pt; -fx-font-weight:bold");
        scoreBoard.getChildren().addAll(playerXScore, playerYScore);
        root.setBottom(scoreBoard);

        return root;
    }

    // add functionality to click button
    public void clickButton(Button button) {
        if (button.getText().equals("-")) {
            if (playerXTurn) {
                button.setText(PLAYER_X);
            } else {
                button.setText(PLAYER_Y);
            }
            playerXTurn = !playerXTurn;
            checkWinner();
        }
    }

    // check winner
    public void checkWinner() {
        // row
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().equals("-")) {
                // we will have a winner
                String winner = buttons[row][0].getText();
                showWinnerDialogue(winner);
                updateScore(winner);
                resetBoard();
            }
        }

        // column
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().equals("-")) {
                // we will have a winner
                String winner = buttons[0][col].getText();
                showWinnerDialogue(winner);
                updateScore(winner);
                resetBoard();
            }
        }
        //left diagonal
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().equals("-")) {
            // we will have a winner
            String winner = buttons[0][0].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
        }

        //right diagonal
        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().equals("-")) {
            // we will have a winner
            String winner = buttons[0][2].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
        }

        //tie
        boolean tie=true;
        for (Button[] row : buttons) {
            for (Button button : row) {

                if(button.getText().equals("-")){
                    tie=false;
                    break;
                }
            }
        }

        if(tie){
            resetBoard();
            showTieDialogue();
        }


        
    }
    //when some one is win then show winning dialogue
    private void showWinnerDialogue(String winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Winner");
        alert.setContentText("Congratulations " + winner + " you won the game");
        alert.showAndWait();
    }

    //when match is tie show tie dialogue
    private void showTieDialogue() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Tie");
        alert.setContentText("Game over ! Its a tie");
        alert.showAndWait();
    }


    // update score
    public void updateScore(String winner) {
        if (winner.equals(PLAYER_X)) {
            xScore++;
            playerXScore.setText("Player X: " + xScore);
        } else {
            yScore++;
            playerYScore.setText("Player Y: " + yScore);
        }
    }

    // reset board
    public void resetBoard() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("-");
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
