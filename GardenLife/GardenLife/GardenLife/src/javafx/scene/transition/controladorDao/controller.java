package javafx.scene.transition.controladorDao;

import javafx.scene.transition.mapeo.Producto;
import javafx.scene.transition.mapeo.Usuario;
import javafx.scene.transition.mapeo.tipoProducto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;
import java.util.Iterator;
import java.util.List;
public class controller {



    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public void inicio() {
        System.err.println("Iniciando");
        try {
            String conexiones = "D:/danie/Documents/GardenLife/GardenLife/GardenLife/GardenLife/src/javafx/scene/transition/hibernate.cfg.xml";
            Configuration configuration = new Configuration().configure(new File(conexiones));
            System.err.println("Leyendo configuracion.");
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("No se puede crear la Sesion" + ex);
            throw new ExceptionInInitializerError(ex);
        }




    }

    public static void registro(int idProducto, String fecha, String condicion, String nombre, String tipo){
        controller dao = new controller();
        Session session = factory.openSession();

        session.beginTransaction();

        Criteria crit = session.createCriteria(tipoProducto.class);
        crit.add(Restrictions.eq("clasificacion", tipo));
        List result = crit.list();
        tipoProducto tipoP = null;
        for (Iterator iterator =
             result.iterator(); iterator.hasNext();){
             tipoP = (tipoProducto) iterator.next();
        }

        Producto e1 = new Producto(idProducto, fecha ,condicion, nombre);


        e1.setTipoProducto(tipoP);


        session.save(e1);


        session.getTransaction().commit();
        session.close();

    }

    public static void registroTipo(String nombre){
        controller dao = new controller();
        Session session = factory.openSession();
        session.beginTransaction();
        tipoProducto e1 = new tipoProducto(nombre);
        session.save(e1);
        session.getTransaction().commit();
        session.close();
    }

    public static void eliminarProducto(int codigo){
        Session session = factory.openSession();
        session.beginTransaction();

        Criteria crit = session.createCriteria(Producto.class);
        crit.add(Restrictions.eq("idProducto", codigo));
        List empList1 = crit.list();

        //List empList1 = session.createQuery(" from Producto  emp where emp.idProducto = " + codigo).list();
        for (Iterator iterator =
             empList1.iterator(); iterator.hasNext();){
            Producto dao = (Producto) iterator.next();
            System.out.println("PRODUCTO ELIMINADO: " + dao.getNombre());
            session.delete(dao);
        }
        session.getTransaction().commit();
    }

    public static void editarTipoProducto(String nombre, String clasificacion){
        Session session = factory.openSession();
        session.beginTransaction();

        Criteria crit = session.createCriteria(tipoProducto.class);
        crit.add(Restrictions.eq("clasificacion", nombre));
        List empList1 = crit.list();
        //List empList1 = session.createQuery(" from tipoProducto  emp where emp.clasificacion = '" + nombre + "'").list();
        for (Iterator iterator =
             empList1.iterator(); iterator.hasNext();){
            tipoProducto dao = (tipoProducto) iterator.next();
            dao.setClasificacion(clasificacion);
            session.update(dao);
        }
        session.getTransaction().commit();
    }

    public static void editarProducto(String nombre, String fecha, String condicion, int idProducto){
        Session session = factory.openSession();
        session.beginTransaction();

        Criteria crit = session.createCriteria(Producto.class);
        crit.add(Restrictions.eq("idProducto", idProducto));

        List empList1 = crit.list();
       // List empList1 = session.createQuery(" from Producto  emp where emp.idProducto = " + idProducto).list();
        for (Iterator iterator =
             empList1.iterator(); iterator.hasNext();){
            Producto dao = (Producto) iterator.next();
            dao.setNombre(nombre);
            dao.setCondicion(condicion);
            dao.setFechaIngreso(fecha);
            session.update(dao);
        }
        session.getTransaction().commit();
    }

    public static List getProductos() {
        Session session = factory.openSession();
        List empList1 = session.createQuery(" from Producto  emp ").list();
        System.out.println("Productos de tabla  productos ");
        for (Iterator iterator =
             empList1.iterator(); iterator.hasNext();){
            Producto dao = (Producto) iterator.next();
        }

        return empList1;
    }

    public static List getTipos() {
        Session session = factory.openSession();
        List empList1 = session.createQuery(" from tipoProducto emp ").list();
        System.out.println("tipoProducto de tabla  tipoProducto ");
        for (Iterator iterator =
             empList1.iterator(); iterator.hasNext();){
            tipoProducto dao = (tipoProducto) iterator.next();
            System.out.println(dao.getClasificacion());
        }

        return empList1;
    }


    public static boolean getUsuario(String usuario, String contrasenia) {
        Session session = factory.openSession();
        boolean acceso = false;

       Criteria crit = session.createCriteria(Usuario.class);
       crit.add(Restrictions.eq("usuario", usuario));
       crit.add(Restrictions.eq("contrasenia", contrasenia));
       List result = crit.list();

       if(!result.isEmpty()){
           acceso = true;
       }
        return acceso;
    }
}
