package com.app.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.exeption.LoginException;
import com.app.exeption.invalidSpreadsheetException;
import com.app.model.User;
import com.app.util.GoogleUtilApi;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.inject.Inject;

public class SpreadsheetService {
    Sheets service ;

    @Inject
    public SpreadsheetService(GoogleUtilApi api) throws IOException,GeneralSecurityException,LoginException,invalidSpreadsheetException{
        this.service = api.getSheetsService();
    }

    public void addSpreadsheet(String id,User user) throws IOException, invalidSpreadsheetException{
        try{
            Spreadsheet spreadsheet = this.service.spreadsheets().get(id).execute();
            Map<String,List<List<Object>>> dataRange = new HashMap<>();
            for(Sheet sheet :spreadsheet.getSheets()){
                String sheetName = sheet.getProperties().getTitle();
                ValueRange response = service.spreadsheets().values().get(id,sheetName ).execute();
                
                dataRange.put(sheetName,response.getValues());

            }
            com.app.model.Spreadsheet spreadsheetModal = new com.app.model.Spreadsheet();
            spreadsheetModal.setId(id);
            spreadsheetModal.setData(dataRange);
            spreadsheetModal.setName(spreadsheet.getProperties().getTitle());
            user.getRepository().addSpreadsheet(spreadsheetModal);
            }catch(Exception e){
                throw new invalidSpreadsheetException();
            }
    }

    
    public void deleteSpreadsheet(String id,User user){
        com.app.model.Spreadsheet spreadsheet = user.getRepository().getSpreadsheetById(id);
        user.getRepository().getSpreadsheets().remove(spreadsheet);
    }

    
}
