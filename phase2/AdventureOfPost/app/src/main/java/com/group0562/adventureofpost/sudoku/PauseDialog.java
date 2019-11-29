package com.group0562.adventureofpost.sudoku;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.group0562.adventureofpost.R;
import com.group0562.adventureofpost.auth.ui.LoginActivity;

public class PauseDialog extends AppCompatDialogFragment {
    @NonNull


    private PauseDialogListener listener;
    private final String RETURN_NO_SAVE = "RETURN_NO_SAVE";
    private final String RETURN_SAVE = "RETURN_SAVE";
    private final String RESUME = "RESUME";
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sudoku_pause, null);

        builder.setView(view)
                .setTitle("")
                .setNegativeButton("Exit without Save", (dialog, which) -> listener.saveGame(RETURN_NO_SAVE))
                .setNeutralButton("Exit with Save", (dialog, which) -> listener.saveGame(RETURN_SAVE))
                .setPositiveButton("Resume game", (dialog, which) -> listener.saveGame(RESUME));
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

    public interface PauseDialogListener{
        void saveGame(String mode);
    }
}
