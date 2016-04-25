package com.telepacific.components;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import com.telepacific.api.LoginApi;
import com.telepacific.bus.LoginEvent;
import com.telepacific.bus.UIEventBus;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import org.vaadin.dialogs.ConfirmDialog;

@UIScope
public class CurrentUserLabel extends Label implements ContextClickEvent.ContextClickListener {

    @Inject
    private LoginApi loginApi;

    @Inject
    CurrentUserLabel(UIEventBus uiEventBus){
        uiEventBus.register(this);
        addContextClickListener(this);
        setCaptionAsHtml(true);
    }

    @Subscribe
    public void onLogin(LoginEvent loginEvent){
        setCaption("logged in as <b>" + loginEvent.getUser().getUserName() + "</b>");
    }

    @Override
    public void contextClick(ContextClickEvent event) {

    }
}
