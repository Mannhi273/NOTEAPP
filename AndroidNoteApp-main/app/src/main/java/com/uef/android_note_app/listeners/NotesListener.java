package com.uef.android_note_app.listeners;

import com.uef.android_note_app.entities.Note;

public interface NotesListener {
    void onNoteClicked( Note note, int position);
}
