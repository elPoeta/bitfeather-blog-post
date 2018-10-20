package elpoeta.bitfeather.modelo;

/**
 *
 * @author elpoeta
 */
public class Categoria {
    private Integer id;
	private String nombre;
        private String imagen;
	private boolean isActiva;
	
	public Categoria() {
          this.imagen = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAAyCAYAAADSprJaAAACCklEQVRYhc2YW3UCMRCGJ1GAhF9CHsiERxwUB8UBOCgOwAE4AAfgAByAA+pg+xCu7e4yExLof848bvLtZG4JkUbwjhBGFmFuENYGfLQI1dkM+GgQ1hZ+SggjQg+q9Zs3dh1CGBmE/e2GUjPgJYE/nwDgz99/m2oGYU/o9lV/H936/OY1NhcAeJfr71u8sia4ztsAbmJl23QEScH3hM3uGAx4+WKAyiJUBO9OXuj2E126M+DNjX0nxMfq5AVtJvCisRDBD7UwROhBDSDLMDEIEcJY4bpDc2r99YgYwiCsFEE0FAGcpIDgjdgLShnwLiuEBU8SIESFjyx4ITsKZVtWpD0RvBMcxUoHoKu+p4+aI1mVERcA3koBrhBERODBbSDFPOdFaQAD3jQsdq7nCiUAxFjTDDqtAN4lduDZ48XFAPoZxIB37wfQxNo/BnCdRIBNHgBKmT9CJRoBxEqawnICEJGm9cc6EMZZAQiuowPQzR9CCB4ojmCSH4CILHgiywL9AJQdopgXNBD5mtIzEEUlCEz1FJaiR4Nx0aO4KPaNWpAydaEVhgcxRqLleyRTg3T7FvxFCB/ZuqNUFjypb+V++hKYR5clA96WBZHf4DMNsDUyCAdxBy0idRsvUS+UE1WxoqWDSLjFSVTyQUUuwTNC9AIPykFEkGH7y1zBgeYepAcLXpxT9vqMkBaMP/fz6IiAJi/RAAAAAElFTkSuQmCC";
          this.isActiva = true;
            }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

        public void setImagen(String imagen){
            this.imagen = imagen;
        }
        
        public String getImagen(){
           return this.imagen;
        }
	
        public boolean isActiva() {
		return isActiva;
	}

	public void setActiva(boolean isActiva) {
		this.isActiva = isActiva;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", isActiva=" + isActiva + "]";
	}
	
}
