package hibernate1;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nanohp
 */
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Ejemplo1 {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Faltan argumentos");
            } else {
                System.out.println("Args length:" + args.length);
                for (int i = 0; i < args.length; i++) {
                    System.out.println(args[i]);
                }
//                Configuration cfg = new Configuration().addClass(Libro.class)
                Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

//                cfg.configure();
                SessionFactory sf = cfg.buildSessionFactory();
                Session sesion = sf.openSession();
                
                if (args[0].equals("lista")) {
                    
                    List libros = sesion.createQuery("from Libro").list();
                    
                    System.out.println("ID\tTitulo\tFecha\tPrecio");

                    System.out.println("------\t-------\t----------\t------");
                    for (Iterator it = libros.iterator(); it.hasNext();) {
                        Libro libro = (Libro) it.next();
                        System.out.println(libro.getId() + "\t"
                                + libro.getTitulo() + "\t" + libro.getFechaPublicacion() + "\t" + libro.getPrecio());
                    }
                    
                } else if (args[0].equals("crear")) {
                    String titulo = args[1];
                    Date fechaPublicacion =
                            DateFormat.getDateInstance(DateFormat.SHORT).parse(args[2]);
                    float precio = Float.parseFloat(args[3]);
                    Libro libro = new Libro(titulo, fechaPublicacion,precio);
                    
                    sesion.beginTransaction();
                    sesion.save(libro);
                    sesion.flush();
                    sesion.getTransaction().commit();
                    
                } else if (args[0].equals("modificar")) {
                    int id = Integer.parseInt(args[1]);
                    String titulo = args[2];
                    Date fechaPublicacion =
                            DateFormat.getDateInstance(DateFormat.SHORT).parse(args[3]);
                    float precio = Float.parseFloat(args[4]);
                    sesion.beginTransaction();
                    
                    Libro libro = (Libro) sesion.load(Libro.class, new Integer(id));
                    libro.setTitulo(titulo);
                    libro.setFechaPublicacion(fechaPublicacion);
                    libro.setPrecio(precio);
                    sesion.update(libro);
                    sesion.flush();
                    sesion.getTransaction().commit();
                    
                    
                } else if (args[0].equals("borrar")) {
                    int id = Integer.parseInt(args[1]);
                    
                    sesion.beginTransaction();
                    Libro libro = (Libro) sesion.load(Libro.class, new Integer(id));
                    sesion.delete(libro);
                    sesion.flush();
                    sesion.getTransaction().commit();
                    
                }
                sesion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}