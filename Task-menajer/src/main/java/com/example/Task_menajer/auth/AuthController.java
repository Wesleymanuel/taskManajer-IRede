package com.example.Task_menajer.auth;

import com.example.Task_menajer.DTOs.SingInUserRequestDTO;
import com.example.Task_menajer.DTOs.SingInUserResponseDTO;
import com.example.Task_menajer.DTOs.SingUpUserRequestDTO;
import com.example.Task_menajer.DTOs.SingUpUserResponseDTO;
import com.example.Task_menajer.domain.entitys.User;
import com.example.Task_menajer.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authentication;
    private final JwtConfig jwtConfig;

    AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authentication, JwtConfig jwtConfig){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authentication = authentication;
        this.jwtConfig = jwtConfig;
    }

    @Operation(
            summary = "cadastra usuario",
            description = "registra usuario na aplicao",
            method = "POST"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "resgistra usuario com sucesso na aplicao",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SingUpUserRequestDTO.class)
                    )
            )
    })
    @PostMapping("/register")
    public ResponseEntity<SingUpUserResponseDTO> singUp(
            @Valid
            @RequestBody SingUpUserRequestDTO dto
    ){
        User newUser = new User();
        newUser.setName(dto.userName());
        newUser.setEmail(dto.userEmail());
        newUser.setPassword(passwordEncoder.encode(dto.userPassword()));

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SingUpUserResponseDTO(newUser.getUser_id(), newUser.getName(), newUser.getEmail()));
    }


    @Operation(
            summary = "login do usuario",
            description = "recebe as informacoes para realizar login e enviar token de autenticacao",
            method = "POST"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "usuario realizou login com sucesso e possui o token de autenticacao",
                    content = @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = SingInUserResponseDTO.class)
                    )
            )
    })
    @PostMapping("/login")
    public ResponseEntity<SingInUserResponseDTO> singIn(
            @Valid
            @RequestBody SingInUserRequestDTO dto
    ){
        UsernamePasswordAuthenticationToken emailAndPass = new UsernamePasswordAuthenticationToken(dto.userEmail(),dto.userPassword());
        Authentication authentication1 = authentication.authenticate(emailAndPass);

        User user = (User) authentication1.getPrincipal();
        String token = jwtConfig.generateToken(user);
        return ResponseEntity.ok(new SingInUserResponseDTO(token));
    }
}
