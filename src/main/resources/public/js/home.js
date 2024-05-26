document.addEventListener("DOMContentLoaded",function(){
    let import_sheet_link = document.querySelector(".import-sheet-link");
    import_sheet_link.addEventListener("click",function(e){
       
        load(); 
        fetch("/addSpreadSheetById?spreadsheetId="+document.querySelector(".sheet-id").value).then(
            response=>{
                if(response.status==200){
                    stopLoad();
                    window.location.href = "/spreadsheet";
                }
            }
        )
    });
    function load(){
        document.querySelector(".modal-container").classList.remove("d-none");
        document.querySelector(".loader-container").classList.remove("d-none");
    }
    function stopLoad(){
        document.querySelector(".modal-container").classList.add("d-none");
        document.querySelector(".loader-container").classList.add("d-none");
    }
});