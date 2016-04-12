package com.telepacific.components;

import com.google.inject.Inject;

import com.telepacific.api.Cisco;
import com.telepacific.domain.JMXAddress;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;

import java.util.List;

@UIScope
public class InterfaceSelector extends NativeSelect {

    @Inject
    public InterfaceSelector(final Cisco cisco, final DeviceSelector deviceSelector, final JMXAddressesGrid jmxAddressesGrid) {
        setEnabled(false);
        setNullSelectionAllowed(true);
        setNullSelectionItemId("Select interface here..");

        deviceSelector.addValueChangeListener((ValueChangeListener) new ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String selectedDevice = (String) event.getProperty().getValue();

                if (selectedDevice == null) {
                    InterfaceSelector.this.select(null);
                    InterfaceSelector.this.setEnabled(false);
                    return;
                }

                InterfaceSelector.this.removeAllItems();
                InterfaceSelector.this.addItems(cisco.availableInterfaces(selectedDevice));
                InterfaceSelector.this.setEnabled(true);
            }
        });

        addValueChangeListener((ValueChangeListener) new ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String selectedDevice = (String) deviceSelector.getValue();

                if (selectedDevice == null) {
                    return;
                }

                String selectedInterface = (String) event.getProperty().getValue();

                if (selectedInterface == null) {
                    return;
                }

                switch (selectedInterface) {
                    case "jmx-addresses": {
                        final List<JMXAddress> jmxAddresses = cisco.getJMXAddresses(selectedDevice);

                        BeanItemContainer<JMXAddress> beanItemContainer = new BeanItemContainer<>(JMXAddress.class);

                        for (JMXAddress jmxAddress : jmxAddresses) {
                            beanItemContainer.addBean(jmxAddress);
                        }

                        jmxAddressesGrid.setContainerDataSource(beanItemContainer);
                        UI.getCurrent().getNavigator().navigateTo("jmx-addresses-view");
                        break;
                    }
                }
            }
        });
    }
}
