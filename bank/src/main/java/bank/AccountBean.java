package bank;

import exception.IllegalCreditException;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountBean {

    @Inject
    private AccountRepository accountRepository;


    @Transactional
    public void credit(long accountId, long amount) throws IllegalCreditException {
        Account account = accountRepository.findBy(accountId);
        account.setBalance(account.getBalance() + amount);
        if (account.getBalance() < 0) {
            throw new IllegalCreditException();
        }
    }

    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }
}
