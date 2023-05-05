package color;

public class RGBint {
	private int[] RGB;
	
	
	
	
	
	//A partir de la representación del valor RGB mediante un solo entero, que java usa en algunos paquetes por defecto,
	//a tres enteros, uno por canal
	public static RGBint getRGBintFromint(int rgbAsint) {
		int r=(rgbAsint>>16)&(0xFF);
		int g=(rgbAsint>>8)&(0xFF);
		int b=(rgbAsint)&(0xFF);
		
		return new RGBint(r,g,b);
		
	
	}

	
	
	public RGBint(int R,int G,int B) {
		RGB= new int[]{R,G,B};
		
	}
	public RGBint(int[] RGB) throws Exception {
		
		if (RGB.length!=3) {
			throw new Exception("Formato RGB no válido");
		}
		this.RGB= RGB;
		
	}
	
	
	//Métodos get 
	public int red() {
		return RGB[0];
	}
	public int green() {
		return RGB[1];
	}
	public int blue() {
		return RGB[2];
	}
	public int[] getRGBints() {
		return RGB;
	}
	
	//Método get válido para los tres,útil al usar filtros y evitar código con if o switch
	//podría  mejorarse haciendo comprobaciones sobre el entero recibido, y gestionando posibles errores.
	public int getCanal(int canalcolor) {
		return RGB[canalcolor];
		
	}
	
	
	
	
	//método para obtener a partir de los tres enteros representando los valores RGB,
	//la representación en un solo entero.
	public int getRGBasint() {
		int RGBasint=0;
		RGBasint+= RGB[0]<<16;
		RGBasint+= RGB[1]<<8;
		RGBasint+= RGB[2];
		
		return RGBasint;
	}
	
	
	
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("(").append(this.red()).append(", ").append(this.green()).append(", ")
		.append(this.blue()).append(")");
		
		//return ("( "+ this.red()+", "+this.green()+", "+this.blue()+" )");
		return(sb.toString());
		
	}
	
	
	//método setter que vale para los tres valores RGB. 
	public void setCanal(int valor,int canalcolor) {
		RGB[canalcolor]=valor;
		
	}
	
	
	
}