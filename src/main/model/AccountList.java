package model;

import java.util.LinkedList;
import java.util.List;

// Represents a list of accounts to be shown in app
public class AccountList {
    List<Account> accountList;

    // EFFECTS: constructor, new empty list
    public AccountList() {
        accountList = new LinkedList<>();
    }

    // EFFECTS: add given account to account list
    // MODIFIES: this
    public void addAccount(Account account) {
        accountList.add(account);
    }

    // REQUIRES: specified account exists in account list
    // EFFECTS: deletes specified account from account list
    // MODIFIES: this
    public void deleteAccount(Account account) {
        accountList.remove(account);
    }

    // REQUIRE: account list is not empty
    // EFFECTS: construct account name list with its position in list
    public List<String> accountNameWithPositionList() {
        LinkedList<String> listOfAccountName = new LinkedList<>();
        int i = 1;
        for (Account account : accountList) {
            listOfAccountName.add(i + ". " + account.getAccountName());
            i++;
        }
        return listOfAccountName;
    }

    //REQUIRE: given string is integer, position starts at one the position given exists in account list
    //EFFECTS: given position of account on list and return the according account
    public Account getAccountFromStrPos(String pos) {
        return accountList.get(Integer.parseInt(pos) - 1);
    }

    //EFFECTS: return the size of account list
    public Integer length() {
        return accountList.size();
    }

    //EFFECTS: return true if account list contains account
    public boolean contains(Account account) {
        return accountList.contains(account);
    }

    //EFFECTS: return true if account list is empty
    public boolean isEmpty() {
        return accountList.isEmpty();
    }

}
