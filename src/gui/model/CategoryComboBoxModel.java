package gui.model;

import dao.CategoryDAO;
import entity.Category;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

public class CategoryComboBoxModel implements ComboBoxModel<Category> {

    public List<Category> cs = new CategoryDAO().list();

    public Category c;

    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if (!cs.isEmpty()) {
            return c;
        } else {
            return null;
        }
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
