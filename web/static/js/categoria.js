class Categoria{
    static async consultarCategorias(){
        const URL_CATEGORIA_SERVER = 'api/CategoriaServer';
          try{
            const categorias = await Http.get(URL_CATEGORIA_SERVER);
            console.log(categorias);
            return categorias;
        }catch (err){
           console.log(`Error: ${err}`); 
        }
    }
    static async menuRender(){
          try{
            const categorias = await Categoria.consultarCategorias();
            let template =
                       `<h3>Categorias\><span class="blink">_</span></h3>
                            <a href="#">Todas</a>
                             ${categorias.map( categoria =>
                           `<a href="#">${categoria.nombre}</a>`).join('')}`;
          
            document.querySelector('#panel-Sidenav').innerHTML = template;
  
        }catch(err){
                console.log(`Error: ${err}`);
        }
    }
    static menu(ev){
          if(!ev.classList.contains("change")){
             document.querySelector('.sidenavpanel').style.width = "250px";
        
    }else{
        document.querySelector('.sidenavpanel').style.width = "0";
    }
    ev.classList.toggle("change"); 
    }
}
Categoria.menuRender();