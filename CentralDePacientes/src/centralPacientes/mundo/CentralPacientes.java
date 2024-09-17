/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Central de Pacientes.
 * Adaptado de CUPI2 (Uniandes)
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package centralPacientes.mundo;

import java.util.ArrayList;

/**
 * Esta clase representa una central en la que se maneja una lista de pacientes
 */
public class CentralPacientes {
    // Atributos
    private Paciente primero;
    private int numPacientes;
    private ArrayList<Paciente> pacientes;
    private ArrayList<String> listaClinicas;


    // Constructores
    public CentralPacientes() {
        primero = null;
        numPacientes = 0;
        pacientes = new ArrayList<>();
        listaClinicas = new ArrayList<>();
        listaClinicas.add("Clínica del Country");
        listaClinicas.add("Clínica Palermo");
        listaClinicas.add("Clínica Reina Sofía");
        listaClinicas.add("Clínica El Bosque");
        listaClinicas.add("Clínica San Ignacio");
        listaClinicas.add("Otra");
    }

    // Métodos

    // ... (otros métodos)
    public Paciente localizar(int codigo) {
        Paciente actual = primero;
        while (actual != null && actual.darCodigo() != codigo) {
            actual = actual.darSiguiente();
        }
        return actual;
    }
    /**
     * Adiciona un paciente a la lista de pacientes después del paciente con el código especificado.
     */
    public void agregarPacienteAntesDe(int cod, Paciente pac) throws NoExisteException {
        Paciente anterior = null;
        Paciente actual = primero;

        while (actual != null && actual.darCodigo() != cod) {
            anterior = actual;
            actual = actual.darSiguiente();
        }

        if (actual == null) {
            throw new NoExisteException(cod);
        }

        // Si anterior es null, significa que el paciente a agregar debe ir al principio de la lista.
        if (anterior == null) {
            pac.cambiarSiguiente(primero);
            primero = pac;
        } else {
            anterior.cambiarSiguiente(pac);
            pac.cambiarSiguiente(actual);
        }

        numPacientes++;
    }
    public void agregarPacienteDespuesDe(int cod, Paciente pac) throws NoExisteException {
        Paciente anterior = localizar(cod);

        if (anterior == null) {
            throw new NoExisteException(cod);
        } else {
            pac.cambiarSiguiente(anterior.darSiguiente());
            anterior.cambiarSiguiente(pac);
        }
        numPacientes++;
    }

    /**
     * Elimina el paciente con el código especificado.
     */
    public void eliminarPaciente(int cod) throws NoExisteException {
        Paciente actual = primero;
        Paciente anterior = null;

        while (actual != null && actual.darCodigo() != cod) {
            anterior = actual;
            actual = actual.darSiguiente();
        }

        if (actual == null) {
            throw new NoExisteException(cod);
        } else {
            if (anterior == null) {
                primero = actual.darSiguiente();
            } else {
                anterior.cambiarSiguiente(actual.darSiguiente());
            }
            numPacientes--;
        }
    }

    // ... (otros métodos)
    public int darNumeroPacientes() {
        return numPacientes;
    }
    /**
     * Devuelve una lista con los pacientes de la central
     */
    public ArrayList<Paciente> darPacientes() {
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        Paciente actual = primero;

        while (actual != null) {
            listaPacientes.add(actual);
            actual = actual.darSiguiente();
        }

        return listaPacientes;
    }

    // ... (otros métodos)

    /**
     * Retorna la cantidad de hombres que hay en la lista
     */
    public int cantHombres() {
        int cont = 0;
        Paciente p = primero;

        while (p != null) {
            if (p.darSexo() == 1) {
                cont++;
            }
            p = p.darSiguiente();
        }

        return cont;
    }

    /**
     * Retorna la cantidad de mujeres que hay en la lista
     */
    public int cantMujeres() {
        int cont = 0;
        Paciente p = primero;

        while (p != null) {
            if (p.darSexo() == 2) {
                cont++;
            }
            p = p.darSiguiente();
        }

        return cont;
    }

    /**
     * De las 6 opciones de clínicas que tiene la central
     * ¿Cuál es el nombre de la más ocupada, la que tiene más pacientes?
     *
     * @return nombre de la clínica
     */
    public String metodo4() {
        if (listaClinicas.isEmpty()) {
            return "No hay clínicas registradas";
        }

        String clinicaMasOcupada = listaClinicas.get(0);
        int maxPacientes = 0;

        for (String clinica : listaClinicas) {
            int numPacientesClinica = 0;

            for (Paciente paciente : pacientes) {
                if (paciente.darClinica().equals(clinica)) {
                    numPacientesClinica++;
                }
            }

            if (numPacientesClinica > maxPacientes) {
                maxPacientes = numPacientesClinica;
                clinicaMasOcupada = clinica;
            }
        }

        return clinicaMasOcupada;
    }
    public void agregarPacienteAlComienzo(Paciente pac) {
        if (primero == null) {
            // Si la lista está vacía, establecer el nuevo paciente como el primero.
            primero = pac;
        } else {
            // Realiza la adición antes del paciente que está al inicio de la lista.
            pac.cambiarSiguiente(primero);
            primero = pac;
        }
        numPacientes++;
    }
    public void agregarPacienteAlFinal(Paciente pac) {
        if (primero == null) {
            // Si la lista está vacía, establecer el nuevo paciente como el primero.
            primero = pac;
        } else {
            // Busca el último paciente de la lista
            Paciente p = localizarUltimo();

            // Adiciona el paciente después del último paciente de la lista
            p.cambiarSiguiente(pac);
        }
        numPacientes++;
    }
    private Paciente localizarUltimo() {
        if (primero == null) {
            return null; // La lista está vacía
        }

        Paciente actual = primero;
        while (actual.darSiguiente() != null) {
            actual = actual.darSiguiente();
        }

        return actual;
    }
    public ArrayList<String> darListaClinicas() {
        return listaClinicas;
    }
}
