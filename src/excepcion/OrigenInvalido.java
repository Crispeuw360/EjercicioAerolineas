package excepcion;

public class OrigenInvalido  extends Exception{

	private static final long serialVersionUID = 1L;

	public OrigenInvalido(String mensaje) 
	{
        super(mensaje);
    }

}
