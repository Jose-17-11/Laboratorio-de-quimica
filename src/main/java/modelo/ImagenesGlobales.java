package modelo;

public enum ImagenesGlobales {
	IMAGE_PATH1("ruta_de_la_imagen_1"),
    IMAGE_PATH2("ruta_de_la_imagen_2");

    private final String path;

    ImagenesGlobales(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
