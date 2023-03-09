package com.lucascastro.saladereuniao.service;

import com.lucascastro.saladereuniao.exception.ResourceNotFoundException;
import com.lucascastro.saladereuniao.model.Room;
import com.lucascastro.saladereuniao.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    private final RoomRepository roomRepository;


    public List<Room> getAllRooms()
    {
        return roomRepository.findAll();
    }

    public ResponseEntity<Room> getRoomById(@PathVariable long roomId) throws ResourceNotFoundException
    {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: "+roomId));
        return ResponseEntity.ok().body(room);
    }

    public Room createRoom (@Valid @RequestBody Room room)
    {
        return roomRepository.save(room);
    }


    public ResponseEntity<Room> updateRoom(@PathVariable long roomId,@Valid @RequestBody Room roomDatails) throws ResourceNotFoundException
    {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id: "+roomId ));
     room.setName(roomDatails.getName());
     room.setDate(roomDatails.getDate());
     room.setStartHour(roomDatails.getStartHour());
      room.setEndHour(roomDatails.getEndHour());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    public Map<String,Boolean> deleteRoom(@PathVariable long roomId) throws ResourceNotFoundException
    {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResolutionException("Room not found with id:" +roomId));
        roomRepository.delete(room);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",true);
        return response;

    }

}
