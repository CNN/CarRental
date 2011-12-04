package carrental;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This is the main panel regarding vehicles.
 * It contains JPanels for every relevant screen, when dealing with vehicles.
 * These are implemented as inner classes.
 * @author CNN
 */
public class VehicleTypePanel extends SuperPanel {

    private JPanel mainScreenPanel, createPanel, viewPanel, centerPanel_create;

    public VehicleTypePanel() {
        //Sets the different subpanels (defined as inner classes below). Also adds them to this object with JPanel.add().
        RemakeAll();
        AssignAndAddSubPanels(mainScreenPanel, createPanel, viewPanel, null, null);
        //Removes the default gaps between components
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBackground(Color.BLACK);
        showMainScreenPanel();
    }

    //Temporary Main
    public static void main(String[] args) {
        JFrame frame = new JFrame("VehicleTypeFrame");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.add(new VehicleTypePanel());
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void makeMainScreenPanel() {
        mainScreenPanel = new JPanel();
        JButton createButton, listButton, viewTypeButton;
        JPanel centerPanel, buttonFlowPanel, buttonGridPanel;
        TitledBorder titleBorder;
        //Panel settings
        mainScreenPanel.setLayout(new BorderLayout());
        titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Vehicle type (Overview)");
        mainScreenPanel.setBorder(titleBorder);

        centerPanel = new JPanel();
        mainScreenPanel.add(centerPanel, BorderLayout.CENTER);
        //Button panel with a gridlayout for vertical alignment.
        buttonGridPanel = new JPanel();
        buttonGridPanel.setLayout(new BoxLayout(buttonGridPanel, BoxLayout.PAGE_AXIS));
        //extra buttonpanel with a default flowlayout, to shrink the button to minimum size,
        buttonFlowPanel = new JPanel();
        buttonFlowPanel.add(buttonGridPanel);
        centerPanel.add(buttonFlowPanel);
        //Colors
        mainScreenPanel.setBackground(new Color(216, 216, 208));
        centerPanel.setBackground(new Color(239, 240, 236));
        //Create-button
        createButton = new JButton("Create a new vehicle type");
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showCreatePanel();
            }
        });

        buttonGridPanel.add(createButton);
        //Create some blank space between the buttons:
        buttonGridPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //View vehicle-Button
        viewTypeButton = new JButton("View a vehicle type");
        viewTypeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showViewEntityPanel();
            }
        });
        buttonGridPanel.add(viewTypeButton);
    }

    @Override
    public void makeCreatePanel() {
        createPanel = new JPanel();
        JPanel buttonPanel, vehicleTypeNamePanel, pricePanel, descriptionPanel;
        JScrollPane centerScrollPane;
        JLabel vehicleTypeNameLabel, priceLabel, descriptionLabel;
        JTextField vehicleTypeNameField, priceField;
        JTextArea descriptionArea;
        JButton createButton, cancelButton;
        final int defaultJTextFieldColumns = 20, strutDistance = 0;

        //Panel settings
        createPanel.setLayout(new BorderLayout());
        createPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Create a vehicle type"));
        //Center Panel
        centerPanel_create = new JPanel();
        centerPanel_create.setLayout(new BoxLayout(centerPanel_create, BoxLayout.PAGE_AXIS));
        centerPanel_create.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));

        //Center ScrollPane
        centerScrollPane = new JScrollPane(centerPanel_create);
        //Add the scrollpane to the mainPanel of the Create-functionality
        createPanel.add(centerScrollPane, BorderLayout.CENTER);

        //Colors
        createPanel.setBackground(new Color(216, 216, 208));

        //Vehicle type name
        vehicleTypeNameLabel = new JLabel("Vehicle Type Name");
        vehicleTypeNameField = new JTextField(defaultJTextFieldColumns);
        vehicleTypeNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        vehicleTypeNamePanel.add(Box.createHorizontalStrut(5));
        vehicleTypeNamePanel.add(vehicleTypeNameLabel);
        vehicleTypeNamePanel.add(Box.createHorizontalStrut(strutDistance));
        vehicleTypeNamePanel.add(vehicleTypeNameField);
        centerPanel_create.add(vehicleTypeNamePanel);

        //Price per day
        priceLabel = new JLabel("Price per day");
        priceField = new JTextField(defaultJTextFieldColumns);
        pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pricePanel.add(Box.createHorizontalStrut(5));
        pricePanel.add(priceLabel);
        pricePanel.add(Box.createHorizontalStrut(33 + strutDistance));
        pricePanel.add(priceField);
        centerPanel_create.add(pricePanel);

        //Additional Comment
        descriptionLabel = new JLabel("Description");
        descriptionArea = new JTextArea(3, 30);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        descriptionPanel.add(Box.createHorizontalStrut(5));
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createHorizontalStrut(43 + strutDistance));
        descriptionPanel.add(descriptionArea);
        centerPanel_create.add(descriptionPanel);
        
        //ButtonPanels
        buttonPanel = new JPanel();
        //Add the scrollpane to the mainPanel of the Create-functionality
        createPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
        buttonPanel.add(Box.createHorizontalGlue());
        //cancel-Button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO NICLASONLY Set text() for all fields aka blank
                showMainScreenPanel();
            }
        });
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(5));

        //create-button
        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO NICLASONLY make the database update here
            }
        });
        buttonPanel.add(createButton);
    }

    @Override
    public void makeViewEntityPanel() {
        viewPanel = new JPanel();
        JScrollPane centerScrollPane;
        JPanel centerPanel, buttonPanel, vehicleTypeNamePanel, pricePanel, descriptionPanel;
        JLabel vehicleTypeNameLabel, priceLabel, descriptionLabel;
        JTextField vehicleTypeNameField, priceField;
        JTextArea descriptionArea;
        String vehicleTypeNameString, priceString, descriptionString;
        JButton createButton, cancelButton;
        final int defaultJTextFieldColumns = 20, strutDistance = 0;

        //Panel settings
        viewPanel.setLayout(new BorderLayout());
        viewPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Create a vehicle type"));
        //Center Panel
        centerScrollPane = new JScrollPane();
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));

        //Center ScrollPane
        centerScrollPane.getViewport().add(centerPanel);
        viewPanel.add(centerScrollPane, BorderLayout.CENTER);

        //ButtonPanels
        buttonPanel = new JPanel();
        viewPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
        buttonPanel.add(Box.createHorizontalGlue());


        //Colors
        viewPanel.setBackground(new Color(216, 216, 208));
        centerPanel.setBackground(new Color(239, 240, 236));

        //Vehicle type name
        vehicleTypeNameLabel = new JLabel("Vehicle Type Name");
        vehicleTypeNameString = "SUPERMEGANICECAR (example)";
        vehicleTypeNameField = new JTextField(vehicleTypeNameString, defaultJTextFieldColumns);
        vehicleTypeNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        vehicleTypeNamePanel.add(Box.createHorizontalStrut(5));
        vehicleTypeNamePanel.add(vehicleTypeNameLabel);
        vehicleTypeNamePanel.add(Box.createHorizontalStrut(strutDistance));
        vehicleTypeNamePanel.add(vehicleTypeNameField);
        centerPanel.add(vehicleTypeNamePanel);

        //Price per day
        priceLabel = new JLabel("Price per day");
        priceString = "1337 (Example)";
        priceField = new JTextField(priceString, defaultJTextFieldColumns);
        pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pricePanel.add(Box.createHorizontalStrut(5));
        pricePanel.add(priceLabel);
        pricePanel.add(Box.createHorizontalStrut(33 + strutDistance));
        pricePanel.add(priceField);
        centerPanel.add(pricePanel);

        //Additional Comment
        descriptionLabel = new JLabel("Description");
        descriptionString = "This is a description. \n Bla bla. \n (example)";
        descriptionArea = new JTextArea(descriptionString, 3, 30);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        descriptionPanel.add(Box.createHorizontalStrut(5));
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createHorizontalStrut(43 + strutDistance));
        descriptionPanel.add(descriptionArea);
        centerPanel.add(descriptionPanel);

        //cancel-Button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO NICLASONLY Set text() for all fields aka blank
                showMainScreenPanel();
            }
        });
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(5));

        //create-button
        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO NICLASONLY make the database update here
            }
        });
        buttonPanel.add(createButton);
    }

    @Override
    public void makeAddTypePanel() {
    }

    ;


    @Override
    public void makeListPanel() {
    }

    ;
    

    public JPanel getCenterJPanel_create() {
        return centerPanel_create;
    }
}
