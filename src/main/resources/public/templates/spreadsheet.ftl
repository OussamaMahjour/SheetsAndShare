<#include "layout.ftl">

<#macro content>
<link href="/css/spreadsheet.css" rel="stylesheet">
<div class="spreadsheets-container container d-flex justify-content-center align-items-start ">
        <div class="row spreadsheet overflow-hiddenc">
            <table class="table border">
                <thead class="thead-dark">
                    <tr >
                        <th scope="col"></th>
                        <th scope="col">Name</th>
                        <th scope="col">Id</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <#if data??>
                        <#list data as key,value>
                    <tr>
                        <th scope="row"><i class="fa-solid fa-caret-down  drop-down-close" id="${key.id}"></i></th>
                        <td>${key.name}</td>
                        <td>${key.id}</td>
                        <td><i class="fa-solid fa-trash" ></i></td>
                    </tr>
                    <tr>
                        <td colspan="4" class="p-0"> 
                            <div class="spreadsheet-content spreadsheet-content-close card-range-${key.id}">
                            <div class="card text-center ">
                                <div class="card-header">
                                     <ul class="nav nav-tabs card-header-tabs">
       
                                        <#list value?keys as sheet>
                                            <li class="nav-item">
                                                <a class="nav-link <#if sheet?index == 0> active </#if>" id="${key.id}-${sheet}" aria-current="true" href="#">${sheet}</a>
                                            </li>
                                        </#list>
     

                                    </ul>
                                </div>
                                <div class="card-body">
                                <#list value as sheet,valuerange>
                                    <table class="table <#if valuerange?index == 0> active-table </#if> d-none  table-${key.id}-${sheet}">
                                        <#list valuerange as row >
                                            <#if row?index == 0>
                                                <thead>
                                                    <tr>
                                                    <#list row as column>
                                                        <th scope="col">${column}</th>
                                                            </#list>
                                                    </tr>
                                                </thead>
                                            <#else>
                                            
                                                    <tr>
                                                    <#list row as column>
                                                    <#if column?index == 0>
                                                        <th scope="col">${column}</th>
                                                    <#else>
                                                        <td>${column}</td>
                                                    </#if>
                                                     </#list>
                                                    <tr>
                                            </#if>
                                        </#list>
                                    </table>
        
                                </#list>
                            </div>
                        </div>
                            
                            </div>


                            
                        </td>
                    </tr>
                        </#list>
                    <#else>
                    <tr>
                        <td colspan="4">there is nothing to show here</td>
                    </tr>
                    </#if>
                    
                </tbody>
            </table>
                

        </div>
</div>
<script src="/js/spreadsheet.js"></script>
</#macro>