/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pechemoulenew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Scanner;


/**
 *
 * @author cypri
 */
public class PecheMouleNew {
//InputStram
    //Printer
    //Wrtier
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic hereµ
        //ARGS[O] : IP
        //ARGS[1] : PORT
        //ARGS[2] : NOM EQUIPE
        InetAddress adresseIP;
        //Récupération adresse IP
        try{
            
            String temp = "127.0.0.1";
            
            byte[] tableIP = new byte[4];
            
            char ch = temp.charAt(0);
            
            int i = 0;
            String chaineIP = "";
            while(ch != '.'){
                
                i++;
                chaineIP = chaineIP + ch;
                
                ch = temp.charAt(i);
            }
            
            tableIP[0] = (byte)Integer.parseInt(chaineIP);
            chaineIP ="";
            i++;
            ch = temp.charAt(i);
            
            while(ch != '.'){
                i++;
                chaineIP = chaineIP + ch;
                ch = temp.charAt(i);
            }
            tableIP[1] = (byte)Integer.parseInt(chaineIP);
            chaineIP ="";
            i++;
            ch = temp.charAt(i);
            
            while(ch != '.'){
                i++;
                chaineIP = chaineIP + ch;
                ch = temp.charAt(i);
            }
            
            tableIP[2] = (byte)Integer.parseInt(chaineIP);
            chaineIP ="";
            
            i++;
            ch = temp.charAt(i);
            
            while(ch != '.' && i<temp.length()-1){
                i++;
                chaineIP = chaineIP + ch;
                ch = temp.charAt(i);
            }
            chaineIP = chaineIP + temp.charAt(temp.length()-1);
            tableIP[3] = (byte)Integer.parseInt(chaineIP);
            
     
            
            adresseIP = InetAddress.getByAddress(tableIP);
             
                    
           InetSocketAddress serveurAdresse = new InetSocketAddress(adresseIP, 1337);

            //Création du socket avec l'adresse locale et le port
            System.out.println(adresseIP.getHostAddress());
            Socket s = new Socket(adresseIP,1337);
            
            //PARTIE TRAITEMENT DU SOCKET ET ENVOI DES DONNEES
            
            Scanner sc1 = new Scanner(s.getInputStream());
            PrintStream p = new PrintStream(s.getOutputStream());
            BufferedReader b =  new BufferedReader(new InputStreamReader (s.getInputStream()));
            
//String[] s =  
           
           
            p.println("Hello World, le retour !");
            String rec = b.readLine();
            System.out.println(rec);
            Frite f = new Frite(2,4);
            while(rec!="FIN"){
                
                p.println(f.aleat());
                System.out.println(b.readLine());
                rec = b.readLine();
                System.out.println(rec);
                p.println(f.aleat());
            }
    
            System.out.println();
        }catch (UnknownHostException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
                
        
        //127.0
        //1337
    }
    
}
