package FileExplorer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

/**
 * Created by Kristiina on 12.10.2015.
 *
 * How to make frames:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html#windowevents
 *
 * JList and File class, Loop to find all files and fill them into the list
 * https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
 * http://docs.oracle.com/javase/7/docs/api/java/io/File.html
 */



public class WindowFrame  {







    public static void GUI() {

        JFrame MainWindow = new JFrame("File Explorer"); //Creating and naming the window
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closing the window = exits the program


        String[] columnNames = {"File name",
                "Time created",
                "Size",
                "Random1",
                "Random2"};

        Object[][] data = {
                {"Folder1", "09.10.2015",
                        "1 KB", "", ""},
                {"Folder2", "10.10.2015",
                        "1 KB", "", ""}


        };

        //JTable table = new JTable(data, columnNames);

        JList list = new JList(columnNames);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);


        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250,80));





        MainWindow.setLayout(new BorderLayout());
        //MainWindow.add(table.getTableHeader(), BorderLayout.PAGE_START);
        MainWindow.add(list, BorderLayout.CENTER);

        Image icon = Toolkit.getDefaultToolkit().getImage("FolderIcon.png"); //Import icon
        MainWindow.setIconImage(icon);

        MainWindow.pack();
        MainWindow.setPreferredSize(new Dimension(1000, 750)); //SIze of the window
        MainWindow.setVisible(true); //Display the window

    }




    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI();

            }
        });
    }



}
