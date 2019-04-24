package com.hewittj.interview;

import com.hewittj.interview.resource.TransactionDataResource;

/**
 * Created by Jalisa on 4/22/19.
 */
public class InterviewApplication {

    public static void main(final String[] args) throws Exception {
        new InterviewApplication().run();
    }

    public void run(){
        final TransactionDataResource resource = new TransactionDataResource();
        resource.printTransactionSummaries();
    }
}
