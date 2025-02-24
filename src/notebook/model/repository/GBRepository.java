package notebook.model.repository;

import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Optional;

public interface GBRepository {
    List<User> findAll();
    User create(User user);
    Optional<User> findById(Long id);
    Optional<User> update(Long userId, User update);
    void delete(Long id);
    String prompt(String message);
    User createUser();
    void createDataBase();
    void addUser();
    void readUserItem();
}
