package dk.tg.renovation.models.services;

import dk.tg.renovation.models.entities.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminRepository implements Ilogin<Admin> {


    @Override
    public Admin logIn(String un, String pw) {
        Admin admin = new Admin("tgadmin", "8g1uiro4k21a");
        if (un.equals(admin.getUsername()) && pw.equals(admin.getPassword())) {
            return admin;

        }
        return null;
    }
}
