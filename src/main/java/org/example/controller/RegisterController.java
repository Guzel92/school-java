package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.manager.RegisterManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegisterController {
private final RegisterManager manager;
@GetMapping("/getAll")
    public RegisterResponseDTO getAll(){
    return manager.getAll();
}

@GetMapping("/getById")
public RegisterGetByIdResponseDTO getById(@RequestParam long id){
    return manager.getById(id);
}
@PostMapping("/save")
    public RegisterSaveResponseDTO save(@RequestBody RegisterSaveRequestDTO requestDTO){
    return manager.save(requestDTO);
}
@GetMapping("/getParticipants")
public ParticipantsDTO getParticipants(@RequestParam long id){
    return manager.getParticipants(id);
}

}
