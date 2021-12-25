/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BBDD.BaseDeDatos;
import Modelo.Excepciones;
import Modelo.ModeloAlumno;
import Modelo.ModeloCita;
import Vista.VistaCita;
import com.toedter.calendar.JCalendar;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-abr-2021 17:07:40
 */
public class Controlador implements ActionListener {

    ModeloAlumno ma;
    VistaCita vc;

    /**
     * constructor delc ontrolador con dos parametro y la funcion inicia
     *
     * @param ma
     * @param vc
     * @throws SQLException
     */
    public Controlador(ModeloAlumno ma, VistaCita vc) throws SQLException {
        this.ma = ma;
        this.vc = vc;
        inicia();
    }

    /**
     * funcion que inicia la vista y los componentes
     *
     * @throws SQLException
     */
    private void inicia() throws SQLException {
        vc.setVisible(true);
        programaBotones();
        muestranombres();
    }

    /**
     * @param funcion que instancian los botones
     */
    private void programaBotones() {
        vc.getListadoalumnos().setActionCommand("listadoalumnos");
        vc.getListadoalumnos().addActionListener(this);
        vc.getBotoncrear().setActionCommand("insertar");
        vc.getBotoncrear().addActionListener(this);
        vc.getBotonactualizar().setActionCommand("actualizar");
        vc.getBotonactualizar().addActionListener(this);
        vc.getBotonborrar().setActionCommand("borrar");
        vc.getBotonborrar().addActionListener(this);
        vc.getBotoncita().setActionCommand("cita");
        vc.getBotoncita().addActionListener(this);
        vc.getBotonanularcita().setActionCommand("anularcita");
        vc.getBotonanularcita().addActionListener(this);
        vc.getBotonactualizarcita().setActionCommand("actualizarcita");
        vc.getBotonactualizarcita().addActionListener(this);
        vc.getBotonimportaralumnos().setActionCommand("importaralumnos");
        vc.getBotonimportaralumnos().addActionListener(this);
        vc.getBotonimportarcitas().setActionCommand("importarcitas");
        vc.getBotonimportarcitas().addActionListener(this);
        vc.getBotonexportaralumnos().setActionCommand("exportaralumnos");
        vc.getBotonexportaralumnos().addActionListener(this);
        vc.getBotonexportarcitas().setActionCommand("exportarcitas");
        vc.getBotonexportarcitas().addActionListener(this);
        vc.getBotonjavadoc().setActionCommand("abrirjavadoc");
        vc.getBotonjavadoc().addActionListener(this);
    }

    /**
     * funcion que recoge los eventos
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        String comando = ae.getActionCommand();
        switch (comando) {
            case "listadoalumnos":
                rellenarLista();
                break;

            case "insertar":
            try {
                insertarAlumno();
            } catch (Excepciones ex) {
                ex.getMessage();
            }
                break;

            case "actualizar":
            try {
                actualizarAlumno();
            } catch (Excepciones ex) {
                ex.getMessage();
            }
                break;

            case "borrar":
                borrarAlumno();
                break;

            case "cita":
                darCita();
                break;

            case "anularcita":
                anularCita();
                break;

            case "actualizarcita":
                actualizarCita();
                break;

            case "exportarcitas":
                ModeloCita.exportarCitas();
                break;

            case "exportaralumnos":
                ModeloAlumno.exportarAlumnos();
                break;

            case "importarcitas":
                int seleccion = vc.getSelectorfichero().showOpenDialog(vc);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File fichero = vc.getSelectorfichero().getSelectedFile();
                    try {
                        ModeloCita.importarCitas(fichero);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else {
                }
                break;
            case "importaralumnos":
                int selecAlum = vc.getSelectorfichero().showOpenDialog(vc);
                if (selecAlum == JFileChooser.APPROVE_OPTION) {
                    File fichero = vc.getSelectorfichero().getSelectedFile();
                    try {
                        ModeloAlumno.importarAlummos(fichero);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
                break;
                case "abrirjavadoc":
            try {
                File javadoc=new java.io.File("target/site/apidocs/index.html").getAbsoluteFile();
                Desktop.getDesktop().open(javadoc);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * funcion que muestra los nombres de la BBDD en la lista
     */
    private void muestranombres() {
        try {
            JComboBox<String> lista = vc.getListadoalumnos();
            
            Statement sesion;
            sesion = BaseDeDatos.conexion();
            String consulta = "SELECT * FROM alumnos";
            ResultSet datos = sesion.executeQuery(consulta);
            while (datos.next()) {
                String nombre = datos.getString("nombre");
                lista.addItem(nombre);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * funcion que rellena la lista del combobox
     */
    public void rellenarLista() {
        //guardamos el nombre del alumno seleccionado
        JComboBox<String> listado = vc.getListadoalumnos();
        String nombreseleccionado = listado.getSelectedItem().toString();

        //extraemos los datos de la persona seleccionada
        ModeloAlumno alumno = new ModeloAlumno();
        alumno = alumno.datosAlumno(nombreseleccionado);

        //introducimos el nombre extraido de la BBDD en el cuadro de texto
        JTextField textonombre = vc.getTextonombre();
        String nombre = alumno.getNombre();
        textonombre.setText(nombre);

        //introducimos el apellido1 extraido de la BBDD en el cuadro de texto
        JTextField textoapellido1 = vc.getApellidouno();
        String apellido1 = alumno.getApellido1();
        textoapellido1.setText(apellido1);

        //introducimos el apellido2 extraido de la BBDD en el cuadro de texto
        JTextField textoapellido2 = vc.getApellidodos();
        String apellido2 = alumno.getApellido2();
        textoapellido2.setText(apellido2);

        //introducimos el email de la base de datos en el cuadro de texto
        JTextField textoemail = vc.getTextoemail();
        String email = alumno.getEmail();
        textoemail.setText(email);

        //introducimos el telefono extraido de la BBDD en el cuadro de texto
        JTextField textotelefono = vc.getTextotelefono();
        int telefono = alumno.getTelefono();
        String tel = String.valueOf(telefono);
        textotelefono.setText(tel);

        //introducimos si tiene cita en el cuadro
        ModeloCita mostrarcita = new ModeloCita();
        mostrarcita = mostrarcita.datosCita(alumno.getIdAlumno());
        JTextField citadia = vc.getTextodia();
        String citafecha = mostrarcita.getFecha();
        JTextField citahora = vc.getTextohora();
        String citas = mostrarcita.getHora();
        citadia.setText(citafecha);
        citahora.setText(citas);
    }

    /**
     * funcion que inserta un alumno nuevo y compruba el patron del email
     */
    public void insertarAlumno() throws Excepciones {
        //extraemos los datos de las casillas
        JTextField nombreinsertar = vc.getTextonombre();
        JTextField ape1insertar = vc.getApellidouno();
        JTextField ap2insertar = vc.getApellidodos();
        JTextField emailinsertar = vc.getTextoemail();
        JTextField telefonoinsertar = vc.getTextotelefono();

        //pasamos el texto del telefono a int
        String telefonoextraido = telefonoinsertar.getText();
        int telefonotransformado = Integer.parseInt(telefonoextraido);

        //seteamos los datos en el objeto alumnoBorrar y llamamos a la funcion que los introduce en la BBDD
        ModeloAlumno alumnoinsertar = new ModeloAlumno();
        alumnoinsertar.setEmail(emailinsertar.getText());
        alumnoinsertar.setNombre(nombreinsertar.getText());
        alumnoinsertar.setApellido1(ape1insertar.getText());
        alumnoinsertar.setApellido2(ap2insertar.getText());
        alumnoinsertar.setTelefono(telefonotransformado);

        //se comprueba si el patron de email es valido
        
            if (alumnoinsertar.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            alumnoinsertar.insertarAlumno(alumnoinsertar);
            }else {
                throw new Excepciones("email");
        }
        

        //lo añadimos al combobox
        JComboBox<String> insertaNew = vc.getListadoalumnos();
        insertaNew.addItem(alumnoinsertar.getNombre());
    }

    /**
     * funcion que actualiza el alumno en la base de datos
     */
    public void actualizarAlumno() throws Excepciones {
        //guardamos el nombre del alumnoBorrar seleccionado
        JComboBox<String> listaActualizar = vc.getListadoalumnos();
        String nombreActualizado = listaActualizar.getSelectedItem().toString();

        //extraemos los datos de las casillas
        JTextField nombreactualizar = vc.getTextonombre();
        JTextField ape1actualizar = vc.getApellidouno();
        JTextField ape2actualizar = vc.getApellidodos();
        JTextField emailactualizar = vc.getTextoemail();
        JTextField telefonoactualizar = vc.getTextotelefono();

        //pasamos el texto del telefono a int
        String telefonoextraid = telefonoactualizar.getText();
        int telefonotransformad = Integer.parseInt(telefonoextraid);

        //creamos el objeto alumnoActualizado y le pasamos los datos de las casillas para que lo actualice
        ModeloAlumno alumnoActualizado = new ModeloAlumno();
        alumnoActualizado = alumnoActualizado.datosAlumno(nombreActualizado);
        int idAlumno = alumnoActualizado.getIdAlumno();
        alumnoActualizado.setEmail(emailactualizar.getText());
        alumnoActualizado.setNombre(nombreactualizar.getText());
        alumnoActualizado.setApellido1(ape1actualizar.getText());
        alumnoActualizado.setApellido2(ape2actualizar.getText());
        alumnoActualizado.setTelefono(telefonotransformad);
        alumnoActualizado.setIdAlumno(idAlumno);

        //se comprueba si el patron de email es valido
        if (alumnoActualizado.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            alumnoActualizado.actualizarAlumno(alumnoActualizado);
            //lo actualizamos en el combobox
            JComboBox<String> actualizaNew = vc.getListadoalumnos();
            actualizaNew.addItem(alumnoActualizado.getNombre());
            actualizaNew.removeItem(nombreActualizado);
        } else {
            throw new Excepciones("email");
        }

    }

    /**
     * funcion que borra un alumno de la base de datos
     */
    public void borrarAlumno() {
        //guardamos el nombre del alumnoBorrar seleccionado
        JComboBox<String> listaBorrar = vc.getListadoalumnos();
        String nombreBorrar = listaBorrar.getSelectedItem().toString();

        //extraemos el nombre de la persona seleccionada
        ModeloAlumno alumnoBorrar = new ModeloAlumno();
        ModeloAlumno datosAlumno = alumnoBorrar.datosAlumno(nombreBorrar);
        //extraemos el id de la persona seleccionada y le pasamos el id para que lo borre
        int idAlumnoBorrar = datosAlumno.getIdAlumno();
        datosAlumno.borrarAlumno(idAlumnoBorrar);
        //lo actualizamos en el combobox
        JComboBox<String> borrarNew = vc.getListadoalumnos();
        borrarNew.removeItem(nombreBorrar);
    }

    /**
     * funcion que da cita al alumno
     */
    public void darCita() {
        //extraemos dia, mes, año y hora del calendario
        JCalendar calendario = vc.getCalendario();
        JTextField textodia = vc.getTextodia();
        JTextField textohora = vc.getTextohora();
        JComboBox<String> hora = vc.getHora();
        JComboBox<String> minutos = vc.getMinutos();

        //guardamos el nombre de alumno seleccionado
        JComboBox<String> lista = vc.getListadoalumnos();
        String nombreseleccionad = lista.getSelectedItem().toString();
        //extraemos los datos de la persona seleccionada
        ModeloAlumno alum = new ModeloAlumno();
        alum = alum.datosAlumno(nombreseleccionad);
        //introducimos si tiene cita en el cuadro
        ModeloCita cita = new ModeloCita();
        cita = cita.datosCita(alum.getIdAlumno());
        if (cita.getIdCita() > 0) {
            JOptionPane.showMessageDialog(null, "Ya tiene cita asignada, no puedes darle mas");
        } else {
            //le damos formato a la fecha
            String horaseleccionada = hora.getSelectedItem().toString();
            String minutosseleccionado = minutos.getSelectedItem().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            cita.setFecha(sdf.format(calendario.getDate()));
            cita.setHora(horaseleccionada + ":" + minutosseleccionado);
            cita.insertarCita(alum, cita);
            //insertamos los valores en los cuadros
            textodia.setText(sdf.format(calendario.getDate()));
            textohora.setText(horaseleccionada + ":" + minutosseleccionado);
        }
    }

    /**
     * funcion que actualiza la cita al alumno
     */
    public void anularCita() {
        //guardamos el nombre del alumnoBorrar seleccionado
        JComboBox<String> citaborrar = vc.getListadoalumnos();
        String citaalumno = citaborrar.getSelectedItem().toString();

        //extraemos el nombre de la persona seleccionada
        ModeloAlumno alumnocita = new ModeloAlumno();
        alumnocita = alumnocita.datosAlumno(citaalumno);

        //le pasamos el id de la cita para borrarlo
        ModeloCita citaborrada = new ModeloCita();
        citaborrada.borrarCita(alumnocita.getIdAlumno());

        //actualizamos los jtextfield de texto
        JTextField fechaborrada = vc.getTextodia();
        String borrarfecha = "";
        JTextField horaborrada = vc.getTextohora();
        String borrarhora = "";
        fechaborrada.setText(borrarfecha);
        horaborrada.setText(borrarhora);
    }

    /**
     * funcion que actualiza la cita
     */
    public void actualizarCita() {
        //guardamos el nombre del alumnoBorrar seleccionado
        JComboBox<String> alumnocitacion = vc.getListadoalumnos();
        String alumnoseleccionado = alumnocitacion.getSelectedItem().toString();

        //extraemos los datos de la persona seleccionada
        ModeloAlumno alumno1 = new ModeloAlumno();
        alumno1 = alumno1.datosAlumno(alumnoseleccionado);

        //introducimos si tiene cita en el cuadro
        ModeloCita citaalumno1 = new ModeloCita();
        citaalumno1 = citaalumno1.datosCita(alumno1.getIdAlumno());

        //si no tiene cita mostramos un mensaje
        if (citaalumno1.getIdCita() > 0) {

            //extraemos dia, mes, año y hora del calendario
            JCalendar newcalendario = vc.getCalendario();
            JComboBox<String> newhora = vc.getHora();
            JComboBox<String> newminutos = vc.getMinutos();

            //le damos formato a la fecha e introducimos valores al objeto
            String newhoraseleccionada = newhora.getSelectedItem().toString();
            String newminutosseleccionado = newminutos.getSelectedItem().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            citaalumno1.setFecha(sdf.format(newcalendario.getDate()));
            citaalumno1.setHora(newhoraseleccionada + ":" + newminutosseleccionado);

            //actualizamos la cita
            citaalumno1.actualizarCita(citaalumno1);

            //actualizamos los jtextfield de texto
            JTextField fechaactualizada = vc.getTextodia();
            String actualizarfecha = citaalumno1.getFecha();
            JTextField horaactualizada = vc.getTextohora();
            String actualizarhora = citaalumno1.getHora();
            fechaactualizada.setText(actualizarfecha);
            horaactualizada.setText(actualizarhora);
        } else {
            JOptionPane.showMessageDialog(null, "Este alumno no tiene cita asignada");
        }
    }

}
