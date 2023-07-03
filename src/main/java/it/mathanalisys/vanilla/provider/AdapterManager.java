package it.mathanalisys.vanilla.provider;

import it.mathanalisys.vanilla.Vanilla;

public class AdapterManager {

    public AdapterManager(){
        new VanillaBoard().runTaskTimer(Vanilla.get(), 0L, 20L);
        new NametagsAdapter().runTaskTimer(Vanilla.get(), 20L, 20L);
    }
}
