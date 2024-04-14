package examplemod.potions.examples;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import examplemod.potions.BasePotion;

import static examplemod.MyMod.makeID;

public class YummyPotion extends BasePotion {
    public static final String ID = makeID(YummyPotion.class.getSimpleName());

    private static final Color LIQUID_COLOR = CardHelper.getColor(255, 0, 255);
    private static final Color HYBRID_COLOR = CardHelper.getColor(255, 255, 0);
    private static final Color SPOTS_COLOR = null;

    public YummyPotion() {
        super(ID, 50, PotionRarity.COMMON, PotionSize.MOON, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
    }

    @Override
    public String getDescription() {
        return String.format(potionStrings.DESCRIPTIONS[0], potency);
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, potency));
    }
}
