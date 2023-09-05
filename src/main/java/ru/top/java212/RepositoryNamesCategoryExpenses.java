package ru.top.java212;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RepositoryNamesCategoryExpenses {
    private final List<String> listAllNamesCategoryExpenses = new ArrayList<>();

    public RepositoryNamesCategoryExpenses() {
        this.listAllNamesCategoryExpenses.add("коммуналка");
    }

    public List<String> getListAllNamesCategoryExpenses() {
        return listAllNamesCategoryExpenses;
    }

    public boolean setListAllNamesCategoryExpenses(String nameOld, String nameNew) {
        Iterator<String> iterator = listAllNamesCategoryExpenses.iterator();
        int i=0;
        while (iterator.hasNext()){
            if (listAllNamesCategoryExpenses.get(i).equals(nameOld)){
                listAllNamesCategoryExpenses.set(i, nameNew);
                return true;
            }
            i++;
        }
        return false;
    }

    public void addNameCategoryExpense(String nameAdded){
        listAllNamesCategoryExpenses.add(nameAdded);
    }

    public boolean deleteNameCategoryExpense(String nameDeleted){
        Iterator<String> iterator = listAllNamesCategoryExpenses.iterator();
        int i=0;
        while (iterator.hasNext()){
            if (listAllNamesCategoryExpenses.get(i).equals(nameDeleted)){
                    listAllNamesCategoryExpenses.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
}
