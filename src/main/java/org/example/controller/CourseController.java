package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CourseGetAllResponseDTO;
import org.example.dto.CourseGetByIdResponseDTO;
import org.example.dto.CourseSaveRequestDTO;
import org.example.dto.CourseSaveResponseDTO;
import org.example.manager.CourseManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseManager manager;

    @GetMapping("/getAll")
    public CourseGetAllResponseDTO getAll(){
        return manager.getAll();
    }
    @GetMapping("/getById")
    public CourseGetByIdResponseDTO getById(@RequestParam long id){
        return manager.getById(id);
    }
    @PostMapping("/save")
    public CourseSaveResponseDTO save(@RequestBody CourseSaveRequestDTO requestDTO){
        return manager.save(requestDTO);
    }
    @PostMapping("/removeById")
    public void removeByIdFromParam(@RequestParam long id) {
        manager.removeById(id);
    }

}
