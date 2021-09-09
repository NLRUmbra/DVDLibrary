/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Controller.DVDLibraryController;
import Dao.DVDLibraryDao;
import UI.DVDLibraryView;
import UI.UserIO;
import Dao.DVDLibraryDaoFileImpl;
import UI.UserIOConsoleImpl;

/**
 *
 * @author Noah McElroy
 */
public class App {
    public static void main(String[] args){
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
}
