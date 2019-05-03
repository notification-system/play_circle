package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.config.security.ClientToken;
import com.nosy.admin.nosyadmin.config.security.KeycloakClient;
import com.nosy.admin.nosyadmin.dto.EmailTemplateDto;
import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.dto.UserDto;
import com.nosy.admin.nosyadmin.model.EmailProviderProperties;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.service.EmailTemplateService;
import com.nosy.admin.nosyadmin.service.InputSystemService;
import com.nosy.admin.nosyadmin.service.UserService;
import com.nosy.admin.nosyadmin.utils.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RequestMapping("/api/v1/nosy")


public class EmailAdminController {
    private EmailTemplateService emailTemplateService;
    private InputSystemService inputSystemService;
    private UserService userService;
    private Conversion conversion;
    @Autowired
    private KeycloakClient keycloakClient;

    @Autowired
    public EmailAdminController(Conversion conversion, EmailTemplateService emailTemplateService, InputSystemService inputSystemService, UserService userService) {
        this.emailTemplateService = emailTemplateService;
        this.inputSystemService = inputSystemService;
        this.userService = userService;
        this.conversion = conversion;
    }

    @PostMapping(value = "/inputsystems/{inputSystemId}/emailtemplates/{emailTemplateId}/post")
    public ResponseEntity<EmailTemplate> emailTemplatePost(@PathVariable String inputSystemId,
                                                           @PathVariable String emailTemplateId,
                                                           @RequestBody EmailProviderProperties emailProviderProperties,

                                                           Principal principal) {

        return new ResponseEntity<>(emailTemplateService.postEmailTemplate(inputSystemId, emailTemplateId,
                emailProviderProperties, principal.getName()), HttpStatus.OK);
    }


    @PostMapping(value = "/inputsystems", consumes = "application/json")
    public InputSystem newType(@RequestBody InputSystemDto inputSystemDto, Principal principal) {
        return inputSystemService.saveInputSystem(conversion.convertToInputSystem(inputSystemDto), principal.getName());
    }

    @GetMapping(value = "/inputsystems")

    public ResponseEntity<Set<InputSystem>> getInputSystems(Principal principal) {
        return new ResponseEntity<>(inputSystemService.getListOfInputSystems(principal.getName()), HttpStatus.OK);
    }

    @GetMapping(value = "/inputsystems/emailproviders")

    public ResponseEntity<List<String>> getEmailAllProviders(Principal principal) {
        return new ResponseEntity<>(emailTemplateService.getAllEmailProviders(), HttpStatus.OK);
    }


    @PutMapping(value = "/inputsystems/{inputSystemId}")

    public ResponseEntity<InputSystem> updateInputSystemName(@PathVariable String inputSystemId,
                                                             @RequestBody InputSystemDto inputSystemDto, Principal principal) {

        return new ResponseEntity<>(inputSystemService.updateInputSystemStatus(inputSystemId,
                conversion.convertToInputSystem(inputSystemDto), principal.getName())
                , HttpStatus.OK);

    }


    @PostMapping(value = "/inputsystems/{inputSystemId}/emailtemplates")

    public ResponseEntity<EmailTemplate> newEmailTemplate(@PathVariable String inputSystemId,
                                                          @RequestBody EmailTemplateDto emailTemplateDto, Principal principal) {
        return new ResponseEntity<>(emailTemplateService.newEmailTemplate(conversion.convertToEmailTemplate(emailTemplateDto),
                inputSystemId, principal.getName()), HttpStatus.CREATED);

    }


    @GetMapping(value = "/inputsystems/{inputSystemId}/emailtemplates/{emailTemplateId}")

    public ResponseEntity<EmailTemplate> getEmailTemplateByInputSystemAndEmailTemplateId(
            @PathVariable String inputSystemId,
            @PathVariable String emailTemplateId, Principal principal) {
        return new ResponseEntity<>(emailTemplateService.getEmailTemplateById(emailTemplateId, inputSystemId, principal.getName()), HttpStatus.OK);
    }

    @PutMapping(value = "/inputsystems/{inputSystemId}/emailtemplates/{emailTemplateId}")

    public ResponseEntity<EmailTemplate> updateEmailTemplate(@PathVariable String inputSystemId, @PathVariable String emailTemplateId,
                                                             @RequestBody EmailTemplateDto emailTemplateDto, Principal principal
    ) {
        return new ResponseEntity<>(emailTemplateService.updateEmailTemplate(conversion.convertToEmailTemplate(emailTemplateDto),
                inputSystemId, emailTemplateId, principal.getName()), HttpStatus.OK);


    }


    @GetMapping(value = "/inputsystems/{inputSystemId}/emailtemplates")

    public ResponseEntity<List<EmailTemplate>> getEmailTemplates(@PathVariable String inputSystemId, Principal principal) {
        return new ResponseEntity<>(emailTemplateService.getListOfEmailTemplates(inputSystemId, principal.getName()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/inputsystems/{inputSystemId}/emailtemplates/{emailTemplateId}")
    public ResponseEntity<String> deleteEmailTemplate(@PathVariable String inputSystemId, @PathVariable String emailTemplateId, Principal principal) {
        emailTemplateService.deleteEmailTemplate(inputSystemId, emailTemplateId, principal.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(value = "/inputsystems/{inputSystemId}")
    public ResponseEntity<String> deleteInputSystem(@PathVariable String inputSystemId, Principal principal) {
        inputSystemService.deleteInputSystem(inputSystemId, principal.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @GetMapping(path = "/auth/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        userService.logoutUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping(path = "/auth/status")
    public ResponseEntity<Boolean> isAuthenticated(@RequestBody String token) throws IOException {
        return new ResponseEntity<>(keycloakClient.isAuthenticated(token), HttpStatus.OK);

    }

    @PostMapping(value = "/auth/token")
    public ResponseEntity<ClientToken> getToken(@RequestBody @Valid UserDto userdto) throws IOException {
        return new ResponseEntity<>(keycloakClient.getTokens(conversion.convertToUser(userdto)), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> newUser(@RequestBody @Valid UserDto userdto) {
        return new ResponseEntity<>(userService.addUser(conversion.convertToUser(userdto)), HttpStatus.OK);
    }


    @DeleteMapping(value = "/users/profile")
    public ResponseEntity<String> deleteUsername(HttpServletRequest request) {
        userService.deleteUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(value = "/users/profile")
    public ResponseEntity<User> getUserProfile(HttpServletRequest request) {

        return new ResponseEntity<>(userService.getUserInfo(request), HttpStatus.OK);
    }


}
