
import java.io.*;
import java.util.*;

public class CarList extends ArrayList<Car> {

//    List<Car> NEW_LIST2 = new ArrayList<>();

    BrandList brandList = new BrandList();

    public CarList(BrandList bList) {
        brandList = bList;
    }

        public boolean loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
//                System.out.println("Empty list");
                return false;
            } else {
                FileReader fr = new FileReader(filename);
                BufferedReader bf = new BufferedReader(fr);
                String line;
                while ((line = bf.readLine()) != null) {
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String carID = stk.nextToken().trim();
                    String brandID = stk.nextToken().trim();
                    String color = stk.nextToken().trim();
                    String frameID = stk.nextToken().trim();
                    String engineID = stk.nextToken().trim();
                    int pos = brandList.searchID(brandID);
                    Brand b = brandList.get(pos);

                    Car car = new Car(carID, b, color, frameID, engineID);
                    this.add(car);
                    
                }
//                System.out.println("End of file");
                bf.close();
                fr.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return true;
    }

    public boolean saveToFile(String filename) {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return false;
        }
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);

            for (Car x : this) {
                fw.write(x.getCarID() + ", " + x.getBrand().getBrandID() + ", " + x.getColour() + ", " + x.getFrameID() + ", " + x.getEngineID() + "\n");
            }
            System.out.println("Successfully wrote to the file.");
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return true;
    }

    public int searchID(String carID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {
            if (this.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {
            if (this.get(i).getFrameID().equals(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int n = this.size();
        for (int i = 0; i < n; i++) {
            if (this.get(i).getEngineID().equals(eID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        String carID = null;
        String colour;
        String frameID;
        String engineID;
        int i;
        boolean check = false;
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        //-----------
        while (check == false) {
            check = true;
            carID = "";
            while (carID.equals("")) {
                System.out.print("CarID: ");
                carID = sc.nextLine();
            }
            for (i = 0; i < this.size(); i++) {
                if (this.get(i).getCarID().equals(carID)) {
                    check = false;
                }
            }
        }
        //------------
        Brand b = (Brand) menu.ref_getChoice(brandList);
        //------------
        colour = "";
        while (colour.equals("")) {
            System.out.print("Colour: ");
            colour = sc.nextLine();
        }
        //------------        
        do {
            System.out.println("FrameID: ");
            frameID = sc.nextLine();
        } while (!frameID.matches("[fF][\\d]{5}"));
        //-----------
        do {
            System.out.println("engineID: ");
            engineID = sc.nextLine();
        } while (!engineID.matches("[eE][\\d]{5}"));
        //-----------
        Car car = new Car(carID, b, colour, frameID, engineID);
        this.add(car);
    }

    public void printBasedBrandName(String b) {
        int count = 0;
        int n = this.size();
        for (int i = 0; i < n; i++) {
            Car c = this.get(i);
            if (c.getBrand().brandName.contains(b)) {
                System.out.print(c.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        Scanner sc = new Scanner(System.in);
        String removedID = sc.nextLine();
        int pos = searchID(removedID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            this.remove(pos);
        }
        return true;
    }

    public boolean updateCar() {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        String colour, frameID, engineID;
        System.out.println("Enter the carID:");
        String updatedID = sc.nextLine();
        int pos = searchID(updatedID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            //------------
            Brand b = (Brand) menu.ref_getChoice(brandList);
            //------------
            colour = "";
            while (colour.equals("")) {
                System.out.print("Colour: ");
                colour = sc.nextLine();
            }
            //------------        
            do {
                System.out.println("FrameID: ");
                frameID = sc.nextLine();
            } while (!frameID.matches("[fF][\\d]{4}"));
            //-----------
            do {
                System.out.println("engineID: ");
                engineID = sc.nextLine();
            } while (!engineID.matches("[eE][\\d]{4}"));
            //-----------
            this.get(pos).setBrand(b);
            this.get(pos).setColour(colour);
            this.get(pos).setEngineID(engineID);
            this.get(pos).setFrameID(frameID);
        }
        return true;
    }

    public void listCars() {
        this.sort((Car o1, Car o2) -> {
            if (o1.getBrand().getBrandName().compareTo(o2.getBrand().getBrandName()) > 0) {
                return 1;
            } else if (o1.getBrand().getBrandName().compareTo(o2.getBrand().getBrandName()) < 0) {
                return -1;
            } else {
                return o1.getCarID().compareTo(o2.getCarID());
            }
        });
        int i;
        int n = this.size();
        for (i = 0; i < n; i++) {
            System.out.println(this.get(i).screenString());
        }
    }
}
