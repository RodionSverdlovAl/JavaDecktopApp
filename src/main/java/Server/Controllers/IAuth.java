package Server.Controllers;

import Server.Entity.Account;

public interface IAuth {
    public Account authentication(String login, String password);
}
