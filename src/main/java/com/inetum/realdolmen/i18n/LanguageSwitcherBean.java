package com.inetum.realdolmen.i18n;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LanguageSwitcherBean implements Serializable {

    private Locale locale = Locale.ENGLISH;

    public Locale getLocale() {
        return this.locale;
    }

    public String getLocaleTag() {
        return locale.toLanguageTag();
    }

    public void setLocaleTag(String localeTag) {
        locale = Locale.forLanguageTag(localeTag);
    }

}
