package com.example.baity.Model;

public class Verify_request {
    String email;
    String verificationcode;

    public Verify_request(String email, String verificationcode) {
        this.email = email;
        this.verificationcode = verificationcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }
}
