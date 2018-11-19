package com.funderburg;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class GUI extends JPanel {
    private boolean DEBUG = false;

    public GUI() {
        super(new GridLayout(1,0));

        String[] columnNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        Calendar c = Calendar.getInstance();
//        c.setTime(yourDate);

        int year = 2018, month = 11;

        int daysInMonth;

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||month == 10 || month == 12) {
            daysInMonth = 31;
        } else if (month = 2) {
            daysInMonth = 28;
        } else {
            daysInMonth = 30;
        }


        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        Object[][] data = {
                {"Kathy", "Smith", "Snowboarding", 5, false, "doo", "lee"},
                {"John", "Doe", "Rowing", 3, true, "doo", "lee"},
                {"Sue", "Black", "Knitting", 2, false, "doo", "lee"},
                {"Jane", "White", "Speed reading", 20, true, "doo", "lee"},
                {"Joe", "Brown", "Pool", 10, false, "doo", "lee"}
        };

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
        JFrame frame = new JFrame("Calendar");
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
