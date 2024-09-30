import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Collection collection = new Collection();
        boolean exit = false;
        boolean loggedIn = false;
        collection.loadCollection();
        System.out.println("---- Welcome to the Electronic Record System -----");

        while (!exit) {
            if(!loggedIn) {
                System.out.printf("Enter username : ");
                String username = sc.nextLine();
                System.out.printf("Enter password : ");
                String pass = sc.nextLine();
                if (username.equals("admin") && pass.equals("1234")) {
                    loggedIn = true;
                    System.out.println("Logged In!!");
                }
            }
            if(!loggedIn){
                System.out.println("Invalid details entered!");
                continue;
            }

            displayMenu();
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.print("\033[0m");
                switch (choice) {
                    case 1:
                        Item item = new Item(0, "Hello", "Hello", 1, 1, ItemType.FASTFOOD);

                        if (collection.getItems().size() > 0) {
                            item.setId(collection.getItems().get(collection.getItems().size() - 1).getId() + 1);
                        } else {
                            item.setId(1);
                        }

                        System.out.print("Enter the name of the item: ");
                        System.out.print("\033[0;32m");
                        item.setName(sc.nextLine());

                        System.out.print("\033[0m");
                        System.out.print("Enter the description of the item: ");
                        System.out.print("\033[0;32m");
                        item.setDescription(sc.nextLine());

                        System.out.print("\033[0m");
                        System.out.print("Enter the quantity of the item: ");
                        System.out.print("\033[0;32m");


                        while (true) {
                            try {
                                System.out.print("\033[0;32m");
                                item.setQuantity(sc.nextInt());
                                System.out.print("\033[0m");
                                break;
                            } catch (InputMismatchException e) {

                                System.out.print("\033[0;31m");
                                System.out.print("Invalid input! Enter a positive integer: ");

                                System.out.print("\033[0m");

                                System.out.print("\033[0;32m");
                                sc.nextLine();
                                System.out.print("\033[0m");
                            }
                        }
                        sc.nextLine(); //ignoring enter keys after taking int input
                        System.out.print("Enter the type of the item [");
                        for(ItemType itemType : ItemType.values()){
                            System.out.print(itemType+", ");
                        }
                        System.out.print("] : \033[0;32m");
                        while (true) {
                            try {

                                System.out.print("\033[0;32m");
                                item.setType(ItemType.valueOf(sc.nextLine().toUpperCase()));

                                System.out.print("\033[0m");
                                break;
                            } catch (IllegalArgumentException e) {

                                System.out.print("\033[0;31m");
                                System.out.print("Invalid input! Enter a valid type: ");

                                System.out.print("\033[0m");

                            }
                        }
                        System.out.print("Enter the price of the item: ");
                        System.out.print("\033[0;32m");
                        while (true) {
                            try {
                                item.setPrice(sc.nextDouble());
                                System.out.print("\033[0m");

                                break;
                            } catch (InputMismatchException e) {

                                System.out.print("\033[0;31m");
                                System.out.print("Invalid input! Enter a positive number: ");
                                System.out.print("\033[0m");
                                System.out.print("\033[0;32m");
                                sc.nextLine();

                            }
                        }

                        collection.addItem(item);

                        System.out.print("\033[0;31m");
                        System.out.println("\nItem added to collection");

                        System.out.print("\033[0m");
                        break;

                    case 2:
                        collection.displayCollection();
                        break;
                    case 3:

                        System.out.print("Enter ID of the item to remove: ");

                        System.out.print("\033[0;32m");

                        while (true) {
                            try {
                                collection.removeItem(sc.nextInt());
                                break;
                            } catch (InputMismatchException e) {

                                System.out.print("\033[0;31m");
                                System.out.print("Invalid input! Enter a positive integer: ");

                                System.out.print("\033[0;32m");
                                sc.nextLine();
                                //change color to white
                                System.out.print("\033[0m");
                            }

                        }

                        break;
                    case 4:
                        System.out.print("Enter ID of the item to edit: ");

                        System.out.print("\033[0;32m");
                        //ask user to enter id until not positive integer
                        while (true) {
                            try {
                                collection.editItem(sc.nextInt());
                                System.out.print("\033[0m");
                                break;
                            } catch (InputMismatchException e) {

                                System.out.print("\033[0;31m");
                                System.out.print("Invalid input! Enter a positive integer: ");
                                System.out.print("\033[0;32m");
                                sc.nextLine();
                                System.out.print("\033[0m");

                            }
                        }

                        break;
                    case 5:
                        collection.loadCollection();
                        break;
                    case 6:
                        collection.saveCollection();
                        break;
                    case 7:
                        //save collection before exit
                        collection.saveCollection();
                        //change color of text to red
                        System.out.print("\033[0;31m");
                        System.out.println("\n\tThank you for using the Electronic Record System\n");
                        //change color of text to white
                        System.out.print("\033[0m");
                        exit = true;
                        break;
                    default:

                        System.out.print("\033[0;31m");
                        System.out.println(
                                "Invalid choice! Please enter valid choice");
                        System.out.print("\033[0m");
                        break;
                }
            } catch (InputMismatchException e) {

                System.out.print("\033[0;31m");
                System.out.println("Invalid input, please enter a number between 1 and 7");
                System.out.print("\033[0m");
                sc.nextLine();
            }
        }

        sc.close();
    }

    // display main menu of application
    private static void displayMenu() {
        // Reset terminal color to default
        System.out.print("\033[0m");
        // Display menu options
        System.out.println("---------------------------------------------------");
        System.out.println("\t    MAIN MENU");
        System.out.println("\t1: Add an item");
        System.out.println("\t2: Display the collection");
        System.out.println("\t3: Remove an item");
        System.out.println("\t4: Edit an item");
        System.out.println("\t5: Load collection");
        System.out.println("\t6: Save collection");
        System.out.println("\t7: Exit");

        // Set terminal color to green and prompt user for input
        System.out.print("\033[0;32m");
        System.out.print("Enter your choice (1-7): ");
    }
}

