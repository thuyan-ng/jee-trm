package com.inetum.realdolmen.i18n;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class LanguageOptionBean {

    private List<Locale> locales = Arrays.asList(Locale.ENGLISH, Locale.FRENCH);

    public List<Locale> getLocales() {
        return this.locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

}
