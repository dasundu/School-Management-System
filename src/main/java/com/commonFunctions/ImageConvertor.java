package com.commonFunctions;

import java.util.Base64;

import com.mysql.cj.jdbc.Blob;

public class ImageConvertor {
	
    // Method to convert a Blob image to a Base64 encoded String
    public static String getImage(Blob image) {
        byte[] imageData;            // Declare a byte array to hold image data
        String covertedImage = null; // Initialize the string that will hold the Base64 encoded image
        
        try {
            // Convert Blob to byte array
            imageData = image.getBytes(1, (int) image.length());

            // Encode the byte array to a Base64 encoded String
            covertedImage = Base64.getEncoder().encodeToString(imageData);

        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace if an exception occurs
        }

        return covertedImage; // Return the Base64 encoded image string
    }
}

