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
    void depositMoney_positiveAmount_happyPath() {
        account.depositMoney(100);

        assertEquals(400, account.getBalance(),
                "Le solde doit augmenter du montant déposé");
    }

    @Test
    void depositMoney_negativeAmount_edgeCase() {
        account.depositMoney(-50);

        assertEquals(300, account.getBalance(),
                "Un dépôt négatif ne doit pas modifier le solde");
    }

    @Test
    void depositMoney_zeroAmount_edgeCase() {
        account.depositMoney(0);

        assertEquals(300, account.getBalance(),
                "Un dépôt de 0 ne doit pas modifier le solde");
    }

    @Test
    void withdrawMoney_validAmount_happyPath() {
        boolean result = account.withdrawMoney(100);

        assertTrue(result, "Le retrait doit réussir");
        assertEquals(200, account.getBalance(),
                "Le solde doit être diminué du montant retiré");
    }

    @Test
    void withdrawMoney_moreThanBalance_edgeCase() {
        boolean result = account.withdrawMoney(400);

        assertFalse(result, "Le retrait ne doit pas être autorisé");
        assertEquals(300, account.getBalance(),
                "Le solde ne doit pas changer si le retrait échoue");
    }

    @Test
    void withdrawMoney_negativeAmount_edgeCase() {
        boolean result = account.withdrawMoney(-20);

        assertFalse(result, "Un retrait négatif doit être refusé");
        assertEquals(300, account.getBalance(),
                "Le solde ne doit pas changer");
    }

    @Test
    void withdrawMoney_equalToWithdrawLimit_edgeCase() {
        boolean result = account.withdrawMoney(500);

        assertFalse(result, "Un retrait égal à la limite doit être refusé");
    }

    @Test
    void withdrawMoney_exceedsCumulativeWithdrawLimit_edgeCase() {
        boolean first = account.withdrawMoney(300);
        boolean second = account.withdrawMoney(250);

        assertTrue(first, "Premier retrait valide");
        assertFalse(second, "Le second retrait dépasse la limite cumulée");
    }
}
