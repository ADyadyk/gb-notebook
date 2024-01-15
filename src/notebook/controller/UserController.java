package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;
import notebook.util.Commands;

import java.util.List;
import java.util.Objects;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
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
    public Commands selectCommandController(String commandNumber){
        return repository.selectCommand(commandNumber);
    }
    public void showCommandsController(){
        repository.showCommands();
    }
}
