package com.telepacific.components;

import com.google.inject.Inject;

import com.telepacific.api.Cisco;
import com.telepacific.domain.JMXAddress;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;

import java.util.List;

@UIScope
public class InterfaceSelector extends NativeSelect {

    @Inject
    public InterfaceSelector(Cisco cisco, DeviceSelector deviceSelector, JMXAddressesGrid jmxAddressesGrid) {
        setEnabled(false);
        setNullSelectionAllowed(true);
        setNullSelectionItemId("Select interface here..");


        deviceSelector.addValueChangeListener((ValueChangeListener) event -> {
            String selectedDevice = (String) event.getProperty().getValue();

            if (selectedDevice == null) {
                select(null);
                setEnabled(false);
                return;
            }

            removeAllItems();
            addItems(cisco.availableInterfaces(selectedDevice));
            setEnabled(true);
        });

        addValueChangeListener((ValueChangeListener) event -> {
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
        });
    }
}
