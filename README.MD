***HOMEWORK***
___
**Ход выполнения домашнего задания:**

1. Перенес все методы из dao в repozitory и устранил все возникающие конфликты в Main.java и UserRepository.java
2. Добавил возможность удалять user. Для этого в UserView.java в блоке switch-case добавил пункт DELETE. Соответственно, в UserController добавил дополнительный метод, который реализовал в UserRepository. В интерфейсе GBRepository.java метод delete() сделал void.
3. Перенёс методы prompt() и createUser() из UserView.java (слой view) в UserRepository.java (слой model). В UserController.java (слой controller) добавил два метода promptController() и createUserController(), которые вызывают из UserRepository.java соответствующие методы (когда из слоя view программа обращается к этим методам).
4. Упростил ввод команд создав методы showCommands() и selectCommand() в слое model. Соответственно добавил методы selectCommandController() и showCommandController() в слой controller для их вызова их слоя model при обращении к ним из слоя view. В слое view подкорректировал метод run().
5. Выполнил отчистку класса Main.java - перенёс все методы в соответствующие слои.