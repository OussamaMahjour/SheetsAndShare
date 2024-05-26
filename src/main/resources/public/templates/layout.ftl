<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sheets&Share</title>
    <link rel="stylesheet" type="text/css" href="/css/layout.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body class="d-flex justify-content-center">
<div class="row global-container ">
    <div class="col-2 side-bar overflow-hidden px-4">
        <div class="profile ">
              <div class="profile-img ">
                <img src="<#if profile??>${profile}<#else>email.profile@gmail.com</#if>" class="rounded-circle" alt="...">
              </div>
              <div class="profile-email ">
                <h4 class="py-1 ps-2 overflow=hidden"> <#if email??>${email}<#else>email.profile@gmail.com</#if></h4>
              </div>

        </div>
        <div class="menu pt-5">
            <ul class="nav nav-pills flex-column mb-auto">
              <li class="nav-item mb-2 px-2">
                  <a href="#" class="nav-link active-item py-1" aria-current="page">
                      <i class=" fa-solid fa-house  bi me-2" width="16" height="16"></i>
                      Home
                  </a>
              </li>
              <li class="nav-item mb-2 px-2">
                  <a href="#" class="nav-link py-1">
                       <i class=" fa-solid fa-database  bi me-2" width="16" height="16"></i>
                      Tables
                  </a>
                </li>
                <li class="nav-item mb-2 px-2">
                  <a href="#" class="nav-link  py-1">
                       <i class=" fa-solid fa-envelope-open-text   bi me-2" width="16" height="16"></i>
                      Email
                  </a>
                </li>
                <li class="nav-item mb-2 px-2">
                  <a href="#" class="nav-link  py-1">
                     <i class=" fa-solid fa-table bi me-2" width="16" height="16"></i>
                    Sheets
                  </a>
                </li>
      
            </ul>
        </div>
        <div class="exit  d-flex justify-content-center " >
          <a href="/logout" class="text-white text-decoration-none  "> 
            <i class="fa-solid  fa-arrow-right-from-bracket mx-2"></i>
            Log Out
          </a>
        </div>

    </div>
    <div class="col-10  content"><@content/></div>
<div>

<script src="/js/layout.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>