package org.example;

import org.example.Model.Clientes;
import org.example.Model.RegistroLavado;
import org.example.Model.Servicios;
import org.example.Model.Vehiculos;
import org.example.Util.ConexionBD;
import org.example.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConexionBD.obtenerConexion()) {

            Scanner scanner = new Scanner(System.in);
            int op, op1, op2, op3, op4;

            System.out.println("Bienvenido a Lavalauris! ...ʕ•́ᴥ•̀ʔっ...");
            do{

                System.out.println("Seleccione uno de los siguientes grupo:");
                System.out.println("1. Clientes");
                System.out.println("2. Servicios");
                System.out.println("3. Vehiculos");
                System.out.println("4. Registros de lavado");
                System.out.println("5. Salir");
                op= scanner.nextInt();

                switch (op){
                    case 1://clientes
                        do{
                            ClientesDAO clientesDAO = new ClientesDAOimpl(connection);
                            System.out.println("--->Seleccione:");
                            System.out.println("1. Registrar a un cliente");
                            System.out.println("2. Buscar (leer) un cliente");
                            System.out.println("3. Actualizar datos de un cliente");
                            System.out.println("4. Eliminar a un cliente");
                            System.out.println("5. Lista de los clientes");
                            System.out.println("6. Salir");
                            op1= scanner.nextInt();

                            switch (op1){
                                case 1://registrar
                                    scanner.nextLine();
                                    System.out.println("Nombre/s:");
                                    String nombre= scanner.nextLine();
                                    System.out.println("Apellido/s");
                                    String apellido=scanner.nextLine();
                                    System.out.println("Telefono:");
                                    String telefono=scanner.nextLine();
                                    System.out.println("Email:");
                                    String email=scanner.nextLine();
                                    System.out.println("Dirección:");
                                    String direccion=scanner.nextLine();

                                    Clientes clientes=new Clientes(nombre, apellido, telefono, email, direccion);
                                    clientesDAO.crear(clientes);
                                    System.out.println("---cliente registrado---");
                                    break;

                                case 2://leer
                                    System.out.println("Ingrese el ID del cliente:");
                                    int idleer=scanner.nextInt();
                                    Clientes clientes1= clientesDAO.leer(idleer);
                                    if(clientes1!=null){
                                        System.out.println(clientes1);
                                    }else{
                                        System.out.println("--->Cliente no encontrado<---");
                                    }
                                    break;

                                case 3://actualizar
                                    System.out.print("ID del cliente a actualizar: ");
                                    int idAC = scanner.nextInt();
                                    Clientes clientes2 = clientesDAO.leer(idAC);
                                    if (clientes2 != null) {
                                        scanner.nextLine();
                                        System.out.print("Nuevo nombre: ");
                                        clientes2.setNombre(scanner.nextLine());
                                        System.out.print("Nuevo apellido: ");
                                        clientes2.setApellido(scanner.nextLine());
                                        System.out.print("Nuevo telefono: ");
                                        clientes2.setTelefono(scanner.nextLine());
                                        scanner.nextLine();
                                        System.out.print("Nuevo email: ");
                                        clientes2.setEmail(scanner.nextLine());
                                        System.out.print("Nueva direccion: ");
                                        clientes2.setDireccion(scanner.nextLine());

                                        clientesDAO.actualizar(clientes2);
                                        System.out.println("--->Cliente actualizado<---");
                                    } else {
                                        System.out.println("...Cliente no encontrado...");
                                    }
                                    break;

                                case 4://eliminar
                                    System.out.print("ID  del cliente que desea eliminar: ");
                                    int idEliminar = scanner.nextInt();
                                    clientesDAO.eliminar(idEliminar);
                                    System.out.println("--->Cliente eliminado<---");
                                    break;

                                case 5://lista
                                    List<Clientes> clientesList = clientesDAO.listar();
                                    for (Clientes c : clientesList) {
                                        System.out.println(c);
                                    }
                                    break;

                                case 6:
                                    System.out.println("...Saliendo al menu principal...");
                                    break;

                                default:
                                    System.out.println("---> Ingrese una opcion correcta >:");
                            }
                        }while (op1!=6);
                        break;

                    case 2://Servicios
                        do{
                            ServiciosDAO serviciosDAO = new ServiciosDAOimpl(connection);
                            System.out.println("--->Seleccione:");
                            System.out.println("1. Registrar un servicio");
                            System.out.println("2. Buscar (leer) un servicio");
                            System.out.println("3. Actualizar datos de un servicio");
                            System.out.println("4. Eliminar un servicio");
                            System.out.println("5. Lista de servicios");
                            System.out.println("6. Salir");
                            op2= scanner.nextInt();

                            switch (op2){
                                case 1://registrar
                                    scanner.nextLine();
                                    System.out.println("Nombre:");
                                    String nombre= scanner.nextLine();
                                    System.out.println("Precio");
                                    double precio=scanner.nextDouble();

                                    Servicios servicios=new Servicios(nombre, precio);
                                    serviciosDAO.crear(servicios);
                                    System.out.println("---servicio registrado---");
                                    break;

                                case 2://leer
                                    System.out.println("Ingrese el ID del servicio:");
                                    int idleer=scanner.nextInt();
                                    Servicios servicios1= serviciosDAO.leer(idleer);
                                    if(servicios1!=null){
                                        System.out.println(servicios1);
                                    }else{
                                        System.out.println("--->Servicio no encontrado<---");
                                    }
                                    break;

                                case 3://actualizar
                                    System.out.print("ID del servicio a actualizar: ");
                                    int idAC = scanner.nextInt();
                                    Servicios servicios2 = serviciosDAO.leer(idAC);
                                    if (servicios2 != null) {
                                        scanner.nextLine();
                                        System.out.print("Nuevo nombre: ");
                                        servicios2.setNombre(scanner.nextLine());
                                        System.out.print("Nuevo precio: ");
                                        servicios2.setPrecio(scanner.nextDouble());

                                        serviciosDAO.actualizar(servicios2);
                                        System.out.println("--->Servicio actualizado<---");
                                    } else {
                                        System.out.println("...Servicio no encontrado...");
                                    }
                                    break;

                                case 4://eliminar
                                    System.out.print("ID  del servicio que desea eliminar: ");
                                    int idEliminar = scanner.nextInt();
                                    serviciosDAO.eliminar(idEliminar);
                                    System.out.println("--->Servicio eliminado<---");
                                    break;

                                case 5://lsita
                                    List<Servicios> serviciosList = serviciosDAO.listar();
                                    for (Servicios s : serviciosList) {
                                        System.out.println(s);
                                    }
                                    break;

                                case 6:
                                    System.out.println("...Saliendo al menu principal...");
                                    break;

                                default:
                                    System.out.println("---> Ingrese una opcion correcta >:");
                            }
                        }while (op2!=6);
                        break;

                    case 3://Vehiculos
                        do{
                            VehiculosDAO vehiculosDAO = new VehiculosDAOimpl(connection);
                            System.out.println("--->Seleccione:");
                            System.out.println("1. Registrar un vehiculo");
                            System.out.println("2. Buscar (leer) un vehiculo");
                            System.out.println("3. Actualizar datos de un vehiculo");
                            System.out.println("4. Eliminar un vehiculo");
                            System.out.println("5. Lista de los vehiculos");
                            System.out.println("6. Salir");
                            op3= scanner.nextInt();

                            switch (op3){
                                case 1://registrar
                                    scanner.nextLine();
                                    System.out.println("ID de cliente:");
                                    int id_cliente= scanner.nextInt();
                                    System.out.println("marca");
                                    String marca=scanner.nextLine();
                                    System.out.println("modelo");
                                    String modelo=scanner.nextLine();
                                    System.out.println("placa");
                                    String placa=scanner.nextLine();
                                    System.out.println("color");
                                    String color=scanner.nextLine();
                                    System.out.println("tipo");
                                    String tipo=scanner.nextLine();

                                    Vehiculos vehiculos=new Vehiculos(id_cliente, marca, modelo, placa, color, tipo);
                                    vehiculosDAO.crear(vehiculos);
                                    System.out.println("---Vehiculo registrado---");
                                    break;

                                case 2://leer
                                    System.out.println("Ingrese el ID del vehiculo:");
                                    int idleer=scanner.nextInt();
                                    Vehiculos vehiculos1= vehiculosDAO.leer(idleer);
                                    if(vehiculos1!=null){
                                        System.out.println(vehiculos1);
                                    }else{
                                        System.out.println("--->Vehiculo no encontrado<---");
                                    }
                                    break;

                                case 3://actualizar
                                    System.out.print("ID del vehiculo a actualizar: ");
                                    int idAC = scanner.nextInt();
                                    Vehiculos vehiculos2 = vehiculosDAO.leer(idAC);
                                    if (vehiculos2 != null) {
                                        scanner.nextLine();
                                        System.out.print("ID del cliente: ");
                                        vehiculos2.setId_cliente(scanner.nextInt());
                                        System.out.print("Nuevo marca: ");
                                        vehiculos2.setMarca(scanner.nextLine());
                                        System.out.print("Nuevo modelo: ");
                                        vehiculos2.setModelo(scanner.nextLine());
                                        System.out.print("Nuevo placa: ");
                                        vehiculos2.setPlaca(scanner.nextLine());
                                        System.out.print("Nuevo color: ");
                                        vehiculos2.setColor(scanner.nextLine());
                                        System.out.print("Nuevo tipo: ");
                                        vehiculos2.setTipo(scanner.nextLine());

                                        vehiculosDAO.actualizar(vehiculos2);
                                        System.out.println("--->Vehiculo actualizado<---");
                                    } else {
                                        System.out.println("...Vehiculo no encontrado...");
                                    }
                                    break;

                                case 4://eliminar
                                    System.out.print("ID  del vehiculo que desea eliminar: ");
                                    int idEliminar = scanner.nextInt();
                                    vehiculosDAO.eliminar(idEliminar);
                                    System.out.println("--->vehiculo eliminado<---");
                                    break;

                                case 5://lsita
                                    List<Vehiculos> vehiculosList = vehiculosDAO.listar();
                                    for (Vehiculos v : vehiculosList) {
                                        System.out.println(v);
                                    }
                                    break;

                                case 6:
                                    System.out.println("...Saliendo al menu principal...");
                                    break;

                                default:
                                    System.out.println("---> Ingrese una opcion correcta >:");
                            }
                        }while (op3!=6);
                        break;

                    case 4://registro lavado
                        do{
                            RegistroLavadoDAO registroLavadoDAO = new RegistroLavadoDAOimpl(connection);
                            System.out.println("--->Seleccione:");
                            System.out.println("1. Ingresar un registro de lavado");
                            System.out.println("2. Buscar (leer) un registro de lavado");
                            System.out.println("3. Actualizar datos de un registro de lavado");
                            System.out.println("4. Eliminar un registro de lavado");
                            System.out.println("5. Lista de los registros de lavado");
                            System.out.println("6. Salir");
                            op4= scanner.nextInt();

                            switch (op4){
                                case 1://registrar
                                    scanner.nextLine();
                                    System.out.println("ID del vehiculo:");
                                    int id_vehiculo= scanner.nextInt();
                                    System.out.println("ID del servicio");
                                    int id_servicio=scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Fecha del lavado (yyyy-MM-dd):");
                                    String fechaLavado = scanner.nextLine();
                                    LocalDate fechalavado2 = LocalDate.parse(fechaLavado);
                                    System.out.println("Hora de inicio (HH:mm):");
                                    String horaInicio = scanner.nextLine();
                                    LocalTime horaInicio2 = LocalTime.parse(horaInicio);
                                    System.out.println("Hora fin (HH:mm):");
                                    String horaFin = scanner.nextLine();
                                    LocalTime horaFin2 = LocalTime.parse(horaFin);
                                    System.out.println("Precio total");
                                    double precioTotal=scanner.nextDouble();

                                    RegistroLavado registroLavado=new RegistroLavado(id_vehiculo, id_servicio, fechalavado2, horaInicio2, horaFin2, precioTotal);
                                    registroLavadoDAO.crear(registroLavado);
                                    System.out.println("---Registro ingresado---");
                                    break;

                                case 2://leer
                                    System.out.println("Ingrese el ID del registro:");
                                    int idleer=scanner.nextInt();
                                    RegistroLavado registroLavado1= registroLavadoDAO.leer(idleer);
                                    if(registroLavado1!=null){
                                        System.out.println(registroLavado1);
                                    }else{
                                        System.out.println("--->Registro no encontrado<---");
                                    }
                                    break;

                                case 3://actualizar
                                    System.out.print("ID del registro a actualizar: ");
                                    int idAC = scanner.nextInt();
                                    RegistroLavado registroLavado2 = registroLavadoDAO.leer(idAC);
                                    if (registroLavado2 != null) {
                                        scanner.nextLine();
                                        System.out.print("nuevo ID del vehiculo: ");
                                        registroLavado2.setId_vehiculo(scanner.nextInt());
                                        System.out.print("Nuevo ID de servicio: ");
                                        registroLavado2.setId_servicio(scanner.nextInt());
                                        System.out.print("Nueva fecha (yyyy-MM-dd): ");
                                        String fechalavado = scanner.nextLine();
                                        LocalDate fechaLavado3 = LocalDate.parse(fechalavado);
                                        registroLavado2.setFechaLavado(fechaLavado3);
                                        System.out.print("Nueva hora inicio (HH:mm): ");
                                        String horainicio = scanner.nextLine();
                                        LocalTime horaInicio3 = LocalTime.parse(horainicio);
                                        registroLavado2.setHoraInicio(horaInicio3);
                                        System.out.print("Nueva hora fin (HH:mm): ");
                                        String horafin = scanner.nextLine();
                                        LocalTime horaFin3 = LocalTime.parse(horafin);
                                        registroLavado2.setHoraFin(horaFin3);
                                        System.out.print("Nuevo precio total: ");
                                        registroLavado2.setPrecioTotal(scanner.nextDouble());

                                        registroLavadoDAO.actualizar(registroLavado2);
                                        System.out.println("--->Registro actualizado<---");
                                    } else {
                                        System.out.println("...Registro no encontrado...");
                                    }
                                    break;

                                case 4://eliminar
                                    System.out.print("ID  del registro que desea eliminar: ");
                                    int idEliminar = scanner.nextInt();
                                    registroLavadoDAO.eliminar(idEliminar);
                                    System.out.println("--->Registro eliminado<---");
                                    break;

                                case 5://lsita
                                    List<RegistroLavado> registroLavadoList = registroLavadoDAO.listar();
                                    for (RegistroLavado r : registroLavadoList) {
                                        System.out.println(r);
                                    }
                                    break;

                                case 6:
                                    System.out.println("...Saliendo al menu principal...");
                                    break;

                                default:
                                    System.out.println("---> Ingrese una opcion correcta >:");
                            }
                        }while (op4!=6);
                        break;

                    case 5://salida
                        System.out.println("Saliendo...");
                        System.out.println("Que tenga buen día ʕ•́ᴥ•̀ʔっ ...");
                        break;

                    default:
                        System.out.println("---> Ingrese una opcion correcta >:");

                }

            }while (op!=5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
