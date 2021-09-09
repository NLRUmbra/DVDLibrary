/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DTO.DVD;
import java.util.List;

/**
 *
 * @author Noah McElroy
 */
public class DVDLibraryView {
    private UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection() {
        // io.print("Main Menu");
        // io.print("1. List Students");
        // io.print("2. Create New Student");
        // io.print("3. View a Student");
        // io.print("4. Remove a Student");
        // io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        int[] releaseDate = io.readDate("Please enter DVD release date");
        String MPAARating = io.readString("Please enter MPAA rating");
        String directorName = io.readString("Please enter director's name");
        String studioName = io.readString("Please enter studio");
        String userRating = io.readString("Please enter user rating");

        return new DVD(title, releaseDate[0], releaseDate[1], releaseDate[2], MPAARating, directorName, studioName, userRating);
    }
    
    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        readEnter();
    }
    
    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            printDVDEntry(currentDVD);
        }
        readEnter();
    }
    
    public String getDVDChoice() {
        return io.readString("Please enter the DVD title");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            printDVDEntry(dvd);
        } else {
            io.print("No such DVD.");
        }
        readEnter();
    }
    
    public DVD editDVD(String title) {
        int[] newDate = io.readDate("Please enter new date");
        String newMPAARating = io.readString("Please enter new MPAA rating");
        String newDirectorName = io.readString("Please enter new director's name");
        String newStudio = io.readString("Please enter new studio's name");
        String newUserRating = io.readString("Please enter new user rating");
        return new DVD(title, newDate[0], newDate[1], newDate[2], newMPAARating, newDirectorName, newStudio, newUserRating);
    }
    
    private void printDVDEntry(DVD dvd) {
        io.print(dvd.getTitle());
        io.print("\tRelease Date: " + String.format("%02d", dvd.getMonth()) + "-" + String.format("%02d", dvd.getDay()) + "-" + dvd.getYear());
        io.print("\tMPAA Rating: " + dvd.getMPAARating());
        io.print("\tDirector: " + dvd.getDirectorName());
        io.print("\tStudio: " + dvd.getStudio());
        io.print("\tUser Rating: "+ dvd.getUserRating());
        io.print("");
    }
    
    private void readEnter(){
        io.readString("Please hit enter to continue.");
    }

    public void displayCreateDVDBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
