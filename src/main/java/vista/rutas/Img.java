package vista.rutas;

public enum Img {
	IMAGEN_1("C:\\Users\\Jose Manuel\\eclipse-workspace\\Laboratorio-de-quimica\\src\\main\\resources\\img\\tecCuautla.png"),
	IMAGEN_2("C:\\Users\\Jose Manuel\\eclipse-workspace\\Laboratorio-de-quimica\\src\\main\\resources\\img\\TecNM.png"),
    IMAGEN_3("C:\\Users\\Jose Manuel\\eclipse-workspace\\Laboratorio-de-quimica\\src\\main\\resources\\img\\admin.png");

    private final String ruta;

    private Img(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
}
