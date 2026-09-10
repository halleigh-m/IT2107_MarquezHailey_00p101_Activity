import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Menu
        String[] items = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea"};
        double[] prices = {80.00, 120.00, 100.00, 70.00, 90.00};

        // Variables for the order summary
        int totalItems = 0;
        double totalBeforeDiscount = 0.00;
        double totalDiscount = 0.00;

        char orderAgain = 'Y';

        while (orderAgain == 'Y') {

            // Display menu
            System.out.println("==== M E N U ====");
            for (int i = 0; i < items.length; i++) {
                System.out.printf("%d. %-10s - $%.2f%n",
                        (i + 1), items[i], prices[i]);
            }

            // Ask for order
            System.out.print("\nEnter item number: ");
            int itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            char student = input.next().toUpperCase().charAt(0);

            // Validate item number and quantity
            if (itemNumber < 1 || itemNumber > 5 ||
                    quantity < 1 || quantity > 10) {

                System.out.println(
                    "\nInvalid order! Please enter a valid item and quantity."
                );

            } else {

                // Calculate subtotal
                double subtotal = prices[itemNumber - 1] * quantity;

                // Calculate discount
                double discountRate;

                if (student == 'Y' && subtotal >= 500) {
                    // Student + purchase of $500 or more
                    discountRate = 0.15;
                } else if (student == 'Y') {
                    // Student only
                    discountRate = 0.10;
                } else if (subtotal >= 500) {
                    // Purchase of $500 or more
                    discountRate = 0.05;
                } else {
                    // No discount
                    discountRate = 0.00;
                }

                double discount = subtotal * discountRate;
                double orderTotal = subtotal - discount;

                // Display order information
                System.out.printf("\nSubtotal: $%.2f%n", subtotal);
                System.out.printf("Discount: $%.2f%n", discount);
                System.out.printf("Order total: $%.2f%n", orderTotal);

                // Add to overall transaction
                totalItems += quantity;
                totalBeforeDiscount += subtotal;
                totalDiscount += discount;
            }

            // Ask if customer wants another order
            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = input.next().toUpperCase().charAt(0);

            System.out.println();
        }

        // Final order summary
        double finalAmount = totalBeforeDiscount - totalDiscount;

        System.out.println("==== ORDER SUMMARY ====");
        System.out.println("Total items: " + totalItems);
        System.out.printf("Total before discount: $%.2f%n",
                totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n",
                totalDiscount);
        System.out.printf("Final amount: $%.2f%n",
                finalAmount);

        System.out.println("Thank you for ordering!");

        input.close();
    }
}
