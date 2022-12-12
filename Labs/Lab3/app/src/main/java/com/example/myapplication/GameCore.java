package com.example.myapplication;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameCore {
    private static Position currentMoveFrom = new Position(-1, -1);
    private static Position currentMoveTo = new Position(-1, -1);

    // field constants
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BLUE = 3;
    public static final int MAG = 4;
    private static final int TOTALCOL = 4;

    //gameplay parameters
    public static final int RANDPERMOVE = 3;
    private static final int BALLSONSTART = 24;
    private static final int FIELDSIZE = 8;
    private static final int SEQMIN = 5;

    private Ball[] nextBalls;
    private Integer[][] field;
    private Graph<Position> graph;
    private int score;

    public GameCore() {
        nextBalls = new Ball[RANDPERMOVE];
        this.field = new Integer[FIELDSIZE][FIELDSIZE];
        for (int i = 0; i < FIELDSIZE; ++i)
            Arrays.fill(field[i], 0);

        List<Position> pos = new ArrayList<>();
        for (int i = 0; i < FIELDSIZE; ++i)
            for (int j = 0; j < FIELDSIZE; ++j)
                pos.add(new Position(i, j));

        graph = new Graph<>(pos);
        for (int i = 0; i < FIELDSIZE; ++i)
            for (int j = 0; j < FIELDSIZE; ++j) {
                if (i > 0)
                    graph.addEdge(new Position(i, j), new Position(i - 1, j));
                if (i < FIELDSIZE - 1)
                    graph.addEdge(new Position(i, j), new Position(i + 1, j));
                if (j > 0)
                    graph.addEdge(new Position(i, j), new Position(i, j - 1));
                if (j < FIELDSIZE - 1)
                    graph.addEdge(new Position(i, j), new Position(i, j + 1));
            }

        for (int i = 0; i < BALLSONSTART; ++i) {
            Ball ball = randomizeBall();
            addBall(ball.p, ball.color);
        }
        for (int i = 0; i < RANDPERMOVE; ++i) {
            nextBalls[i] = randomizeBall();
            field[nextBalls[i].p.row][nextBalls[i].p.col] = -nextBalls[i].color;
        }
    }

    public int getScore() {
        return this.score;
    }

    public Integer[][] getField() {
        return this.field;
    }

    public Ball[] getNextBalls() {
        return nextBalls;
    }

    private boolean isFull() {
        boolean hasSpace = false;
        for (int i = 0; i < FIELDSIZE; ++i) {
            for (int j = 0; j < FIELDSIZE; ++j)
                if (field[i][j] <= 0) {
                    hasSpace = true;
                    break;
                }
            if (hasSpace)
                break;
        }
        return !hasSpace;
    }

    private Ball randomizeBall() {
        Random random = new Random();
        int color = 1 + random.nextInt(TOTALCOL);
        if (!this.hasZero()) {
            if (this.lastRand())
                nextBalls[RANDPERMOVE - 2] = new Ball(1 + random.nextInt(TOTALCOL), currentMoveTo);
            return new Ball(color, currentMoveFrom);
        }
        int r = random.nextInt(8);
        int c = random.nextInt(8);
        while (field[r][c] != 0) {
            r = random.nextInt(8);
            c = random.nextInt(8);
        }
        return new Ball(color, new Position(r, c));
    }

    public GameStates moveBall(Position from, Position to) {
        if (from.equals(to))
            return GameStates.REMOVESELECT;

        if (field[from.row][from.col] == 0)
            return GameStates.NOBALL;

        currentMoveFrom = from;
        currentMoveTo = to;
        int color = field[from.row][from.col];
        removeBall(from);

        if (!graph.DFS(from, to)) {
            addBall(from, color);
            return GameStates.INVALIDPATH;
        }

        for (int i = 0; i < RANDPERMOVE; ++i)
            addBall(nextBalls[i].p, nextBalls[i].color);

        addBall(to, color);

        updateField();

        if (this.isFull())
            return GameStates.ENDGAME;

        for (int i = 0; i < RANDPERMOVE; ++i) {
            nextBalls[i] = randomizeBall();
            field[nextBalls[i].p.row][nextBalls[i].p.col] = -nextBalls[i].color;
        }

        return GameStates.SUCCESS;
    }

    private void updateField() {
        boolean[][] deletionMatrix = new boolean[FIELDSIZE][FIELDSIZE];
        int currentColor = 0;
        int seq = 0;
        Position last = new Position(-1, -1);

        //checking rows
        for (int i = 0; i < FIELDSIZE; ++i) {
            seq = 0;
            currentColor = 0;
            for (int j = 0; j < FIELDSIZE; ++j) {
                if (currentColor != field[i][j]) {
                    if (seq >= SEQMIN)
                        updateUtilRow(deletionMatrix, last, seq);

                    currentColor = field[i][j];
                    seq = currentColor <= 0 ? 0 : 1;
                } else if (currentColor != 0) {
                    ++seq;
                    last.col = j;
                    last.row = i;
                }
            }
            if (seq >= SEQMIN)
                updateUtilRow(deletionMatrix, last, seq);
        }

        //checking columns
        for (int j = 0; j < FIELDSIZE; ++j) {
            seq = 0;
            currentColor = 0;
            for (int i = 0; i < FIELDSIZE; ++i) {
                if (currentColor != field[i][j]) {
                    if (seq >= SEQMIN)
                        updateUtilCol(deletionMatrix, last, seq);

                    currentColor = field[i][j];
                    seq = currentColor <= 0 ? 0 : 1;
                } else if (currentColor != 0) {
                    ++seq;
                    last.col = j;
                    last.row = i;
                }
            }
            if (seq >= SEQMIN)
                updateUtilCol(deletionMatrix, last, seq);
        }

        //diagonal from 70 to 07
        seq = 0;
        for (int sum = SEQMIN - 1; sum <= 2 * FIELDSIZE - SEQMIN + 1; ++sum) {
            int i, j;
            if (sum <= FIELDSIZE - 1) {
                j = 0;
                i = sum - j;
            } else {
                i = FIELDSIZE - 1;
                j = sum - i;
            }

            seq = 0;
            currentColor = 0;
            while (i >= 0 && j < FIELDSIZE && j >= 0 && i < FIELDSIZE) {
                if (currentColor != field[i][j]) {
                    if (seq >= SEQMIN)
                        updateUtilDiag70to07(deletionMatrix, last, seq);

                    currentColor = field[i][j];
                    seq = currentColor <= 0 ? 0 : 1;
                } else if (currentColor != 0) {
                    ++seq;
                    last.col = j;
                    last.row = i;
                }
                --i;
                ++j;
            }
            if (seq >= SEQMIN)
                updateUtilDiag70to07(deletionMatrix, last, seq);
        }

        //diagonal from 77 to 00
        seq = 0;
        for (int sum = 0; sum <= FIELDSIZE - SEQMIN + 1; ++sum) {
            int i = 0;
            int j = sum - i;
            seq = 0;
            currentColor = 0;
            while (j < FIELDSIZE && i < FIELDSIZE) {
                if (currentColor != field[i][j]) {
                    if (seq >= SEQMIN)
                        updateUtilDiag77to00(deletionMatrix, last, seq);

                    currentColor = field[i][j];
                    seq = currentColor <= 0 ? 0 : 1;
                } else if (currentColor != 0) {
                    ++seq;
                    last.col = j;
                    last.row = i;
                }
                ++j;
                ++i;
            }
            if (seq >= SEQMIN)
                updateUtilDiag77to00(deletionMatrix, last, seq);

            j = 0;
            i = sum - j;
            seq = 0;
            currentColor = 0;
            while (j < FIELDSIZE && i < FIELDSIZE) {
                if (currentColor != field[i][j]) {
                    if (seq >= SEQMIN)
                        updateUtilDiag77to00(deletionMatrix, last, seq);

                    currentColor = field[i][j];
                    seq = currentColor <= 0 ? 0 : 1;
                } else if (currentColor != 0) {
                    ++seq;
                    last.col = j;
                    last.row = i;
                }
                ++j;
                ++i;
            }
            if (seq >= SEQMIN)
                updateUtilDiag77to00(deletionMatrix, last, seq);
        }

        int totalBalls = 0;
        for (int i = 0; i < FIELDSIZE; ++i)
            for (int j = 0; j < FIELDSIZE; ++j)
                if (deletionMatrix[i][j]) {
                    ++totalBalls;
                    removeBall(new Position(i, j));
                }

        score += 5 * totalBalls;
    }

    private void updateUtilRow(boolean[][] deletionMatrix, Position last, int seq) {
        int r = last.row;
        int c = last.col;
        while (seq != 0) {
            deletionMatrix[r][c] = true;
            --c;
            --seq;
        }
    }

    private void updateUtilCol(boolean[][] deletionMatrix, Position last, int seq) {
        int r = last.row;
        int c = last.col;
        while (seq != 0) {
            deletionMatrix[r][c] = true;
            --r;
            --seq;
        }
    }

    private void updateUtilDiag70to07(boolean[][] deletionMatrix, Position last, int seq) {
        int r = last.row;
        int c = last.col;
        while (seq != 0) {
            deletionMatrix[r][c] = true;
            --c;
            ++r;
            --seq;
        }
    }

    private void updateUtilDiag77to00(boolean[][] deletionMatrix, Position last, int seq) {
        int r = last.row;
        int c = last.col;
        while (seq != 0) {
            deletionMatrix[r][c] = true;
            --c;
            --r;
            --seq;
        }
    }

    private void addBall(Position p, int color) {
        int r = p.row;
        int c = p.col;
        field[r][c] = color;

        if (r > 0)
            graph.removeEdge(p, new Position(r - 1, c));
        if (r < FIELDSIZE - 1)
            graph.removeEdge(p, new Position(r + 1, c));
        if (c > 0)
            graph.removeEdge(p, new Position(r, c - 1));
        if (c < FIELDSIZE - 1)
            graph.removeEdge(p, new Position(r, c + 1));
    }

    private void removeBall(Position p) {
        int r = p.row;
        int c = p.col;
        this.field[r][c] = 0;

        if (r > 0 && field[r - 1][c] <= 0)
            graph.addEdge(p, new Position(r - 1, c));
        if (r < FIELDSIZE - 1 && field[r + 1][c] <= 0)
            graph.addEdge(p, new Position(r + 1, c));
        if (c > 0 && field[r][c - 1] <= 0)
            graph.addEdge(p, new Position(r, c - 1));
        if (c < FIELDSIZE - 1 && field[r][c + 1] <= 0)
            graph.addEdge(p, new Position(r, c + 1));
    }

    private boolean hasZero() {
        for (int i = 0; i < FIELDSIZE; ++i)
            for (int j = 0; j < FIELDSIZE; ++j)
                if (field[i][j] == 0) {
                    return true;
                }
        return false;
    }

    private boolean lastRand() {
        int count = 0;
        for (int i = 0; i < FIELDSIZE; ++i)
            for (int j = 0; j < FIELDSIZE; ++j)
                if (field[i][j] <= 0) {
                    ++count;
                }
        return count == 1;
    }
}
