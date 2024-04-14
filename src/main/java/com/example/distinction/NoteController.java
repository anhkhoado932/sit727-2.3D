package com.example.distinction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("")
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return "notes";
    }

    @PostMapping("/new")
    public ResponseEntity<?> insertNote(@RequestBody NoteRequest noteRequest) {
        try {
            Note note = new Note();
            note.setTitle(noteRequest.getTitle());
            note.setContent(noteRequest.getContent());
            note.setCreatedAt(new Date());

            Note savedNote = noteService.save(note);
            return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to insert note.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
