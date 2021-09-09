/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import DTO.DVD;
import java.util.List;
/**
 *
 * @author Noah McElroy
 */
public interface DVDLibraryDao {
    DVD AddDVD(String title, DVD dvd) throws DVDLibraryDaoException;
    
    DVD RemoveDVD(String title) throws DVDLibraryDaoException;
    
    DVD EditDVD(String title) throws DVDLibraryDaoException;
    
    DVD DisplayDVDDetails(String title) throws DVDLibraryDaoException;
    
    List<DVD> ListAllDVD() throws DVDLibraryDaoException;
}
