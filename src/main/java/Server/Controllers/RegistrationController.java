package Server.Controllers;

import Server.Entity.Account;
import Server.Services.DataBase.DataBaseHandlerAccounts;
import Server.Services.DeEnCode;

import java.util.Random;

public class RegistrationController implements IRegistration {
    public String registration(String name, String login, String email, String password){
        String salt = generateRandomSalt(20);
        password += salt;
        Account account = new Account(name, login,email,DeEnCode.encode(password),salt,false);
        System.out.println("Имя: "+account.getName());
        System.out.println("Логин: "+account.getLogin());
        System.out.println("Электронная почта: "+account.getEmail());
        System.out.println("Пароль: "+DeEnCode.decode(account.getPassword()));
        System.out.println("Соль: "+account.getSalt());
        DataBaseHandlerAccounts dbHandler = new DataBaseHandlerAccounts();
        if(dbHandler.putAccount(account) == 1){
            return "success";
        }else{
            return "not_success";
        }
    }

    public String generateRandomSalt(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
