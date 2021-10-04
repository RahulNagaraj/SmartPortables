import java.sql.*;
import java.util.*;

public class MySqlDataStoreUtilities
{
    static Connection conn = null;

    public static void getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables","root","admin");
        }
        catch(Exception e) {
            System.out.println("Unable to connect to DB");
        }
    }


    public static void deleteOrder(int orderId,String orderName) {
        try {
            getConnection();
            String deleteOrderQuery ="Delete from customerOrders where OrderId=? and orderName=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1,orderId);
            pst.setString(2,orderName);
            pst.executeUpdate();
        }
        catch(Exception e) {

        }
    }

    public static void insertCustomerOrder(CustomerOrder order) {
        try {
            getConnection();
            String insertCustomerOrderQuery = "Insert into customerOrders (" +
                    "OrderId," +
                    "UserName," +
                    "OrderName," +
                    "OrderPrice," +
                    "streetAddress," +
                    "cityAddress," +
                    "stateAddress," +
                    "zipcode," +
                    "customerName," +
                    "creditCardNo," +
                    "hasWarranty," +
                    "deliveryMethod," +
                    "orderDate," +
                    "deliveryDate," +
                    "maxOrderCancellationDate" +
                    ") "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1,order.getOrderId());
            pst.setString(2,order.getUserName());
            pst.setString(3,order.getOrderName());
            pst.setDouble(4,order.getOrderPrice());
            pst.setString(5,order.getStreetAddress());
            pst.setString(6,order.getCityAddress());
            pst.setString(7,order.getStateAddress());
            pst.setString(8,order.getZipcode());
            pst.setString(9,order.getCustomerName());
            pst.setString(10,order.getCreditCardNo());
            pst.setString(11,order.isHasWarranty());
            pst.setString(12,order.getDeliveryMethod());
            pst.setString(13,order.getOrderDate());
            pst.setString(14,order.getDeliveryDate());
            pst.setString(15,order.getMaxOrderCancellationDate());

            System.out.println("insert customer order Query: " + pst.toString());

            pst.executeUpdate();
        }
        catch(Exception e) {
            System.out.println("Unable to insert customer order: " + e.getMessage() );
        }
    }

    public static HashMap<Integer, ArrayList<CustomerOrder>> selectOrder() {

        HashMap<Integer, ArrayList<CustomerOrder>> customerOrders = new HashMap<>();

        try {

            getConnection();
            //select the table 
            String selectOrderQuery ="select * from customerOrders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<CustomerOrder> orderList = new ArrayList<>();
            while(rs.next()) {
                if(!customerOrders.containsKey(rs.getInt("orderId"))) {
                    ArrayList<CustomerOrder> arr = new ArrayList<>();
                    customerOrders.put(rs.getInt("orderId"), arr);
                }
                ArrayList<CustomerOrder> listCustomerOrders = customerOrders.get(rs.getInt("orderId"));
                System.out.println("data is: " + rs.getInt("orderId") + customerOrders.get(rs.getInt("orderId")));

                //add to orderpayment hashmap
                CustomerOrder order = new CustomerOrder(
                        rs.getInt("orderId"),
                        rs.getString("userName"),
                        rs.getString("orderName"),
                        rs.getDouble("orderPrice"),
                        rs.getString("streetAddress"),
                        rs.getString("cityAddress"),
                        rs.getString("stateAddress"),
                        rs.getString("zipcode"),
                        rs.getString("customerName"),
                        rs.getString("hasWarranty"),
                        rs.getString("creditCardNo"),
                        rs.getString("deliveryMethod"),
                        rs.getString("orderDate"),
                        rs.getString("deliveryDate"),
                        rs.getString("maxOrderCancellationDate")
                );
                listCustomerOrders.add(order);
            }
        }
        catch(Exception e) {
            System.out.println("Unable to perform select customer order : " + e.getMessage());
        }
        return customerOrders;
    }


    public static void registerUser(String username, String password, String repassword, String usertype) {
        try
        {

            getConnection();
            String registerUserQuery = "Insert into registration(" +
                    "username," +
                    "password," +
                    "repassword," +
                    "usertype) "
                    + "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(registerUserQuery);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,repassword);
            pst.setString(4,usertype);
            pst.execute();
        }
        catch(Exception e) {

        }
    }

    public static HashMap<String, User> selectUser()
    {
        HashMap<String,User> hm=new HashMap<>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "Select * from  registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while(rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("usertype"));
                hm.put(rs.getString("username"), user);
            }
        }
        catch(Exception e) {
        }
        return hm;
    }

    public static HashMap<String, PetTracker> getPetTracker() {
        HashMap<String, PetTracker> hm = new HashMap<>();
        try {
            getConnection();

            String selectWirelessPlan="Select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectWirelessPlan);
            pst.setString(1,"petTracker");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                PetTracker petTracker = new PetTracker(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getString("productRebate"),
                        rs.getString("productDescription")
                );
                hm.put(rs.getString("productId"), petTracker);
                petTracker.setId(rs.getString("productId"));
                
                /*
                try
                {
                    String selectaccessory = "Select * from Product_accessories where productName=?";
                    PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
                    pstacc.setString(1,rs.getString("Id"));
                    ResultSet rsacc = pstacc.executeQuery();
                    
                    HashMap<String,String> acchashmap = new HashMap<String,String>();
                    while(rsacc.next())
                    {    
                        if (rsacc.getString("accessoriesName") != null)
                        {
                        
                        acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
                        con.setAccessories(acchashmap);
                        }
                    }					
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
                */
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String, VirtualReality> getVirtualReality()
    {
        HashMap<String,VirtualReality> hm = new HashMap<>();
        try {
            getConnection();

            String selectVirtualReality="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectVirtualReality);
            pst.setString(1,"virtualReality");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                VirtualReality virtualReality = new VirtualReality(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getString("productRebate"),
                        rs.getString("productDescription")
                );
                hm.put(rs.getString("productId"), virtualReality);
                virtualReality.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String, FitnessWatch> getFitnessWatches()
    {
        HashMap<String, FitnessWatch> hm = new HashMap<>();
        try
        {
            getConnection();

            String selectFitnessWatch="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectFitnessWatch);
            pst.setString(1,"Fitness Watch");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                FitnessWatch fitnessWatch = new FitnessWatch(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getString("productRebate"),
                        rs.getString("productDescription")
                );
                hm.put(rs.getString("productId"), fitnessWatch);
                fitnessWatch.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String,Headphone> getHeadphones() {
        HashMap<String,Headphone> hm=new HashMap<>();
        try {
            getConnection();

            String selectHeadphone="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectHeadphone);
            pst.setString(1,"Headphone");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                Headphone headphone = new Headphone(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getString("productRebate"),
                        rs.getString("productDescription")
                );
                hm.put(rs.getString("productId"), headphone);
                headphone.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String,Laptop> getLaptops()
    {
        HashMap<String,Laptop> hm=new HashMap<>();
        try {
            getConnection();

            String selectLaptop="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectLaptop);
            pst.setString(1,"Laptop");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                Laptop laptop = new Laptop(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getString("productRebate"),
                        rs.getString("productDescription")
                );
                hm.put(rs.getString("productId"), laptop);
                laptop.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String,Phone> getPhones()
    {
        HashMap<String,Phone> hm = new HashMap<>();
        try {
            getConnection();

            String selectPhone="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectPhone);
            pst.setString(1,"Phone");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                Phone phone = new Phone(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getString("productRebate"),
                        rs.getString("productDescription")
                );
                hm.put(rs.getString("productId"), phone);
                phone.setId(rs.getString("productId"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;
    }

    public static HashMap<String,SmartWatch> getSmartWatches()
    {
        HashMap<String,SmartWatch> hm=new HashMap<>();
        try {
            getConnection();

            String selectSmartWatch="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectSmartWatch);
            pst.setString(1,"Smart Watch");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                SmartWatch smartWatch = new SmartWatch(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getString("productRebate"),
                        rs.getString("productDescription")
                );
                hm.put(rs.getString("productId"), smartWatch);
                smartWatch.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String,VoiceAssistant> getVoiceAssistants()
    {
        HashMap<String,VoiceAssistant> hm=new HashMap<>();
        try {
            getConnection();

            String selectVoiceAssistant="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectVoiceAssistant);
            pst.setString(1,"Voice Assistant");
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                VoiceAssistant voiceAssistant = new VoiceAssistant(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getString("productRebate"),
                        rs.getString("productDescription")
                );
                hm.put(rs.getString("productId"), voiceAssistant);
                voiceAssistant.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static String addProduct(
            String productType,
            String productId,
            String productName,
            double productPrice,
            String productImage,
            String productManufacturer,
            String productCondition,
            double productDiscount,
            String productRebate,
            String productDescription,
            String prod
    ) {
        String msg = "Product is added successfully";
        try
        {
            getConnection();
            String addProductQurey = "Insert into productDetails(" +
                    "productId," +
                    "productType," +
                    "productName," +
                    "productPrice," +
                    "productImage," +
                    "productManufacturer," +
                    "productCondition," +
                    "productDiscount," +
                    "productRebate," +
                    "productDescription" +
                    ")" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?);";

            String name = productType;

            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1,productId);
            pst.setString(2,productType);
            pst.setString(3,productName);
            pst.setDouble(4,productPrice);
            pst.setString(5,productImage);
            pst.setString(6,productManufacturer);
            pst.setString(7,productCondition);
            pst.setDouble(8,productDiscount);
            pst.setString(9,productRebate);
            pst.setString(10,productDescription);

            pst.executeUpdate();
            try {
                if (!prod.isEmpty())
                {
                    String addProductAccessoriesQuery =  "Insert INTO  productAccessories(" +
                            "productName," +
                            "accessoriesName)" +
                            "VALUES (?,?);";
                    PreparedStatement pst1 = conn.prepareStatement(addProductAccessoriesQuery);
                    pst1.setString(1,prod);
                    pst1.setString(2,productId);
                    pst1.executeUpdate();
                }
            }
            catch(Exception e)
            {
                msg = "Erro while adding the product";
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            msg = "Erro while adding the product";
            e.printStackTrace();

        }
        return msg;
    }
    public static String updateProduct(
            String productType,
            String productId,
            String productName,
            double productPrice,
            String productImage,
            String productManufacturer,
            String productCondition,
            double productDiscount,
            String productRebate,
            String productDescription
    ) {
        String msg = "Product is updated successfully";
        try
        {
            getConnection();
            String updateProductQurey = "Update productDetails SET " +
                    "productType=?," +
                    "productName=?," +
                    "productPrice=?," +
                    "productImage=?," +
                    "productManufacturer=?," +
                    "productCondition=?," +
                    "productDiscount=?, " +
                    "productRebate=?, " +
                    "productDescription=? " +
                    "where productId =?;" ;
            PreparedStatement pst = conn.prepareStatement(updateProductQurey);

            pst.setString(1,productType);
            pst.setString(2,productName);
            pst.setDouble(3,productPrice);
            pst.setString(4,productImage);
            pst.setString(5,productManufacturer);
            pst.setString(6,productCondition);
            pst.setDouble(7,productDiscount);
            pst.setString(8,productRebate);
            pst.setString(9,productDescription);
            pst.setString(10,productId);
            pst.executeUpdate();
        }
        catch(Exception e) {
            msg = "Product cannot be updated";
            e.printStackTrace();
        }
        return msg;
    }

    public static String deleteProduct(String productId) {
        String msg = "Product is deleted successfully";
        try {
            getConnection();
            String deleteProductQuery ="Delete from productDetails where productId=?";
            PreparedStatement pst = conn.prepareStatement(deleteProductQuery);
            pst.setString(1,productId);
            pst.executeUpdate();
        }
        catch(Exception e) {
            msg = "Proudct cannot be deleted";
        }
        return msg;
    }

    public static List<Store> getAllStores() {
        List<Store> allStores = new ArrayList<>();
        try {
            getConnection();

            String getAllStoresQuery = "Select * from store";
            PreparedStatement pst = conn.prepareStatement(getAllStoresQuery);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Store store = new Store(
                        rs.getString("storeId"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zipcode")
                );

                allStores.add(store);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStores;
    }
}	