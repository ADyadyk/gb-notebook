package notebook.model.repository.impl;

import notebook.util.Commands;
import notebook.util.DBConnector;
import notebook.util.UserValidator;
import notebook.util.mapper.impl.UserMapper;
import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.io.*;
import java.util.*;

import static notebook.util.DBConnector.createDB;

public class UserRepository implements GBRepository {
    private final UserMapper mapper;

    public UserRepository() {
        this.mapper = new UserMapper();
    }

    // Перенёс из устаревшего DAO:
    public List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(DBConnector.DB_PATH);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public List<User> findAll() {
        List<String> lines = readAll();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.toOutput(line));
        }
        return users;
    }
    public void userValid(User user){
        UserValidator validator = new UserValidator();
        user.setFirstName(validator.isValid(user.getFirstName()));
        user.setLastName(validator.isValid(user.getLastName()));
    }
    @Override
    public User create(User user) {
        userValid(user);
        List<User> users = findAll();
        long max = 0L;
        for (User u : users) {
            long id = u.getId();
            if (max < id){
                max = id;
            }
        }
        long next = max + 1;
        user.setId(next);
        users.add(user);
        write(users);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(Long userId, User update) {
        List<User> users = findAll();
        User editUser = users.stream()
                .filter(u -> u.getId()
                        .equals(userId))
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        if(!update.getFirstName().isEmpty()){
            editUser.setFirstName(update.getFirstName());
        }
        if(!update.getLastName().isEmpty()){
            editUser.setLastName(update.getLastName());
        }
        if(!update.getPhone().isEmpty()){
            editUser.setPhone(update.getPhone());
        }
        write(users);
        return Optional.of(update);
    }

    @Override
    public void delete(Long userId) {
        List<User> users = findAll();
        User deleteUser = users.stream()
                .filter(u -> u.getId()
                        .equals(userId))
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        users.remove(deleteUser);
        write(users);
    }

    // Перенёс из устаревшего DAO:
    public void saveAll(List<String> data) {
        try (FileWriter writer = new FileWriter(DBConnector.DB_PATH, false)) {
            for (String line : data) {
                // запись всей строки
                writer.write(line);
                // запись по символам
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void write(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u: users) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
    }

    @Override
    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    @Override
    public User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }

    @Override
    public Commands selectCommand(String commandNumber){
        if(Integer.parseInt(commandNumber) == 1) return Commands.ADD_MORE;
        if(Integer.parseInt(commandNumber) == 2) return Commands.READ;
        if(Integer.parseInt(commandNumber) == 3) return Commands.CREATE;
        if(Integer.parseInt(commandNumber) == 4) return Commands.UPDATE;
        if(Integer.parseInt(commandNumber) == 5) return Commands.LIST;
        if(Integer.parseInt(commandNumber) == 6) return Commands.DELETE;
        if(Integer.parseInt(commandNumber) == 7) return Commands.EXIT;
        return null;
    }

    @Override
    public void showCommands(){
        System.out.println("1 - Добавить несколько записей");
        System.out.println("2 - Прочитать запись по id");
        System.out.println("3 - Создать запись");
        System.out.println("4 - Обновить данные в записи");
        System.out.println("5 - Вывести записи списком");
        System.out.println("6 - Удалить запись");
        System.out.println("7 - Выйти из справочника");
    }
    @Override
    public void createDataBase(){
        createDB();
    }

    @Override
    public void addMoreUsers(){
        addUser();
        boolean check = true;
        while (check) {
            System.out.println("Добавить ещё одного абонента?");
            System.out.println("1 - Да");
            System.out.println("2 - Нет");
            int number = Integer.parseInt(prompt("Сделайте выбор: "));
            if(number == 2) check = false;
            if(number == 1){
                addUser();
            }
        }
    }
    @Override
    public void addUser(){
        User u = createUser();
        create(u);
    }

    @Override
    public void readUserItem(){
        String id = prompt("Идентификатор пользователя: ");
        try {
            List<User> users = findAll();
            for (User user : users) {
                if (Objects.equals(user.getId(), Long.parseLong(id))) {// ИСПРАВЬ
                    System.out.println(user);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
