document.addEventListener("DOMContentLoaded",function(){
    let menu_items = document.querySelectorAll(".side-bar .nav-link");
    let path =window.location.pathname=="/"?"home":window.location.pathname.replace("/","");
   
    console.log(".nav-"+path);
    let active_menu_item = document.querySelector(".nav-"+path);
    active_menu_item.classList.add("active-item");
    menu_items.forEach(function(element){
        element.addEventListener('click',function(e){
            element.classList.add("active-item");
            active_menu_item.classList.remove("active-item");
            active_menu_item = element;
        });
    })
    
});