package org.example;

import org.example.Model.Clientes;
import org.example.Util.ConexionBD;
import org.example.dao.ClientesDAO;
import org.example.dao.ClientesDAOimpl;
import org.example.dao.ServiciosDAO;
import org.example.dao.ServiciosDAOimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConexionBD.obtenerConexion()) {

            Scanner scanner = new Scanner(System.in);
            int op, op1, op2, op3, op4;

            do{
                System.out.println("Bienvenido a Lavalauris! ...ʕ•́ᴥ•̀ʔっ...");
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

                                case 2://leer

                                case 3://actualizar

                                case 4://eliminar

                                case 5://lsita

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

                                case 2://leer

                                case 3://actualizar

                                case 4://eliminar

                                case 5://lsita

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

                                case 2://leer

                                case 3://actualizar

                                case 4://eliminar

                                case 5://lsita

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
