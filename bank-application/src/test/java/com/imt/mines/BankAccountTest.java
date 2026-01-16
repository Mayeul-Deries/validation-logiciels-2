package com.imt.mines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
        account.setWithdrawLimit(500);
        account.depositMoney(300);
    }

    @Test
    void depositMoneyPositiveAmountHappyPath() {
        account.depositMoney(100);

        assertEquals(400, account.getBalance(),
                "Le solde doit augmenter du montant déposé");
    }

    @Test
    void depositMoneyNegativeAmountEdgeCase() {
        account.depositMoney(-50);

        assertEquals(300, account.getBalance(),
                "Un dépôt négatif ne doit pas modifier le solde");
    }

    @Test
    void depositMoneyZeroAmountEdgeCase() {
        account.depositMoney(0);

        assertEquals(300, account.getBalance(),
                "Un dépôt de 0 ne doit pas modifier le solde");
    }

    @Test
    void withdrawMoneyValidAmountHappyPath() {
        boolean result = account.withdrawMoney(100);

        assertTrue(result, "Le retrait doit réussir");
        assertEquals(200, account.getBalance(),
                "Le solde doit être diminué du montant retiré");
    }

    @Test
    void withdrawMoneyMoreThanBalanceEdgeCase() {
        boolean result = account.withdrawMoney(400);

        assertFalse(result, "Le retrait ne doit pas être autorisé");
        assertEquals(300, account.getBalance(),
                "Le solde ne doit pas changer si le retrait échoue");
    }

    @Test
    void withdrawMoneyNegativeAmountEdgeCase() {
        boolean result = account.withdrawMoney(-20);

        assertFalse(result, "Un retrait négatif doit être refusé");
        assertEquals(300, account.getBalance(),
                "Le solde ne doit pas changer");
    }

    @Test
    void withdrawMoneyEqualToWithdrawLimitEdgeCase() {
        boolean result = account.withdrawMoney(500);

        assertFalse(result, "Un retrait égal à la limite doit être refusé");
    }

    @Test
    void withdrawMoneyExceedsCumulativeWithdrawLimitEdgeCase() {
        boolean first = account.withdrawMoney(300);
        boolean second = account.withdrawMoney(250);

        assertTrue(first, "Premier retrait valide");
        assertFalse(second, "Le second retrait dépasse la limite cumulée");
    }

    @Test
    void testToString() throws Exception {
        Person p = new Person("John\nM\n25\n180\n75\nBrown\nBlue\njohn@mail.com");
        BankAccount account = new BankAccount(1000, 500, "2024-01-01", p);
        account.setAccountNumber(42);

        String result = account.toString();

        assertTrue(result.contains("42"));
        assertTrue(result.contains("1000"));
        assertTrue(result.contains("2024-01-01"));
        assertTrue(result.contains("500"));
        assertTrue(result.contains("John"));
    }

    @Test
    void testConvertToText() throws Exception {
        Person p = new Person("Alice\nF\n30\n165\n60\nBlond\nGreen\nalice@mail.com");
        BankAccount account = new BankAccount(200, 300, "2023-12-31", p);
        account.setAccountNumber(7);

        String result = account.convertToText(account);

        assertNotNull(result);
        assertTrue(result.contains("7"));
        assertTrue(result.contains("200"));
        assertTrue(result.contains("300"));
        assertTrue(result.contains("2023-12-31"));
        assertTrue(result.contains("Alice"));
    }

    @Test
    void testLoadFromTextWithInvalidFile() {
        BankAccount account = new BankAccount();

        int result = account.loadFromText("non_existing_file.txt");

        assertEquals(0, result);
    }

}
