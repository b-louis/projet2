package exe;


import java.io.File;

import Agents.PredatorAgent;
import Agents.PreyAgent;
import Environnement.Island;
import toolbox.CAtoolbox;
import toolbox.ImageBuffer;
import toolbox.ImageFrame;
import toolbox.CAImageBuffer;

public class MyworldPix extends CAImageBuffer {

	public static void main(String[] args) {
		
		
		// initialisation generale
	    
				int dx = 1024;
				int dy = 1024;
				
				int displayWidth = dx;  // 200
				int displayHeight = dy; // 200

				// pick dimension for display
				if ( displayWidth < 200 )
					displayWidth = 200;
				else
					if ( displayWidth > 600 )
						displayWidth = 600;
					else
						if ( displayWidth < 300 )
							displayWidth = displayWidth * 2; 
				if ( displayHeight < 200 )
					displayHeight = 200;
				else
					if ( displayHeight > 600 )
						displayHeight = 600;
					else
						if ( displayHeight < 300 )
							displayHeight = displayHeight * 2; 
				
				
				int delai = 200;//100; // -- delay before refreshing display -- program is hold during delay, even if no screen update was requested. USE WITH CARE. 
				int nombreDePasMaximum = Integer.MAX_VALUE;
				int it = 0;
				int displaySpeed = 1;//50; // from 1 to ...
				
				CAImageBuffer image = new CAImageBuffer(dx,dy);
			    ImageFrame imageFrame =	ImageFrame.makeFrame( "MonkiIsland", image, delai, displayWidth, displayHeight );

			    // initialise l'ecosysteme
			    File Height = new File("island.png");
				File Moist = new File("moisture.png");
				File Temp = new File("temper.png");
				
				ImageBuffer MoistureMap;
				ImageBuffer HeightMap;	//infos nécessaires a la crea d'un monde interessant et coherent
				ImageBuffer TempMap;
				
			    ImageBuffer MoistureMap = LoadFromDisk("moisture.png");
			    try
				{
				

					MoistureMap = LoadFromDisk("moisture.png");//new ImageBuffer(1024,1024); // method 1 : call constructor
					HeightMap =null; //new ImageBuffer(1024,1024);	//initialisation des données pour la création de l'ile
					TempMap =null;//new ImageBuffer(1024,1024);

				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.exit(-1);
				}
			    

			    
				Island world = new Island(dx,dy);

				
				for ( int i = 0 ; i != 10 ; i++ )
					world.Proies.add(new PreyAgent((int)(Math.random()*dx),(int)(Math.random()*dy),world));
				for ( int i = 0 ; i != 10 ; i++ )
					world.Predateurs.add(new PredatorAgent((int)(Math.random()*dx),(int)(Math.random()*dy),world));
				
			    // mise a jour de l'�tat du monde
				
				while ( it != nombreDePasMaximum )
				{
					// 1 - display
					
					if ( it % displaySpeed == 0 )
						world.display(image); 

					// 2 - update
								
					world.step();
								
					// 3 - iterate
					
					it++;
					
					try {
						Thread.sleep(delai);
					} catch (InterruptedException e) 
					{
					}
				}
				
			}

		}

	}

}
