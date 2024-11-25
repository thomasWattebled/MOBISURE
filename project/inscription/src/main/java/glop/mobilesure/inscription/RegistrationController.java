package glop.mobilesure.inscription;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
    @RequestMapping("/api/registration")
    public class RegistrationController {

        private final UserRepository userRepository;

        public RegistrationController(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @PostMapping
        public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
            userRepository.save(user); // Enregistrer l'utilisateur dans la base de données
            return new ResponseEntity<>("Utilisateur enregistré avec succès", HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity<List<UserDTO>> getAllUsers() {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }
    }