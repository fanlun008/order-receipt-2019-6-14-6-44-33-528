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
        printHeadersInfo(output);

        // prints lineItems
        double totSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            appendLineItemInfo(lineItem, output);
            double salesTax = getSalesTax(lineItem);
            totSalesTax += salesTax;
            totalAmount += lineItem.totalAmount() + salesTax;
        }
        printStateTaxAndTotalAmount(output, totSalesTax, totalAmount);
        return output.toString();
    }


    private void printStateTaxAndTotalAmount(StringBuilder output, double salesTax, double totalAmount) {
        output.append("Sales Tax").append('\t').append(salesTax);
        output.append("Total Amount").append('\t').append(totalAmount);
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
    }


    private void printHeadersInfo(StringBuilder output) {
        output.append("======Printing Orders======\n");
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress()+"\n");
    }
}