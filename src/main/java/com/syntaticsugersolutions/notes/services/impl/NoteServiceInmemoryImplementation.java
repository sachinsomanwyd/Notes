package com.syntaticsugersolutions.notes.services.impl;

import com.syntaticsugersolutions.notes.exceptions.NoteNotFoundException;
import com.syntaticsugersolutions.notes.models.Note;
import com.syntaticsugersolutions.notes.services.NoteService;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class NoteServiceInmemoryImplementation implements NoteService {

    private Map<Long,Note> db = new HashMap<>();
    private static Long id =0l;

    @Override
    public List<Note> getNotes() {
        List<Note> list = new ArrayList<>();
        db.forEach((k,v)-> list.add(v));
        return list;
    }

    @Override
    public Note getNoteById(Long id) throws NoteNotFoundException {
        if(db.get(id) != null){
            return db.get(id);
        }
        throw new NoteNotFoundException("Note with id : "+id+" not found!");
    }

    @Override
    public Note createNote(Note note) {
        note.setId(++id);
        db.put(id,note);
        return note;
    }

    @Override
    public Note updateNote(Long id, Note note) throws NoteNotFoundException{
        if(db.get(id) !=null){
            note.setCreatedAt(db.get(id).getCreatedAt());
            note.setId(id);
            note.setModifiedAt(new Date());
            db.put(id, note);
            return note;
        }
        throw new NoteNotFoundException("Note with id "+id+" not found!");
    }

    @Override
    public String deleteNote(Long id) throws NoteNotFoundException{
        if(db.get(id) != null){
            Note note = db.remove(id);
            return "Note "+note.getId() + " deleted!";
        }
        throw new NoteNotFoundException("Note with id "+id+" not found!");
    }
}
