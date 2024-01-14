package notebook.util;

public class UserValidator {
    public String isValid(String item){
        if(item.isEmpty()){
            throw new RuntimeException("Вы не ввели данные!");
        }
        return item.trim().replaceAll(" ", "");
    }
}
