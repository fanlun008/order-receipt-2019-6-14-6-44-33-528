package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    public static final double TAX_RATE = .10;
    private Order order;

    public OrderReceipt(Order o) {
        this.order = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        printHeaders(output);

        // print date, bill no, customer name
//        output.append("Date - " + order.getDate();
        appendNameAndAddress(output, order);
//        output.append(order.getCustomerLoyaltyNumber());

        // prints lineItems
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            appendLineItemInfo(lineItem, output);

            // calculate sales tax @ rate of 10%
            double salesTax = getSalesTax(lineItem);
            totSalesTx += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + salesTax;
        }

        // prints the state tax
        printsTheStateTax(output, totSalesTx);

        // print total amount
        printTotalAmount(output, tot);
        return output.toString();
    }

    private void printTotalAmount(StringBuilder output, double tot) {
        output.append("Total Amount").append('\t').append(tot);
    }

    private void printsTheStateTax(StringBuilder output, double totSalesTx) {
        output.append("Sales Tax").append('\t').append(totSalesTx);
    }

    public Double getSalesTax(LineItem lineItem) {
        return lineItem.totalAmount() * TAX_RATE;
    }

    public void appendLineItemInfo(LineItem lineItem, StringBuilder output) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
//        output.append(String.format("%1$s\t%2$f\t%3$d\t%4$f\n", lineItem.getDescription(),
//                lineItem.getPrice(), lineItem.getQuantity(), lineItem.totalAmount()));
    }

    private void appendNameAndAddress(StringBuilder output, Order order) {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress()+"\n");
    }


    private void printHeaders(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }
}