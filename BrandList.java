
import java.io.*;
import java.util.*;

public class BrandList extends ArrayList<Brand> {
//    List<Brand> NEW_LIST = new ArrayList<>();
    
    public BrandList() {
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
                    StringTokenizer stk = new StringTokenizer(line, ",:");
//                  StringTokenizer stk2 = new StringTokenizer(details, ":");
                    String id = stk.nextToken().trim();
                    String name = stk.nextToken().trim();
                    String sound = stk.nextToken().trim();
//                    sound = sound.substring(0, sound.indexOf(":"));
//                    stk2.nextToken();
                    double price = Double.parseDouble(stk.nextToken().trim());
                    Brand brand = new Brand(id, name, sound, price);
                    this.add(brand);

                }
//                System.out.println("End of file");
                bf.close();
                fr.close();
            }
        } catch (IOException | NumberFormatException e) {
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
            for (Brand x : this) {
                fw.write(x.getBrandID() + ", " + x.getBrandName() + ", " + x.getSoundBrand() + ": " + x.getPrice() + "\n");
            }
            System.out.println("Successfully wrote to the file.");
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return true;
    }
    
    public int searchID(String bID){
        int n=this.size();        
        for (int i=0;i<n;i++){
            if (this.get(i).getBrandID().equalsIgnoreCase(bID)) 
                return i;
        }
        return -1;
    }
    
    public Brand getUserChoice(){
         Menu menu=new Menu();
         return (Brand)menu.ref_getChoice(this);
    }
    
    public void addBrand() {
        String id = "";
        String bName;
        String sBrand;
        double price;
        int i;
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        //---------------
        while (check == false) {
            check = true;
            id = "";
            while (id.equals("")) {
                System.out.print("ID: ");
                id = sc.nextLine();
            }
            for (i = 0; i < this.size(); i++) {
                if (this.get(i).getBrandID().equals(id)) {
                    check = false;
                }
            }
        }
        //------------
        bName = "";
        while (bName.equals("")) {
            System.out.print("Brand name: ");
            bName = sc.nextLine();
        }
        //------------
        sBrand = "";
        while (sBrand.equals("")) {
            System.out.print("Sound brand: ");
            sBrand = sc.nextLine();
        }
        //-----------
        sc = new Scanner(System.in);
        price = 0;
        do {
            System.out.print("Price:");
            price = sc.nextDouble();
        } while (price <= 0);
        Brand brand = new Brand(id, bName, sBrand, price);
        this.add(brand);
        System.out.println("Brand added successfully!");
    }
    
    public void updateBrand(){
        Scanner sc = new Scanner(System.in);
        String brandID;
        System.out.print("Enter the brandID:");
        brandID= sc.nextLine();
        int Pos = searchID(brandID);
        if(Pos<0){
            System.out.println("No found!");
        }else {
        System.out.println("New brand name:");
        String brandName = sc.nextLine();
        System.out.println("New sound brand:");
        String soundBrand = sc.nextLine();
        double price;
        do {
            System.out.println("New price (must be greater than 0):");
            price = sc.nextDouble();
        } while (price <= 0);

        this.get(Pos).setBrandName(brandName);
        this.get(Pos).setSoundBrand(soundBrand);
        this.get(Pos).setPrice(price);

        System.out.println("Brand updated successfully!");
    }
    }

    public void listBrands() {
        int i;
        int n = this.size();
        for (i = 0; i < n; i++) {
            System.out.println(this.get(i).toString());
        }
    }
}
