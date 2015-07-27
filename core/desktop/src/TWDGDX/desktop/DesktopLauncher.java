package TWDGDX.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import TWDGDX.TWDGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 752;
                config.height = 560;
                config.title = "Tower Defense Game ";
		new LwjglApplication(new TWDGame(), config);

	}
}
