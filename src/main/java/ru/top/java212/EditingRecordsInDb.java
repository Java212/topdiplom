package ru.top.java212;

public interface EditingRecordsInDb {
     void editingCategoryNamesExpense(String currentName, String newName);
     void editingCategoryNamesIncome(String currentName, String newName);

     int removeCategory(String whatRemove, String nameRemoveCategory);
}
