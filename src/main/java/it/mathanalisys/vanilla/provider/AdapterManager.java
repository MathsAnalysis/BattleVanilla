package it.mathanalisys.vanilla.provider;

import it.mathanalisys.vanilla.Vanilla;

public class AdapterManager {

    public AdapterManager(){
        new NametagsAdapter().runTaskTimer(Vanilla.get(), 20L, 20L);
    }
}
