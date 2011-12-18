package carrental;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * The super panel is an abstract class that determines how all the subpanels
 * behave.
 * @author CNN
 * @version 2011-12-18
 */
public abstract class SuperPanel extends JPanel {
    private ArrayList<SubPanel> panels;
    private int mainScreen, create, viewEntity, addType, viewType, list;

    public SuperPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    /**
     * This method serves as a way for the superpanels to assign their subpanels
     * as well as add them to their own core.
     * @param mainScreenPanel The JPanel that resembles the main screen 
     * @param createPanel The JPanel that shows the create-functionality
     * @param addTypePanel The JPanel that shows the add new type-functionality
     * @param listPanel  The JPanel that shows the list of entities
     */
    public void AssignAndAddSubPanels(SubPanel mainScreenPanel, SubPanel createPanel, SubPanel viewEntityPanel, SubPanel addTypePanel, SubPanel viewTypePanel, SubPanel listPanel) {
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
        
        for (SubPanel panel : panels) {
            if (panel != null) {
                add(panel);
            }
        }
    }

    /**
     * Hides all the panels.
     */
    public void hideAll() {
        for (SubPanel panel : panels) {
            if (panel != null) {
                panel.setVisible(false);
            }
        }
    }

    /**
     * Sets one specific panel visible, hiding all the others.
     * @param chosen visible panel
     */
    private void onlyVisible(SubPanel chosen) {
        if (chosen != null) {
            hideAll();
            chosen.update();
            chosen.setVisible(true);
        }
    }

    /**
     * Shows the main panel only
     */
    public void showMainScreenPanel() {
        onlyVisible(panels.get(mainScreen));
    }

    /**
     * Shows the create panel only
     */
    public void showCreatePanel() {
        onlyVisible(panels.get(create));
    }

    /**
     * Shows the view panel only
     */
    public void showViewEntityPanel() {
        onlyVisible(panels.get(viewEntity));
    }

    /**
     * Shows the add type panel only
     */
    public void showAddTypePanel() {
        onlyVisible(panels.get(addType));
    }

    /**
     * Shows the view type panel only
     */
    public void showViewTypePanel() {
        onlyVisible(panels.get(viewType));
    }

    /**
     * Shows the list panel only
     */
    public void showListPanel() {
        onlyVisible(panels.get(list));
    }

    /**
     * Gets the main screen panel
     * @return main panel
     */
    public SubPanel getMainScreenPanel() {
        return panels.get(mainScreen);
    }

    /**
     * Gets the create panel
     * @return create panel
     */
    public SubPanel getCreatePanel() {
        return panels.get(create);
    }

    /**
     * Gets the view panel
     * @return view panel
     */
    public SubPanel getViewEntityPanel() {
        return panels.get(viewEntity);
    }

    /**
     * Gets the add type panel
     * @return add type panel
     */
    public SubPanel getAddTypePanel() {
        return panels.get(addType);
    }

    /**
     * Gets the view type panel
     * @return view type panel
     */
    public SubPanel getViewTypePanel() {
        return panels.get(viewType);
    }

    /**
     * Gets the list panel
     * @return list panel
     */
    public SubPanel getListPanel() {
        return panels.get(list);
    }
    
    public void updateAll() {
        for(SubPanel p : panels) {
            if(p != null) p.update();
        }
    }
}
