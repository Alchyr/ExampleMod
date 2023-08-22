package examplemod.cards.examples;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.cards.BaseCard;
import examplemod.util.CardStats;

public class MultiVarCard extends BaseCard {
    public static final String ID = makeID(MultiVarCard.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );

    private static final int DAMAGE = 4;
    private static final int BLOCK = 8;

    public MultiVarCard() {
        super(ID, info);

        setDamage(DAMAGE);
        setBlock(BLOCK);
        setCustomVar("D2", 6, 3);
        setCustomVar("D3", 12, 50);
        calculateVarAsDamage("D2");
        calculateVarAsDamage("D3");
        setCustomVar("B2", 5, 3);
        calculateVarAsBlock("B2");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new DamageAction(m, new DamageInfo(p, customVar("beep"), DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.NONE));
    }
}
