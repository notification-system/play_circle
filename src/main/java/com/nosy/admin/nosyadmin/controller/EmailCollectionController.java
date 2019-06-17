package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.dto.EmailCollectionDto;
import com.nosy.admin.nosyadmin.dto.EmailCollectionFileEncodedDto;
import com.nosy.admin.nosyadmin.exceptions.EmailCollectionDoesNotExistException;
import com.nosy.admin.nosyadmin.service.EmailCollectionService;
import com.nosy.admin.nosyadmin.utils.EmailCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PostMapping
    public ResponseEntity<EmailCollectionDto> uploadEmailCollection(
            @RequestBody EmailCollectionFileEncodedDto emailCollectionFileEncodedDto,
            Principal principal) {
        emailCollectionFileEncodedDto.setEmails(emailCollectionService.parseBase64Data(emailCollectionFileEncodedDto.getData()));
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService
                .createEmailCollection(emailCollectionFileEncodedDto, principal.getName())), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EmailCollectionDto> replaceEmailCollection(
            @RequestBody EmailCollectionFileEncodedDto emailCollectionFileEncodedDto) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService
        .replaceEmailCollection(emailCollectionFileEncodedDto)), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<EmailCollectionDto> addToEmailCollection(
            @RequestBody EmailCollectionFileEncodedDto emailCollectionFileEncodedDto) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService
                .addToEmailCollection(emailCollectionFileEncodedDto)), HttpStatus.OK);
    }

    @PostMapping(value = "/list")
    public ResponseEntity<EmailCollectionDto> createEmailCollection(
            @RequestBody EmailCollectionFileEncodedDto emailCollectionFileEncodedDto,
            Principal principal) {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService
                .createEmailCollection(emailCollectionFileEncodedDto, principal.getName())), HttpStatus.CREATED);    }

    @GetMapping(value = "/{emailCollectionId}")
    public ResponseEntity<EmailCollectionDto> getEmailCollectionById(
            @PathVariable String emailCollectionId) {
        try {
            return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDto(emailCollectionService
                    .getEmailCollectionById(emailCollectionId)), HttpStatus.OK);
        } catch (EmailCollectionDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmailCollectionDto>> getAllEmailCollections() {
        return new ResponseEntity<>(EmailCollectionMapper.INSTANCE.toEmailCollectionDtoList(emailCollectionService
                .getAllEmailCollections()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{emailCollectionId}")
    public ResponseEntity<String> deleteEmailCollectionById(
            @PathVariable String emailCollectionId) {
        emailCollectionService.deleteEmailCollectionById(emailCollectionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
