<#include "layout.ftl">

<#macro content>

<link href="/css/tables.css" rel="stylesheet">
<div class="add-table-container add-table-container-close">
        <div class="container add-table-modal  py-3">
            <form action="/table/creatTable" class="form add_table_form" method="post" >
                <div class="  d-flex align-items-center   justify-content-around">
                    <span>
                        Name:  
                        <input name="table-name" placeholder="Table Name" class="table-name border is-invalid p-2"  class="form-controller ms-3">
                       <div class="text-danger d-none invalidName">
                                Please choose a name.
                        </div>
                    </span>
                    <span>
                    <div class="input-group d-flex align-items-center ">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="spreadsheet">Spreadsheet</label>
                        </div>
                        <select class="custome-select select-spreadsheet border py-2 ps-2" name="spreadsheet" id="spreadsheet" >
                             <option selected disabled>Select spreadsheet</option>
                            <#list user.repository.spreadsheets as spreadsheet>
                            <option value="${spreadsheet.id}">
                                ${spreadsheet.name}
                            </option>
                            </#list>
                        </select>
                        
                        </div>
                         <div class="text-danger invalidSpreadsheet d-none">
                                spreadsheet required.
                        </div>
                    </span>
                </div>
                <hr>
                <div class="row ">
                    <div class="col-10 d-flex align-items-center justify-content-between">
                        <label >Number of Columns:  </label> 
                        <input type="number" name="ColumnsNbr" class="form-control col-4 ps-3 columns-nbr" style="width:10rem;" >
                        
                    
                    <div class="input-group d-flex align-items-center  " style="max-width:20rem;">
                        <div class="input-group-prepend">
                            <label class="input-group-text " for="sheet">Sheet</label>
                        </div>
                        
                        <#list user.repository.spreadsheets as spreadsheet>
                        <select name="sheet" class="custome-select select-sheet border py-2  sheet-${spreadsheet.id} d-none ps-2" id="sheet" required>
                             
                            <#list spreadsheet.data as sheet,value >
                            <option value="${sheet}!${value[0]?size}!${value?size}">
                                ${sheet}
                            </option>
                            </#list>
                        </select>
                        </#list>
                        </div>
                       
                    </div>
                    <button type="button" class="btn col-1 add-column " >Add</button>
                </div>
                <div class="row">  
                        <div class="text-danger col-4 text-end">
                                
                        </div>
                <div class="text-danger col-4 text-end d-none invalidSheet">
                                sheet required.
                        </div>
                </div>
                <hr>
                <div class=" table-columns overflow-scroll" style="max-height:30rem;">
                   
                </div>
                <div class="d-flex justify-content-between align-items-center pe-5">
                <span>
                Totale Columns:
                <input type="number" value=0 readonly  min="0" name="totale-number" class="totale_column form-control"  style="width:10rem;" >
                 <div class="text-danger invalidTotalColumn  d-none">
                                please add some columns
                        </div>
                </span>
                <button  class=" btn create-table p-2" >Create</button>
                </div>
            </form>
        </div>

</div>
    
<div class="tables-container container  overflow-scroll">
    <#if user.repository.tables??>
   
    <#list user.repository.tables as table>
        <table class="table table-bordered" >
            <thead>
                <th scope="column " ><i class="fa-solid fa-caret-right px-2 drop-down  drop-down-close" id="table-${table.name}-${table.id}" ></i>${table.name}</th>
            </thead>
            <tbody>
                <tr class="p-0 table-${table.name}-${table.id} close">
                    <td class="p-0">
                    <table class="table table-bordered m-0">
                        <thead>
                            <#list table.data as column >
                            <th scope="column" >${column.name}</th>
                            </#list>
                        </thead>
                        <tbody>
                      <#assign  max_row = 0>
                           <#list table.data as column >
                                    <#if ((column.data?size) > max_row) >
                                         <#assign  max_row = column.data?size>
                                    </#if>
                           </#list>
                                    <#list 0..(max_row-1) as row >
                                        
                                        <tr>
                                            <#list table.data as column >
                                                    <td> 
                                                    <#if column.data[row]??>
                                                    ${column.data[row]}
                                                    </#if>

                                                    
                                                    </td>
                                            </#list>
                                        </tr>
                                       
                                    </#list>
                           
                        </tbody>

                    </table>
                    </td>
                </tr>
                
            </tbody>
        </table>
    
    
    </#list>
    </#if>



</div>
<button class="add-table rounded-circle"><i class="fa-solid fa-plus fa-xl"></i></button>
<script src="/js/tables.js"></script>

</#macro>