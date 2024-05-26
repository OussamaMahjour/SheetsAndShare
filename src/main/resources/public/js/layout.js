document.addEventListener("DOMContentLoaded",function(){
    let menu_items = document.querySelectorAll(".nav-link");
    let active_menu_item = document.querySelector(".active-item");
    menu_items.forEach(function(element){
        element.addEventListener('click',function(e){
            element.classList.add("active-item");
            active_menu_item.classList.remove("active-item");
            active_menu_item = element;
        });
    })
});