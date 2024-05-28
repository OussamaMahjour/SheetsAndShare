document.addEventListener("DOMContentLoaded",function(){
    let add_table_button = document.querySelector(".add-table");
    let add_table_container =document.querySelector(".add-table-container");
    let spreadsheet_select = document.querySelector(".spreadsheet-selecting");
    let creat_table = document.querySelector(".creat-table");
    add_table_button.addEventListener("click",function(e){
        add_table_container.classList.remove("add-table-container-close");
    });
    add_table_container.addEventListener('click',function(e){
        if (add_table_container == (e.target)) {
            add_table_container.classList.add("add-table-container-close")
           
        }
      
        
    })
    spreadsheet_select.addEventListener("change",function(e){
        
        var selectedValue = e.target.value;
        
        
        document.querySelectorAll(".select-sheet").forEach(function(element){
            element.classList.add("d-none")
        })
        document.querySelector(".select-"+selectedValue).classList.remove("d-none");

        // Do something with the selected value or text
        console.log("Selected value: " + selectedValue);
        console.log("Selected text: " + selectedText);
    })
    creat_table.addEventListener("click",function(e){
        var spreadsheet = spreadsheet_select.value;
        var sheet =  document.querySelector(".select-"+spreadsheet).value;
        var table = document.querySelector(".table-name").value;
        load();
        fetch("/creatTable?spreadsheet="+spreadsheet+"&sheet="+sheet+"&name="+table).then(
            response =>{
                window.location.reload();
            }
        )
        stopLoad();

    });
    function load(){
        document.querySelector(".modal-container").classList.remove("d-none");
        document.querySelector(".loader-container").classList.remove("d-none");
    }
    function stopLoad(){
        document.querySelector(".modal-container").classList.add("d-none");
        document.querySelector(".loader-container").classList.add("d-none");
    }
})