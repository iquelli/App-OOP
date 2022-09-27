package prr.app.main;

import prr.NetworkManager;
import pt.tecnico.uilib.menus.DoOpenMenu;

/**
 * Main menu.
 */
public final class Menu extends pt.tecnico.uilib.menus.Menu {
	public Menu(NetworkManager receiver) {
		super(Label.TITLE, //
				new DoOpenFile(receiver),
				new DoSaveFile(receiver),
                                new DoOpenMenu(Label.OPEN_MENU_CLIENTS,
                                               new prr.app.clients.Menu(receiver.getNetwork())),
                                new DoOpenMenu(Label.OPEN_MENU_TERMINALS,
                                               new prr.app.terminals.Menu(receiver.getNetwork())),
                                new DoOpenMenu(Label.OPEN_MENU_LOOKUPS,
                                               new prr.app.lookups.Menu(receiver.getNetwork())),
				new DoShowGlobalBalance(receiver.getNetwork())//
		);
	}
}
