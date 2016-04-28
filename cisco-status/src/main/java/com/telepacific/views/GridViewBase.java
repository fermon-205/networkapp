package com.telepacific.views;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.Collection;

public abstract class GridViewBase<T> extends VerticalLayout implements View {

    protected final BeanItemContainer<T> beanItemContainer;
    protected final Label label;
    protected final Grid grid;

    public GridViewBase(Class<T> type, String labelText){
        beanItemContainer = new BeanItemContainer<>(type);

        label = new Label(labelText);

        grid = new Grid(beanItemContainer);

        grid.setColumnOrder("name", "ipAddress");

        grid.setSizeFull();

        addComponents(label, grid);

        setExpandRatio(grid, 1);

        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }

    protected abstract Collection<T> getBeans();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        beanItemContainer.removeAllItems();
        beanItemContainer.addAll(getBeans());
    }
}
