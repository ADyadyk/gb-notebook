***HOMEWORK***
___
**Ход выполнения домашнего задания:**

1. Перенес все методы из dao в repozitory и устранил все возникающие конфликты в Main.java и UserRepository.java
2. Добавил возможность удалять user. Для этого в UserView.java в блоке switch-case добавил пункт DELETE. Соответственно, в UserController добавил дополнительный метод, который реализовал в UserRepository. В интерфейсе GBRepository.java метод delete() сделал void.