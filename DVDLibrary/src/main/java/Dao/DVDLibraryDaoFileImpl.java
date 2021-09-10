/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DTO.DVD;
import UI.UserIO;
import UI.UserIOConsoleImpl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Noah McElroy
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    private UserIO io = new UserIOConsoleImpl();
    Map<String, DVD> dvds = new HashMap<>();
    public static final String DVDCollection_FILE = "DVDCollection.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD AddDVD(String title, DVD dvd) throws DVDLibraryDaoException {
            loadDVDCollection();
            DVD newDVD = dvds.put(title, dvd);
            writeDVDCollection();
            return newDVD;
    }

    @Override
    public DVD RemoveDVD(String title) throws DVDLibraryDaoException {
            loadDVDCollection();
            DVD removedDVD = dvds.remove(title);
            writeDVDCollection();
            return removedDVD;
    }

    @Override
    public DVD EditDVD(String title) throws DVDLibraryDaoException {
        loadDVDCollection();
        while(!dvds.containsKey(title)){
          title = io.readString("previous title is not in collection please enter the DVD title");
        }
        io.print("What would you like to change");
        io.print("1. Edit title");
        io.print("2. Edit date");
        io.print("3. Edit MPAA Rating");
        io.print("4. Edit Directors name");
        io.print("5. Edit Studio");
        io.print("6: Edit user rating");
        DVD EditedDVD = dvds.get(title);
        int choice =io.readInt("Please select from the above choices.", 1, 6);
        switch(choice){
            case 1:
               //EditedDVD.setTitle(io.readString("Enter the edited title"));
               //dvds.remove(title);
               String newTitle =io.readString("Enter the edited title");
               DVD DVDFromFile = new DVD(newTitle,EditedDVD.getMonth(),EditedDVD.getDay(),EditedDVD.getYear(),EditedDVD.getMPAARating(),EditedDVD.getDirectorName(), EditedDVD.getStudio(), EditedDVD.getUserRating());
               dvds.remove(title);
               dvds.put(newTitle, DVDFromFile);
               break;
            case 2:
                int month = io.readInt("Please enter the month",1,12);
                int day = io.readInt("Please enter the day",1,31);
                int year = io.readInt("Please enter the year");
                EditedDVD.setDay(day);
                EditedDVD.setMonth(month);
                EditedDVD.setYear(year);
                break;
            case 3:
                EditedDVD.setMPAARating(io.readString("Please Enter the new MPAA rating"));
                break;
            case 4:
                EditedDVD.setDirectorName(io.readString("Please Enter the new director's name"));
                break;
            case 5:
                EditedDVD.setStudio(io.readString("Please enter the new studio"));
                break;
            case 6:
                EditedDVD.setUserRating(io.readString("Please enter the new user rating"));
                break;
            default:
                break;
        }
        writeDVDCollection();
        return EditedDVD;
    }

    @Override
    public DVD DisplayDVDDetails(String title) throws DVDLibraryDaoException {
        loadDVDCollection();
        return dvds.get(title);
    }

    @Override
    public List<DVD> ListAllDVD() throws DVDLibraryDaoException {
        loadDVDCollection();
        return new ArrayList(dvds.values());
    }
    private DVD unmarshallDVD(String DVDAsText){
  
        String[] DVDTokens = DVDAsText.split(DELIMITER);
        DVD DVDFromFile = new DVD(DVDTokens[0],Integer.parseInt(DVDTokens[1]),Integer.parseInt(DVDTokens[2]),Integer.parseInt(DVDTokens[3]),DVDTokens[4],DVDTokens[5],DVDTokens[6],DVDTokens[7]);
        return DVDFromFile;
    
    }
    private String marshallDVD(DVD aDVD){

        String DVDAsText = aDVD.getTitle() + DELIMITER;

        DVDAsText += aDVD.getMonth() + DELIMITER;

        DVDAsText += aDVD.getDay() + DELIMITER;

        DVDAsText += aDVD.getYear() + DELIMITER;
        DVDAsText += aDVD.getMPAARating() + DELIMITER;
        DVDAsText += aDVD.getDirectorName()+ DELIMITER;
        DVDAsText += aDVD.getStudio()+ DELIMITER;
        DVDAsText += aDVD.getUserRating();

    
        return DVDAsText;
    } 
    private void loadDVDCollection() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVDCollection_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load dvd data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent student unmarshalled
        DVD currentDVD;
        // Go through DVDCollection_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the title as the map key for our DVD object.
            // Put currentDVD into the map using title as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    private void writeDVDCollection() throws DVDLibraryDaoException {
  
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVDCollection_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save dvd data.", e);
        }

    
        String DVDAsText;
        List<DVD> DVDList = this.ListAllDVD();
        for (DVD currentDVD : DVDList) {
            // turn a DVD into a String
            DVDAsText = marshallDVD(currentDVD);
            // write the DVD object to the file
            out.println(DVDAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
