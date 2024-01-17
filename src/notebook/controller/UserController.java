package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;
import notebook.model.repository.impl.UserRepository;
import notebook.util.Commands;

import java.util.List;

public class UserController {
    private final GBRepository repository;

    public UserController(){
        this.repository = new UserRepository();
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public void readUserItemController(){
        repository.readUserItem();
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }
    //Метод, который вызовет удаление user:
    public void deleteUser(String userId){
        repository.delete(Long.parseLong(userId));
    }

    // Вывод списка User используя UserRepository
    public List<User> readAll(){
        return repository.findAll();
    }

    public String promptController(String message){
        return repository.prompt(message);
    }

    public User createUserController(){
        return repository.createUser();
    }
    public void crateDataBaseController(){
        repository.createDataBase();
    }
    public void addUserController(){
        repository.addUser();
    }
}
