package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anna on 2018-07-01.
 */
//tells Spring to bootstrap the repository during component scan
@Repository
// extending interface gives basic crud operations like save, delete, count etc.
public interface NoteRepository extends JpaRepository<Note, Long> {
}
