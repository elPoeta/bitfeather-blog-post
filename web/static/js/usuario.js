class Usuario{
    static consultar(){
        const URL_LOGIN = 'api/LoginServer';
        Http.get(URL_LOGIN)
                .then(data =>{
                    console.log('User>> ',data);
                 if(data !== null && data!=='error'){
                    const login = document.querySelector('.login');
                    const avatar = document.querySelector("#panel-avatar");
                    avatar.innerHTML = `<img src="${data.avatar}" alt="user-logo" class="logo-user">`;
                           login.innerText = data.nombre;
                           login.removeAttribute('id');
                           login.setAttribute('id','logout-header');
                }else{
                    document.querySelector('.login').innerText = 'Login';
                }
        })
                .catch(err =>{
                    console.error(err);
        });
    }
    
    static panelUser(){
       
        const div = document.createElement('div');
        const ul = document.createElement('ul');
        const liCuenta = document.createElement('li');
        const liLogout = document.createElement('li');
        const linkCuenta = document.createElement('a');
        const linkLogout = document.createElement('a');
        
        div.className= 'panel-usuario hide-panel-usuario';
        let t = document.createTextNode("Mi cuenta"); 
        linkCuenta.setAttribute('href','#');
        linkCuenta.appendChild(t);
        linkCuenta.className = "menu-usuario";
        linkCuenta.setAttribute('id', `miCuenta`);
        linkCuenta.setAttribute("onclick", "MiCuenta.verMenu();");
        liCuenta.appendChild(linkCuenta);
        t = document.createTextNode("Cerrar sesion"); 
    
        linkLogout.setAttribute('href','#');
        linkLogout.className = "menu-usuario";
        linkLogout.setAttribute('id', `logout`);
        linkLogout.setAttribute("onclick", "Usuario.cerrarSesion();");
        linkLogout.appendChild(t);
        liLogout.appendChild(linkLogout);
    
        ul.appendChild(liCuenta);
        ul.appendChild(liLogout);
    
        div.appendChild(ul);
        
        document.querySelector('.header').appendChild(div);
}
     static cerrarSesion(){
        console.log("OUT!!!");
        Http.get('api/privado/Logout')
            .then( data =>{
                console.log('LogOut ',data);
                location.replace('login.html');
        }).catch(err =>{
         console.log('Error ',err);
        });
    }
}

 Usuario.consultar();


