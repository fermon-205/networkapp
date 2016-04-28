package com.telepacific.components;

import com.google.inject.Inject;

import com.vaadin.data.Property;
import com.vaadin.guice.annotation.AllKnownGuiceViews;
import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.navigator.View;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;

import java.util.Set;

@UIScope
public class InterfaceSelector extends NativeSelect {

    @Inject
    public InterfaceSelector(@AllKnownGuiceViews Set<View> views) {
        setNullSelectionAllowed(true);
        setNullSelectionItemId("Select interface here..");

        for (View view : views) {
            GuiceView annotation = view.getClass().getAnnotation(GuiceView.class);

            if(annotation.name().equals("")){
                addItems("startpage");
            } else {
                addItem(annotation.name());
            }
        }

        addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String target = event.getProperty().getValue().toString();

                if("startpage".equals(target)){
                    target = "";
                }

                UI.getCurrent().getNavigator().navigateTo(target);
            }
        });
    }
}
