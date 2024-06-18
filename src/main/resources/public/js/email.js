document.addEventListener("DOMContentLoaded",function(){

    let select_table = document.querySelector(".select-table");
    select_table.addEventListener("change",function(e){
        
        document.querySelectorAll(".column").forEach(function(element){
            element.classList.add("d-none");
        })
        document.querySelector(".table-"+select_table.value).classList.remove("d-none");
    })

    let compile = document.querySelector(".compile");
    compile.addEventListener("click",function(e){
        
        
        var message = encodedString = encodeURIComponent(document.querySelector(".message").value);
        var subject = document.querySelector(".subject").value; 
        var dest_table = document.querySelector(".select-table").value;
        var dest_column = document.querySelector(".column:not(.d-none)").value;
        fetch(`compileEmail?message=${message}&subject=${subject}&dest_table=${dest_table}&dest_column=${dest_column}`)
        .then(
            response=>response.text()
        ).then(
            text=>{
                
                document.querySelector(".compiled-message").innerHTML = text;
            }
        );
    })
    const dropdownButton = document.getElementById('dropdownMenuButton');
    // Get the main dropdown menu
    const dropdownMenu = document.querySelector('.dropdown-menu');

    // Toggle the main dropdown menu on button click
    dropdownButton.addEventListener('click', function() {
        dropdownMenu.classList.toggle('show');
    });

    // Close the main dropdown menu if clicked outside
    document.addEventListener('click', function(event) {
        if (!dropdownButton.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.classList.remove('show');
        }
    });

    // Get all submenu items
    const submenuItems = document.querySelectorAll('.dropdown-submenu');

    // Add event listeners for submenu hover
    submenuItems.forEach(function(submenuItem) {
        submenuItem.addEventListener('mouseenter', function() {
            const submenu = submenuItem.querySelector('.dropdown-menu');
            submenu.classList.add('show');
        });

        submenuItem.addEventListener('mouseleave', function() {
            const submenu = submenuItem.querySelector('.dropdown-menu');
            submenu.classList.remove('show');
        });
    });

    let send = document.querySelector(".send");
    send.addEventListener("click",function(e){
        var message = encodedString = encodeURIComponent(document.querySelector(".message").value);

        var subject = document.querySelector(".subject").value; 
        var dest_table = document.querySelector(".select-table").value;
        var dest_column = document.querySelector(".column:not(.d-none)").value;

        load();
        fetch(`/sendEmail?message=${message}&subject=${subject}&dest_table=${dest_table}&dest_column=${dest_column}`)
        stopLoad();
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