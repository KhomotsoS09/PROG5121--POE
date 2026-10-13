package com.mycompany.loginapp;

public class Login {
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital =!password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-={}:;\"'<>,.?/].*");
        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }
    public boolean checkCellPhoneNumber(String cell) {
        return cell.matches("^\\+27\\d{9}$");
    }
    public String registerUser(String username, String password, String cell) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "User registered successfully.";
    }
    public boolean loginUser(String enteredUser, String enteredPass, String realUser, String realPass) {
        return enteredUser.equals(realUser) && enteredPass.equals(realPass);
    }
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Successful login";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
