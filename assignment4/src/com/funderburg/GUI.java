package com.funderburg;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GUI extends JPanel {
    private boolean DEBUG = false;
    public static String CALHEADER = "";
    public static String CALTITLE = "Calendar";

    public GUI() {
        super(new GridLayout(1,0));

//        JTextField textField = new JTextField(10);
//        textField.setActionCommand("someting");
//        textField.addActionListener(this);

        String[] columnNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        Calendar cal = Calendar.getInstance();

        int year = 2018, month = 11, day = 19;

        int daysInMonth;

        // Get the number of days for the month
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            daysInMonth = 31;
        } else if (month == 2) {
            daysInMonth = 28;
        } else {
            daysInMonth = 30;
        }

        // Get the day of the week for the first day of the month
        String firstDayString = month + "/" + "01" + "/" + year;
        int firstDayOfMonth = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date myDate = null;
        try {
            myDate = sdf.parse(firstDayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("EEE");
        String sMyDate = sdf.format(myDate);
        System.out.println(sMyDate);

        switch (sMyDate) {
            case "Sun":
                firstDayOfMonth = 0;
                break;
            case "Mon":
                firstDayOfMonth = 1;
                break;
            case "Tue":
                firstDayOfMonth = 2;
                break;
            case "Wed":
                firstDayOfMonth = 3;
                break;
            case "Thu":
                firstDayOfMonth = 4;
                break;
            case "Fri":
                firstDayOfMonth = 5;
                break;
            case "Sat":
                firstDayOfMonth = 6;
                break;
        }

        // Get header for calendar
        switch (month) {
            case 1:
                CALHEADER = "January";
                break;
            case 2:
                CALHEADER = "February";
                break;
            case 3:
                CALHEADER = "March";
                break;
            case 4:
                CALHEADER = "April";
                break;
            case 5:
                CALHEADER = "May";
                break;
            case 6:
                CALHEADER = "June";
                break;
            case 7:
                CALHEADER = "July";
                break;
            case 8:
                CALHEADER = "August";
                break;
            case 9:
                CALHEADER = "September";
                break;
            case 10:
                CALHEADER = "October";
                break;
            case 11:
                CALHEADER = "November";
                break;
            case 12:
                CALHEADER = "December";
                break;
        }

        // Create calendar data for the table to be shown
        int x = 1, y = 0;
        Object[][] data = new Object[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (x <= daysInMonth && y >= firstDayOfMonth) {
                    data[i][j] = x;
                    x++;
                } else {
                    data[i][j] = "";
                }
                y++;
            }
        }

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 250));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);

        // ASKPROF:  Best method to add a header to the table (refer to https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#show)
        //           (Have a title with the calendar along a header above the default header of the DOW)
        // ASKPROF:  Incorporation of hash map to to contain memo data, maybe an array?
        // ASKPROF:  How to add a text area below or next to the table
        // ASKPROF:  Use of external calendar libraries: how to do that? Is that better than manually creating?
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame(CALTITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        GUI newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
