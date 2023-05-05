package color;


import java.io.IOException;

public class Pruebas6 {

	
	// ejemplo de como aplicar los distintos filtros a una sola imagen.
	public static void main(String[] args) {

				
				RGBlector lector;
				
				double [] filtro_medias_vertical;
				double [] filtro_medias_horizontal;
				double [][] filtro_medias_2D;
				double[][] gradiente;
				int radio=4;
				double potencia=0.4;
				
				
				String nombre_imagen="foto2",formato=".jpg";
				try {
					
					lector = new RGBlector(nombre_imagen+formato);
				
					
					/*for (int i = 0; i < 10; i++) {
						System.out.println(i);
				    	for (int j = 0; j < 5; j++) {
				    		System.out.print(imagenProcesada[i][j]); 		
				    	}
				    	System.out.println();
				    }
				    	*/
				    
					
					
					filtro_medias_horizontal=lector.media_color_horizontal(RGBlector.CANALAZUL);
					lector.filtro_horizontal(filtro_medias_horizontal, potencia, RGBlector.CANALAZUL);
					filtro_medias_horizontal=lector.media_color_horizontal(RGBlector.CANALROJO);
					lector.filtro_horizontal(filtro_medias_horizontal, potencia, RGBlector.CANALROJO);
					filtro_medias_horizontal=lector.media_color_horizontal(RGBlector.CANALVERDE);
					lector.filtro_horizontal(filtro_medias_horizontal, potencia, RGBlector.CANALVERDE);
					
					lector.guardarComo(nombre_imagen+"-filtro-horizontal"+formato);
					
					
					System.out.println("Horizontal");
					
					
					lector = new RGBlector(nombre_imagen+formato);
					
					
					filtro_medias_vertical=lector.media_color_vertical(RGBlector.CANALAZUL);
					lector.filtro_vertical(filtro_medias_vertical, potencia, RGBlector.CANALAZUL);
					filtro_medias_vertical=lector.media_color_vertical(RGBlector.CANALROJO);
					lector.filtro_vertical(filtro_medias_vertical, potencia, RGBlector.CANALROJO);
					filtro_medias_vertical=lector.media_color_vertical(RGBlector.CANALVERDE);
					lector.filtro_vertical(filtro_medias_vertical, potencia, RGBlector.CANALVERDE);
					
					lector.guardarComo(nombre_imagen+"-filtro-vertical"+formato);
					
					System.out.println("Vertical");
					
					
					lector = new RGBlector(nombre_imagen+formato);
					
					
					filtro_medias_2D=lector.media_color_2D(radio, RGBlector.CANALAZUL);
					lector.filtro_2D(filtro_medias_2D, potencia, RGBlector.CANALAZUL);
					filtro_medias_2D=lector.media_color_2D(radio, RGBlector.CANALROJO);
					lector.filtro_2D(filtro_medias_2D, potencia, RGBlector.CANALROJO);
					
					
					lector.guardarComo(nombre_imagen+"-filtro-2D"+formato);
					
					System.out.println("2D");

					
					lector = new RGBlector(nombre_imagen+formato);
					lector.quitarcolor(RGBlector.CANALVERDE);
					lector.guardarComo(nombre_imagen+"-sin-verdes"+formato);

					System.out.println("Quitar color");
					
					
					lector = new RGBlector(nombre_imagen+formato);
					gradiente= lector.gradiente2D(2,1,200, 350, 40);
					lector.filtro_2D(gradiente, 0.1, RGBlector.CANALROJO);
					lector.filtro_2D(gradiente, 0.3, RGBlector.CANALAZUL);
					gradiente= lector.gradiente2D(2,-1,150, 300, 40);
					lector.filtro_2D(gradiente, 0.2, RGBlector.CANALVERDE);
					
					lector.guardarComo(nombre_imagen+"-filtro-2D-gradientes"+formato);

					
					
					System.out.println("Filtro 2D con gradiente");

					
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				

			}


		
		
		
		
		
		

		

}

