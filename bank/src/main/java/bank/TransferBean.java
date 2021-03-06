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
public class TransferBean {

    @Inject
    private TransferRepository transferRepository;

    @Inject AccountBean accountBean;

    @Transactional
    public Transfer createTransfer(CreateTransferCommand command) {
        var transfer = new Transfer();
        transfer.setSrc(command.getSrc());
        transfer.setDest(command.getDest());
        transfer.setAmount(command.getAmount());
        var created = transferRepository.save(transfer);

        try {
            accountBean.credit(command.getSrc(), -command.getAmount());
            accountBean.credit(command.getDest(), command.getAmount());
            created.setResult("ok");
        } catch (IllegalCreditException e) {
            created.setResult("error");
        }

        return created;
    }

    public List<Transfer> listTransfers() {
        return transferRepository.findAll();
    }
}
