package examplemod.cards.examples;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import examplemod.cards.BaseCard;
import examplemod.character.MyCharacter;
import examplemod.powers.examples.StupidPower;
import examplemod.util.CardStats;

public class OvercomplicatedCard extends BaseCard {
    public static final String ID = makeID(OvercomplicatedCard.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );

    private static final int DAMAGE = 4;

    public OvercomplicatedCard() {
        super(ID, info);

        setDamage(DAMAGE, 3);
        setCustomVar("D2", VariableType.DAMAGE, 3, -1);
        setCustomVar("D3", VariableType.DAMAGE, 2);

        setCraftCost(4);
    }

    protected void setCraftCost(int amount) {
        setCustomVar("craft", VariableType.MAGIC, amount, (target, base)->{
            int temp = base;

            for (AbstractPower pow : AbstractDungeon.player.powers) {
                if (pow instanceof SomeInterface) {
                    temp = ((SomeInterface) pow).methodInInterface(temp);
                }
            }
            return temp;
        });
        colorCustomVar("craft", Settings.CREAM_COLOR, Settings.RED_TEXT_COLOR, Settings.GREEN_TEXT_COLOR);
    }

    public interface SomeInterface {
        int methodInInterface(int amt);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, calcActualDamage(damage), damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(p, p, new StupidPower(p, 1)));
    }

    private int calcActualDamage(int temp) {
        return 0;
    }
}
