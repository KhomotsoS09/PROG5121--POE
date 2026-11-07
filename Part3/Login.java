public class Login {
    private String username;
    private String password;
    private String cellphone;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8
            && password.matches(".*[A-Z].*")
            && password.matches(".*[0-9].*")
            && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    public boolean checkCellPhoneNumber(String cell) {
        return cell.length() == 12 && cell.startsWith("+27");
    }

    public String registerUser(String username, String password, String cell) {
        this.username = username;
        this.password = password;
        this.cellphone = cell;
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Registration successful.";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    public String returnLoginStatus(boolean success, String firstName, String lastName) {
        if (success) return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        return "Username or password incorrect, please try again.";
    }
}
