package ex44;

/*
 *  UCF COP3330 Fall 2021 Assignment 44 Solution
 *  Copyright 2021 Jenna Busch
 */

//pseudocode
//I will first need to create a new class to store all the products in
//In the main i will need to create a json reader object
//I will then need to get the json file
//Create an array list for the products to be stored into
//use a while loop to loop through the json file
//then use a while loop to go around sorting through the array until they input one in the dictionary 
//in the while loop take their input and search through the array list of product objects
//once we find the correct product print out its information
//then we end 

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class exercise44 {

    //class for the products
    public static class Product {
        private String name;
        private double price;
        private long quantity;

        public Product(String name, double price, long quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public long getQuantity() {
            return quantity;
        }

        public String toString(){
            return String.format("Name: %s\nprice: %.2f\nquantity: %d\n" ,getName(), getPrice(), getQuantity());
        }
    }



    public static void main(String[] args) throws IOException, ParseException {

        JsonReader reader = new JsonReader(new FileReader("src/main/java/ex44/exercise44_input.json"));

        reader.beginObject();
        reader.nextName();
        reader.beginArray();

        ArrayList<Product> products = new ArrayList<Product>();
        while (reader.peek() != JsonToken.END_ARRAY)
        {
            reader.beginObject();

            reader.nextName();
            String name = reader.nextString();
            reader.nextName();
            double price = reader.nextDouble();
            reader.nextName();
            int quantity = reader.nextInt();

            products.add(new Product(name, price, quantity));
            reader.endObject();
        }

        reader.endArray();
        reader.endObject();
        reader.close();


        Scanner input = new Scanner(System.in);

        boolean found = false;
        while (!found)
        {
            System.out.print("What is the product name? ");
            String searchParam = input.next();
            for (int i = 0; i < products.size(); i++)
            {
                if (searchParam.equals(products.get(i).getName()))
                {
                    found = true;
                    System.out.println(products.get(i));
                }
            }
            if (!found)
            {
                System.out.println("Sorry, that product was not found in our inventory.");
            }
        }
    }
}
