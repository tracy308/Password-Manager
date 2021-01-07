package model;

import exceptions.InputTooLongException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account testAccount;

    @BeforeEach
    void runBefore() {
        testAccount = new Account();
    }

    @Test
    void testConstructor() {
        assertEquals("", testAccount.getAccountName());
        assertEquals("n/a", testAccount.getUserName());
        assertEquals("n/a", testAccount.getEmail());
        assertEquals("n/a", testAccount.getPassword());
    }

    @Test
    void addAccountNameShortInputTest() {
        try {
            testAccount.addAccountName("gmail");
        } catch (InputTooLongException e) {
            fail("Exception should not be caught");
        }
        assertEquals("gmail", testAccount.getAccountName());
        assertEquals("n/a", testAccount.getUserName());
        assertEquals("n/a", testAccount.getEmail());
        assertEquals("n/a", testAccount.getPassword());
    }

    @Test
    void addAccountNameTooLongInputTest() {
        try {
            testAccount.addAccountName("I am Iron man. The past is in the past. Intelligence. Happy Birthday!!!");
            fail("Exception is not be caught");
        } catch (InputTooLongException e) {
            System.out.println("Exception caught");
        }
        assertEquals("gmail", testAccount.getAccountName());
        assertEquals("n/a", testAccount.getUserName());
        assertEquals("n/a", testAccount.getEmail());
        assertEquals("n/a", testAccount.getPassword());
    }

    @Test
    void addUserNameTest() {
        testAccount.addUserName("unicorn123");
        assertEquals("", testAccount.getAccountName());
        assertEquals("unicorn123", testAccount.getUserName());
        assertEquals("n/a", testAccount.getEmail());
        assertEquals("n/a", testAccount.getPassword());
    }

    @Test
    void addEmailTest() {
        testAccount.addEmail("abc@ubc.ca");
        assertEquals("", testAccount.getAccountName());
        assertEquals("n/a", testAccount.getUserName());
        assertEquals("abc@ubc.ca", testAccount.getEmail());
        assertEquals("n/a", testAccount.getPassword());
    }

    @Test
    void addPassword() {
        testAccount.addPassword("123456");
        assertEquals("", testAccount.getAccountName());
        assertEquals("n/a", testAccount.getUserName());
        assertEquals("n/a", testAccount.getEmail());
        assertEquals("123456", testAccount.getPassword());
    }

    @Test
    void addAllFields() {
        try {
            testAccount.addAccountName("UBC");
        } catch (InputTooLongException e) {
            fail("No exception should be caught");
        }
        testAccount.addUserName("prof99");
        testAccount.addEmail("prof99@ubc.ca");
        testAccount.addPassword("Password?12");
        assertEquals("UBC", testAccount.getAccountName());
        assertEquals("prof99", testAccount.getUserName());
        assertEquals("prof99@ubc.ca", testAccount.getEmail());
        assertEquals("Password?12", testAccount.getPassword());
    }
}