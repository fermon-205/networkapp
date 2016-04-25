package com.telepacific.components;

import com.google.inject.Inject;

import com.telepacific.api.LoginApi;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.BaseTheme;

import org.vaadin.dialogs.ConfirmDialog;

@UIScope
public class LogoutLink extends Button {

    @Inject
    LogoutLink(final LoginApi loginApi){
        addStyleName(BaseTheme.BUTTON_LINK);

        setCaption("logout");

        addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                ConfirmDialog.show(UI.getCurrent(), "confirm logout", "do you want to logout?", "yes", "no", new ConfirmDialog.Listener() {
                    @Override
                    public void onClose(ConfirmDialog confirmDialog) {
                        if(confirmDialog.isConfirmed()){
                            loginApi.logout();
                        }
                    }
                });
            }
        });
    }
}
