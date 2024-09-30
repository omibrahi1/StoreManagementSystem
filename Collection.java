import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Collection class
public class Collection implements Serializable {

    // Define the serialVersionUID
    private static final long serialVersionUID = 1L;

    // items list
    private List<Item> items;

    // default constructor
    public Collection() {
        items = new ArrayList<>();
    }

    // constructor that takes list of items as a parameter
    public Collection(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    //  Getter for the items list
    public List<Item> getItems() {
        return items;
    }

    // Setter for the items list
    public void setItems(List<Item> items) {
        this.items = items;
    }

    // Define the method to remove an item from the collection
    public void removeItem(int ID) {

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == ID) {
                items.remove(i);
                System.out.print("\033[0;31m");
                System.out.println("Item with ID: " + ID + " has been removed");
                System.out.print("\033[0m");
                return;
            }
        }

        System.out.println("Item does not exist with ID: " + ID);
    }


    public void editItem(int ID) {

        boolean found = false;

        for (Item item : items) {
            if (item.getId() == ID) {
                found = true;

                System.out.print("\033[0m");
                System.out.println("What do you want to edit?");
                System.out.println("1- Name");
                System.out.println("2- Description");
                System.out.println("3- Price");
                System.out.println("4- Quantity");
                System.out.println("5- Type");
                System.out.print("> ");

                System.out.print("\033[0;32m");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                System.out.print("\033[0m");
                switch (choice) {
                    case 1:
                        System.out.print("Enter the new name: ");
                        item.setName(sc.next());
                        break;
                    case 2:
                        System.out.print("Enter the new description: ");
                        item.setDescription(sc.next());
                        break;
                    case 3:
                        System.out.print("Enter the new price: ");

                        while (true) {
                            try {
                                item.setPrice(sc.nextDouble());
                                break;
                            } catch (InputMismatchException e) {
                                System.out.print("Invalid input! Enter a positive number: ");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 4:
                        System.out.print("Enter the new quantity: ");

                        while (true) {
                            try {
                                item.setQuantity(sc.nextInt());
                                break;
                            } catch (InputMismatchException e) {
                                System.out.print("Invalid input! Enter a positive integer: ");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Enter the new type: ");

                        while (true) {
                            try {
                                item.setType(ItemType.valueOf(sc.next().toUpperCase()));
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.print("Invalid input! Enter a valid type: ");
                                sc.nextLine();
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
                System.out.print("\033[0;31m");
                System.out.println("Item Updated Successfully");
                System.out.print("\033[0;37m");
            }

        }
        if (!found) {
            System.out.println("Item does not exist with ID: " + ID);
        }
    }


    public void saveCollection() {
        try {

//            FileWriter fileWriter = new FileWriter("items.txt");
            FileOutputStream fileOutputStream = new FileOutputStream("items.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Item item : items) {
                String line = String.format("%d,%s,%s,%.2f,%d,%s", item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getQuantity(), item.getType());
                objectOutputStream.writeObject(line);
                objectOutputStream.flush();
            }



        } catch (IOException e) {

            e.printStackTrace();
        }


        System.out.print("\033[0;31m");
        System.out.println("Items Saved successfully");

        System.out.print("\033[0;37m");
    }


    public void loadCollection() {
        try {

            FileInputStream fileReader = new FileInputStream("items.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileReader);

            while (true) {
                try {
                    String line = (String) objectInputStream.readObject();
                    String[] data = line.split(",");
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String description = data[2];
                    double price = Double.parseDouble(data[3]);
                    int quantity = Integer.parseInt(data[4]);
                    ItemType type = ItemType.valueOf(data[5].trim());
                    Item item = new Item(id, name, description, price, quantity, type);
                    if(!items.contains(item)) {
                        this.items.add(item);
                    }else {
                        return;
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    break;
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.print("\033[0;31m");
        System.out.println("Items loaded successfully");
        System.out.print("\033[0;37m");
    }


    public void displayCollection() {

        if (items.isEmpty()) {
            System.out.println("No items in the collection");
        } else {

            System.out.println("\033[31m");
            System.out.println("\t\t\t\t\t\tITEMS IN THE COLLECTION");

            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            System.out.println("ID\tName\t\t\t\tPrice\t\tQuantity\tType\t\tDescription");
            System.out.println("\033[37m");

            for (Item item : items) {
                System.out.print(item.getId());

                for (int i = 0; i < 4 - String.valueOf(item.getId()).length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(item.getName());
                for (int i = 0; i < 20 - item.getName().length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(item.getPrice());
                for (int i = 0; i < 12 - String.valueOf(item.getPrice()).length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(item.getQuantity());
                for (int i = 0; i < 12 - String.valueOf(item.getQuantity()).length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(item.getType());
                for (int i = 0; i < 16 - String.valueOf(item.getType()).length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(item.getDescription());
                for (int i = 0; i < 8 - item.getDescription().length(); i++) {
                    System.out.print(" ");
                }
                System.out.println();

            }
            System.out.println();
        }

    }
}


