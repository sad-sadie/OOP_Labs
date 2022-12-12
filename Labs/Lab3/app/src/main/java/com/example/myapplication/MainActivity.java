package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.myapplication.GameCore.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static ImageView[][] imageField;
    static ImageButton[][] buttonMatrix;

    TextView textScore;

    TextView textPlayAgain;
    Button buttonYes;
    Button buttonNo;
    ImageView imagePlayAgain;

    Button buttonRestart;

    boolean selected = false;
    boolean readyForMove = false;
    Position from = new Position(-1, -1);
    Position to = new Position(-1, -1);

    GameCore gameManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textScore = findViewById(R.id.textScore);
        imagePlayAgain = findViewById(R.id.imagePlayAgain);
        textPlayAgain = (TextView) findViewById(R.id.textPlayAgain);
        buttonNo = (Button) findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(this);
        buttonYes = (Button) findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(this);
        buttonRestart = (Button) findViewById(R.id.buttonRestart);
        buttonRestart.setOnClickListener(this);

        buttonMatrix = new ImageButton[8][8];

        buttonMatrix[0][0] = findViewById(R.id.buttonSpace00);
        buttonMatrix[0][1] = findViewById(R.id.buttonSpace01);
        buttonMatrix[0][2] = findViewById(R.id.buttonSpace02);
        buttonMatrix[0][3] = findViewById(R.id.buttonSpace03);
        buttonMatrix[0][4] = findViewById(R.id.buttonSpace04);
        buttonMatrix[0][5] = findViewById(R.id.buttonSpace05);
        buttonMatrix[0][6] = findViewById(R.id.buttonSpace06);
        buttonMatrix[0][7] = findViewById(R.id.buttonSpace07);

        buttonMatrix[1][0] = findViewById(R.id.buttonSpace10);
        buttonMatrix[1][1] = findViewById(R.id.buttonSpace11);
        buttonMatrix[1][2] = findViewById(R.id.buttonSpace12);
        buttonMatrix[1][3] = findViewById(R.id.buttonSpace13);
        buttonMatrix[1][4] = findViewById(R.id.buttonSpace14);
        buttonMatrix[1][5] = findViewById(R.id.buttonSpace15);
        buttonMatrix[1][6] = findViewById(R.id.buttonSpace16);
        buttonMatrix[1][7] = findViewById(R.id.buttonSpace17);

        buttonMatrix[2][0] = findViewById(R.id.buttonSpace20);
        buttonMatrix[2][1] = findViewById(R.id.buttonSpace21);
        buttonMatrix[2][2] = findViewById(R.id.buttonSpace22);
        buttonMatrix[2][3] = findViewById(R.id.buttonSpace23);
        buttonMatrix[2][4] = findViewById(R.id.buttonSpace24);
        buttonMatrix[2][5] = findViewById(R.id.buttonSpace25);
        buttonMatrix[2][6] = findViewById(R.id.buttonSpace26);
        buttonMatrix[2][7] = findViewById(R.id.buttonSpace27);

        buttonMatrix[3][0] = findViewById(R.id.buttonSpace30);
        buttonMatrix[3][1] = findViewById(R.id.buttonSpace31);
        buttonMatrix[3][2] = findViewById(R.id.buttonSpace32);
        buttonMatrix[3][3] = findViewById(R.id.buttonSpace33);
        buttonMatrix[3][4] = findViewById(R.id.buttonSpace34);
        buttonMatrix[3][5] = findViewById(R.id.buttonSpace35);
        buttonMatrix[3][6] = findViewById(R.id.buttonSpace36);
        buttonMatrix[3][7] = findViewById(R.id.buttonSpace37);

        buttonMatrix[4][0] = findViewById(R.id.buttonSpace40);
        buttonMatrix[4][1] = findViewById(R.id.buttonSpace41);
        buttonMatrix[4][2] = findViewById(R.id.buttonSpace42);
        buttonMatrix[4][3] = findViewById(R.id.buttonSpace43);
        buttonMatrix[4][4] = findViewById(R.id.buttonSpace44);
        buttonMatrix[4][5] = findViewById(R.id.buttonSpace45);
        buttonMatrix[4][6] = findViewById(R.id.buttonSpace46);
        buttonMatrix[4][7] = findViewById(R.id.buttonSpace47);

        buttonMatrix[5][0] = findViewById(R.id.buttonSpace50);
        buttonMatrix[5][1] = findViewById(R.id.buttonSpace51);
        buttonMatrix[5][2] = findViewById(R.id.buttonSpace52);
        buttonMatrix[5][3] = findViewById(R.id.buttonSpace53);
        buttonMatrix[5][4] = findViewById(R.id.buttonSpace54);
        buttonMatrix[5][5] = findViewById(R.id.buttonSpace55);
        buttonMatrix[5][6] = findViewById(R.id.buttonSpace56);
        buttonMatrix[5][7] = findViewById(R.id.buttonSpace57);

        buttonMatrix[6][0] = findViewById(R.id.buttonSpace60);
        buttonMatrix[6][1] = findViewById(R.id.buttonSpace61);
        buttonMatrix[6][2] = findViewById(R.id.buttonSpace62);
        buttonMatrix[6][3] = findViewById(R.id.buttonSpace63);
        buttonMatrix[6][4] = findViewById(R.id.buttonSpace64);
        buttonMatrix[6][5] = findViewById(R.id.buttonSpace65);
        buttonMatrix[6][6] = findViewById(R.id.buttonSpace66);
        buttonMatrix[6][7] = findViewById(R.id.buttonSpace67);

        buttonMatrix[7][0] = findViewById(R.id.buttonSpace70);
        buttonMatrix[7][1] = findViewById(R.id.buttonSpace71);
        buttonMatrix[7][2] = findViewById(R.id.buttonSpace72);
        buttonMatrix[7][3] = findViewById(R.id.buttonSpace73);
        buttonMatrix[7][4] = findViewById(R.id.buttonSpace74);
        buttonMatrix[7][5] = findViewById(R.id.buttonSpace75);
        buttonMatrix[7][6] = findViewById(R.id.buttonSpace76);
        buttonMatrix[7][7] = findViewById(R.id.buttonSpace77);

        //buttonMatrix[0][0].setOnClickListener(this);
        for (ImageButton[] ba : buttonMatrix)
            for (ImageButton b : ba)
                b.setOnClickListener(this);


        imageField = new ImageView[8][8];

        imageField[0][0] = findViewById(R.id.imageBall00);
        imageField[0][1] = findViewById(R.id.imageBall01);
        imageField[0][2] = findViewById(R.id.imageBall02);
        imageField[0][3] = findViewById(R.id.imageBall03);
        imageField[0][4] = findViewById(R.id.imageBall04);
        imageField[0][5] = findViewById(R.id.imageBall05);
        imageField[0][6] = findViewById(R.id.imageBall06);
        imageField[0][7] = findViewById(R.id.imageBall07);

        imageField[1][0] = findViewById(R.id.imageBall10);
        imageField[1][1] = findViewById(R.id.imageBall11);
        imageField[1][2] = findViewById(R.id.imageBall12);
        imageField[1][3] = findViewById(R.id.imageBall13);
        imageField[1][4] = findViewById(R.id.imageBall14);
        imageField[1][5] = findViewById(R.id.imageBall15);
        imageField[1][6] = findViewById(R.id.imageBall16);
        imageField[1][7] = findViewById(R.id.imageBall17);

        imageField[2][0] = findViewById(R.id.imageBall20);
        imageField[2][1] = findViewById(R.id.imageBall21);
        imageField[2][2] = findViewById(R.id.imageBall22);
        imageField[2][3] = findViewById(R.id.imageBall23);
        imageField[2][4] = findViewById(R.id.imageBall24);
        imageField[2][5] = findViewById(R.id.imageBall25);
        imageField[2][6] = findViewById(R.id.imageBall26);
        imageField[2][7] = findViewById(R.id.imageBall27);

        imageField[3][0] = findViewById(R.id.imageBall30);
        imageField[3][1] = findViewById(R.id.imageBall31);
        imageField[3][2] = findViewById(R.id.imageBall32);
        imageField[3][3] = findViewById(R.id.imageBall33);
        imageField[3][4] = findViewById(R.id.imageBall34);
        imageField[3][5] = findViewById(R.id.imageBall35);
        imageField[3][6] = findViewById(R.id.imageBall36);
        imageField[3][7] = findViewById(R.id.imageBall37);

        imageField[4][0] = findViewById(R.id.imageBall40);
        imageField[4][1] = findViewById(R.id.imageBall41);
        imageField[4][2] = findViewById(R.id.imageBall42);
        imageField[4][3] = findViewById(R.id.imageBall43);
        imageField[4][4] = findViewById(R.id.imageBall44);
        imageField[4][5] = findViewById(R.id.imageBall45);
        imageField[4][6] = findViewById(R.id.imageBall46);
        imageField[4][7] = findViewById(R.id.imageBall47);

        imageField[5][0] = findViewById(R.id.imageBall50);
        imageField[5][1] = findViewById(R.id.imageBall51);
        imageField[5][2] = findViewById(R.id.imageBall52);
        imageField[5][3] = findViewById(R.id.imageBall53);
        imageField[5][4] = findViewById(R.id.imageBall54);
        imageField[5][5] = findViewById(R.id.imageBall55);
        imageField[5][6] = findViewById(R.id.imageBall56);
        imageField[5][7] = findViewById(R.id.imageBall57);

        imageField[6][0] = findViewById(R.id.imageBall60);
        imageField[6][1] = findViewById(R.id.imageBall61);
        imageField[6][2] = findViewById(R.id.imageBall62);
        imageField[6][3] = findViewById(R.id.imageBall63);
        imageField[6][4] = findViewById(R.id.imageBall64);
        imageField[6][5] = findViewById(R.id.imageBall65);
        imageField[6][6] = findViewById(R.id.imageBall66);
        imageField[6][7] = findViewById(R.id.imageBall67);

        imageField[7][0] = findViewById(R.id.imageBall70);
        imageField[7][1] = findViewById(R.id.imageBall71);
        imageField[7][2] = findViewById(R.id.imageBall72);
        imageField[7][3] = findViewById(R.id.imageBall73);
        imageField[7][4] = findViewById(R.id.imageBall74);
        imageField[7][5] = findViewById(R.id.imageBall75);
        imageField[7][6] = findViewById(R.id.imageBall76);
        imageField[7][7] = findViewById(R.id.imageBall77);


        gameManager = new GameCore();
        updateField();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRestart:
                if (imagePlayAgain.getVisibility() == View.GONE) {
                    gameManager = new GameCore();
                    updateField();
                }

                break;

            case R.id.buttonNo:
                imagePlayAgain.setVisibility(View.GONE);
                textPlayAgain.setVisibility(View.GONE);
                buttonYes.setVisibility(View.GONE);
                buttonNo.setVisibility(View.GONE);
                break;

            case R.id.buttonYes:
                gameManager = new GameCore();
                updateField();

                imagePlayAgain.setVisibility(View.GONE);
                textPlayAgain.setVisibility(View.GONE);
                buttonYes.setVisibility(View.GONE);
                buttonNo.setVisibility(View.GONE);
                break;

            case R.id.buttonSpace00:
                manageSelect(0, 0);
                break;
            case R.id.buttonSpace01:
                manageSelect(0, 1);
                break;
            case R.id.buttonSpace02:
                manageSelect(0, 2);
                break;
            case R.id.buttonSpace03:
                manageSelect(0, 3);
                break;
            case R.id.buttonSpace04:
                manageSelect(0, 4);
                break;
            case R.id.buttonSpace05:
                manageSelect(0, 5);
                break;
            case R.id.buttonSpace06:
                manageSelect(0, 6);
                break;
            case R.id.buttonSpace07:
                manageSelect(0, 7);
                break;

            //1
            case R.id.buttonSpace10:
                manageSelect(1, 0);
                break;
            case R.id.buttonSpace11:
                manageSelect(1, 1);
                break;
            case R.id.buttonSpace12:
                manageSelect(1, 2);
                break;
            case R.id.buttonSpace13:
                manageSelect(1, 3);
                break;
            case R.id.buttonSpace14:
                manageSelect(1, 4);
                break;
            case R.id.buttonSpace15:
                manageSelect(1, 5);
                break;
            case R.id.buttonSpace16:
                manageSelect(1, 6);
                break;
            case R.id.buttonSpace17:
                manageSelect(1, 7);
                break;

            //2
            case R.id.buttonSpace20:
                manageSelect(2, 0);
                break;
            case R.id.buttonSpace21:
                manageSelect(2, 1);
                break;
            case R.id.buttonSpace22:
                manageSelect(2, 2);
                break;
            case R.id.buttonSpace23:
                manageSelect(2, 3);
                break;
            case R.id.buttonSpace24:
                manageSelect(2, 4);
                break;
            case R.id.buttonSpace25:
                manageSelect(2, 5);
                break;
            case R.id.buttonSpace26:
                manageSelect(2, 6);
                break;
            case R.id.buttonSpace27:
                manageSelect(2, 7);
                break;

            //3
            case R.id.buttonSpace30:
                manageSelect(3, 0);
                break;
            case R.id.buttonSpace31:
                manageSelect(3, 1);
                break;
            case R.id.buttonSpace32:
                manageSelect(3, 2);
                break;
            case R.id.buttonSpace33:
                manageSelect(3, 3);
                break;
            case R.id.buttonSpace34:
                manageSelect(3, 4);
                break;
            case R.id.buttonSpace35:
                manageSelect(3, 5);
                break;
            case R.id.buttonSpace36:
                manageSelect(3, 6);
                break;
            case R.id.buttonSpace37:
                manageSelect(3, 7);
                break;

            //4
            case R.id.buttonSpace40:
                manageSelect(4, 0);
                break;
            case R.id.buttonSpace41:
                manageSelect(4, 1);
                break;
            case R.id.buttonSpace42:
                manageSelect(4, 2);
                break;
            case R.id.buttonSpace43:
                manageSelect(4, 3);
                break;
            case R.id.buttonSpace44:
                manageSelect(4, 4);
                break;
            case R.id.buttonSpace45:
                manageSelect(4, 5);
                break;
            case R.id.buttonSpace46:
                manageSelect(4, 6);
                break;
            case R.id.buttonSpace47:
                manageSelect(4, 7);
                break;

            //5
            case R.id.buttonSpace50:
                manageSelect(5, 0);
                break;
            case R.id.buttonSpace51:
                manageSelect(5, 1);
                break;
            case R.id.buttonSpace52:
                manageSelect(5, 2);
                break;
            case R.id.buttonSpace53:
                manageSelect(5, 3);
                break;
            case R.id.buttonSpace54:
                manageSelect(5, 4);
                break;
            case R.id.buttonSpace55:
                manageSelect(5, 5);
                break;
            case R.id.buttonSpace56:
                manageSelect(5, 6);
                break;
            case R.id.buttonSpace57:
                manageSelect(5, 7);
                break;

            //6
            case R.id.buttonSpace60:
                manageSelect(6, 0);
                break;
            case R.id.buttonSpace61:
                manageSelect(6, 1);
                break;
            case R.id.buttonSpace62:
                manageSelect(6, 2);
                break;
            case R.id.buttonSpace63:
                manageSelect(6, 3);
                break;
            case R.id.buttonSpace64:
                manageSelect(6, 4);
                break;
            case R.id.buttonSpace65:
                manageSelect(6, 5);
                break;
            case R.id.buttonSpace66:
                manageSelect(6, 6);
                break;
            case R.id.buttonSpace67:
                manageSelect(6, 7);
                break;

            //7
            case R.id.buttonSpace70:
                manageSelect(7, 0);
                break;
            case R.id.buttonSpace71:
                manageSelect(7, 1);
                break;
            case R.id.buttonSpace72:
                manageSelect(7, 2);
                break;
            case R.id.buttonSpace73:
                manageSelect(7, 3);
                break;
            case R.id.buttonSpace74:
                manageSelect(7, 4);
                break;
            case R.id.buttonSpace75:
                manageSelect(7, 5);
                break;
            case R.id.buttonSpace76:
                manageSelect(7, 6);
                break;
            case R.id.buttonSpace77:
                manageSelect(7, 7);
                break;
            default:
                break;
        }

        if (selected)
            displaySelected();

        //int result = gameManager.nextStepInRound(selectedSpace[0], selectedSpace[1]);
        if (readyForMove) {
            switch (gameManager.moveBall(from, to)) {
                case INVALIDPATH:
                    Toast.makeText(getApplicationContext(), "No valid path!", Toast.LENGTH_SHORT).show();
                    break;

                case SUCCESS:
                    updateField();
                    break;

                case ENDGAME:
                    Toast.makeText(getApplicationContext(), "No more moves!", Toast.LENGTH_SHORT).show();
                    updateField();
                    displayFinalScore();
                    break;

                case NOBALL:
                    Toast.makeText(getApplicationContext(), "Select space with a ball!", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
            selected = false;
            readyForMove = false;
            revertSelected();
        }
    }

    private void manageSelect(int row, int col) {
        Integer[][] field = gameManager.getField();
        if (selected) {
            if (field[row][col] > 0) {
                revertSelected();
                from.row = row;
                from.col = col;
            } else {
                readyForMove = true;
                to.row = row;
                to.col = col;
            }
        } else {
            if (field[row][col] > 0) {
                selected = true;
                from.row = row;
                from.col = col;
            } else {
                selected = false;
                Toast.makeText(getApplicationContext(), "Select space with a ball!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void updateField() {
        Integer[][] field = gameManager.getField();
        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j) {
                switch (field[i][j]) {
                    case RED:
                        imageField[i][j].setImageResource(R.drawable.red_bl);
                        break;
                    case BLUE:
                        imageField[i][j].setImageResource(R.drawable.blue_bl);
                        break;
                    case GREEN:
                        imageField[i][j].setImageResource(R.drawable.green_bl);
                        break;
                    case MAG:
                        imageField[i][j].setImageResource(R.drawable.mag_bl);
                        break;
                    case -RED:
                        imageField[i][j].setImageResource(R.drawable.red_next);
                        break;
                    case -GREEN:
                        imageField[i][j].setImageResource(R.drawable.green_next);
                        break;
                    case -BLUE:
                        imageField[i][j].setImageResource(R.drawable.blue_next);
                        break;
                    case -MAG:
                        imageField[i][j].setImageResource(R.drawable.mag_next);
                        break;
                    case 0:
                        imageField[i][j].setImageResource(0);
                }
                if (field[i][j] == 0)
                    imageField[i][j].setVisibility(View.INVISIBLE);
                else
                    imageField[i][j].setVisibility(View.VISIBLE);
            }

        textScore.setText("Score: " + gameManager.getScore());
    }

    public void displayFinalScore() {
        textScore.setText("Final Score: " + gameManager.getScore());

        imagePlayAgain.setVisibility(View.VISIBLE);
        textPlayAgain.setVisibility(View.VISIBLE);
        buttonYes.setVisibility(View.VISIBLE);
        buttonNo.setVisibility(View.VISIBLE);
    }

    public void displaySelected() {
        Integer[][] field = gameManager.getField();
        imageField[from.row][from.col].setVisibility(View.INVISIBLE);
        switch (field[from.row][from.col]) {
            case RED:
                imageField[from.row][from.col].setImageResource(R.drawable.red_wh);
                break;
            case BLUE:
                imageField[from.row][from.col].setImageResource(R.drawable.blue_wh);
                break;
            case GREEN:
                imageField[from.row][from.col].setImageResource(R.drawable.green_wh);
                break;
            case MAG:
                imageField[from.row][from.col].setImageResource(R.drawable.mag_wh);
                break;
        }
        imageField[from.row][from.col].setVisibility(View.VISIBLE);
    }

    public void revertSelected() {
        Integer[][] field = gameManager.getField();
        Ball[] next = gameManager.getNextBalls();
        switch (field[from.row][from.col]) {
            case RED:
                imageField[from.row][from.col].setImageResource(R.drawable.red_bl);
                imageField[from.row][from.col].setVisibility(View.VISIBLE);
                break;
            case BLUE:
                imageField[from.row][from.col].setImageResource(R.drawable.blue_bl);
                imageField[from.row][from.col].setVisibility(View.VISIBLE);
                break;
            case GREEN:
                imageField[from.row][from.col].setImageResource(R.drawable.green_bl);
                imageField[from.row][from.col].setVisibility(View.VISIBLE);
                break;
            case MAG:
                imageField[from.row][from.col].setImageResource(R.drawable.mag_bl);
                imageField[from.row][from.col].setVisibility(View.VISIBLE);
                break;
            case 0:
                imageField[from.row][from.col].setImageResource(0);
                imageField[from.row][from.col].setVisibility(View.INVISIBLE);
            default:
                break;
        }
    }
}