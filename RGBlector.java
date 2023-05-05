package color;


import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;




public class RGBlector {
	
	//constantes para que al llamar a los filtros sea transparente el color manipulado viendo el código
	static final int CANALROJO=0;
	static final int CANALVERDE=1;
	static final int CANALAZUL=2;
	
	//en imagen se guardará la imagen como array bidimensional de colores RGB
	private RGBint[][] imagen;
	
	//la ruta no la uso realmente más que al principio, pero podría ser útil si se añaden funcionalidades
	private String ruta;
	
	//dimensiones de la imagen
	private int alto=0,ancho=0;
	
	
	//el constructor lo uso para extraer los valores RGB de la imagen 
	//a partir del archivo especificado en la ruta
	public RGBlector(String ruta) throws IOException {
		//Idealmente se harían más comprobaciones
		this.ruta=ruta;
		crearRGBimagen();
		
	}
		
	private void crearRGBimagen() throws IOException {
		//https://www.tutorialspoint.com/how-to-get-pixels-rgb-values-of-an-image-using-java-opencv-library#:~:text=Retrieving%20the%20pixel%20contents%20(ARGB%20values)%20of%20an%20image%20%E2%88%92&text=Get%20the%20pixel%20value%20at,and%20getBlue()%20methods%20respectively.
		
		File archivo = new File(ruta);
	    BufferedImage img = ImageIO.read(archivo);
	    alto=img.getHeight();
	    ancho= img.getWidth();
	    
	    imagen=new RGBint[alto][ancho];
	    
	    
	    for (int i = 0; i < alto; i++) {
	    	for (int j = 0; j < ancho; j++) {
 

	        	// en BufferedImage lee horizontal primero
	            imagen[i][j] = RGBint.getRGBintFromint( img.getRGB(j,i));
	            }}
	}
	
	public RGBint[][] getRGBimagen() {
		return imagen;
		
	}
	
	
	
	
	
	
	
	
	
	//guardar imagen en la carpeta del proyecto con el nombre especificado
	public void guardarComo(String nombre) {
		
		BufferedImage imagen_guardar =new BufferedImage(ancho, alto,BufferedImage.TYPE_INT_RGB);


		for(int i=0; i< alto;i++) {
			for (int j=0;j<ancho;j++) {
			
				imagen_guardar.setRGB(j, i, imagen[i][j].getRGBasint());
						}}
        
		try {
			ImageIO.write(imagen_guardar, "jpg", new File(nombre));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	
	
	
	
	///////////////////
	//// Filtros /////
	/////////////////
	
	//A partir de ciertos parámetros, los filtros se usarán para modificar la imagen.
	
	public void filtro_vertical(double[] filtro, double potencia, int canalcolor) {
		
		//habría que ver si esta condición es superflua
		if (filtro==null) {
			return;
		}
		if (potencia>1 ||potencia<0) {
			System.out.println("El parametro potencia debe estar entre 0 y 1");
			return;
		}
		if (ancho != filtro.length) {
			System.out.println("El tamaño del array no es adecuada, debe ser: "+ancho);
		}
		
		double valor_temporal;
		
		for (int j=0;j<ancho;j++) {
			for (int i=0;i<alto;i++) {
			
			//Se pueden ahorrar operaciones en estos bucles, no reescribiendo valor_temporal....
				valor_temporal= (double) imagen[i][j].getCanal(canalcolor);
				valor_temporal= (1-potencia)*valor_temporal+potencia*filtro[j];
				imagen[i][j].setCanal((int) valor_temporal, canalcolor);
				
			}
		}	
	}
	
		
	public void filtro_horizontal(double[] filtro, double potencia, int canalcolor) {
		
		
		if (filtro==null) {
			return;
		}
		
		if (potencia>1 ||potencia<0) {
			System.out.println("El parametro potencia debe estar entre 0 y 1");
			return;
		}
		if (alto != filtro.length) {
			System.out.println("El tamaño del array no es adecuada, debe ser: "+alto);
		}
		
		double valor_temporal;
		for (int i=0;i<alto;i++) {
			for (int j=0;j<ancho;j++) {
			
				
				valor_temporal= (double) imagen[i][j].getCanal(canalcolor);
				valor_temporal= (1-potencia)*valor_temporal+potencia*filtro[i];
				imagen[i][j].setCanal((int) valor_temporal, canalcolor);
				
				
			}
		}
		
		
	}
	public void filtro_2D(double[][] filtro, double potencia, int canalcolor) {
		if (filtro==null) {
			return;
		}
		
		if (potencia>1 ||potencia<0) {
			System.out.println("El parametro potencia debe estar entre 0 y 1");
			return;
		}
		if (alto != filtro.length||ancho!=filtro[0].length) {
			System.out.println("El tamaño del array no es adecuada, debe ser: "+alto+"x"+ancho);
		}
		
		
		 
		double valor_temporal;
		for (int i=0;i<alto;i++) {
			for (int j=0;j<ancho;j++) {
				
				valor_temporal= (double) imagen[i][j].getCanal(canalcolor);
				valor_temporal= (1-potencia)*valor_temporal+potencia*filtro[i][j];
				
				//el castear double a int en principio no redondea. 
				imagen[i][j].setCanal((int) valor_temporal,canalcolor);
			}
				
		}
		
		
	}
	
	
	public void quitarcolor(int canalcolor) {
		
		for (int i=0;i<alto;i++) {
			for (int j=0;j<ancho;j++) {
				 
				imagen[i][j].setCanal(0,canalcolor);

			}
				
		}
			
	}
	
	

	
	
	///////////////////////////////////////////////
	//// Creación de parámetros para filtros /////
	/////////////////////////////////////////////

	// El uso de medias en principio quizás podría "suavizar" cambios bruscos en colores
	
	public double[] media_color_horizontal(int canalcolor) {
		
		double [] vector_medias= new double[alto] ;
		double suma_acumulada;
		
		
		//variar 2ª coordenada= moverse horizontalmente
		for (int i=0;i<alto;i++) {
			suma_acumulada=0;
			
			for(int j=0; j<ancho;j++) {
				suma_acumulada+=imagen[i][j].getCanal(canalcolor);				
			}
			vector_medias[i]=suma_acumulada/ancho;
		}	
		return vector_medias;
	}


	public double[] media_color_vertical(int canalcolor) {
		
		double [] vector_medias= new double[ancho] ;
		double suma_acumulada;

		//variar primera componente: movimiento vertical 
		for(int j=0; j<ancho;j++) {
			suma_acumulada=0;
			for (int i=0;i<alto;i++) {
				suma_acumulada+=imagen[i][j].getCanal(canalcolor);				
			}
			vector_medias[j]=suma_acumulada/alto;
		}	
		return vector_medias;
		
	}
	

	
	public double[][] media_color_2D( int radio_medias, int canalcolor) {
		
		if(imagen==null) {
			
			return null;
		}
		
		double [][] vector_medias= new double[alto][ancho] ;

		
		if (alto*ancho==1) {
			vector_medias[0][0]= (double) imagen[0][0].getCanal(canalcolor);
			return vector_medias;
		}
		
		double suma_acumulada;
		int limizquierda,limderecha,limabajo,limarriba;

		
		for(int j=0; j<ancho;j++) {
			for (int i=0;i<alto;i++) {
		
				
				suma_acumulada=0;
				
				//esquinas del cuadrado de pixeles del que calcular la media alrededor del pixel i,j
				limizquierda=Math.max(0, j-radio_medias);
				limderecha=Math.min(j+radio_medias,ancho);
				limabajo=Math.max(0, i-radio_medias);
				limarriba=Math.min(i+radio_medias,alto);
				
				

				for(int k=limizquierda; k<limderecha;k++) {
					for (int l=limabajo;l<limarriba;l++) {
						
						suma_acumulada+=imagen[l][k].getCanal(canalcolor);
						
					}}
								
				
				vector_medias[i][j]=suma_acumulada/((1+limderecha-limizquierda)*(1+limarriba-limabajo));
			}
			
		}	
		return vector_medias;
		
	}
	
	
	//el gradiente hace que resaltemos colores en ciertas zonas de la imagen, de forma que el
	// cambio en el color sea gradual. Los pasos indican hacia donde y cuanto crece el color
	// el centro y su valor es un punto con valor conocido desde el que calcular el resto
	public double[][] gradiente2D(double paso_horizontal,double paso_vertical, 
			int centro_vertical, int centro_horizontal, double valor_centro){
		
		double[][] grad = new double[alto][ancho];
		
		for(int j=0; j<ancho;j++) {
			for (int i=0;i<alto;i++) {
				
				grad[i][j]=paso_horizontal*(j-centro_horizontal)+paso_vertical*(i-centro_vertical)+valor_centro;
				
				if (grad[i][j]<0) {
					grad[i][j]=0;
				}
				
				if (grad[i][j]>255) {
					grad[i][j]=255;
				}
				
    		}}
		
		
		//
		
		
		return grad;
		
	}
	
	
	
}
