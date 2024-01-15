package notebook.view;

import notebook.controller.UserController;
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
                    userController.readUserItemController();
                    break;
                case UPDATE:
                    String userId = userController.promptController("Enter user id: ");
                    userController.updateUser(userId, userController.createUserController());
                    break;
                case LIST:
                    System.out.println(userController.readAll());
                    break;
                case DELETE:
                    userController.deleteUser(userController.promptController("Enter user id: "));
                    break;
            }
        }
    }
}
