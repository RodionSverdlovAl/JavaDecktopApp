package Server.Controllers;

public interface IRegistration {
    public String registration(String name, String login, String email, String password);
    public String generateRandomSalt(int length);
}
