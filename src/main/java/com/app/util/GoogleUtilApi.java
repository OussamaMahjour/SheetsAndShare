package com.app.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import com.app.exeption.LoginException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.sheets.v4.Sheets;
import com.google.inject.Inject;



/**
 * Utility class for interacting with various Google APIs using OAuth 2.0 authentication.
 */
public class GoogleUtilApi {
    private final JsonFactory jsonFactory;
    private final NetHttpTransport httpTransport;


    /**
     * Constructs a GoogleUtilApi instance.
     * 
     * @param jsonFactory JSON factory used for JSON processing.
     * @param httpTransport HTTP transport used for making HTTP requests.
     */
    @Inject
    public GoogleUtilApi(JsonFactory jsonFactory, NetHttpTransport httpTransport) {
        this.jsonFactory = jsonFactory;
        this.httpTransport = httpTransport;

    }



    /**
     * Retrieves the Google Authorization Code Flow configured with client secrets and scopes.
     * 
     * @return GoogleAuthorizationCodeFlow instance.
     * @throws IOException If an error occurs during flow initialization or credential loading.
     */
    public GoogleAuthorizationCodeFlow getFlow() throws IOException{
        GoogleClientSecrets clientSecrets = this.getClientSecrets();
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        this.httpTransport, jsonFactory, clientSecrets, GoogleApiConfig.SCOPES)
            .setDataStoreFactory(new DSFactory())//Use  DataStore Api for storing Tokens
            .setAccessType("offline")
            .build();
        return flow;
    }


     /**
     * Loads Google client secrets from credentials file.
     * 
     * @return GoogleClientSecrets loaded client secrets.
     * @throws IOException If the credentials file cannot be read.
     */
    private GoogleClientSecrets getClientSecrets() throws IOException {
        InputStream in = GoogleUtilApi.class.getResourceAsStream(GoogleApiConfig.CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + GoogleApiConfig.CREDENTIALS_FILE_PATH);
        }
        return GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));
    }


    /**
     * Generates the authorization URL for initiating OAuth 2.0 flow.
     * 
     * @return Authorization URL for user authentication.
     * @throws IOException If an error occurs during URL generation.
     */
    public String getAuthURL() throws IOException{
        GoogleAuthorizationCodeFlow flow = this.getFlow();
        String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(GoogleApiConfig.REDIRECTED_URI).build();
        return authorizationUrl;
    }

   
    /**
     * Retrieves the user credential required for accessing Google APIs.
     * 
     * @return Credential instance for accessing Google APIs.
     * @throws IOException If an error occurs during credential loading.
     * @throws LoginException If user login fails or authentication expired
     */
    public Credential getCredential() throws IOException,LoginException{
        Credential credential = this.getFlow().loadCredential("user");
        if (credential != null
            && (credential.getRefreshToken() != null
                || credential.getExpiresInSeconds() == null
                || credential.getExpiresInSeconds() > 60)) {
        return credential;
        }else{
            throw new LoginException();
        }
    }
 

     /**
     * Creates and returns an instance of Gmail service for interacting with Gmail API.
     * 
     * @return Gmail service instance.
     * @throws IOException If an error occurs during service initialization.
     * @throws GeneralSecurityException If there is a security-related issue.
     * @throws LoginException If user login fails or authentication is required.
     */
    public  Gmail getGmailService() throws IOException,GeneralSecurityException,LoginException{
            Gmail service = new Gmail.Builder(this.httpTransport, jsonFactory, this.getCredential())
                .setApplicationName(GoogleApiConfig.APPLICATION_NAME)
                .build();
            return service;
    }

    /**
     * Creates and returns an instance of Sheets service for interacting with Google Sheets API.
     * 
     * @return Sheets service instance.
     * @throws IOException If an error occurs during service initialization.
     * @throws GeneralSecurityException If there is a security-related issue.
     * @throws LoginException If user login fails or authentication is required.
     */
    public  Sheets getSheetsService() throws IOException,GeneralSecurityException,LoginException{
            Sheets service = new Sheets.Builder(this.httpTransport, this.jsonFactory, this.getCredential())
                .setApplicationName(GoogleApiConfig.APPLICATION_NAME)
                .build();
            return service;

    }

     /**
     * Creates and returns an instance of People service for interacting with Google People API.
     * 
     * @return PeopleService instance.
     * @throws IOException If an error occurs during service initialization.
     * @throws GeneralSecurityException If there is a security-related issue.
     * @throws LoginException If user login fails or authentication is required.
     */
    public PeopleService getPeopleService()throws IOException,GeneralSecurityException,LoginException{
        PeopleService peopleService = new PeopleService.Builder(this.httpTransport, this.jsonFactory, this.getCredential())
        .setApplicationName(GoogleApiConfig.APPLICATION_NAME)
        .build();
        return peopleService;
    }
   
     /**
     * Sets the token obtained from OAuth 2.0 authentication into the datastore.
     * 
     * @param code Authorization code obtained during OAuth 2.0 flow.
     * @throws IOException If an error occurs during token request or credential creation.
     */
    public void setToken(String code) throws IOException{
        GoogleAuthorizationCodeFlow flow = this.getFlow();
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(GoogleApiConfig.REDIRECTED_URI).execute();
        this.getFlow().createAndStoreCredential(tokenResponse, "user");

    }
}
