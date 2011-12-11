package carrental;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author CNN
 */
public abstract class SuperPanel extends JPanel {

    // private JPanel mainScreenPanel, createPanel, viewEntityPanel, addTypePanel, listPanel;
    private ArrayList<JPanel> panels;
    private int mainScreen, create, viewEntity, addType, viewType, list;

    public SuperPanel() {
        //Removes the default gaps between components
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    /**
     * This method serves as a way for the superpanels to assign their subpanels as well as add them to their own core.
     * @param mainScreenPanel The JPanel that resembles the main screen 
     * @param createPanel The JPanel that shows the create-functionality
     * @param addTypePanel The JPanel that shows the add new type-functionality
     * @param listPanel  The JPanel that shows the list of entities
     */
    public void AssignAndAddSubPanels(JPanel mainScreenPanel, JPanel createPanel, JPanel viewEntityPanel, JPanel addTypePanel, JPanel viewTypePanel, JPanel listPanel) {
        panels = new ArrayList();

        panels.add(mainScreenPanel);
        mainScreen = panels.size() - 1;

        panels.add(createPanel);
        create = panels.size() - 1;

        panels.add(viewEntityPanel);
        viewEntity = panels.size() - 1;

        panels.add(viewTypePanel);
        viewType = panels.size() - 1;

        panels.add(addTypePanel);
        addType = panels.size() - 1;

        panels.add(listPanel);
        list = panels.size() - 1;
        //adding all the panels to this JPanel.
        for (JPanel panel : panels) {
            if (panel != null) {
                add(panel);
            }
        }
    }

    public void hideAll() {
        for (JPanel panel : panels) {
            if (panel != null) {
                panel.setVisible(false);
            }
        }
    }

    private void onlyVisible(JPanel chosen) {
        if (chosen != null) {
            hideAll();
            chosen.setVisible(true);
        }
    }

    //Methods for showing the panel
    public void showMainScreenPanel() {
        onlyVisible(panels.get(mainScreen));
    }

    public void showCreatePanel() {
        onlyVisible(panels.get(create));
    }

    public void showViewEntityPanel() {
        onlyVisible(panels.get(viewEntity));
    }

    public void showAddTypePanel() {
        onlyVisible(panels.get(addType));
    }

    public void showViewTypePanel() {
        onlyVisible(panels.get(viewType));
    }

    public void showListPanel() {
        onlyVisible(panels.get(list));
    }

    public JPanel getMainScreenPanel() {
        return panels.get(mainScreen);
    }

    public JPanel getCreatePanel() {
        return panels.get(create);
    }

    public JPanel getViewEntityPanel() {
        return panels.get(viewEntity);
    }

    public JPanel getAddTypePanel() {
        return panels.get(addType);
    }

    public JPanel getViewTypePanel() {
        return panels.get(viewType);
    }

    public JPanel getListPanel() {
        return panels.get(list);
    }
}
