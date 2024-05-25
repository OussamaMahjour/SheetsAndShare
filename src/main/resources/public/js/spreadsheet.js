document.addEventListener("DOMContentLoaded",function(){
    let spreadsheetCardHeads = document.querySelectorAll(".card-header .nav-link");
    let active_spreadsheetCardHead = document.querySelector(".card-header .active");
    let active_table = document.querySelector(".active-table");
    
    spreadsheetCardHeads.forEach(function(element){
        element.addEventListener('click',function(e){
            active_spreadsheetCardHead.classList.remove("active");
            element.classList.add("active");
            active_spreadsheetCardHead = element;
            document.querySelector(".active-table").classList.remove("active-table");
            document.querySelector(".table-"+element.id).classList.add("active-table");
        })

    });
})