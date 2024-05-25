<#include "layout.ftl">

<#macro content>
<style>
.card table:not(.active-table){
    display:none;
}
</style>
<div class="container">

<form class="d-flex" action="/spreadSheet" method="get">
    <input name="spreadSheetId" class="form-control mx-3" placeholder="ID ..." style="width:85%;">
    <input type="submit" class="btn btn-primary" value="import "  style="width:10%;">
</form>


 <#if data??>
<div class="card text-center">
  <div class="card-header">
    <ul class="nav nav-tabs card-header-tabs">
       
        <#list data?keys as key>
      <li class="nav-item">
        <a class="nav-link <#if key?index == 0> active </#if>" id="${key?index}" aria-current="true" href="#">${key}</a>
      </li>
      </#list>
     

    </ul>
  </div>
  <div class="card-body">
        <#list data?values as sheet>
            <table class="table <#if sheet?index == 0> active-table </#if> table-${sheet?index}">
                <#list sheet as row >
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
 <#else>
 <h3 class="text-white">
      there is nothing to show here
</h3>
</#if>

</div>
<script src="/js/spreadsheet.js"></script>
</#macro>