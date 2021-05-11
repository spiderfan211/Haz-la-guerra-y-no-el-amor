package com.ceslopedevega.scrum;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.ceslopedevega.scrum.modelo.Direction;
import com.ceslopedevega.scrum.modelo.Faction;
import com.ceslopedevega.scrum.modelo.Item;
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
		NPC enemy = null;
		/*
		 * Para añadir una habitación, se usa
		 * map.add(new Room ("nombrehabitación", identificadorhabitación, "descripción", direccionnorte, direccionsur, direccionoeste, direccioneste, null);
		 * new NPC("nombre", habitacion, lealtadcomovalornumerico-si es enemigo 0)
		 * */
		try {
			map.add(new Room("room0", 0,  "Te encuentras en una plaza, vacía y pavimentada", 1, Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, null));
	        map.add(new Room("room1", 1, "Te encuentras en una casa blanca al norte de la plaza.", 3, 0, 2, 4, null));
	        
	        ArrayList<Item> roomitems = new ArrayList<>();
	        ArrayList<Item> enemyitems = new ArrayList<>();
	        roomitems.add(new Item("Espada Maestra", 1, false, true));
	        roomitems.add(new Item("Escudo de Hyrule", 1, true, false));
	        enemyitems.add(new Item("Garras", 1, false, true));
	        enemy = new NPC("Lady Dimitrescu", 4, 20, 20, 4, 10, 100, Faction.NONE.name(), null, 0, enemyitems);
	        map.add(new Room("room2", 2, "Estás en la casa dorada, al oeste de la casa blanca.", Direction.NOEXIT, Direction.NOEXIT, Direction.NOEXIT, 1, enemy, roomitems));
	        enemy.setRoom(map.get(2));
	        
	        
	        ArrayList<Item> enemyitems1 = new ArrayList<>();
	        enemyitems1.add(new Item("Maza de troll", 1, false, true));
	        enemy = new NPC("Troll de cueva", 9, 30, 30, 2, 0, 0, Faction.NONE.name(), null, 0, enemyitems1);
	        map.add(new Room("room3", 3, "Estás en la cueva de los trolls, al norte de la casa blanca.", Direction.NOEXIT, 1, Direction.NOEXIT, Direction.NOEXIT, enemy));
	        enemy.setRoom(map.get(3));
	        
	        ArrayList<Item> roomitems2 = new ArrayList<>();
	        ArrayList<Item> enemyitems2 = new ArrayList<>();
	        enemyitems2.add(new Item("Espadón del gran señor", 1, true, true));
	        roomitems2.add(new Item("Humanidad", 2));
	        enemy = new NPC("Gwyn", 8, 35, 35, 6, 0, 0, Faction.NONE.name(), null, 0, enemyitems2);
	        map.add(new Room("room4", 4, "Has llegado a una cueva oscura. No ves casi nada, pero sabes que has venido desde el oeste.", 5, Direction.NOEXIT, 1, 5, enemy, roomitems2));
	        enemy.setRoom(map.get(4));
	        
	        
	        map.add(new Room("room5", 5, "Has llegado a la sala final. Enhorabuena.", Direction.NOEXIT, Direction.NOEXIT, 4, Direction.NOEXIT, null));
	
	        
	        ArrayList<Item> items = new ArrayList<>();
	        items.add(new Item("Poción de curación", 2, true));
	        //		introduce aquí el nombre de tu personaje
	        //						 |
	        //					 	 ˇ
	        player = new Player ("Ganon", 7, 20, 20, 3, 10, 200, Faction.NONE.name(), map.get(0), items );
	        
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
