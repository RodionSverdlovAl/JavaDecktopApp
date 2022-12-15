package Server.Controllers;

import Server.Entity.Account;
import Server.Services.DataBase.DataBaseHandlerAccounts;
import Server.Services.DeEnCode;

public class AuthController implements IAuth{
    @Override
    public Account authentication(String login, String password) {
        DataBaseHandlerAccounts dbHandler = new DataBaseHandlerAccounts();
        Account account = new Account();
        account = dbHandler.getAccountForAuth(login);
        if(account != null){
            password += account.getSalt();
            if(password.equals(DeEnCode.decode(account.getPassword()))){
                System.out.println("пароль верный");
                return account;
            }else{
                System.out.println("пароль не верный");
                return null;
            }
        }else{
            System.out.println("пароль или логин не верный");
            return null;
        }
    }
}
