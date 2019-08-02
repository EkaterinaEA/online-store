package info.sjd.service.backEndService;

import info.sjd.dao.UserDAO;
import info.sjd.model.User;

public class UserService {

    public static User create(User user){

        if (UserDAO.findById(user.getUserId()) == null){
            return UserDAO.create(user);
        }
        return null;
    }
    public static User findByName(String name){
        return UserDAO.findByName(name);
    }
    public static User findByNameAndPassword(String name, String password){
        return UserDAO.findByNameAndPassword(name, password);
    }
}
