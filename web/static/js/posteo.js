class Posteo{
    constructor(){
        this.quill;
        this.catSelect={};
    }
    static async consultarPosts(id){
      
        const URL_POST_SERVER = 'api/PostServer?&q=';
        const posts = await Http.get(URL_POST_SERVER +JSON.stringify(id));
        return posts;
    }
 
     static async crearPost(){
          const URL_CREAR_POST_SERVER_PRIVADO = 'api/privado/CrearPostServer';
          try{
              
          const data = await Http.get(URL_CREAR_POST_SERVER_PRIVADO)
           
             if(data != 'error'){
              Posteo.loading(true);  
             const categorias = await Categoria.consultarCategorias();
	    
           const  template = `
	        <section class="main-crear-post">
                <div class="header-crear-post">
                  <figure>
                    <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACoCAYAAAClrKHEAAAQyklEQVR4nO1d7VHsvA52UsE2cGdUws47xN4/d6ADTgfQAdwK2A6gA+iA0wF0AB1AB9DBe38o2Xwn/owtr54ZzZzhwK7j6LElWZaEYPgD7HcC1HUJ1WMB6q0E9W8B8qf+97MAdSfgALGHyWBsCzgAkkL+lKD+XZMC5KsAdR172AxGWMB+V0L1qEOKaaKoLwHyJvZjMBj+AepOd8fQI8rFVexHYjDcAQdo/AvfgqYX+ygMqoDq1teusUCSHwHVbexHZTCM4OJrWMqzgP0u9nMzGKsoQT1tTI5mN/lgk4uRNuDiKgY5BibXPvY0MBiTCOWQm5OEdxJGaoi8e4zMLQYjJaSwe3SFo1uMdAAHiE2I8S6i/saeFgZDCBEvcrUu8iH23DAYogD1FZ8MszvJG0e2GPEA8k9sEuhJ9ciHiYzNUYJ8ia/82rsJJzoytkXofKswwr4Jwwaw3wmQNyXIhwLUG9rv6m7WNIFqH1/ZreVp49llkAVcXBUgX+dNE/lTgnwYEqUEeUxA0a2Fz0oYyzC8r4FpHO2tvgLke2wldxFOS2HMw+G+RhM6ja3gfkii3mK/CkZiiHBfI2nhyBYDAftdCeo5tkKmJ/Il9qthmGApcmT9mQcoQH7EV8b0pAD543WuGQHR2vb+wpAg/9A8r9hO2MyiAqhu25fmGIaE/W4pfMvSJQiHfElgfLZgl0OEh328a+iLPAZ4nQzfmDp8Q0WvHrVi9iBvUs6uTVeYICSwdjqNjnb1KEBdC6guUdR1CeqZdwwmSP4gkz6el7APQgUJXl89B+GUE0IoQH7GVpjzEjavaCGhEjpzUoB8F6DuBRygG5pORQpQf/WyjZkcNJGg0tXE+BWg7rtDTTFqdjKZ4AAC1H0B8r0rJ3IzCANPwH9jK1tHnkZnMkkSmXeF8wHmUEW+dyFfJldbqPaphZZxh+PiDGeHGLf32puE1eWodE6C5KjJfIzzhhjxsUFTmgWyvLfjUHfxiTA1RvUd69UwYgCqfd3f7zX2ao39N9R1yinzfNh3DjBsk+xJ+RM0lYyfgX2PrIFnH8+xFY2u8K3APBGwE+w5iQD5J/arZPiGx/7h5ywFyN/Yr5LhE7xreBY2r/JBsucHdIXNq1zA5PAubF7lAq4sEkjYvKIPAunrtlKA+m5Sy2PcY2Hzijow2TCrnaMA9V2CehrnZ+13W5KEzSvqQIVJNjXDQiHfVwuswX5XE2iDMbF5RRpldifjmpmyG5mUbF5RBqj7+ArtV0x6j5eeWkMXoP5O3Ydh84oyMq1M0kt5X50DH6ZWx4TCVP/u7UpuoUYVlE7JMQtW79qsEUGEcKzxNeNfwMUV3yWnjCTvaC+SYy+EEEEIIuzatBUgPzl1PUdg1IpESLcA9X0K02qahDYEqcPc2oUnmBwZo/TkmG6wc/SVUDPqVID8tJoYzYAFkyNnEHHMJ5VwhSC4A8iji/KumVpMjsxRgnyJrfwa5Ji+lrpIkJmSP6bA/oijHbYm3wuTwxOwjsF13R/mde6gugD11ghWrlHX4d4Bgd2j65CPxz8miNapufV8XVyhzIyHoQ/Y7wTIm9JT+4u2vYbHdx+jZpWpLD5whyDovHO/vuQBF1eh2+sVoL4EyBu3nYVE5GolZwkuruqo1q39RDA2QYQOYm0BQQtTO/XdQ6scDtv/6QMXsU2JMUUUAerOaNyxB70uXIqTNBKsX4BFBHX8x7anebLCaRmEkfwtVPmwOP4y8YNB64M9RnSUUD0avuv3tmkQSt0LJWj7DNxNZhZhNq8Y3qF5yQ5vbsqjVsSx7gKGBApCkp/xOEiYVxyVIoUVctS7wZOT2Qz7He4u/m59Tp6xlYmbV0gQPs8gg4WSUD5SfWa+89aVKLMH0Gk7T0wQUpghRzBi9L4bdxS/OkbAvGKCEMF81ZtxP8ig46j2prvJvAlP5L45eYLUSXcC1F2W4eoJnwOzmiPlqBmUbVr0b0NFA5ggNear3j9nQ5RJhzyBqKMGSVaDPxT8D5IEQVtcp44YeaKUnZJQUXeNKSyQZD0ySsT/0HuYiBjY1yXIB/NnlA/GdjocAJuQygcB8sbrM2mPoWeib+tr6GKiKo2ePhHxP2oFOoaeR2PgxL+VTdke/V1jUtpM0wUlq0kx9T0FqLetneF63L/JF77rXIPQXmwp3BxMliAdu7tRbF/POiJKfZFIJ9EP7z1sYOLg4vCFJhUNE7HE8z79GmSUau2aVEIMDgwjfoV/Zvljk/2K6RJhSYIXnOrrxR1TrysYsasukzG7jMaB96qjK77BS38PNnEmSLBxUJ3I9zn42UcwxQR13yYR6i0UOB5KgRZifT6SIEgiKdtzyX1DkzkISeAAAi7+Z2d9JGYmL4KUg44Sd77SqTKJJtS04o/9SgOlhGqPJlEnMgbVZe93/vPPf+0XCUIESf16bUoE8eGAFyA/fd5nKEC+zg64XvwwuqTpQC9Us2l/x61HjAB17/ouNgOVE/SORKmAXjr2RRmVHMLQ6JMPsiyGVuEAs+ZVnTOFO1FNoAWLop0Lt6gnKR/EphBzPInQfak94/BDjInPLw2vGQwPuzCka+5jdJ+rWdWXdoZ2Ptz8r6RO2NdAgSDR6lrhCusQAjey+291d5M6emTvY9TfN1LalWKBU39nRRBKSJkgBai/0U5msYiZ00pp3nuk2uuQpDGJuiHdJYd9CsOwLB72Le+U+Hdu+kKui1aKd9AFqPuop7Kg7tzJLd+tnmGCJNiWevgz+ToRotfzzyx2gYbsrj5TEmF6E8QmwzRBIp24on3tVPoSI0aOUZpBF6vmRHyonOjot6aW9i7S+fz636s+UKPY7gtHQpkQOohNhmmCxPA33A//rHeNCQxNGQHyz3jHwBSPLnG0HWA4QBu5Wi9U3jyb+/sldAYiBBOkLn/55kgM/1mso16IGMHrhuULkD/N76K/Zhcd0kt+lO8+0pJInYEIkSpBAk9imxX75T7ecH1AuuHcAtQXjr1/d8doMZnql6HfKetdCHd9IXUGIkSaBAm2DaNyeeo5oRF6xnSN6zZdw9D8Gihv8/fd6JXunYbGt+r5AAYJl/4IQugMRIhkCfLi7QFPu4XXlP7xjTnY75AM1eN8ByT5Y3QjcmDzn/62F4VaX0zKXhZAPbeG6SJtFMst80L72VNBfDLMvwwngLouHdNDJsbVv2cN1WXdFsyIfCb+yiB9vb6xuN+hE69h3g1Cuk1mr020Dj/PPvu7APVt/iIjIzYZZibyy+phoNrjCh7ibKcuduahNVgv92n1/bTJpEYLB6i7+kru6GDPduyNSWm7i5A7AxEiTYKUYLAVwwHCkaKpBFg9+u5nsZiF23u+1inXOoUe5Y7JF4xAVXvXzO3TvRL8DhuSREk0dUJsIswSpI7Tn65sdoFdTycLFlAS7YgOqHuUNXOqnzvWS3N3TFEfkQTfw61J5UJyIV4h0iVIOTBjsIPsAVJMjbEXn8GIcbnP0y7lIa9s8Lk/vfJCuGDdl5i+/94InvLLo4Dqllz0qkF8JdGT+iWT3jGmn8vhDKX2uZoW1DPKHGzOcLGqHvF8pbokS4Il6NYtjS157RxdgliYHXii/dwhwY8QJ4f+aXwKH+2dvWGNrsRrZS0h5XT3cxDj5L1Jc6ljqmHayVfs5xqM7+hTZzcFEyS+LL4guLjCQMX0haY2H8s9pyycMEFYHGQymjU4zMNkxHFt2fr/vmI/w7IwQVh8KtB06v3pFB13E0r1zAgThFZd3lyl60OMK+33MgvQzHqOP2aj5ztur9meQLEuVm7SS8EYnJx3lOwhXR9jTZggLA4yylHCyobEzKglIUyQlEppnquMCdLkl8Wv/+tHSBMkl1WKrgwJkn5UylRIE4RW+4M8ZdlJpy+UCSLo5GPlKr10E4LV9teFOEH4LCSudC9P5Rl2J08QchXeN5f60tFt58LQl7fP7b2L/DKWyROEQ72LL/dlmMbtc5UfZrrGf94gc3jcVKF9gwkyFsx9mrk3juVynM3SURGDbCOKxAmS74uxUdqVfh4duC4so7sg2UYUmSDkxaYHiasfUoD8qC8VOX1O+sIEISvW1dg5A0FbjIrlJYnzJci4QqIm8l/1vcpzfWf9cnVik8SZEaSpE2U/X7x7uL8D9UankHW2zuHopXz7KCDgvnvkeBhoNQ9Hd+XdCFPKhFX59AuDpS116VBXOO+28shh9ZOO0ek2VQ/4uwT11DU/qK92Prs+CaHXbGZhLL+4WzNBUAjtIPPN5tOosWSujH7Mqf5cuGbaokIwQSgSZA40/RPr6NQSXHbTZveoP4cJkg1BBKXqi47RqSU4N7BslYEJgkInirWC1F+ol9bLq3Ow3ip5eXztjpb6fDJBTOGl/W8ocmBBtbDPv9+53Q8f1r06r3OneYIEfm9bIjUzyyZ3yhYuK/5w9+jM56+vuSAq9JrpLCKp02NPZxqacDsYnHFE67I+bYMbmtHCNZmq2JKPaTVA7F0kqBM+B4eFYW73mP4ef2ZXt4mNiYR5Z4QOA52BF4U2Nw22cMLn4HYF1iCMqU2QpmMTNs0JumCcdrmLqzYDAHse6mdYZBLK1cbGplYB6iuaM+fU8thg9zD4roBPaww93+wcCAIHOKUrJ9g8s3PxqCeu9xDcauEaKkamBKF/F2QCJahnVDL6pS+tJ8EhtG28exh8n/XzBIAeQTJ0yGMrtVeCWNrobkmadmYFE4QIYiu1X4JYJCw65J9Z7R4G827zuaGgE/nK6jCwQV6VFs1Xc7dUEHunNEuC5IicCGLeRdY+rWRU58oQTBAiyIsg8sPo4R3C2K4Rm9wIMjpFzwU5EcRUqWzTSlx3DyGYIGSQHUF0r9w65EP5iPfrZCm4fodPrC0m2aaZlA53H7oraic9YVbalIll0Q27TuUY6YZ6bQ8GMbPYPVpDzaZfn5tcT9Hr3Ju+kpo1lfS9euhGlqy/10OyoGvMnwlCHUZFC/xOjj5B7OxeP9Vb3J45N4JkmWayhlgE0fUPrAji78ak0yWg/AiS4Sn6GuIRRM8EMiYIpvC/+iCIa9SGCZIByBMEqssS5INbpu7cd6svl0ckRRCN95Flmska4hFEz/9ZIsgW1URcHjE7gpwjvBAE9rv2fsmSqOsS5EMrjkq6wb1vlzKnWtmxqYAJMo3QChZ0Fd+g3I6L3Z0TQbI9RV9DbOXXU9K5OsPhy6gyQZgg0QngoqRMEE9YMVezTTNZQ2zld1XSLfLMBKg7u7mlQ5D1sZ7bKXqN2MqvJ/MvpwD1d4sxYNEIM4edCZIB9FdRS1MD9ruFhEXNMO38y9mycDRevJIP+nObD0HOMs1EiA0IsgQPZyExSqhibS95s/Z4eRHkDE/RhTAhSJiKf9oKOYeIldWRKOpu1vQCdc8EoQyDMGmoIejWB176jFgE6ZNFfmAOmHxA0mBRPioEWfPlzjLNxGT1DTUEXSd7OZKlvvFz6DUoDTWvpliLBsYeXxwkQBBdJ1vLSSTYfzHUvJqCCTIFDRs5+ATp51Np3c0wVdDYjW+Czashlghyxqfo2rf6PoMNwkckq/dMFgoK1a1++X8myNmg1CzmEHqCfCqTk4JCdbtlU6EC5G+I+bRBCfI404jn82wPCXXTNEITRDuSpRNqxIIUt+0Ln98ZZp8LfbOnVknC7C5nuzJTQToE0Y1kOXSlOlVxkcdG+UvbO+eT2QHyT78EUpdgTUs0+YLVZdR9r58hI03oVx0Mu8XO+UIFyM++Ym3cz5CRHf4PRmcJAHmHH04AAAAASUVORK5CYII=" alt="crearPost">
                 </figure>
                <h2 class="titulo-main">Crear Post</h2>
                </div> 
                  	       
	        <form id="form-crearPost">
	        
	        <div class="contenedorFieldsetCrearPost">
	        <fieldset class="fieldsetCrearPost">
	        <legend>Encabezado</legend>
	        <label for="titulo"><h3>Titulo:</h3></label>
	        <input type="text" id="crear-titulo" name="crear-titulo" placeholder="Titulo" required>
	        <label for="subtitulo"><h3>Subtitulo:</h3></label>
	        <input type="text" id="crear-subtitulo" name="crear-subtitulo" placeholder="Subtitulo" required>
	        </fieldset>  

	        <fieldset class="fieldsetCrearPost">
	        <legend>Categoria</legend>
	        <label for="categoria"><h3>Categoria:</h3></label>
	        <select id="seleccionarCat-crearPost">
	            <option selected disabled>Selecionar Categoria</option>
	            ${categorias.map(categoria =>
	              `<option value="${categoria.id}">${categoria.nombre}</option>`
	            ).join('')}
	          </select>
	          <label for="tag"><h3>Tags (Opcional):</h3></label>
	       <input type="text" id="crear-tag" name="crear-tag" placeholder="Separar con comas">
	       </fieldset>   
	       </div>
	       <fieldset class="fieldsetCrearPost fieldsetCuerpo">
	       <legend>Cuerpo</legend>
	      <input name="cuerpo" id="crear-cuerpo" type="hidden" required>
	      <div id="editor"></div>
	      </fieldset>
	        <button id="btn-publicar-post" onclick="Posteo.publicarPost();">Publicar</button>
	        </form>
	        </section>`;
      
	    document.querySelector('#panel-main').innerHTML = template;
	Posteo.loading(false);
      let selectCategoria = document.querySelector('#seleccionarCat-crearPost');
      selectCategoria.addEventListener('change', (e)=>{
         
      	    this.catSelect = e.target.value;
           
      });
      
      Posteo.renderEditor();  
            
            }
              else{
                  const urlHistory={"url":"crearPost"};
                  sessionStorage.setItem("history", JSON.stringify(urlHistory));
                  window.location.replace("login.html");
              }
            
         } catch(error){
              const urlHistory={"url":"crearPost"};
              sessionStorage.setItem("history", JSON.stringify(urlHistory)); 
              console.log(error);
              window.location.replace("login.html");
          }
         
  }

    static renderEditor(){
        const toolbarOptions = [
            ['bold', 'italic', 'underline'],        
            ['blockquote', 'code-block','link', 'image', 'video'],
          
            [{ 'header': 1 }, { 'header': 2 }],               
            [{ 'list': 'ordered'}, { 'list': 'bullet' }],
            [{ 'script': 'sub'}, { 'script': 'super' }],      
            [{ 'indent': '-1'}, { 'indent': '+1' }],        
            [{ 'direction': 'rtl' }],                    
          
            [{ 'size': ['small', false, 'large', 'huge'] }],  
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
          
            [{ 'color': [] }, { 'background': [] }],        
            [{ 'font': [] }],
            [{ 'align': [] }]
          
            //['clean']                                       
          ];

          this.quill = new Quill('#editor', {
            modules: { 
              syntax:true,  
              toolbar: toolbarOptions,
              imageResize: {}
            },
            placeholder: 'Construye aquí tu epopeya...',
            theme: 'snow'
          });
          
    }

     static publicarPost(){
        const URL_PUBLICAR_POST = 'api/privado/CrearPostServer';
    	const posteo = {}; 
        const cat={"id":this.catSelect};
        posteo.titulo= document.querySelector('#crear-titulo').value;
        posteo.subTitulo= document.querySelector('#crear-subtitulo').value;
        posteo.cuerpo = JSON.stringify(this.quill.getContents());
        posteo.categoria = cat;
     
      try{
        Http.post(URL_PUBLICAR_POST, posteo);  
      }catch(error){
        
          console.log("Error ",error);
      }
      
      
    }
    static async buscarPorCategoria(id){
         const param = {"categoria": "" + id};
         
       try{
            Posteo.loading(true);
            Posteo.ocultarMenu();
            let posts = await Posteo.consultarPosts(param);
        
            Posteo.mostrarPosts(posts);
           
       } catch(err){
           console.log(`Error: ${err}`);
       }
      Posteo.loading(false);   
    }
    
  static async mostrarPosts(posts){
               
               let template ='';
  
	     template = posts.length !== 0 ?  
	        `<section class="main-post">
	            <h2 class="titulo-main">Posts</h2>
                    ${posts.map(post =>
	            `<article class="article-post">
                        <div class="area-t-post">
                          <h2 class="titulo-post" onclick="Posteo.mostrarBlogPost(${post.id});"><a href="#">${post.titulo}</a></h2>
	                  <p>${post.subTitulo}</p>
                          <hr class="blog-post-divisor"/> 
                         </div>
                          
                        <div class="area-a-post">
                         <figure class="area-img-cat">
                            <img class="categoria-img" src="${post.categoria.imagen}"/>
                          
                        </figure>
                            <figure>
                              <img class="logo-user" src="${post.autor.avatar}" alt="img-user"/>
                             
                            </figure>
                          </figure>
                            <figure>
                              <img src="data:image/png;base64,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" alt="img-cal"/>
                            </figure>
                          <a href="#"onclick="Posteo.buscarPorCategoria(${post.categoria.id});">${post.categoria.nombre}</a>
                          <a href="#">${post.autor.nombre}</a>
                          <p>${post.fechaCreacion.date.day}-${post.fechaCreacion.date.month}-${post.fechaCreacion.date.year}</p>
                      </div>
	          </article>`).join('')}
	          </section>
`
	        		 : `<section class="main-post">
	        		        <h2 class="titulo-main">Posts</h2>
	        		          <article class="article-sin-post">
	        		                  <h2 class="titulo-post">Oops!! No hay posts disponibles</h2>
	        		                  <p>Se el primero en postear</p>
	        		          </article>
	        		          </section>`;		
	      
      
	          document.querySelector('#panel-main').innerHTML = template;
	     
	        }
                
             

	 static  async mostrarBlogPost(id){
                
                const param = {"postid":"" + id};
	    	const URL_POST_SERVER = 'api/PostServer?&q=';
               try{
                   Posteo.loading(true);
                   const data = await Http.get(URL_POST_SERVER+JSON.stringify(param));
	    	    Posteo.renderBlog(data);
                    Posteo.loading(false);
	    
               
               } catch (error){
                    console.log('error', error);
                    Posteo.loading(false);
                }
	    }
	    
	static renderBlog(datos){
              
	        let options = {
	            readOnly: true,
                    modules: {syntax:true,toolbar: [['code-block']]},
	            scrollingContainer: '#scrolling-container'
	          };
	          let template = 
	          `<section class="blog-post">
                  <div class="blog-post-header">
                    <figure>
                            <img class="blog-post-categoria-img" src="${datos.categoria.imagen}"/>
                            <i class="far fa-star" id="favorito" onclick="Posteo.agregarFavorito(${datos.id})"></i>
                            <p>${datos.fechaCreacion.date.day}-${datos.fechaCreacion.date.month}-${datos.fechaCreacion.date.year}</p>
                        </figure>
                           <figure class="blog-post-user">
                              <img class="blog-post-user-img" src="${datos.autor.avatar}" alt="img-user"/>
                              <p><a href="#">${datos.autor.nombre}</a></p>
                            </figure>
                   </div>
                   <hr class="blog-post-divisor"/>
	          <h2 class="titulo-blog-post">${datos.titulo}</h2>
	          <p class="subtitulo-blogPost">${datos.subTitulo}</p>
	          <hr class="blog-post-divisor"/>
	          <div id="scrolling-container">
	          <div id="blog"></div>
	          </div>
	          </section>`;  
	          document.querySelector('#panel-main').innerHTML = template;
	          let blog = new Quill('#blog', options);
	          blog.setContents(JSON.parse(datos.cuerpo));
	    }
	static agregarFavorito(post){
            const estrella = document.querySelector('.fa-star');
            console.log(estrella);
            estrella.classList.remove('far');
            estrella.classList.add('fas');
        }
    static ocultarMenu(){
        if(document.querySelector('.boton-flotante').classList.contains("change")){
           document.querySelector('.boton-flotante').classList.toggle("change");
            document.querySelector('.sidenavpanel').style.width = "0";
        }

    }    
    
    static loading(on) {
    const loadingsvg = document.querySelector("#loading");
  if (on) {
      loadingsvg.style.display = "block";
  } else {
      loadingsvg.style.display = "none";
  }
}
}
//Posteo.buscarPorCategoria(0);
