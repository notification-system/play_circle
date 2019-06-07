package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.exceptions.*;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.repository.InputSystemRepository;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class InputSystemService {

  private InputSystemRepository inputSystemRepository;
  private UserRepository userRepository;

  @Autowired
  public InputSystemService(
      InputSystemRepository inputSystemRepository, UserRepository userRepository) {
    this.inputSystemRepository = inputSystemRepository;
    this.userRepository = userRepository;
  }

  public Set<InputSystem> getListOfInputSystems(String username) {

    Optional<User> optionalUserRepository = userRepository.findById(username);
    if (!optionalUserRepository.isPresent()) {
      throw new NotAuthenticatedException();
    }
    return optionalUserRepository.get().getInputSystem();
  }

  public void deleteInputSystem(String inputSystemId, String email) {
    InputSystem checkInputSystem = inputSystemRepository.findByIdAndEmail(email, inputSystemId);
    if (checkInputSystem == null) {
      throw new InputSystemNotFoundException();
    }
    if (checkInputSystem.getEmailTemplate()!=null && !checkInputSystem.getEmailTemplate().isEmpty()) {
      throw new InputSystemHasChildrenException();
    }
    inputSystemRepository.deleteById(inputSystemId);
  }

  public InputSystem saveInputSystem(InputSystem inputSystem, String email) {
    if (inputSystem.getInputSystemName() == null
        || inputSystem.getInputSystemName().trim().equals("")) {
      throw new InputSystemNameIsMandatoryException();
    }
    InputSystem inputSystem1 =
        inputSystemRepository.findByInputSystemNameAndEmail(
            email, inputSystem.getInputSystemName());
    if ((inputSystem1 != null)) {
      throw new InputSystemAlreadyExistsException();
    }
    Optional<User> user = userRepository.findById(email);
    if (!user.isPresent()) {
      throw new UserNotExistsException();
    }

    inputSystem.setUser(user.get());
    return inputSystemRepository.save(inputSystem);
  }

  public InputSystem updateInputSystemStatus(
      String inputSystemId, InputSystem inputSystem, String email) {

    InputSystem checkInputSystem = inputSystemRepository.findByIdAndEmail(email, inputSystemId);

    if (checkInputSystem == null) {
      throw new InputSystemNotFoundException();
    }
    InputSystem checkDuplicate =
        inputSystemRepository.findByInputSystemNameAndEmail(
            email, inputSystem.getInputSystemName());
    if (checkDuplicate != null) {
      throw new InputSystemAlreadyExistsException();
    }
    checkInputSystem.setInputSystemName(inputSystem.getInputSystemName());

    return inputSystemRepository.save(checkInputSystem);
  }
}
