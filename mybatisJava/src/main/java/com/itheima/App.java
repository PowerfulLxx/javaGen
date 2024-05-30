package com.itheima;

import com.itheima.builder.BuildTable;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        BuildTable.getTables();
    }
}
