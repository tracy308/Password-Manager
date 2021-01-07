package model;

import exceptions.InputTooLongException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountListTest {

    private AccountList accountListTest;
    private Account testAccount;

    @BeforeEach
    void runBefore() throws InputTooLongException {
        accountListTest = new AccountList();
        testAccount = new Account();
        testAccount.addAccountName("AccountName1");
        testAccount.addUserName("username1");
        testAccount.addEmail("email1");
        testAccount.addPassword("password1");
    }

    @Test
    void constructorTest() {
        assertEquals(0, accountListTest.length());
    }

    @Test
    void addOneAccountTest() {
        accountListTest.addAccount(testAccount);
        assertEquals(1, accountListTest.length());
        assertEquals(testAccount, accountListTest.getAccountFromStrPos("1"));
    }

    @Test
    void addMultipleAccountTest() {
        for (int i = 0; i <= 5; i++) {
            accountListTest.addAccount(testAccount);
        }
        assertEquals(6, accountListTest.length());
    }

    @Test
    void deleteAccountTest() {
        accountListTest.addAccount(testAccount);
        assertEquals(1, accountListTest.length());
        accountListTest.deleteAccount(testAccount);
        assertEquals(0, accountListTest.length());
    }

    @Test
    void deleteAccountFromMiddleOfListTest() {
        Account otherAccount = new Account();

        for (int i = 0; i <= 5; i++) {
            accountListTest.addAccount(otherAccount);
        }
        accountListTest.addAccount(testAccount);
        assertEquals(7, accountListTest.length());

        for (int i = 0; i <= 5; i++) {
            accountListTest.addAccount(otherAccount);
        }
        assertEquals(13, accountListTest.length());

        accountListTest.deleteAccount(testAccount);
        assertEquals(12, accountListTest.length());

        assertFalse(accountListTest.contains(testAccount));
    }

    @Test
    void printsAccountNameOnlyListOneAccountTest() {
        accountListTest.addAccount(testAccount);
        assertTrue(accountListTest.accountNameWithPositionList().contains("1. AccountName1"));
    }

    @Test
    void printsAccountNameOnlyListMultipleAccountTest() throws InputTooLongException {
        Account otherAccount = new Account();
        otherAccount.addAccountName("AccountName2");
        for (int i = 0; i <= 5; i++) {
            accountListTest.addAccount(otherAccount);
        }
        accountListTest.addAccount(testAccount);

        assertTrue(accountListTest.accountNameWithPositionList().contains("1. AccountName2"));
        assertTrue(accountListTest.accountNameWithPositionList().contains("3. AccountName2"));
        assertTrue(accountListTest.accountNameWithPositionList().contains("7. AccountName1"));
    }

    @Test
    void getStringPosTest() {
        Account otherAccount = new Account();
        for (int i = 0; i <= 5; i++) {
            accountListTest.addAccount(otherAccount);
        }
        accountListTest.addAccount(testAccount);

        assertEquals(testAccount, accountListTest.getAccountFromStrPos("7"));
    }

    @Test
    void isEmptyTest() {
        assertTrue(accountListTest.isEmpty());
    }
}
