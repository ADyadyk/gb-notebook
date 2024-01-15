package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = userController.promptController("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = userController.createUserController();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = userController.promptController("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = userController.promptController("Enter user id: ");
                    userController.updateUser(userId, userController.createUserController());
                    break;
                case LIST:
                    System.out.println(userController.readAll());
                    break;
                case DELETE:
                    String userId2 = userController.promptController("Enter user id: ");
                    userController.deleteUser(userId2);
                    break;
            }
        }
    }
}
