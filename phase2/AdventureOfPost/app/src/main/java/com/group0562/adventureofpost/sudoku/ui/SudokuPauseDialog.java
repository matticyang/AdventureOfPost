package com.group0562.adventureofpost.sudoku.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.group0562.adventureofpost.R;

/**
 * Dialog that pops up when user presses pause button in game.
 */
public class SudokuPauseDialog extends AppCompatDialogFragment {

    private PauseDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sudoku_pause, null);

        builder.setView(view)
                .setTitle("")
                .setNegativeButton("Exit without Save", (dialog, which) -> listener.saveGame(Modes.EXIT_NO_SAVE))
                .setNeutralButton("Exit with Save", (dialog, which) -> listener.saveGame(Modes.EXIT_SAVE))
                .setPositiveButton("Resume game", (dialog, which) -> listener.saveGame(Modes.RESUME));
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (PauseDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement PauseDialogListener");
        }
    }

    interface PauseDialogListener {
        void saveGame(Modes mode);
    }

    enum Modes {
        EXIT_NO_SAVE, RESUME, EXIT_SAVE
    }
}
