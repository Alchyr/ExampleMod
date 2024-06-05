package examplemod.powers.examples;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import examplemod.cards.examples.OvercomplicatedCard;
import examplemod.powers.BasePower;

import static examplemod.MyMod.makeID;

public class StupidPower extends BasePower implements OvercomplicatedCard.SomeInterface {
    public static final String POWER_ID = makeID("StupidPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public StupidPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public int methodInInterface(int amt) {
        return amt - this.amount;
    }
}
