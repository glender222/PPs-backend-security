package com.example.demo.login.Encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryption {
public static void main(String[] args) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = "prueba";
    String encodedPassword = encoder.encode(rawPassword);
    System.out.println(encodedPassword);;;
    }
}


// admin 
// prueba
// $2a$10$pMoYgksR31dTVmkCNEWMHu3aVhzvb0B55Bke936dcAZyil2xuS31e
// Leroy.silvaS
// agua
// $2a$10$94EtEuat6LDPIWaMntRaA.lw7FPsuePzuOpTU0fFE0eiW3r3hvCli