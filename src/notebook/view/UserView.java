package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;


public class UserView {
    private final UserController userController;

    public UserView(){
        this.userController = new UserController();
    }

    public void run(){
        userController.crateDataBaseController();
        Commands com;

        while (true) {

            userController.showCommandsController();
            String commandNumber = userController.promptController("Введите номер команды: ");
            com = userController.selectCommandController(commandNumber);

            if (com == Commands.EXIT) return;
            switch (com) {
                case ADD_MORE:
                    userController.addMoreUsersController();
                    break;
                case CREATE:
                    userController.addUserController();
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
