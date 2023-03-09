package com.lucascastro.saladereuniao.controller;

import com.lucascastro.saladereuniao.exception.ResourceNotFoundException;
import com.lucascastro.saladereuniao.model.Room;
import com.lucascastro.saladereuniao.repository.RoomRepository;
import com.lucascastro.saladereuniao.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@CrossOrigin permite o front consumir
@RestController @CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAllRooms()
    {
        return roomService.getAllRooms();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable long id) throws ResourceNotFoundException
    {
       return roomService.getRoomById(id);
    }

    @PostMapping("/rooms")
    public Room createRoom (@Valid @RequestBody Room room)
    {
        return roomService.createRoom(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable long id,@Valid @RequestBody Room roomDatails) throws ResourceNotFoundException
    {
     return roomService.updateRoom(id,roomDatails);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String,Boolean> deleteRoom(@PathVariable long id) throws ResourceNotFoundException
    {
      return roomService.deleteRoom(id);

    }

}
