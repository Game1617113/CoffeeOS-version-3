package cafe3;


public class CoffeeOrder {
    private String menuName;
    private double price;
    private int quantity;

    public CoffeeOrder(String menuName, double price, int quantity) {
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
    }

    public static double calFinalPrice(double basePrice, int idLastTwo) {
        return basePrice + (idLastTwo * 2);
    }

    public static int calFinalQuantity(int amount, int idLastTwo) {
        int idLastDigit = idLastTwo % 10;
        return amount + (idLastDigit % 5);
    }

    public static CoffeeOrder create(String menuName, double basePrice, int amount, int idLastTwo) {
        double finalPrice = calFinalPrice(basePrice, idLastTwo);
        int finalQuantity = calFinalQuantity(amount, idLastTwo);
        return new CoffeeOrder(menuName, finalPrice, finalQuantity);
    }

    public double calTotal() {
        return price * quantity;
    }

//    public double calDiscount() {
//        double total = calTotal();
//        if (total >= 500) {
//            return total * 0.10;
//        }
//        return 0;
//    }

//    public void showReceipt() {
//        double total = calTotal();
//        double discount = calDiscount();
//        double net = total - discount;
//
//        System.out.println("============= Receipt =============");
//        System.out.println("Menu name   : " + menuName);
//        System.out.println("Price       : " + price + " Baht");
//        System.out.println("Quantity    : " + quantity);
//        System.out.println("-----------------------------------");
//        System.out.println("Total price : " + total + " Baht");
//        System.out.println("Discount    : " + discount + " Baht");
//        System.out.println("Net price   : " + net + " Baht");
//        System.out.println("===================================");
//    }

    public static double calGrandTotal(CoffeeOrder[] orders, int count) {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += orders[i].calTotal();
        }
        return sum;
    }

    public static double calGrandDiscount(double grandTotal) {
        if (grandTotal >= 500) {
            return grandTotal * 0.10;
        }
        return 0;
    }

    public static void showSummaryReceipt(CoffeeOrder[] orders, int count) {
        if (count == 0) {
            System.out.println("No order.");
            return;
        }
        double total = calGrandTotal(orders, count);
        double discount = calGrandDiscount(total);
        double net = total - discount;

        System.out.println("============= Receipt =============");
        for (int i = 0; i < count; i++) {
            CoffeeOrder o = orders[i];
            System.out.printf("%d. %s : %.2f x %d = %.2f Baht%n", i + 1, o.menuName, o.price, o.quantity, o.calTotal());
        }
        System.out.println("-----------------------------------");
        System.out.printf("Total price : %.2f Baht%n", total);
        System.out.printf("Discount    : %.2f Baht%n", discount);
        System.out.printf("Net price   : %.2f Baht%n", net);
        System.out.println("===================================");
    }
}