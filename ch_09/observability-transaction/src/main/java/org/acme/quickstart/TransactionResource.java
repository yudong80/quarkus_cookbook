package org.acme.quickstart;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/tx")
public class TransactionResource {

    // tag::histogram[]
    @Metric(name = "transaction-evolution") // <1>
    Histogram transactionHistogram;

    // end::histogram[]

    // tag::gauge[]
    private long highestTransaction = 0; // <1>
    
    // end::gauge[]
    // tag::count[]
    @Counted( // <1>
            name = "number-of-transactions", // <2>
            displayName = "Transactions", // <3>
            description = "How many transactions have been processed" // <4>
    )
    // end::count[]
    // tag::meter[]
    @Metered( // <1>
            name = "transactions",
            unit = MetricUnits.SECONDS, // <2>
            description = "Rate of transactions"        
    )
    // end::meter[]
    // tag::timed[]
    @Timed( // <1>
        name = "average-transaction",
        unit = MetricUnits.SECONDS,
        description = "Average duration of transaction"
    )
    // end::timed[]
    // tag::body[]
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doTransaction(Transaction transaction) { 
    // end::body[]
        // tag::gauge[]
        if (transaction.amount > highestTransaction) { // <2>
            highestTransaction = transaction.amount;
        }
        // end::gauge[] 
        // tag::histogram[]
        transactionHistogram.update(transaction.amount); // <2>
        // end::histogram[]
    // tag::body[] 
        return Response.ok().build();
    }
    // end::body[]

    // tag::gauge[]
    @Gauge( // <3>
            name = "highest-gross-transaction", // <4>
            description = "Highest transaction so far.",
            unit= MetricUnits.NONE //<5>
    )
    public long highestTransaction() {
        return highestTransaction;
    }
    // end::gauge[]

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }
}
