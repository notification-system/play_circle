package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.dto.EmailCollectionDto;
import com.nosy.admin.nosyadmin.service.EmailCollectionService;
import com.nosy.admin.nosyadmin.utils.EmailCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping("/api/v1/nosy/emailgroups")
public class EmailCollectionController {
    private EmailCollectionService emailCollectionService;

    @Autowired
    public EmailCollectionController(EmailCollectionService emailCollectionService) {
        this.emailCollectionService = emailCollectionService;
    }

    @PostMapping(value = "/inputsystems/upload/{inputSystemId}/{name}", consumes = "multipart/form-data")
    public ResponseEntity<EmailCollectionDto> uploadMultipart(
            @RequestParam("file") MultipartFile file,
            @PathVariable String inputSystemId,
            @PathVariable String name) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService.parseEmailCollection(file, inputSystemId, name)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/inputsystems/{inputSystemId}/{name}")
    public ResponseEntity<EmailCollectionDto> createGroup(
            @RequestBody List<String> emails,
            @PathVariable String inputSystemId,
            @PathVariable String name) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService.createEmailCollection(emails, inputSystemId, name)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/inputsystems/{inputSystemId}")
    public ResponseEntity<List<EmailCollectionDto>> getAllEmailCollectionsByInputSystemId(
            @PathVariable String inputSystemId) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDtoList(emailCollectionService.getAllEmailCollectionsByInputSystemId(inputSystemId)), HttpStatus.OK);
    }

    @GetMapping(value = "/inputsystems/")
    public ResponseEntity<List<EmailCollectionDto>> getAllEmailCollections() {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDtoList(emailCollectionService.getAllEmailCollections()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/inputsystems/{emailCollectionId}")
    public ResponseEntity<String> deleteEmailCollectionById(
            @PathVariable String emailCollectionId) {
        emailCollectionService.deleteEmailCollectionById(emailCollectionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
