package com.ceslopedevega.scrum;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.ceslopedevega.scrum.modelo.Direction;
import com.ceslopedevega.scrum.modelo.Faction;
import com.ceslopedevega.scrum.modelo.NPC;
import com.ceslopedevega.scrum.modelo.Player;
import com.ceslopedevega.scrum.modelo.Room;

public class CreadorMapas {
	private static ArrayList<Room> map;
	private static Player player;
	/*
	 * Clase preparada para crear mapas para el juego y nombre del jugador.
	 * El mapa y el jugador sobrescribirán los ya existentes en el archivo de guardado.
	 * */
	public static void main(String[] args) {
		map =  new ArrayList<>();
		NPC enemytemp = null;
		/*
		 * Para añadir una habitación, se usa
		 * map.add(new Room ("nombrehabitación", identificadorhabitación, "descripción", direccionnorte, direccionsur, direccionoeste, direccioneste, null);
		 * new NPC("nombre", habitacion, lealtadcomovalornumerico-si es enemigo 0)
		 * */
		try {
			map.add(new Room("room0", 0,  "Te encuentras en una plaza, vacía y pavimentada", 1, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, null));
	        map.add(new Room("room1", 1, "Te encuentras en una casa blanca al norte de la plaza.", 3, 0, 2, 4, null));
	        
	        enemytemp = new NPC("nombre", null, 0);
	        map.add(new Room("room2", 2, "Estás en la casa dorada, al oeste de la casa blanca.", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, 1, enemytemp));
	        enemytemp.setRoom(map.get(2));
	        enemytemp = new NPC("troll", null, 0);
	        map.add(new Room("room3", 3, "Estás en la cueva de los trolls, al norte de la casa blanca.", Direction.NOEXIT, 1, Direction.NOEXIT, Direction.NOEXIT, enemytemp));
	        enemytemp.setRoom(map.get(3));
	        enemytemp=new NPC ("enemigofinal", null, 0);
	        map.add(new Room("room4", 4, "Has llegado a una cueva oscura. No ves casi nada, pero sabes que has venido desde el oeste.", 5, Direction.NOEXIT, 1, 5, enemytemp));
	        enemytemp.setRoom(map.get(4));
	        map.add(new Room("room5", 5, "Has llegado a la sala final. Enhorabuena.", Direction.NOEXIT, Direction.NOEXIT, 4, Direction.NOEXIT, null));
	
	        //		introduce aquí el nombre de tu personaje
	        //					 |
	        //					 ˇ
	        player = new Player ("Ganon", 5, 5, 5, 10, 200, Faction.NONE.name(), map.get(0) );
		}catch(Exception e) {
			e.printStackTrace();
		}
		saveGame();
		System.out.println("Datos guardados. Ya puede iniciar el juego");
	}
	
	public static void saveGame()
	{
        FileOutputStream file = null;
        ObjectOutputStream out = null;

        try 
        {
            // Se crea el fichero de salida
        	file = new FileOutputStream("./saves/gameplayer.dat");
            out = new ObjectOutputStream(file);
                        
            out.writeObject(player);
           
            System.out.println("Se han guardado los datos del personaje en gameplayer.dat\n");
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("1"+e.getMessage());
        } 
        catch (IOException e) 
        {
            System.out.println("2"+e.getMessage());
        } 
        finally 
        {
            try 
            {
                if(file!=null) file.close();
                if(out!=null) out.close();
            } 
            catch (IOException e) 
            {
                System.out.println("3"+e.getMessage());
            }
        }
        
        FileOutputStream file2 = null;
        ObjectOutputStream out2 = null;

        try 
        {
            // Se crea el fichero de salida
        	file2 = new FileOutputStream("./saves/gamemap.dat");
            out2 = new ObjectOutputStream(file2);
                        
            for(Room r : map) {
            	out2.writeObject(r);
            }
           
            System.out.println("Se han guardado los datos del mapa en gamemap.dat\n");
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("1"+e.getMessage());
        } 
        catch (IOException e) 
        {
            System.out.println("2"+e.getMessage());
        } 
        finally 
        {
            try 
            {
                if(file!=null) file.close();
                if(out!=null) out.close();
            } 
            catch (IOException e) 
            {
                System.out.println("3"+e.getMessage());
            }
        }
	}

}
