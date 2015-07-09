package net.playmcm.qwertysam;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.playmcm.qwertysam.gui.ModGui;
import net.playmcm.qwertysam.io.SaveHandling;
import net.playmcm.qwertysam.util.MessageSender;

/**
 * Uses S3APacketTabComplete.java and Timer.java from Minecraft src code.
 * @date_created July 7th 2015
 * @date_updated July 8th 2015
 * @version 1.0
 */
public class ModMain 
{
	/**
	 * Sends the messages.
	 */
	private static MessageSender messageSender = new MessageSender();
	
	private ModGui modGui = new ModGui(0, "", 1);
	
	/**
	 * Filters out spam when a button is being held down.
	 */
	private boolean filterSpam = false;
	
	/**
	 * Loads saves upon the first runthrough.
	 */
	private boolean firstRun = true;
	
	/**
	 * Has access to the main gameloop through Timer.updateTimer();
	 */
	public void gameLoop()
	{
		if(firstRun)
		{
			SaveHandling.loadOptions();
			firstRun = false;
			System.out.println("CustomOneTitle = " + SaveHandling.getCustomOneTitle());
			System.out.println("CustomOne1 = " + SaveHandling.getCustomOne1());
			System.out.println("CustomOne2 = " + SaveHandling.getCustomOne2());
			System.out.println("CustomTwoTitle = " + SaveHandling.getCustomTwoTitle());
			System.out.println("CustomTwo1 = " + SaveHandling.getCustomTwo1());
			System.out.println("CustomTwo2 = " + SaveHandling.getCustomTwo2());
		}
		
		if (Minecraft.getMinecraft().currentScreen == modGui || Minecraft.getMinecraft().currentScreen == null) //Stops the GUI from opening while not in-game
		{
			if (isKeyPressed() && filterSpam == false) //Controls the flow of the buttons so that it doesn't get spammed when held down.
			{
				if (Minecraft.getMinecraft().currentScreen == modGui) //Exits if it's already on the menu
				{
					modGui.exitGui();
				}
				else //Goes to the menu if it's not there
				{
					Minecraft.getMinecraft().displayGuiScreen(modGui); 
				}
				filterSpam = true;
			}
			else if (!isKeyPressed() && filterSpam == true)
			{
				filterSpam = false;
			}
		}
	}
	
	/**
	 * @return Whether the ~ key is being pressed or not.
	 */
	public static boolean isKeyPressed()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_GRAVE); //The ~ key
	}
}
