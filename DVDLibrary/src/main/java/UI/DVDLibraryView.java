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
    public DVDLibraryView(UserIO io){
        this.io = io;
    }
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD to the collection");
        io.print("2. Remove a DVD from the collection");
        io.print("3. Edit a DVD in the collection");
        io.print("4. View all DVDs in the collection");
        io.print("5. View information about a specific DVD");
        io.print("6. Exit.");

        return io.readInt("Please select from the above choices.", 1, 6);
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
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
     public void displayCreateDVDBanner() {
        io.print("=== Add new DVD ===");
    }
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created.  Please hit enter to continue");
    }
    public void displayRemoveDVDBanner () {
        io.print("=== Remove a DVD ===");
    }
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    public void displayEditBanner() {
        io.print("=== Edit A DVD ==="); 
    }
    
    public void displayDVDInfoBanner() {
        io.print("=== Display DVD Information ===");
    }

}
