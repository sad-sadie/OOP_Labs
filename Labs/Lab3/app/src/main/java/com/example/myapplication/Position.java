package com.example.myapplication;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class Position {
    public int row;
    public int col;

    public Position(int row, int col) {
        this.col = col;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    // TODO
    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(row, col);
        }
        return -1;
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
