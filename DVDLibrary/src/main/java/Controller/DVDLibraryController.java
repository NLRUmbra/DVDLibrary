/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Noah McElroy
 */
import Dao.DVDLibraryDao;
import Dao.DVDLibraryDaoException;
import DTO.DVD;
import UI.DVDLibraryView;
import UI.UserIO;
import UI.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author agrah
 */
public class DVDLibraryController {
    
    //private ClassRosterView view = new ClassRosterView();
    //private ClassRosterDao dao = new ClassRosterDaoFileImpl();
    private DVDLibraryView view;
    private DVDLibraryDao dao;
    private UserIO io = new UserIOConsoleImpl();
    
    public DVDLibraryController(DVDLibraryDao daoIn, DVDLibraryView viewIn){
        this.dao = daoIn;
        this.view = viewIn;
    }
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try{
            while(keepGoing){

                menuSelection = getMenuSelection();

                switch(menuSelection){
                    case 1: 
                        createDVD();                       
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        viewDVD(); //this searches for and displays dvd info
                                   //fulfills requirements 5. and 6.
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        }catch(DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.AddDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }
    
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String title = view.getDVDChoice();
        DVD removedDVD = dao.RemoveDVD(title);
        view.displayRemoveResult(removedDVD);
    }

    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditBanner();
        String title = view.getDVDChoice();
        DVD EditedDVD = dao.EditDVD(title);
    }
        
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dVDList = dao.ListAllDVD();
        view.displayDVDList(dVDList);
    }
    
    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        String title = view.getDVDChoice();
        DVD dvd = dao.DisplayDVDDetails(title);
        view.displayDVD(dvd);
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage(){
        view.displayExitBanner();
    }
}
