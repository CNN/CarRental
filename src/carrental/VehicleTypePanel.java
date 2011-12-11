package carrental;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 *
 * @author CNN
 * @version 2011-12-09
 */
public class VehicleTypePanel extends JPanel {

    JScrollPane descriptionScrollPane;
    JPanel centerPanel, buttonPanel, vehicleTypeNamePanel, pricePanel, descriptionPanel;
    JLabel vehicleTypeNameLabel, priceLabel, descriptionLabel;
    JTextField vehicleTypeNameField, priceField;
    JTextArea descriptionArea;
    JButton actionButton, backButton, deleteButton;
    final int defaultJTextFieldColumns = 20, strutDistance = 0;
    VehicleType selectedVehicleType;

    public VehicleTypePanel() {
        //Panel settings
        setLayout(new BorderLayout(0, 0));// no gaps between components.
        //Center Panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
        add(centerPanel, BorderLayout.CENTER);

        //ButtonPanels
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15)); //add some space between the right edge of the screen
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
        pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        pricePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        pricePanel.add(priceLabel);
        pricePanel.add(Box.createRigidArea(new Dimension(33 + strutDistance, 0)));
        pricePanel.add(priceField);
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
     * 
     * 
     * @param vehicleType The VehicleType to show in the returned centerPanel. If null - the text is blank.
     * @param backButton  The button to have the back/cancel functionality in the panel. If null - the buttons has no function.
     * @param actionButton The button to have the back/cancel functionality in the panel. If null - the button has no function.
     * @return 
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

    public ArrayList<JTextComponent> getTextComponents() {
        ArrayList<JTextComponent> array = new ArrayList<>();
        array.add(vehicleTypeNameField);
        array.add(priceField);
        array.add(descriptionArea);

        return array;
    }

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
