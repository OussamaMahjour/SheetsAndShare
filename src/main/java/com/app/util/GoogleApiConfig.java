package com.app.util;

import java.util.Arrays;
import java.util.List;

import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.sheets.v4.SheetsScopes;

public class GoogleApiConfig {
    public static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    public static final String APPLICATION_NAME = "Sheets&Share";
    public static final String REDIRECTED_URI = "http://localhost:8080/callback";
    public static final List<String> SCOPES = Arrays.asList(
        GmailScopes.GMAIL_SEND, 
        SheetsScopes.SPREADSHEETS, 
        PeopleServiceScopes.USERINFO_PROFILE, 
        PeopleServiceScopes.USERINFO_EMAIL
    );
}