package net.freeze.observers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class FreezeObservers implements ModInitializer {
	public static GameRules.Key<GameRules.BooleanRule> FREEZE_OBSERVERS;

	@Override
	public void onInitialize() {
		FREEZE_OBSERVERS = GameRuleRegistry.register("freezeObservers", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
	}
}