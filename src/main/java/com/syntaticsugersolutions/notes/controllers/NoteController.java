package com.syntaticsugersolutions.notes.controllers;

import com.syntaticsugersolutions.notes.exceptions.NoteNotFoundException;
import com.syntaticsugersolutions.notes.models.Note;
import com.syntaticsugersolutions.notes.services.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("/health")
    public String getServiceHealth(){
        return "{Status : UP}";
    }

    @GetMapping("/note")
    public List<Note> getAllNotes(){
        return noteService.getNotes();
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) throws NoteNotFoundException{

        Note note = noteService.getNoteById(id);
        return new ResponseEntity<Note>(note, HttpStatus.OK);


    }

    @PostMapping("/note")
    public Note createNote(@RequestBody Note note){
        note.setCreatedAt(new Date());
        note.setModifiedAt(new Date());
        return noteService.createNote(note);
    }

    @PutMapping("/note/{id}")
    public Note updateNote(@PathVariable Long id,@RequestBody Note updatedNote)throws NoteNotFoundException{
        return noteService.updateNote(id, updatedNote) ;
    }


    @DeleteMapping("/note/{id}")
    public String deleteNote(@PathVariable Long id)throws NoteNotFoundException{

        return noteService.deleteNote(id);
    }


}

