package fi.himmi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author himmi
 */
import java.sql.*;
import java.util.*;

public interface LajiDao<L,  K> {
    
    void create(L object) throws SQLException;
    L read(K key) throws SQLException;
    L update(L object) throws SQLException;
    void delete(K key) throws SQLException;
    List<L> list() throws SQLException;
}
