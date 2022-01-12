package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.StatGetSumResponseDTO;
import org.example.dto.StatResponseDTO;
import org.example.manager.StatManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register_positions")
@RequiredArgsConstructor
public class StatController {
    private final StatManager manager;
    @GetMapping ("/getAll")
    public StatResponseDTO getAll(){
     return manager.getAll();
    }
    @GetMapping("/getSum")
public StatGetSumResponseDTO getSum(@RequestParam long courseId){
        return manager.getSum(courseId);
}


}
