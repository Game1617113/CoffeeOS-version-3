package cafe3;

import java.util.Scanner;

public class CoffeeShop {

    static void showMenu(String[] menuList, double[] price) {
        System.out.println("Here is our menu.");
        System.out.println("----------- Menu -----------");
        for (int i = 0; i < menuList.length; i++) {
            System.out.println((i + 1) + "\t" + menuList[i] + "\t" + price[i] + "\t Baht.");
        }
        System.out.println("----------------------------");
    }

    static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter number only : ");
            sc.next();
        }
        return sc.nextInt();
    }

    static int readQuantity(Scanner sc) {
        System.out.print("How many cups? : ");
        int quantity = readInt(sc);
        while (quantity < 1) {
            System.out.print("Quantity must be at least 1 : ");
            quantity = readInt(sc);
        }
        return quantity;
    }

    static boolean askContinue(Scanner sc) {
        System.out.print("Would you like to continue shopping? (y/n) : ");
        return !sc.next().equalsIgnoreCase("n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] menuList = {"Espresso", "Americano", "Latte", "Cappuccino", "Mocha", "Tea", "Green tea with milk", "Fresh milk", "Chocolate", "Lychee juice"};
        double[] price = {60, 65, 75, 75, 65, 40, 50, 45, 50, 40};
        int idLastTwo = 33;
        CoffeeOrder[] orders = new CoffeeOrder[100];
        int count = 0;

        while (count < orders.length) {
            showMenu(menuList, price);
            System.out.print("Please choose menu by enter number.(1-10)(0=close): ");
            int choose = readInt(sc);

            if (choose == 0) {
                break;
            }
            if (choose < 1 || choose > menuList.length) {
                System.out.println("Invalid menu number.");
                continue;
            }

            int amount = readQuantity(sc);
            orders[count++] = CoffeeOrder.create(menuList[choose - 1], price[choose - 1], amount, idLastTwo);

            if (!askContinue(sc)) {
                break;
            }
        }

        if (count == orders.length) {
            System.out.println("Order limit reached.");
        }

        CoffeeOrder.showSummaryReceipt(orders, count);
        sc.close();
    }
}