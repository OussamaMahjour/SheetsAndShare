document.addEventListener("DOMContentLoaded",function(){
    let add_table_button = document.querySelector(".add-table");
    let add_table_container =document.querySelector(".add-table-container");
    let add_column = document.querySelector(".add-column");
    let select_spreadsheet = document.querySelector(".select-spreadsheet");

    add_table_button.addEventListener("click",function(e){
        add_table_container.classList.remove("add-table-container-close");
    });

    add_table_container.addEventListener('click',function(e){
        if (add_table_container == (e.target)) {
            add_table_container.classList.add("add-table-container-close")       
        }
    })
    select_spreadsheet.addEventListener("change",function(e){
        var select_sheet = document.querySelector(".sheet-"+select_spreadsheet.value);
        
        document.querySelectorAll(".select-sheet").forEach(function(element){
            element.classList.add("d-none");
        })
        select_sheet.classList.remove("d-none");
    })

    add_column.addEventListener("click",function(e){
        var column_nbr = document.querySelector(".columns-nbr");
        var column_container = document.querySelector(".table-columns");
        var sheet = document.querySelector(".select-sheet:not(.d-none)");
        var end_col = sheet.value.split("!")[1];
        var end_row = sheet.value.split("!")[2];
        var alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var column_option = "";
        for(var j=0;j<end_col;j++){
          
                column_option+= `<option value="${j}">${alphabets[j]}</option>`;
            
            
        }
        var row_options = "";
        for(var j=0;j<end_row;j++){
          
                row_options+=`<option value="${j}">${j}</option>`;
            
            
        }
      
        

        
        
        if(column_nbr!=null && !isNaN(parseInt(column_nbr.value, 10)) ){
            for(var i=0;i<column_nbr.value;i++){
                column_container.innerHTML+=`
                 <div class="border-bottom py-2 px-3 d-flex justify-content-start  row">
                    <div class="col-4 d-flex justify-content-around border-end p-2 ">
                        <label class="" >Name:</label>
                        <input name="table-column-${i}"  class=" column-${i}  " style="width:10rem;" >
                       
                        </div> 
                       
                    <div class="col-2 d-flex justify-content-around border-end p-2 ">
                            <label class="" >Column:</label>
                            <select name="sheet-column-${i}">
                                ${column_option}
                            
                            </select>
                    </div>
                    <div class="col-3 d-flex justify-content-around border-end p-2 ">
                             <label class="" >row:</label>
                               
            
                             <select name="row-start-${i}">
                             
                                ${row_options}
                             </select>
                             <select name="row-end-${i}">
                               
                                ${row_options}
                             </select>
                    
                    </div>
                            <span class="text-muted col-1">${sheet.value.split("!")[0]}</span>
                        <div class="text-danger invalidColumnName-${i} d-none">
                            please provide a name
                        </div>
                     </div>
                `;
                 
            }
       
        var total = document.querySelector(".totale_column");
        total.value = parseInt(total.value, 10) + parseInt(column_nbr.value, 10) ;
        }
        
        
    })
    window.onload = function() {
        select_spreadsheet.value = '';
        document.querySelector(".totale_column").value =0;
    };
    document.querySelectorAll(".drop-down").forEach(function(element){
        element.addEventListener("click",function(e){
            if(element.classList.contains("fa-caret-right")){
                element.classList.remove("fa-caret-right");
                element.classList.add("fa-caret-down");
              document.querySelector("."+element.id).classList.remove("close");
            }
            else{
                element.classList.remove("fa-caret-down");
                element.classList.add("fa-caret-right");
                document.querySelector("."+element.id).classList.add("close");

            }
            console.log(element.id);
        })
    })
    let form = document.querySelector(".add_table_form");
    form.addEventListener("submit",function(e){
        e.preventDefault();
        var table_name = document.querySelector(".table-name");
        if(table_name.value==""){
            document.querySelector(".invalidName").classList.remove("d-none");
            return;
        }
        if(select_spreadsheet==null || select_spreadsheet.value==""){
            document.querySelector(".invalidSpreadsheet").classList.remove("d-none");
            return
        }
        var  sheet = document.querySelector(".select-sheet");
        if(sheet ==null || sheet.value == ""){
            document.querySelector(".invalidSheet").classList.remove("d-none");
            return

        }
        var totale_columns = document.querySelector(".totale_column");
        if(totale_columns ==null || totale_columns.value == "" || totale_columns.value == 0){
            document.querySelector(".invalidTotalColumn").classList.remove("d-none");
            return;

        }
        for(var i =0;i<totale_columns.value;i++){
            if(document.querySelector(".column-"+i).value == "" ){
                document.querySelector(".invalidColumnName-"+i).classList.remove("d-none");
                return
            }
        }
        this.submit();



    })
    function load(){
        document.querySelector(".modal-container").classList.remove("d-none");
        document.querySelector(".loader-container").classList.remove("d-none");
    }
    function stopLoad(){
        document.querySelector(".modal-container").classList.add("d-none");
        document.querySelector(".loader-container").classList.add("d-none");
    }
})
function column_name(table){
    
}