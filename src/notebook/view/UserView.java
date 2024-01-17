package notebook.view;

import notebook.controller.UserController;
import notebook.util.Commands;


public class UserView {
    private final UserController userController;

    public UserView(){
        this.userController = new UserController();
    }

    private void showCommands(){
        System.out.println("1 - Добавить несколько записей");
        System.out.println("2 - Прочитать запись по id");
        System.out.println("3 - Создать запись");
        System.out.println("4 - Обновить данные в записи");
        System.out.println("5 - Вывести записи списком");
        System.out.println("6 - Удалить запись");
        System.out.println("7 - Выйти из справочника");
    }
    private Commands selectCommand(String commandNumber){
        if(Integer.parseInt(commandNumber) == 1) return Commands.ADD_MORE;
        if(Integer.parseInt(commandNumber) == 2) return Commands.READ;
        if(Integer.parseInt(commandNumber) == 3) return Commands.CREATE;
        if(Integer.parseInt(commandNumber) == 4) return Commands.UPDATE;
        if(Integer.parseInt(commandNumber) == 5) return Commands.LIST;
        if(Integer.parseInt(commandNumber) == 6) return Commands.DELETE;
        if(Integer.parseInt(commandNumber) == 7) return Commands.EXIT;
        return null;
    }
    public void run(){
        userController.crateDataBaseController();
        Commands com;

        while (true) {

            showCommands();
            String commandNumber = userController.promptController("Введите номер команды: ");
            com = selectCommand(commandNumber);

            if (com == Commands.EXIT) return;
            switch (com) {
                case ADD_MORE:
                    userController.addUserController();
                    boolean check = true;
                    while (check) {
                        System.out.println("Добавить ещё одного абонента?");
                        System.out.println("1 - Да");
                        System.out.println("2 - Нет");
                        int number = Integer.parseInt(userController.promptController("Сделайте выбор: "));
                        if(number == 2) check = false;
                        if(number == 1){
                            userController.addUserController();
                        }
                    }
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
