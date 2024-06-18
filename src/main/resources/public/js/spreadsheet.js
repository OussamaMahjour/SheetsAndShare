document.addEventListener("DOMContentLoaded",function(){
    let spreadsheetCardHeads = document.querySelectorAll(".card-header .nav-link");
    let active_spreadsheetCardHead = document.querySelector(".card-header .active");
    let active_table = document.querySelector(".active-table");
    let drop_down = document.querySelectorAll(".drop-down-close");
    
    
    spreadsheetCardHeads.forEach(function(element){
        element.addEventListener('click',function(e){
            active_spreadsheetCardHead.classList.remove("active");
            element.classList.add("active");
            active_spreadsheetCardHead = element;
            active_table.classList.add("d-none");
            document.querySelector(".table-"+element.id).classList.add("active-table");
            document.querySelector(".table-"+element.id).classList.remove("d-none");
            active_table = document.querySelector(".table-"+element.id);
        })

    });


    drop_down.forEach(function(element){
        element.addEventListener("click",function(e){
            if(element.classList.contains("drop-down-close")){
                element.classList.remove("drop-down-close");
          
                document.querySelector(".card-range-"+element.id).classList.remove("spreadsheet-content-close");
            }
            else{
                element.classList.add("drop-down-close");
                document.querySelector(".card-range-"+element.id).classList.add("spreadsheet-content-close");

            }
            
          
    })
    })

    let delete_spreadsheet = document.querySelectorAll(".delet-sheet");
    delete_spreadsheet.forEach(function(element){
        element.addEventListener("click",function(e){
            var spreadsheetId = element.id.split("-")[1];
            console.log("/spreadsheet/deleteSpreadsheet?id="+spreadsheetId);
            fetch("/spreadsheet/deleteSpreadsheet?id="+spreadsheetId).then(
                response =>{
                    if(response.status==200){
                        window.location.reload();
                    }
                }
            );

        });
        
    });
    
    
})