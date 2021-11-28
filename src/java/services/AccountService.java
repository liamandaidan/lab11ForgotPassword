package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {

    public boolean forgotPassword(String email, String path) {

        //find user in db
        User user;
        try {
            user = new UserDB().get(email); 
            if (user == null) {
                throw new Exception();
            }
            Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful user found by {0}", user.getEmail()); 
            return true;
        } catch (Exception e) {
            Logger.getLogger(AccountService.class.getName()).log(Level.WARNING, "Could not find user{0}", email);
            return false;
        }
    }

    public void sendTemplatedEmail(String email, String path) {
        try {
            User user = new UserDB().get(email);
            String to = user.getEmail();
            String subject = "Notes App Login";
            String template = path + "/emailtemplates/forgotP.html";

            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("email", (user.getEmail()));
            tags.put("password", user.getPassword());

            GmailService.sendMail(to, subject, template, tags);
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                /*
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/welcome.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());

                GmailService.sendMail(to, subject, template, tags);
                 */
                // GmailService.sendMail(email, "Notes app login!", "A login has been made to your notes app account.", false);
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
