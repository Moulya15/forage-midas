package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    private int count = 0;

    @KafkaListener(topics = "${general.kafka-topic}")
    public void listen(String transactionLine) {

        String[] transactionData = transactionLine.split(", ");

        Transaction transaction = new Transaction(
                Long.parseLong(transactionData[0]),
                Long.parseLong(transactionData[1]),
                Float.parseFloat(transactionData[2])
        );

        count++;
        System.out.println("Transaction " + count + " amount = " + transaction.getAmount());
    }
}

