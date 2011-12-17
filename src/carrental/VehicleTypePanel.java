package carrental;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * This class provides a panel with an overview of a vehicle types attributes.
 * The panel can be customized with up to 3 buttons and can be set to show information about a certain vehicle type.
 * @author CNN
 * @version 2011-12-09
 */
public class VehicleTypePanel extends JPanel {

    private JTextField vehicleTypeNameField, priceField;
    private JTextArea descriptionArea;
    private JButton actionButton, backButton, deleteButton;

    /**
     * Sets up a basic panel with blank text fields.
     */
    public VehicleTypePanel() {

        JLabel vehicleTypeNameLabel, priceLabel, dkkLabel, descriptionLabel;
        JPanel centerPanel, buttonPanel, vehicleTypeNamePanel, pricePanel, descriptionPanel;
        JScrollPane descriptionScrollPane;
        final int defaultJTextFieldColumns = 20, strutDistance = 0;

        //Panel settings
        setLayout(new BorderLayout(0, 0));

        //Center Panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
        add(centerPanel, BorderLayout.CENTER);

        //ButtonPanels
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        buttonPanel.add(Box.createHorizontalGlue());

        //Vehicle type name
        vehicleTypeNameLabel = new JLabel("Vehicle Type Name");
        vehicleTypeNameField = new JTextField(defaultJTextFieldColumns);
        vehicleTypeNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        vehicleTypeNamePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        vehicleTypeNamePanel.add(vehicleTypeNameLabel);
        vehicleTypeNamePanel.add(Box.createRigidArea(new Dimension(strutDistance, 0)));
        vehicleTypeNamePanel.add(vehicleTypeNameField);
        centerPanel.add(vehicleTypeNamePanel);

        //Price per day
        priceLabel = new JLabel("Price per day");
        priceField = new JTextField(defaultJTextFieldColumns);
        dkkLabel = new JLabel("DKK");
        pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pricePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        pricePanel.add(priceLabel);
        pricePanel.add(Box.createRigidArea(new Dimension(33 + strutDistance, 0)));
        pricePanel.add(priceField);
        pricePanel.add(dkkLabel);
        centerPanel.add(pricePanel);

        //Additional Comment
        descriptionLabel = new JLabel("Description");
        descriptionArea = new JTextArea(3, 30);
        descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        descriptionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(Box.createRigidArea(new Dimension(43 + strutDistance, 0)));
        descriptionPanel.add(descriptionScrollPane);
        centerPanel.add(descriptionPanel);

        //cancel-Button
        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        //Delete-Button
        deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        //create-button
        actionButton = new JButton("Action");
        buttonPanel.add(actionButton);
    }

    /**
     * Sets the panel to show the given parameters.
     * @param vehicleType This is the vehicle type to show information about. If null, the text fields will be blank.
     * @param backButton The button positioned the furthest to the left. If null, the button will not be shown.
     * @param deleteButton The button positioned in the middle. If null, the button will not be shown.
     * @param actionButton The button positioned the furthest to the left. If null, the button will not be shown.
     */
    public void setPanel(VehicleType vehicleType, JButton backButton, JButton deleteButton, JButton actionButton) {
        resetPanel();
        if (vehicleType != null) {
            vehicleTypeNameField.setText(vehicleType.getName());
            priceField.setText(Integer.toString(vehicleType.getPricePerDay()));
            descriptionArea.setText(vehicleType.getDescription());
        }
        if (backButton != null) {
            this.backButton.setText(backButton.getText());
            for (ActionListener a : backButton.getActionListeners()) {
                this.backButton.addActionListener(a);
            }
            this.backButton.setVisible(true);
        }
        if (deleteButton != null) {
            this.deleteButton.setText(deleteButton.getText());
            for (ActionListener a : deleteButton.getActionListeners()) {
                this.deleteButton.addActionListener(a);
            }
            this.deleteButton.setVisible(true);
        }
        if (actionButton != null) {
            this.actionButton.setText(actionButton.getText());
            for (ActionListener a : actionButton.getActionListeners()) {
                this.actionButton.addActionListener(a);
            }
            this.actionButton.setVisible(true);
        }
    }

    /**
     * Returns an array with the text components from the main panel.
     * These text fields can contain text or be blank.
     * 
     * @return An array of text components regarding a vehicle type. 
     * The array will have the name at index 0, the price at index 1 and the description at index 2.
     */
    public ArrayList<JTextComponent> getTextComponents() {
        ArrayList<JTextComponent> array = new ArrayList<>();
        array.add(vehicleTypeNameField);
        array.add(priceField);
        array.add(descriptionArea);

        return array;
    }

    /**
     * Resets the panels text fields to be blank. Also strips the buttons for actionlisteners and sets them invisible.
     */
    private void resetPanel() {
        vehicleTypeNameField.setText(null);
        priceField.setText(null);
        descriptionArea.setText(null);
        backButton.setText("Back");
        for (ActionListener a : backButton.getActionListeners()) {
            backButton.removeActionListener(a);
        }
        backButton.setVisible(false);
        deleteButton.setText("Delete");
        for (ActionListener a : deleteButton.getActionListeners()) {
            deleteButton.removeActionListener(a);
        }
        deleteButton.setVisible(false);
        actionButton.setText("Action");
        for (ActionListener a : actionButton.getActionListeners()) {
            actionButton.removeActionListener(a);
        }
        actionButton.setVisible(false);
    }
}
