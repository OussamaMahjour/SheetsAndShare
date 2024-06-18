<#include "layout.ftl">

<#macro content>
        <link href="/css/email.css" rel="stylesheet">
        <div class="container">
        
            <div class="row ">

            <table>
                <thead>
                    <th scope="column" ></th>
                    <th scope="column"></th>
                </thead>
                <tbody >
                    <tr >
                        <td class="text-end">
                            destination 
                        </td>
                        <td class="p-3 d-flex">
                         <select class="ps-2 mx-4 d-flex select-table" style="width:20%; height:2rem;" >
                                <option selected disabled  > table</option>
                                <#list user.repository.tables as table>
                                 <option value="${table.name}">
                                        ${table.name}
                                 </option>
                                 </#list>
                         </select>
                         <#list user.repository.tables as table>
                          <select class="ps-2 mx-4 d-flex d-none table-${table.name} column" style="width:20%; height:2rem;" >
                                    <option selected disabled >column</option>
                                <#list table.data as column>
                                    <option value="${column.name}">
                                        ${column.name}
                                    </option>

                                </#list>
                          
                          </select>
                          </#list>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-end">
                            subject
                        </td>
                        <td class="p-3">
                            <input style="width:50%;" class="form-control subject  mx-4">

                        </td>

                    </tr>
                </tbody>

            </table>
           
           
            </div>
            <hr>
            <div class="row d-flex justify-content-center">
                <button class="compile mx-1" >Compile</button>
                <button class="send mx-1" >Send</button>
            
            </div>
            <div class="row d-flex justify-content-center" style="height:30rem !important;">
                <div class="col-5 p-3   overflow-hidden">
                 <div class="border rounded border-dark overflow-hidden" style="height:100%;width:100%;">
                    <div class="border-bottom border-dark d-flex ps-4 align-items-center fs-4" style="width:100%;height:10%;">
                    <i class="fa-solid fa-b me-4"></i>
                    <i class="fa-solid fa-italic me-4"></i>
                    <i class="fa-solid fa-image me-4"></i>
                    
                        <div class="dropdown">
                            <span class=" " type="button" id="dropdownMenuButton">
                                {{}}
                            </span>
                            <div class="dropdown-menu border-dark" aria-labelledby="dropdownMenuButton">
                                <#list user.repository.tables as table>

                                <div class="dropdown-submenu">
                                    <a class="dropdown-item dropdown-toggle " href="#">${table.name}</a>
                                    <div class="dropdown-menu  border-dark">
                                        <#list table.data as column>
                                        <a class="dropdown-item " href="#" onclick="document.querySelector('.message').value+='{{${table.name}->${column.name}}}'">${column.name}</a>
                                        </#list>
                                    </div>
                                </div>
                                
                                </#list>
                                
                                
                            </div>
                        </div>
                    </div>
                    <div class="bg-black p-0 " style="width:100%;height:90%;">
                        <textarea class="p-2 message" style="width:100%;height:100%; "></textarea>
                    </div>
                 </div>
                 
                   
                </div>
                <div class="col-5 p-3">
                        
                    <div class="border rounded border-dark p-2 compiled-message" style="height:100%;width:100%;"></div>
                </div>

            </div>
            

        
        
        <div>
        <script src="/js/email.js"></script>
</#macro>