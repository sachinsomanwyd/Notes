package com.syntaticsugersolutions.notes.services;

import com.syntaticsugersolutions.notes.exceptions.NoteNotFoundException;
import com.syntaticsugersolutions.notes.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NoteService {

    public List<Note> getNotes();

    public Note getNoteById(Long id) throws NoteNotFoundException;

    public Note createNote(Note note);

    public Note updateNote(Long id,Note note) throws NoteNotFoundException;

    public String deleteNote(Long id) throws NoteNotFoundException;
}
