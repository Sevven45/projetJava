/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author myriam.fort
 */
public class MainClient {

public static void main(String[] args) {
try {
if (args.length != 2) {
printUsage();
} else {
String address = args[0];
Integer port = new Integer(args[1]);
Client c = new Client(address, port);
}
} catch (UnknownHostException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}
}
private static void printUsage() {
System.out.println("java client.MainClient <address> <port>");
System.out.println("\t<address>: server's ip address");
System.out.println("\t<port>: server's port");
}
    
}
