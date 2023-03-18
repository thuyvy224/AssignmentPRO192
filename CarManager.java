
import java.io.*;
import java.util.*;

public class CarManager {

    public static void main(String[] args) {
        ArrayList<String> ops = new ArrayList<>();
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");
//   List<Brand> NEW_LIST = new ArrayList<>();
        BrandList brandList = new BrandList();
        brandList.loadFromFile("D:\\PRO192\\CarPrj\\src\\brands.txt");
        CarList carList = new CarList(brandList);
        carList.loadFromFile("D:\\PRO192\\CarPrj\\src\\cars.txt");

        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            choice = menu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.print("Enter Brand's ID to search: ");
                    String search = sc.nextLine();
                    if (brandList.searchID(search) == -1) {
                        System.out.println("Not found");
                    } else {
                        System.out.println(brandList.get(brandList.searchID(search)));
                    }
                    break;
                case 4:
                    System.out.println("Enter Brand's ID to update: ");
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("brands.txt");
                    brandList.listBrands();
                    break;
                case 6:
                    carList.listCars();
                    break;
                ///////////////////////////
                case 7:
                    System.out.println("Enter a part of the brand name to search: ");
                    carList.printBasedBrandName(sc.nextLine());
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    System.out.println("Enter Car's ID to remove: ");
                    carList.removeCar();
                    break;
                case 10:
                    System.out.println("Enter Car's ID to update: ");
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile("cars.txt");
                    carList.listCars();
                    break;
            }
        } while (choice > 0 && choice <= 11);
    }
}
