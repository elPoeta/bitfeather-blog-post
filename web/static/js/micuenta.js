class MiCuenta{

    static async consultarCuenta(){
       
          const URL_MI_CUENTA_SERVER_PRIVADO = 'api/privado/MiCuentaServer';
          let  template = ``;
        try{
              
          const autor = await Http.get(URL_MI_CUENTA_SERVER_PRIVADO);
           console.log('autor > ',autor);
             if(autor != 'error'){
                 
	  template = 
                    `<section class="micuenta-main">
                       <div class="micuenta-area-avatar">                        
                        <figure>
                           <img class="micuenta-avatar" src="${autor.avatar}" alt="userimage"/>
                       </figure>
                       <h2>${autor.nombre}</h2>
                        </div> 
                <div class="micuenta-area-menu">
                          <ul class="micuenta-menu">
                       <li class="micuenta-lista" id="adminstrar" onclick="MiCuenta.administrar();">Administrar</li>
                       <li class="micuenta-lista" id="seguidores" onclick="MiCuenta.seguidores();">Seguidores</li>
                       <li class="micuenta-lista" id="siguiendo" onclick="MiCuenta.siguiendo();">Siguiendo</li>
                       <li class="micuenta-lista" id="mensajes" onclick="MiCuenta.mensajes();">Mensajes</li>
                      </ul>
                      <hr/>
                     </div>
                      <section class="micuenta-area-contenido" id="panel-micuenta"></section>
                    </section>`;
              
              }else{
                   window.location.replace("login.html");
              }
            }catch (exception) {
                   console.log(exception); 
              }
              document.querySelector('#panel-main').innerHTML = template;
              MiCuenta.administrar();
}
    static administrar(){
       const admin = document.querySelector('.micuenta-lista');
       MiCuenta.removerActiveClass();
        admin.classList.add('menu-activo');
        let template =
                ` <section class="seleccion">
                    <div class="subir-imagen">
                        <fieldset class="fieldsetCrearPost">
                        <legend>Cambiar imagen</legend>
                         <div class="upload">
                             <input id="archivoinput" type="file" accept="image/*" />
                       </div>
                        <div id="caja">
                            <p>O arrastre y suelte la imagen aquí</p>
                            <figure id="thumb"></figure>    
                        </div>
                        <div class="btn-contenedor">
                          <button class="btn-micuenta" onclick="MiCuenta.guardarAvatar();">Guardar</button>
                        </div>    
                     </fieldset>
                   </div>
                 <div class="cambia-pass">
                    <fieldset class="fieldsetCrearPost">
                        <legend>Cambiar password</legend>
                         <div class="micuenta-input-pass">
                           <input type="password" id="cambiar-pass-actual" name="password-actual" placeholder="  PASSWORD ACTUAL" required>
                           <input type="password" id="cambiar-pass-nuevo" name="password-nuevo" placeholder="  NUEVO PASSWORD" required>
                           <input type="password" id="cambiar-pass-conf-nuevo" name="conf-password-nuevo" placeholder="  REPETIR PASSWORD" required>
 
                         </div>
                           
                        <div class="btn-contenedor">
                          <button class="btn-micuenta" id="btn-guardar-pass">Guardar</button>
                        </div>    
            </fieldset>
                 </div>
             
        </section>`;
        document.querySelector('#panel-micuenta').innerHTML = template;
        MiCuenta.avatar();
    }
      static seguidores(){
       const seg = document.querySelectorAll('.micuenta-lista');
        MiCuenta.removerActiveClass();
        seg[1].classList.add('menu-activo');
        document.querySelector('#panel-micuenta').innerHTML = '';
    }
     
    static siguiendo(){
       const sig = document.querySelectorAll('.micuenta-lista');
     
        MiCuenta.removerActiveClass();
        sig[2].classList.add('menu-activo');
        document.querySelector('#panel-micuenta').innerHTML = '';
    }
    static mensajes(){
       const msj = document.querySelectorAll('.micuenta-lista');
     
        MiCuenta.removerActiveClass();
        msj[3].classList.add('menu-activo');
        
        document.querySelector('#panel-micuenta').innerHTML = '';
    }
   
   static avatar(){
       let imagen;
const archivoInput = document.querySelector('#archivoinput');
const caja = document.querySelector('#caja');

caja.addEventListener("dragenter", e => {
    e.preventDefault();
});

caja.addEventListener("dragover", e => {
     e.preventDefault();
});

caja.addEventListener("drop", e =>{
    e.preventDefault();
    imagen = e.dataTransfer.files[0]; 
    if(imagen){
        resize(imagen); 
    }
});

archivoInput.addEventListener('change', e =>{
    imagen = e.target.files[0];
    if(imagen){
        resize(imagen); 
    }
});


async function resize(imagen){
    const config = {
        img: imagen,
        maxSize: 400
    };
    try{
      const resizeImg = await resizeImage(config);   
        document.querySelector('#thumb').innerHTML= `<img id="micuenta-avatar"  src ="${resizeImg}" alt="avatar">`;
    }catch (e){
        console.log(`Error ${e}`);
    };
    
}


let resizeImage = async (settings) => {
 
    let file = settings.img;
    let maxSize = settings.maxSize;
    let fileReader = new FileReader();
    let image = new Image();
    let canvas = document.createElement('canvas');

    let resize =  () => {
        let width = image.width;
        let height = image.height;
        if (width > height) {
            if (width > maxSize) {
                height *= maxSize / width;
                width = maxSize;   
            }
        } else {
            if (height > maxSize) {
                width *= maxSize / height;
                height = maxSize;
            }
        }
        canvas.width = width;
        canvas.height = height;
        canvas.getContext('2d').drawImage(image, 0, 0, width, height);
        let dataUrl = canvas.toDataURL();
        return dataUrl;
    };

    return new Promise(function (ok, error) {
        if (!file.type.match(/^image\//)) {
            error(new Error("No es una imagen"));
            return;
        }
        fileReader.onload = function (e) {
            image.onload = function () { return ok(resize()); };
            image.src = e.target.result;
            
        };
        fileReader.readAsDataURL(file);
    });
};

   }
    static guardarAvatar(){
        const i = document.querySelector('#micuenta-avatar');
        console.log(i.src);
    }
    static removerActiveClass(){
        const removeActive = document.querySelectorAll('.micuenta-lista');
        for(let i=0; i<removeActive.length; i++){ 
            removeActive[i].classList.remove('menu-activo');
        }
    }

}

