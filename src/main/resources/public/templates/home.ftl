<#include "layout.ftl">

<#macro content>
<link rel="stylesheet" href="/css/home.css">





<div class="container home-content row">
    <div class="col-9 row gap-3">
    <div class="row  rounded logo-content  overflow-hidden">
            <div class="row img-row  ">
                <img src="/imgs/home-logo.png" alt="Sheets&Share">
            </div>
            <div class="row dev-row px-3 ps-5 ">
               
                <table class="table table-borderless">
                    <tbody>
                        <tr>
                            <th scope="row">Dev:</th>
                            <td class="d-flex justify-content-between">
                                <a  href="https://github.com/OussamaMahjour" target="_blank" style="max-width:45%;">https://github.com/OussamaMahjour</a>
                                <a href="https://github.com/abderra-hmane" target="_blank" style="max-width:45%;">https://github.com/abderra-hmane</a>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Src code:</th>
                            <td><a href="https://github.com/OussamaMahjour/SheetsAndShare" target="_blank" style="width:100%;">https://github.com/OussamaMahjour/SheetsAndShare</a></td>
                        </tr>
                        <tr>
                            <th scope="row">Manual:</th>
                            <td colspan="2"><a href="#" style="width:100%;" target="_blank"></a></td>
                        </tr>
                    </tbody>
                </table>
            
                        
               

                
            
            </div>
    </div>
    <div class="row  rounded spreadsheet-home overflow-hidden d-flex justify-content-center ">
        <div class="row row-link d-flex justify-content-between align-items-center">
            <input name="spreadsheetId" class="sheet-id is-invalid" id="validspreadsheet" aria-describedby="inputGroupPrepend3 validationServerUsernameFeedback" required placeholder="ID...">
            
            <button class="import-sheet-link" >Import</button>
            <div id="validspreadsheet" class="invalid-feedback inValidSpreadsheetMessage d-none">
               Please choose a valid id .
            </div>
        </div>
        <div class="row row-or px-5"> <div class="separator">or</div></div>
        <div class="row row-csv d-flex justify-content-center align-items-center ">
                <button >Import from csv</button>
        </div>
    
    </div>
    </div>
    <div class="col-3  rounded right-side-content"></div>

</div>
<script src="/js/home.js"></script>



</#macro>