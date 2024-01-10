package com.picpay.service;

import com.picpay.domain.transaction.Transaction;
import com.picpay.domain.user.User;
import com.picpay.domain.user.UserType;
import com.picpay.dto.TransactionDTO;
import com.picpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    NotificationService notificationService;

    @Autowired
    TransactionRepository repository;

    @Autowired
    UserService service;

    @Autowired
    public RestTemplate restTemplate;

    public List<Transaction> get() {
        return repository.findAll();
    }

    public Transaction getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
          new RuntimeException("Unable to perform the request")
        );
    }

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User reciver = service.getById(transaction.reciverId());
        User sender = service.getById(transaction.senderId());

        this.validateTransaction(sender, transaction.value());

        Transaction newTransaction = new Transaction();
        newTransaction.setSender(sender);
        newTransaction.setReciver(reciver);
        newTransaction.setValue(transaction.value());

        reciver.setBalance(reciver.getBalance().add(transaction.value()));
        sender.setBalance(sender.getBalance().subtract(transaction.value()));

        repository.save(newTransaction);
        notificationService.sendNotifications(reciver, "transfer received");
        service.post(sender);
        service.post(reciver);

        return newTransaction;
    }

    public void validateTransaction(User sender, BigDecimal value) throws Exception {
        if(sender.getUserType() == UserType.SHOPKEEPER) {
            throw new Exception("shopkeepers cannot make transfers");
        }

        if(sender.getBalance().compareTo(value) < 0){
            throw new Exception("insufficient balance");
        }

        if(!authorizeTransaction()) {
            throw new Exception("unauthorized transaction");
        }
    }

    public Boolean authorizeTransaction() {
        ResponseEntity<Map> authorization = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if(authorization.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorization.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
