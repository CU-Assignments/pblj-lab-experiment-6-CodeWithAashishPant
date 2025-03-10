To develop a Java program that processes a large dataset of products using Streams class to:
  - Group products by category
  - Find the most expensive product in each category
  - Calculate the average price of all products


Instruction
Step 1: Create the Product Class
- Define a Product class with attributes:
    name (String)
    category (String)
    price (double)
  
or (Reads product data from a CSV file)
For Example: "Laptop", "Electronics", 1200
             "Phone", "Electronics", 800


Step 2: Create the ProductProcessor Class
- Create a list of products with multiple categories and prices.
- Use Streams API to:
    Group products by category using Collectors.groupingBy().
    Find the most expensive product in each category using Collectors.maxBy().
    Calculate the average price of all products using Collectors.averagingDouble().
- Display the results.


import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name;
    String category;
    double price;
    
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Phone", "Electronics", 800),
            new Product("TV", "Electronics", 1500),
            new Product("Jeans", "Clothing", 60),
            new Product("T-Shirt", "Clothing", 25),
            new Product("Sneakers", "Footwear", 90),
            new Product("Boots", "Footwear", 120),
            new Product("Sandals", "Footwear", 120)
        );
        
        // Group products by category
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));
        
        // Find most expensive product in each category
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));
        
        // Calculate the average price of all products
        double averagePrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));
        
        // Display grouped products
        System.out.println("Grouped Products:");
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });
        
        // Display most expensive product in each category
        System.out.println("\nMost Expensive Product per Category:");
        mostExpensiveByCategory.forEach((category, product) -> 
            System.out.println(category + ": " + product.orElse(null))
        );
        
        // Display average price
        System.out.println("\nAverage Price of All Products: $" + averagePrice);
    }
}


  
    Test Cases
    Test Case	                                     Input Data	                                                                           Expected Output
    Case 1: Normal Case             	     Sample dataset with Electronics, Clothing, and Footwear	                      Grouped products, Most Expensive per category, Average price
    Case 2: Single Category Only           All products in "Electronics"	                                                One category, Most Expensive in Electronics, Average of all
    Case 3: Same Price in a Category	     Two products with the same highest price in "Footwear"	                        Any of the most expensive ones is displayed
    Case 4: Only One Product	             One product "Laptop" in "Electronics"	                                        Laptop as the most expensive, Laptop as the only average
    Case 5: Empty List	                   No products	                                                                  No grouping, No most expensive product, Average price = 0
