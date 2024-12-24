import java.util.*;

class Cake { // Parent Class
String name;
float price;
int quantity;

// GETTER
public String getName() {
return name;
}

public float getPrice() {
return price;
}

public int getQuantity() {
return quantity;
}

// SETTER
public void setName(String newName) {
this.name = newName;
}

public void setPrice(float newPrice) {
this.price = newPrice;
}

public void setQuantity(int quantity) {
this.quantity = quantity;
}

// Display Method with Color
void display() {
// ANSI color codes (green for cakes)
String color = "\u001B[32m"; // Green for cakes
System.out.println(color + name + " : Rupees " + String.format("%.2f", price) + " per pound | Available: " + quantity + " units");
System.out.println("\u001B[0m"); // Reset color to default
}
}

class Pastry extends Cake { // Child Class
@Override
void display() {
// ANSI color codes for pastries (magenta)
String color = "\u001B[35m"; // Magenta for pastries
System.out.println(color + name + " : Rupees " + String.format("%.2f", price) + " per piece | Available: " + quantity + " units");
System.out.println("\u001B[0m"); // Reset color to default
}
}

public class Bakery { // Bakery Class
public static void main(String[] args) {
// `Cake` & `Pastry` Object creation
Cake cake1 = new Cake();
Cake cake2 = new Cake();
Pastry pastry1 = new Pastry();
Pastry pastry2 = new Pastry();

// Initialize Cakes and Pastries
cake1.setName("Chocolate Brownie");
cake1.setPrice(250f);
cake1.setQuantity(50);

cake2.setName("Chocolate Maple");
cake2.setPrice(300f);
cake2.setQuantity(30);

pastry1.setName("Black Forest");
pastry1.setPrice(35f);
pastry1.setQuantity(100);

pastry2.setName("Choco Truffle");
pastry2.setPrice(40f);
pastry2.setQuantity(80);

// List of Cakes and Pastries
List<Cake> cakesList = new ArrayList<>();
cakesList.add(cake1);
cakesList.add(cake2);

List<Pastry> pastriesList = new ArrayList<>();
pastriesList.add(pastry1);
pastriesList.add(pastry2);

Scanner scanner = new Scanner(System.in);

// Display Menu
System.out.println("\u001B[36m        Today's Special Menu        \u001B[0m"); // Cyan for the title
System.out.println("\u001B[36m------------------------------------\u001B[0m");
System.out.println();
System.out.println("\u001B[36mSpecial Cakes                       \u001B[0m");
System.out.println("\u001B[36m------------------------------------\u001B[0m");

// Display Cakes
for (Cake object : cakesList) {
object.display();
}

System.out.println("\n\u001B[36mSpecial Pastries                    \u001B[0m");
System.out.println("\u001B[36m------------------------------------\u001B[0m");

// Display Pastries
for (Pastry object : pastriesList) {
object.display();
}

// User Input for selection
System.out.println("\nEnter the item name you want to buy: ");
String itemName = scanner.nextLine().trim(); // Trim spaces to avoid user errors

if (itemName.isEmpty()) {
System.out.println("Item name cannot be empty.");
return;
}

System.out.println("Enter the quantity you want to buy: ");
int quantityToBuy = -1;

// Validate quantity input
while (quantityToBuy <= 0) {
try {
quantityToBuy = Integer.parseInt(scanner.nextLine());
if (quantityToBuy <= 0) {
System.out.println("Quantity must be a positive integer. Please try again.");
}
} catch (NumberFormatException e) {
System.out.println("Invalid input. Please enter a valid quantity.");
}
}

boolean itemFound = false;
float totalCost = 0f;

// Search for the item in cakes list
for (Cake cake : cakesList) {
if (cake.getName().equalsIgnoreCase(itemName)) {
if (cake.getQuantity() >= quantityToBuy) {
cake.setQuantity(cake.getQuantity() - quantityToBuy); // Decrease the available quantity
totalCost = quantityToBuy * cake.getPrice(); // Calculate total cost
System.out.println("\u001B[33mYou bought " + quantityToBuy + " " + itemName + "(s). Total cost: Rupees " + String.format("%.2f", totalCost) + "\u001B[0m"); // Yellow for total cost
} else {
System.out.println("\u001B[31mSorry, not enough stock available for " + itemName + ".\u001B[0m"); // Red for out of stock
}
itemFound = true;
break;
}
}

// Search for the item in pastries list if not found in cakes
if (!itemFound) {
for (Pastry pastry : pastriesList) {
if (pastry.getName().equalsIgnoreCase(itemName)) {
if (pastry.getQuantity() >= quantityToBuy) {
pastry.setQuantity(pastry.getQuantity() - quantityToBuy); // Decrease the available quantity
totalCost = quantityToBuy * pastry.getPrice(); // Calculate total cost
System.out.println("\u001B[33mYou bought " + quantityToBuy + " " + itemName + "(s). Total cost: Rupees " + String.format("%.2f", totalCost) + "\u001B[0m"); // Yellow for total cost
} else {
System.out.println("\u001B[31mSorry, not enough stock available for " + itemName + ".\u001B[0m"); // Red for out of stock
}
itemFound = true;
break;
}
}
}

// If the item wasn't found in the menu
if (!itemFound) {
System.out.println("\u001B[31mSorry, the item " + itemName + " is not available on the menu.\u001B[0m"); // Red for item not found
}

scanner.close();
}
}
